/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;

@Service
public class NodeService {
	@Autowired
	private MoreMemberNodeMapper moreNodeMapper;
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
	/*public void processMemberPromotion(int nodeId,double orderAmount){
		//查找当前节点的所有父节点名下销售业绩，大于15万。找到一个就返回，找不到返回空。
	    Map map= memberNodeService.findFirstMatchMember(nodeId,totalNumber);
	    if(null !=null && null != map.get(NodeId)){
	    	int match_nodeId = map.get(NodeId);
	         //更新会员级别为主任，其上级中不是主任的都升为主任。
	        memberNodeService.updateMemberLevel(nodeId，levelType);
	        //处理主任晋升为经理，查找当前会员中所有的主任
	        processMemberPromotion(nodeId,主任,经理);
            //处理经理晋升为总监
            processMemberPromotion(nodeId,经理,总监);
            //处理总监晋升为董事   
            processMemberPromotion(nodeId,总监,董事);
	   }
	}*/
	public void processMemberPromotion(int nodeId,String fromLevel,String toLevel){
       //比如传一个普通会员nodeId,查找其上级所有可以晋升的主任。
      /* List<Member> list = memberNodeService.listAllParentNodes(nodeId, fromLevel);
       //更新会员级别为toLevel，查找所有会员是否可以晋升。满足规则的直接晋升。
       memberNodeService.updateMemberLevel(list ，levelType);*/
	}
}
