/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.dao.memberNode.model.more;

import java.math.BigDecimal;
import java.util.Date;


public class CustomNode {
	
	private Integer nodeId;
	private String nodeName;
	private String mobile;
	private BigDecimal orderAmount;
	private Date createTime;
	private CustomNode left;
	private CustomNode right;
	static int counter = 0;
	
	public CustomNode(Integer nodeId){
		this.nodeId = nodeId;
	}
	
	public Integer getNodeId() {
		return nodeId;
	}
	
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CustomNode getLeft() {
		return left;
	}

	public void setLeft(CustomNode left) {
		this.left = left;
	}

	public CustomNode getRight() {
		return right;
	}

	public void setRight(CustomNode right) {
		this.right = right;
	}
}
