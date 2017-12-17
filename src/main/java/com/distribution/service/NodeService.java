/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.utils.DateHelper;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.more.CustomNode;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;
import com.distribution.dao.nodeBonusHistory.mapper.more.MoreNodeBonusHistoryMapper;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;
import com.distribution.dao.nodeBonusHistory.model.more.MoreNodeBonusHistory;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.platformAccountHistory.model.PlatformAccountHistory;

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
		List<MemberNode> list = moreNodeMapper.listSubNodesByRecommendNode(getSubNodeIdsMap(recommendNodeId));
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
        List<MoreMemberNode> list = moreNodeMapper.listParentNodesWithMemberInfo(getParentNodeIdsMap(nodeId));
       
        for(MoreMemberNode m:list){
        	//忽略当前节点
        	if(m.getId().intValue() == nodeId){
        		continue;
        	}
        	//Integer salesNum = m.getFirstAgentCnt();20171210注释该行代码，原因是会员的直推下属数量实时查询。
        	Integer salesNum = moreNodeMapper.countRecommendedTotal(m.getMemberId());
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
		List<MemberNode> list = moreNodeMapper.findParentNodes(getParentNodeIdsMap(nodeId));
		//对返回的字符串进行处理
		if(null != list && list.size() > 0){
			int promNodeId = 0;
			for(MemberNode node : list){
				//查询其下属所有节点的销售业绩,不包括自己。
				Map<String,Object> map = this.getSubNodeIdsMap(node.getId());
				map.put("parentId", node.getId());
				double total = moreNodeMapper.findTotalSalesByParentIdNotIncludeCurrentNode(map);
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
		String ids = this.getParentNodeIds(nodeId);
		param.put("array", ids.split(","));
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
			//20171125 优化查询方式
			List<MemberNode> list = moreNodeMapper.findParentNodes(getParentNodeIdsMap(nodeId));
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
		List<MoreMemberNode> list = moreNodeMapper.listSubNodes(getSubNodeIdsMap(nodeId));
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
		if(node.getLeftId()!=null && !"".equals(node.getLeftId().toString())){
			Map<String,Object> ids = this.getSubNodeIdsMap(node.getLeftId());
			if(null != ids){
				String[] leftNum = (String[])ids.get("array");
				if(null != leftNum && leftNum.length > 0){
					//左节点的所有销售额，不包含折扣单
					Double leftToalSales = moreNodeMapper.findTotalSalesByParentId(ids);
					map.put("leftNum", String.valueOf(leftNum.length));
					map.put("leftToalSales", String.valueOf(leftToalSales));
				}
			}
		}else{
			map.put("leftNum", "0");
			map.put("leftToalSales","0");
		}

		if(node.getRightId()!=null && !"".equals(node.getRightId().toString())) {
			//右节点的所有子节点
			Map<String,Object> ids = this.getSubNodeIdsMap(node.getRightId());
			if(null != ids){
				String[] rightNum = (String[])ids.get("array");
				if (null != rightNum && rightNum.length > 0) {
					//右节点的所有销售额，不包含折扣单
					Double rightToalSales = moreNodeMapper.findTotalSalesByParentId(ids);
					map.put("rightNum", String.valueOf(rightNum.length));
					map.put("rightToalSales", String.valueOf(rightToalSales));
				}
			}
		}else{
			map.put("rightNum", "0");
			map.put("rightToalSales","0");
		}
		//折合钻石数量
		processTotalSalesResult(map);
		return map;
	}
	/**
	 * Name:会员“市级市场”“县级市场”哪个业绩先达到1千万，就先累计1个钻石（扣除1千万的业绩），总业绩也相应的扣除1千万变成一颗钻。
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月17日 下午2:23:52
	 * @param map
	 */
	public void processTotalSalesResult(Map<String,String> map) {
		int standard = 10000000;
		int leftToalSales = Integer.valueOf(map.get("leftToalSales"));
		int rightToalSales = Integer.valueOf(map.get("rightToalSales"));
		//计算左区钻石数量
		if(leftToalSales >= standard) {
			int leftDiamondNum = Math.floorDiv(leftToalSales, standard);
			int leftRestSaleNum = Math.floorMod(leftToalSales, standard);
			map.put("leftDiamondNum", String.valueOf(leftDiamondNum));
			map.put("leftRestSaleNum",String.valueOf(leftRestSaleNum));
		}
		//计算右区钻石数量
		if(rightToalSales >= standard) {
			int rightDiamondNum = Math.floorDiv(rightToalSales, standard);
			int rightRestSaleNum = Math.floorMod(rightToalSales, standard);
			map.put("rightDiamondNum", String.valueOf(rightDiamondNum));
			map.put("rightRestSaleNum",String.valueOf(rightRestSaleNum));
		}
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
	/**
	 * Name: 获取所有子节点
	 * Description: 
	 * @date 2017年11月26日 下午4:14:01
	 * @param nodeId
	 * @return
	 */
	public String getSubNodeIds(int nodeId){
		return moreNodeMapper.getSubNodes(nodeId);
	}
	public Map<String,Object> getSubNodeIdsMap(int nodeId){
		Map<String,Object> map = new HashMap<String,Object>();
		String ids = moreNodeMapper.getSubNodes(nodeId);
		map.put("array", ids.split(","));
		return map;
	}
	/**
	 * Name: 获取所有父亲节点
	 * Description: 
	 * @date 2017年11月26日 下午4:14:01
	 * @param nodeId
	 * @return
	 */
	public String getParentNodeIds(int nodeId){
		return moreNodeMapper.getParentNodes(nodeId);
	}
	public Map<String,Object> getParentNodeIdsMap(int nodeId){
		String ids = moreNodeMapper.getParentNodes(nodeId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("array", ids.split(","));
		return map;
	}
	
	public void insertMemberNodeBonusTest(int nodeId,List<OrderMaster> list,int[] memberIds){
		List<NodeBonusHistory> historyList = new ArrayList<NodeBonusHistory>();
		for(OrderMaster order : list) {
			for(int memberId : memberIds) {
				NodeBonusHistory history = new NodeBonusHistory();
				history.setCreateBy(order.getCreateId());
				history.setCreateTime(order.getCreateTime());
				history.setFromNodeId(nodeId);
				history.setMebmerId(memberId);
				history.setStatus(BonusConstant.BONUS_STATUS_0);
				history.setOrderId(order.getId());
				history.setOrderNo(order.getOrderNo());
				history.setOrderDate(order.getCreateTime());
				historyList.add(history);
			}
		}
        if(historyList.size() > 0){
        	saveNodeBonusHistoryBatch(historyList);
        }
	}
	/**
	 * Name:运营中心左右区业绩 
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月17日 下午3:21:00
	 * @param param:startDate，endDate，scope(left,right,all),pageSize,offset;
	 * @return
	 */
	public Page listOperatorLeftAndRightSales(Page page){
		Map<String,Object> param = page.getParameterMap();
		param.put("pageSize", page.getPageSize());
		param.put("offset", page.getOffset());
		List<Map<String,Object>> list = moreNodeMapper.listOperatorLeftAndRightSales(param);
		int totalCount = moreNodeMapper.countOperatorLeftAndRightSales(param);
		
		if(null != list && list.size() > 0) {
			//查询每个运营中心销售业绩
			for(Map<String,Object> map : list) {
				String flag = String.valueOf(param.get("scope"));
				String startDate = String.valueOf(param.get("startDate"));
				String endDate = String.valueOf(param.get("endDate"));
				processOperatorLeftAndRightSales(map,flag,startDate,endDate);
			}
		}
		page.setTotalCount(totalCount);
		page.setResult(list);
		return page;
	}
	/**
	 * Name: 单独查询每个运营中心左区和右区的销售业绩
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月17日 下午4:30:42
	 * @param map
	 * @param flag
	 * @param startDate
	 * @param endDate
	 */
	public void processOperatorLeftAndRightSales(Map<String,Object> map,String flag,String startDate,String endDate) {
		int nodeId = Integer.parseInt(map.get("nodeId").toString());
		MemberNode node = moreNodeMapper.selectByPrimaryKey(nodeId);
		//查询所有左区销售额
		if(flag.equals("left") || flag.equals("all")) {
			if(node.getLeftId()!=null && !"".equals(node.getLeftId().toString())){
				Map<String,Object> param = this.getSubNodeIdsMap(node.getLeftId());
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				if(null != param){
					String[] leftNum = (String[])param.get("array");
					if(null != leftNum && leftNum.length > 0){
						//左节点的所有销售额，不包含折扣单
						Double leftToalSales = moreNodeMapper.findTotalSalesByParentId(param);
						map.put("leftNum", String.valueOf(leftNum.length));
						map.put("leftToalSales", String.valueOf(leftToalSales));
					}
				}
			}else{
				map.put("leftNum", "0");
				map.put("leftToalSales","0");
			}
		}
		//查询右区销售业绩
		if(flag.equals("right") || flag.equals("all")) {
			if(node.getRightId()!=null && !"".equals(node.getRightId().toString())) {
				//右节点的所有子节点
				Map<String,Object> param = this.getSubNodeIdsMap(node.getRightId());
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				if(null != param){
					String[] rightNum = (String[])param.get("array");
					if (null != rightNum && rightNum.length > 0) {
						//右节点的所有销售额，不包含折扣单
						Double rightToalSales = moreNodeMapper.findTotalSalesByParentId(param);
						map.put("rightNum", String.valueOf(rightNum.length));
						map.put("rightToalSales", String.valueOf(rightToalSales));
					}
				}
			}else{
				map.put("rightNum", "0");
				map.put("rightToalSales","0");
			}
		}
	}
	/**
	 * Name:导出运营中心数据 
	 * Description: 
	 * @author BAB1703658
	 * @date 2017年12月17日 下午5:18:07
	 * @param title
	 * @param headers
	 * @param list
	 * @return
	 */
	public XSSFWorkbook exportExcel(List<Map<String,Object>> list) throws InvocationTargetException {
		//定义表头
		String[] headers = {"运营中心名称","左区人数","左区销售业绩", "右区人数", "右区销售业绩"};
		String title = "运营中心销售业绩";
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		//定义字体
		XSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontHeightInPoints((short) 12);
		//定义样式
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style.setFont(font);
		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++){
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		//设置表格宽度
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		//定义body样式
		XSSFCellStyle styleRight = workbook.createCellStyle();
		styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//居右
		XSSFCellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		for(int j=0;j<list.size();j++){
			XSSFRow bodyRow = sheet.createRow(j+1);
			Map<String,Object> map = list.get(j);
			bodyRow.createCell(0).setCellValue(map.get("memberName")==null?"":String.valueOf(map.get("memberName")));
			bodyRow.createCell(1).setCellValue(map.get("leftNum")==null?"":String.valueOf(map.get("leftNum")));
			bodyRow.createCell(2).setCellValue(map.get("leftToalSales")==null?"":String.valueOf(map.get("leftToalSales")));
			bodyRow.createCell(3).setCellValue(map.get("rightNum")==null?"":String.valueOf(map.get("rightNum")));
			bodyRow.createCell(4).setCellValue(map.get("rightToalSales")==null?"":String.valueOf(map.get("rightToalSales")));
		}
		return  workbook;
	}

}
