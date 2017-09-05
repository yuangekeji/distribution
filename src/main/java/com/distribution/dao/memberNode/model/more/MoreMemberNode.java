/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.dao.memberNode.model.more;

import java.math.BigDecimal;

import com.distribution.dao.memberNode.model.MemberNode;

 /**
  * Name: 
  * Description: 
  * Create Date: 2017年9月4日 下午8:49:38
  * File: com.distribution.dao.memberNode.model.more.MoreMemberNode.java 
  * @version 1.0
  */
public class MoreMemberNode extends MemberNode{
	private String memberName;
	private String memberPhone;
	private BigDecimal orderAmount;
	private Integer rownum;
	private Integer memberId;
	private Integer firstAgentCnt;
	private String memberLevel;
	private String leftLevel;
	private String rightLevel;
	private String isOperator;
	private String isSalesDept;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getRownum() {
		return rownum;
	}
	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getFirstAgentCnt() {
		return firstAgentCnt;
	}
	public void setFirstAgentCnt(Integer firstAgentCnt) {
		this.firstAgentCnt = firstAgentCnt;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getLeftLevel() {
		return leftLevel;
	}
	public void setLeftLevel(String leftLevel) {
		this.leftLevel = leftLevel;
	}
	public String getRightLevel() {
		return rightLevel;
	}
	public void setRightLevel(String rightLevel) {
		this.rightLevel = rightLevel;
	}
	public String getIsOperator() {
		return isOperator;
	}
	public String getIsSalesDept() {
		return isSalesDept;
	}
	public void setIsOperator(String isOperator) {
		this.isOperator = isOperator;
	}
	public void setIsSalesDept(String isSalesDept) {
		this.isSalesDept = isSalesDept;
	}
	
}
