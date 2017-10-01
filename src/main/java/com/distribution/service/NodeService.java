/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.more.CustomNode;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;
import com.distribution.dao.nodeBonusHistory.mapper.more.MoreNodeBonusHistoryMapper;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;
import com.distribution.dao.nodeBonusHistory.model.more.MoreNodeBonusHistory;
import com.distribution.dao.order.model.OrderMaster;

@Service
public class NodeService {
	@Autowired
	private MoreMemberNodeMapper moreNodeMapper;
	@Autowired
	private MoreMemberMapper moreMemberMapper;
	@Autowired
	private MoreNodeBonusHistoryMapper nodeBonusHistoryMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	/**
	 * 通过父节点和左右分区标识来判断是否存在
	 * @param parentNodeId 父节点
	 * @param flag 左右区(left,right)
	 * @author su
	 * @return 大于0存在
	 */
	public int findNodeByParentNode(int parentNodeId,String flag){
		MemberNode node = moreNodeMapper.selectByPrimaryKey(parentNodeId);
		int result = 0;
		if(node == null){
			return -1;
		}
		if(flag.equals("left")){
			result = node.getLeftId()==null?0:node.getLeftId();
		}else{
			result = node.getRightId()==null?0:node.getRightId();
		}
		return result;
	}
	/**
	 * 系统动态排单，策略为等级优先，先上后下，先左后右
	 * @param node node对象
	 * @author su
	 * @return
	 */
	public MemberNode saveNode(MemberNode node){
		//推荐人的节点Id
		Integer recommendNodeId = node.getParentId();
		MemberNode parentNode = getParentNode(recommendNodeId);
		node.setParentId(parentNode.getId());
		node.setCreateTime(new Date());
		moreNodeMapper.insertBackId(node);
		//如果左边为空，先放到左边，否则放右边。
		if(null == parentNode.getLeftId() || parentNode.getLeftId() <= 0){
			parentNode.setLeftId(node.getId());
		}else{
			parentNode.setRightId(node.getId());
		}
		parentNode.setUpdateTime(new Date());
		parentNode.setUpdateBy(node.getCreateBy());
		moreNodeMapper.updateByPrimaryKeySelective(parentNode);
		return node;
	}
	/**
	 * 
	 * 根据根节点找到可以新增节点的位置
	 * @param root
	 * @return MemberNode
	 */
	public MemberNode getParentNode(int recommendNodeId){
		MemberNode result = null;
		//查询结果中的节点ID从小到大排列，不能人为调整节点主键值。
		List<MemberNode> list = moreNodeMapper.listSubNodesByRecommendNode(recommendNodeId);
		Queue<MemberNode> q = new LinkedList<MemberNode>();
		q.add(list.get(0));
		while(!q.isEmpty()){
			MemberNode node = q.poll();
			if(null == node.getLeftId()){
				result = node;
				break;
			}else{
				//左子节点入队
				q.add(findNode(list,node.getLeftId()));
			}
			if(null == node.getRightId()){
				result = node;
				break;
			}else{
				//右子节点入队
				q.add(findNode(list,node.getRightId()));
			}
		}
		return result;
	}
	/**
	 * 
	 * 通过id找到MemberNode
	 * @param list
	 * @param nodeId
	 * @return
	 */
	public MemberNode findNode(List<MemberNode> list,int nodeId){
		MemberNode result = null;
		for(MemberNode node:list){
			if(node.getId().intValue() == nodeId){
				result = node;
				break;
			}
		}
		return result;
	}
	/**
	 * 通过传来的父节点与左右分区信息来存储新节点
	 * @param node node对象
	 * @param flag 左右区(left,right)
	 * @author su
	 * @return
	 */
	public int saveNode(MemberNode node,String flag){
		//node.setCreateBy(createBy);
		moreNodeMapper.insertBackId(node);
		MemberNode parentNode = new MemberNode();
		parentNode.setId(node.getParentId());
		if(flag.equals("left")){
			parentNode.setLeftId(node.getId());
		}else{
			parentNode.setRightId(node.getId());
		}
		parentNode.setUpdateTime(new Date());
		parentNode.setUpdateBy(node.getCreateBy());
		moreNodeMapper.updateByPrimaryKeySelective(parentNode);
		return node.getId();
	}
	/**
	 * 新会员生效时
	 * 其上级所有节点都会分配一个见点奖
	 * 限制逻辑，每个人所得见点将上限与其直销会员卡数有关系
	 * @author su
	 * @date 2017年9月5日 下午1:00:07
	 */
	public void insertMemberNodeBonus(int nodeId,OrderMaster order){
		List<NodeBonusHistory> historyList = new ArrayList<NodeBonusHistory>();
        //查找当前节点的所有父节点，查找其直销的卡数是多少张。
        List<MoreMemberNode> list = moreNodeMapper.listParentNodesWithMemberInfo(nodeId);
       
        for(MoreMemberNode m:list){
        	//忽略当前节点
        	if(m.getId().intValue() == nodeId){
        		continue;
        	}
        	Integer salesNum = m.getFirstAgentCnt();
        	//当前节点会员推荐人数大于零才可以领取见点奖
        	if(null != salesNum && salesNum.intValue() > 0){
        		//取得当前会员可以领节点奖代数
        		int configNum = commonService.getRecommendCount(salesNum);
        		//nodeId节点是当前父节点的代数
        		Integer nodeNum = m.getRownum();
        		//直销的卡数和后台的配置数据对比,在领取的代数范围内。
            	if(null != nodeNum && nodeNum.intValue() <= configNum){
            		//构建见点奖对象
            		NodeBonusHistory history = new NodeBonusHistory();
            		//history.setBonusAmount(bonusPercent);
            		history.setCreateBy(order.getCreateId());
            		history.setCreateTime(new Date());
            		history.setFromNodeId(nodeId);
            		history.setMebmerId(m.getMemberId());
            		history.setStatus(BonusConstant.BONUS_STATUS_0);
            		history.setOrderId(order.getId());
            		history.setOrderNo(order.getOrderNo());
            		history.setOrderDate(order.getCreateTime());
            		historyList.add(history);
            	}
        	}
        }
        if(historyList.size() > 0){
        	saveNodeBonusHistoryBatch(historyList);
        }
	}
	/**
	 * 批量插入见点奖明细
	 * @author su
	 * @date 2017年9月5日 下午6:06:21
	 * @param historyList
	 */
	public void saveNodeBonusHistoryBatch(List<NodeBonusHistory> historyList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", historyList);
		nodeBonusHistoryMapper.insertBatch(historyList);
	}
	/**
	 * 处理会员晋升
	 * 当会员生效时调用
	 * @param nodeId 当前会员节点
	 * @param updateId 更新人
	 * @author su
	 */
	public void processMemberPromotion(int nodeId,int updateId){
		
		//根据当前会员的nodeId查询其所有上级;
		List<MemberNode> list = moreNodeMapper.findParentNodes(nodeId);
		//对返回的字符串进行处理
		if(null != list && list.size() > 0){
			int promNodeId = 0;
			for(MemberNode node : list){
				//查询其下属所有节点的销售业绩,不包括自己。
				double total = moreNodeMapper.findTotalSalesByParentIdNotIncludeCurrentNode(node.getId());
				//判断是否符合主任晋升标准
				if(total >= commonService.getPromotionStandard(BonusConstant.D10,BonusConstant.CODE_00)){
			        //更新会员级别为主任，其上级中不是主任的都升为主任。
					updateParentLevel(node.getId(),BonusConstant.POST_LEVEL1,BonusConstant.POST_LEVEL2);
					promNodeId = node.getId();
			        //主任晋升截止
			        break;
				}
			}
			//处理晋升节点所有上级主任晋升为经理
			processMemberPromotion(promNodeId,BonusConstant.POST_LEVEL2,BonusConstant.POST_LEVEL3,updateId);
            //处理晋升节点所有上级经理晋升为总监
			processMemberPromotion(promNodeId,BonusConstant.POST_LEVEL3,BonusConstant.POST_LEVEL4,updateId);
            //处理晋升节点所有上级总监晋升为董事   
			processMemberPromotion(promNodeId,BonusConstant.POST_LEVEL4,BonusConstant.POST_LEVEL5,updateId);
			//处理晋升节点所有上级董事晋升为全国董事   
			processMemberPromotion(promNodeId,BonusConstant.POST_LEVEL5,BonusConstant.POST_LEVEL6,updateId);
		}else{
			return;
		}
	}
	/**
	 * 
	 * 查找所有可以晋升的节点
	 * @param nodeId
	 * @param fromLevel
	 * @param toLevel
	 */
	public void processMemberPromotion(int nodeId,String fromLevel,String toLevel,int updateId){
        
		Map<String,Object> param = setParamMap(nodeId,fromLevel,toLevel);
		//查找带左右子节点上级
        List<MoreMemberNode> list = moreNodeMapper.listParentNodesWhichHasTwoSubNodes(param);
        List<Integer> members = new ArrayList<Integer>();
        for(MoreMemberNode node:list){
        	//当前节点的左右子节点职务级别是否满足晋升条件
        	String leftLevel = node.getLeftLevel();
        	String rightLevel = node.getRightLevel();
        	//会员主键
        	int promotionId = node.getMemberId();
        	//如果左右的职务都大于等于父节点的职务，执行晋升。
        	if(convertLevel(leftLevel) >= convertLevel(fromLevel) && convertLevel(rightLevel) >= convertLevel(fromLevel)){
        		members.add(promotionId);
        	}
        }
        if(members.size() > 0){
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("memberPost", toLevel);
        	map.put("updateId", updateId);
        	map.put("updateTime", new Date());
        	map.put("memberIds", members);
        	updateMemberLevelBatch(map);
        }
	}
	/**
	 * 
	 * 根据nodeId更新其上级职务
	 * @date 2017年9月2日 下午7:56:28
	 * @param nodeId
	 * @param toLevel
	 */
	public void updateParentLevel(int nodeId,String fromLevel,String toLevel){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("NodeService_updateParentLevel_txmanager");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			List<MemberNode> list = moreNodeMapper.findParentNodes(nodeId);
			if(null != list && list.size() > 0){
				Map<String,Object> param = setParamMap(nodeId,fromLevel,toLevel);
				param.put("list", list);
				moreNodeMapper.updateParentLevel(param);
			}
			txManager.commit(txStatus);
		} catch (Exception e) {
			txManager.rollback(txStatus);
		}
	}
	/**
	 * 
	 * 根据nodeId更新当前会员职务
	 * @param map
	 */
	public void updateMemberLevelBatch(Map<String,Object> map){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("NodeService_updateMemberLevelBatch_txmanager");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			moreMemberMapper.updateMemberPostLevelBatch(map);
			txManager.commit(txStatus);
		} catch (Exception e) {
			txManager.rollback(txStatus);
		}
	}
	/**
	 * 封装参数
	 * @date 2017年9月2日 下午9:41:21
	 * @param nodeId
	 * @param fromLevel
	 * @param toLevel
	 * @return
	 */
	private Map<String,Object> setParamMap(int nodeId,String fromLevel,String toLevel){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("nodeId", String.valueOf(nodeId));
		param.put("fromLevel", fromLevel);
		param.put("toLevel", toLevel);
		return param;
	}
	/**
	 * 为了便于判断，把字符串的级别转换为数字。
	 * @date 2017年9月2日 下午9:40:37
	 * @param memberLevel
	 * @return
	 */
	private int convertLevel(String memberLevel){
		int result = 0;
		if(memberLevel.equals(BonusConstant.POST_LEVEL1)){
			result = 1;
		}else if(memberLevel.equals(BonusConstant.POST_LEVEL2)){
			result = 2;
		}else if(memberLevel.equals(BonusConstant.POST_LEVEL3)){
			result = 3;
		}else if(memberLevel.equals(BonusConstant.POST_LEVEL4)){
			result = 4;
		}else if(memberLevel.equals(BonusConstant.POST_LEVEL5)){
			result = 5;
		}else{
		}
		return result;
	}
	/**
	 * 
	 * 通过某一节点查询下属所有节点
	 * 并构建成二叉树结构
	 * 数据中，节点的id必须是自增长的
	 * 查询结果必须从小到大排列
	 * @date 2017年9月4日 下午8:59:32
	 * @param nodeId
	 * @return
	 */
	public CustomNode buildNodeTreeByNode(int nodeId){
		//构建根节点
		CustomNode root = new CustomNode(nodeId);
		//查询结果中的节点ID从小到大排列，不能人为调整节点主键值。
		List<MoreMemberNode> list = moreNodeMapper.listSubNodes(nodeId);
		root = convertBiTree(root,list);
		return root;
	}
	/**
	 * 从数据表中查询数据构建二叉树对象
	 * 默认参数root,需要设置节点值
	 * @date 2017年9月4日 下午7:13:16
	 * @param root
	 * @return
	 */
	public CustomNode convertBiTree(CustomNode root,List<MoreMemberNode> list) {
		//用来存放所有节点对象
		Map<Integer,CustomNode> map = new HashMap<Integer,CustomNode>();
		map.put(root.getNodeId(), root);
		//定义变量
		CustomNode currentNode = null;
		Map state = new HashMap();
		state.put("checkbox_disabled",true);

		//循环构建二叉树对象
		for(MoreMemberNode m : list){
			//取得缓存的节点对象
			currentNode = map.get(m.getId());
			if(null != currentNode){
				//设置树节点对象属性
//				currentNode.setNodeName(m.getMemberName());
//				currentNode.setOrderAmount(m.getOrderAmount());
				currentNode.setId(m.getMemberPhone());
				String flag = currentNode.getFlag() == null ? "":"("+currentNode.getFlag()+") ";
				currentNode.setText(flag + m.getMemberName() + " [" + m.getMemberPhone() + " , " + m.getOrderAmount()+"]");
//				currentNode.setCreateTime(m.getCreateTime());
				List<CustomNode> children = null;
				if(null != m.getLeftId() || null != m.getRightId()){
					children = new ArrayList<CustomNode>();
					currentNode.setChildren(children);
				}
				if(null != m.getLeftId()){
					CustomNode left = new CustomNode(m.getLeftId());
					left.setFlag("左");
					//currentNode.setLeft(left);
					//计入缓存
					map.put(m.getLeftId(), left);
					children.add(left);
				}
				if(null != m.getRightId()){
					CustomNode right = new CustomNode(m.getRightId());
					//currentNode.setRight(right);
					right.setFlag("右");
					//计入缓存
					map.put(m.getRightId(), right);
					children.add(right);
				}
				if(currentNode.getChildren() != null && currentNode.getChildren().size() == 2 ){
					currentNode.setState(state);
				}
			}else{
				//输出日志当前节点为无效节点
			}
		}
        return root;
    }
	/**
	 * 查询当前节点下的左节点与右节点下的人数与销售总额
	 * @author su
	 * @date 2017年9月7日 下午5:17:13
	 * @param nodeId
	 * @return
	 */
	public Map<String,String> getSubNodeNumberAndSales(int nodeId){
		MemberNode node = moreNodeMapper.selectByPrimaryKey(nodeId);
		Map<String,String> map = new HashMap<String,String>();
		if(null == node){
			return map;
		}
		//左节点的所有子节点
		if(node.getLeftId()!=null && !"".equals(node.getLeftId())){

			List<MoreMemberNode> leftNum = moreNodeMapper.listSubNodes(node.getLeftId());
			if(null != leftNum && leftNum.size() > 0){
				//左节点的所有销售额，不包含折扣单
				Double leftToalSales = moreNodeMapper.findTotalSalesByParentId(node.getLeftId());
				map.put("leftNum", String.valueOf(leftNum.size()));
				map.put("leftToalSales", String.valueOf(leftToalSales));
			}
		}else{
			map.put("leftNum", "0");
			map.put("leftToalSales","0");
		}

		if(node.getRightId()!=null && !"".equals(node.getRightId())) {
			//右节点的所有子节点
			List<MoreMemberNode> rightNum = moreNodeMapper.listSubNodes(node.getRightId());
			if (null != rightNum && rightNum.size() > 0) {
				//右节点的所有销售额，不包含折扣单
				Double rightToalSales = moreNodeMapper.findTotalSalesByParentId(node.getRightId());
				map.put("rightNum", String.valueOf(rightNum.size()));
				map.put("rightToalSales", String.valueOf(rightToalSales));
			}
		}else{
			map.put("rightNum", "0");
			map.put("rightToalSales","0");
		}
		return map;
	}
	/**
	 * 更新要发放的见点奖明细信息
	 * @author su
	 * @date 2017年9月8日 下午2:41:42
	 * @param date
	 * @param nodeBonus
	 */
	public void updateNodeBonusHistory(List<MoreNodeBonusHistory> list){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		//map.put("nodeBonus", nodeBonus);
		//更新状态为已领取
		map.put("bonusStatus", BonusConstant.BONUS_STATUS_1);
		map.put("updateBy", 0);
		map.put("updateTime", new Date());
		nodeBonusHistoryMapper.updateNodeBonusHistory(map);
	}
	/**
	 * 更新发放完的见点奖明细信息
	 * @author su
	 * @date 2017年9月8日 下午2:41:42
	 * @param date
	 * @param nodeBonus
	 */
	public void updateNodeBonusHistoryStatusEnd(List<NodeBonusHistory> list){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		//更新状态为已结算
		map.put("bonusStatus", BonusConstant.BONUS_STATUS_2);
		map.put("updateBy", 0);
		map.put("updateTime", new Date());
		nodeBonusHistoryMapper.updateNodeBonusHistoryStatusEnd(map);
	}
	/**
	 * 调整见点奖金为分红数量乘以奖金金额
	 * @param list
	 * @param nodeBonus
	 * @return
	 */
	public double calculateNodeBonusAmount(List<MoreNodeBonusHistory> list,double nodeBonus){
		double total = 0;
		for(MoreNodeBonusHistory m:list){
			total = total + m.getOrderQty() * nodeBonus;
			m.setBonusAmount(m.getOrderQty() * nodeBonus);
		}
		return total;
	}
}
