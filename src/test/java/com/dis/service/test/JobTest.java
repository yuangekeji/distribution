/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.job.DividendBonusDayJob;
import com.distribution.job.NodeBonusDayJob;

public class JobTest extends SpringServiceTest{
	@Autowired
	private NodeBonusDayJob nodeJob;
	@Autowired
	private DividendBonusDayJob dividendJob;
	
	@Test
	public void sendNodeBonusFromNodeHistoryTest(){
		/*	
		1.有奖要发，无营业额，缓存池没有钱。ok
		2.有奖要发，无营业额，缓存池钱够用。ok
		3.无奖要发，有营业额。ok
		4.无奖要发，也没有营业额。ok
		5.要发的奖金大于营业额可用钱数ok
		6.要发的奖等于 营业额可用的钱数ok
		7.要发的奖小于营业额的钱数 ok
		8.要发的奖大于营业额钱，不够的钱小于缓存池 ok。
		*/
		nodeJob.sendNodeBonusFromNodeHistory();
	}
	@Test
	public void balanceNodeBonusTest(){
		nodeJob.balanceNodeBonus();
	}
	@Test
	public void sendDividendBonusTest(){
		/*	
		1.有奖要发，无营业额，缓存池没有钱 ok
		2.有奖要发，无营业额，缓存池钱够用 ok
		3.无奖要发，有营业额 ok
		4.无奖要发，也没有营业额 ok
		5.要发的奖金大于营业额可用钱数ok
		6.要发的奖等于 营业额可用的钱数ok
		7.要发的奖小于营业额的钱数  ok
		8.要发的奖大于营业额钱，不够的钱小于缓存池 
		*/
		dividendJob.sendDividendBonus();
	}
	@Test
	public void balanceDividendBonusTest(){
		dividendJob.balanceDividendBonus();
	}
	@Test
	public void sendFailureDividendBonus(){
		dividendJob.sendFailureDividendBonus();;
	}
	@Test
	public void savePlatformCashFlowTest() {
		nodeJob.savePlatformCashFlow();
	}
}
