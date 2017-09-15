/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.math.BigDecimal;
import java.util.Date;

import com.distribution.dao.order.model.OrderMaster;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.service.BonusService;

public class BonusServiceTest extends SpringServiceTest{
	private static Logger log = Logger.getLogger(BonusServiceTest.class);
	
	@Autowired
	private BonusService bonusService;
	
	@Test
	public void insertSalseBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(600));
		order.setId(3);
		order.setCreateId(3);
		order.setOrderNo(new Long("1701232"));
		order.setCreateTime(new Date());
		int memberId = 3;
		bonusService.insertSalseBonus(memberId, order);
	}
	@Test
	public void insertFirstGenerationBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(1800));
		order.setId(4);
		order.setCreateId(4);
		order.setOrderNo(new Long("1701233"));
		order.setCreateTime(new Date());
		int memberId = 3;
		bonusService.insertFirstGenerationBonus(memberId, order);
	}
	
	@Test
	public void insertSecondGenerationBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(1800));
		order.setId(5);
		order.setCreateId(5);
		order.setOrderNo(new Long("1701234"));
		order.setCreateTime(new Date());
		int memberId = 3;
		bonusService.insertSecondGenerationBonus(memberId, order);
	}
	@Test
	public void insertMemberLevelBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(3000));
		order.setId(6);
		order.setCreateId(6);
		order.setOrderNo(new Long("1701235"));
		order.setCreateTime(new Date());
		int nodeId = 10;
		bonusService.insertMemberLevelBonus(nodeId, order);
	}
	@Test
	public void insertWorkRoomAndOperatingCenterBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(1800));
		order.setId(40);
		order.setCreateId(29);
		order.setOrderNo(new Long("170909192814050"));
		order.setCreateTime(new Date());
		int nodeId = 144;
		bonusService.insertWorkRoomAndOperatingCenterBonus(nodeId, order);
	}
	@Test
	public void insertOrderBonusTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(9000));
		order.setId(8);
		order.setCreateId(8);
		order.setOrderNo(new Long("1701230"));
		order.setCreateTime(new Date());
		order.setMemberId(9);
		//bonusService.insertOrderBonus(order);
	}
}
