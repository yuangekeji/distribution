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
}
