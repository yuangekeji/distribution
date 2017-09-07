/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distribution.service.BonusPoolService;
import com.distribution.service.BonusService;
import com.distribution.service.CommonService;

@Component
public class NodeBonusDayJob {
	@Autowired
	private BonusService bonusService;
	
	
	/**
	 * 发送见点奖
	 * @author su
	 * @date 2017年9月7日 上午11:05:16
	 */
	public void sendNodeBonusFromNodeHistory(){
		bonusService.saveNodeBonusFromNodeHistory();
		
	}
	/**
	 * 见点奖结算
	 * @author su
	 * @date 2017年9月7日 上午11:11:03
	 */
	public void balanceNodeBonus(){
		
	}
}







