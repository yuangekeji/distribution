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

}
