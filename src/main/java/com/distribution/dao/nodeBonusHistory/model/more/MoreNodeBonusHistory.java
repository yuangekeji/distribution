/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.dao.nodeBonusHistory.model.more;

import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;

 /**
  * @author su
  * @version 1.0
  */
public class MoreNodeBonusHistory extends NodeBonusHistory{
	private Integer orderQty;

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
}
