/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
		BigDecimal amount = new BigDecimal(600);
		//int poolType = BonusConstant.POOL_TYPE_DIVIDEND;
		int poolType = BonusConstant.POOL_TYPE_NODE;
		int addOrReduce = BonusConstant.POOL_BONUS_ADD;
		poolServie.updateCachePool(amount, poolType, addOrReduce);
	}
	@Test
	public void updatePoolTest(){
		BigDecimal amount = new BigDecimal(0);
		int poolType = BonusConstant.POOL_TYPE_DIVIDEND;
		//int poolType = BonusConstant.POOL_TYPE_NODE;
		int addOrReduce = BonusConstant.POOL_BONUS_ADD;
		poolServie.updatePool(amount, poolType, addOrReduce);
	}
	@Test
	public void listPlatformAccountHistory() {
		String startDate = "2017-12-04";
		String endDate = "2017-12-09";
		try {
			XSSFWorkbook wf =poolServie.exportData(startDate, endDate);
			File file = new File("E:\\dev\\personal\\history.xlsx");
			FileOutputStream out = new FileOutputStream(file);
			wf.write(out);
		} catch (InvocationTargetException e) {
		} catch (IOException e) {
		}
	}
}
