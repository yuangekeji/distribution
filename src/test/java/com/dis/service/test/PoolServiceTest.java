/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.common.constant.BonusConstant;
import com.distribution.service.BonusPoolService;

public class PoolServiceTest extends SpringServiceTest{
	@Autowired
	private BonusPoolService poolServie;
	
	@Test
	public void updateCachePoolTest(){
		BigDecimal amount = new BigDecimal(365);
		//int poolType = BonusConstant.POOL_TYPE_DIVIDEND;
		int poolType = BonusConstant.POOL_TYPE_NODE;
		int addOrReduce = BonusConstant.POOL_BONUS_REDUCE;
		poolServie.updateCachePool(amount, poolType, addOrReduce);
	}
	@Test
	public void updatePoolTest(){
		BigDecimal amount = new BigDecimal(365);
		int poolType = BonusConstant.POOL_TYPE_DIVIDEND;
		//int poolType = BonusConstant.POOL_TYPE_NODE;
		int addOrReduce = BonusConstant.POOL_BONUS_ADD;
		poolServie.updatePool(amount, poolType, addOrReduce);
	}
}
