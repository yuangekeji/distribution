/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;

@Service
public class NodeService {
	@Autowired
	private MoreMemberNodeMapper moreNodeMapper;
	@Autowired
	private MoreMemberMapper moreMemberMapper;
	
	//通过父节点和左右分区标识来判断是否存在
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
	//通过传来的父节点与左右分区信息来存储新节点
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
	 * 处理会员晋升
	 * 当会员生效时调用
	 * @date 2017年9月1日 下午3:31:11
	 */
	public void processMemberPromotion(int nodeId,double orderAmount){
		
		//根据当前会员的nodeId查询其所有上级;
		List<MemberNode> list = moreNodeMapper.findParentNodes(nodeId);
		//对返回的字符串进行处理
		if(null != list && list.size() > 0){
			for(MemberNode node : list){
				//查询其下属所有节点的销售业绩
				double total = moreNodeMapper.findTotalSalesByParentId(node.getId());
				//判断是否符合主任晋升标准
				if(total >= BonusConstant.PROMOTION_JUDGE_STANDARD){
			        //更新会员级别为主任，其上级中不是主任的都升为主任。
					updateParentLevel(nodeId,BonusConstant.POST_LEVEL1,BonusConstant.POST_LEVEL2);
			        //主任晋升截止
			        break;
				}
			}
			//处理主任晋升为经理
			processMemberPromotion(nodeId,BonusConstant.POST_LEVEL2,BonusConstant.POST_LEVEL3);
            //处理经理晋升为总监
            processMemberPromotion(nodeId,BonusConstant.POST_LEVEL3,BonusConstant.POST_LEVEL4);
            //处理总监晋升为董事   
            processMemberPromotion(nodeId,BonusConstant.POST_LEVEL4,BonusConstant.POST_LEVEL5);
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
	public void processMemberPromotion(int nodeId,String fromLevel,String toLevel){
        
		Map<String,String> param = setParamMap(nodeId,fromLevel,toLevel);
		//查找带左右子节点上级
        List<Map<String,String>> list = moreNodeMapper.listParentNodesWhichHasTwoSubNodes(param);
        for(Map<String,String> node:list){
        	//当前节点的左右子节点职务级别是否满足晋升条件
        	String leftLevel = node.get("left_level");
        	String rightLevel = node.get("right_level");
        	int promotionId = Integer.parseInt(node.get("id"));
        	//如果左右的职务都大于等于父节点的职务，执行晋升。
        	if(convertLevel(leftLevel) >= convertLevel(fromLevel) && convertLevel(rightLevel) >= convertLevel(fromLevel)){
        		Member member = new Member();
        		member.setId(promotionId);
        		member.setMemberLevel(toLevel);
        		member.setUpdateTime(new Date());
        		//member.setUpdateId(updateId);
        		updateMemberLevel(member);
        	}
        }
	}
	/**
	 * 
	 * 根据nodeId更新其上级职务
	 * @date 2017年9月2日 下午7:56:28
	 * @param nodeId
	 * @param postLevel
	 */
	public void updateParentLevel(int nodeId,String fromLevel,String toLevel){
		/**
		 * update member set member_level = 'toLevel'
			where FIND_IN_SET(node_id,getParentList(10)) and member_level = 'fromLevel'
		 */
		Map<String,String> param = setParamMap(nodeId,fromLevel,toLevel);
		moreNodeMapper.updateParentLevel(param);
	}
	/**
	 * 
	 * 根据nodeId更新当前会员职务
	 * @param nodeId
	 * @param postLevel
	 */
	public void updateMemberLevel(Member member){
		moreMemberMapper.updateByPrimaryKeySelective(member);
	}
	/**
	 * 封装参数
	 * @date 2017年9月2日 下午9:41:21
	 * @param nodeId
	 * @param fromLevel
	 * @param toLevel
	 * @return
	 */
	private Map<String,String> setParamMap(int nodeId,String fromLevel,String toLevel){
		Map<String,String> param = new HashMap<String,String>();
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
}
