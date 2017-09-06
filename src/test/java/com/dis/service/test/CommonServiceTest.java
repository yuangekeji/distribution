/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.common.constant.BonusConstant;
import com.distribution.service.CommonService;

public class CommonServiceTest extends SpringServiceTest{
	private static Logger log = Logger.getLogger(CommonServiceTest.class);
	@Autowired
	private CommonService commonService;
	
	
	@Test
	public void getMaxPercentTest(){
		commonService.initBasicManageList();
		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_01);
		log.info("========== bonusPercent is " + bonusPercent);
	}
	@Test
	public void getSalesBonusPercentTest(){
		double bonusPercent = commonService.getSalesBonusPercent(BonusConstant.D01,BonusConstant.CODE_01,BonusConstant.SALES_BONUS_PER);
		log.info("========== bonusPercent is " + bonusPercent);
	}
	@Test
	public void getPromotionStandardTest(){
		double standard = commonService.getPromotionStandard(BonusConstant.D10,BonusConstant.CODE_00);
		log.info("========== bonusPercent is " + standard);
	}
	@Test
	public void getRecommendCountTest(){
		int configNum = commonService.getRecommendCount(4);
		log.info("========== configNum is " + configNum);
	}
	@Test
	public void multiplyTest(){
		double standard = commonService.getPromotionStandard(BonusConstant.D10,BonusConstant.CODE_00);
		double bonusPercent = commonService.getMaxPercent(BonusConstant.D05,BonusConstant.CODE_01);
		double result = commonService.multiply(bonusPercent, standard);
		log.info("============result is " + result);
	}
}
