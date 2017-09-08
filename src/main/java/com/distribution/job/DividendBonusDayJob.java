/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.service.BonusPoolService;
import com.distribution.service.BonusService;
import com.distribution.service.CommonService;

@Component
public class DividendBonusDayJob {

	@Autowired
	private BonusService bonusService;
	@Autowired
    private JobLogsMapper jobLogsMapper;
	/**
	 * 发放分红包奖
	 * @author su
	 * @date 2017年9月7日 上午11:13:42
	 */
	public void sendDividendBonus(){
		bonusService.saveDividendBonus();
	}
	/**
	 * 结算分红包奖
	 * @author su
	 * @date 2017年9月7日 上午11:14:03
	 */
	public void balanceDividendBonus(){
		bonusService.saveBalanceMemberDividendBonus();
	}
}
