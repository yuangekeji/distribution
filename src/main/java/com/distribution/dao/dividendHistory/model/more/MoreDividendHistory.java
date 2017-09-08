/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.dao.dividendHistory.model.more;

import com.distribution.dao.dividendHistory.model.DividendHistory;

 /**
  * <p>Title: Pactera LMS</p>
  * Name: 
  * Description: 
  * Create Date: 2017年9月8日 上午8:24:31
  * File: com.distribution.dao.dividendHistory.model.more.MoreDividendHistory.java 
  * @author su
  * @version 1.0
  */
public class MoreDividendHistory extends DividendHistory{
	private Integer orderId;

    private Long orderNo;
    
    private Integer memberId;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
