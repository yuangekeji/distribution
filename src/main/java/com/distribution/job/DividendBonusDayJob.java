/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.dao.jobLogs.model.JobLogs;
import com.distribution.service.BonusService;

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
	@Scheduled(cron ="0 30 1 * * ?" )//每天早上1点30分钟执行
	public void sendDividendBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时发放分红包奖/DividendBonusDayJob/sendDividendBonus");
		result = bonusService.saveDividendBonus(result);
		this.saveDividendBonusLog(result);
	}
	/**
	 * 结算分红包奖，结算昨天的分红包。
	 * @author su
	 * @date 2017年9月7日 上午11:14:03
	 */
	@Scheduled(cron ="0 0 23 * * ?" )//每天23点钟执行
	public void balanceDividendBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时结算分红包奖/DividendBonusDayJob/balanceDividendBonus");
		result = bonusService.saveBalanceMemberDividendBonus(result);
		this.saveBlanceDividendBonusLog(result);
	}
	/**
	 * 保存分红包发放执行记录 
	 * @author su
	 * @date 2017年9月8日 下午5:07:30
	 * @param map
	 */
	public void saveDividendBonusLog(Map<String,Object> map){
		JobLogs job = convertToJobLogsFromMap(map);
		jobLogsMapper.insert(job);
	}
	/**
	 * 保存分红包结算执行记录 
	 * @author su
	 * @date 2017年9月8日 下午5:07:30
	 * @param map
	 */
	public void saveBlanceDividendBonusLog(Map<String,Object> map){
		JobLogs job = convertToJobLogsFromMap(map);
		jobLogsMapper.insert(job);
	}
	/**
	 * 封装map值为对象值
	 * @author su
	 * @date 2017年9月8日 下午5:12:26
	 * @param map
	 * @return
	 */
	public JobLogs convertToJobLogsFromMap(Map<String,Object> map){
		JobLogs job = new JobLogs();
		job.setCreateBy(0);
		job.setCreateTime(new Date());
		job.setJobName(map.get("jobName").toString());
		job.setRemarks(map.toString());
		job.setResult(map.get("result").toString());
		job.setRunTime(new Date());
		return job;
	}
}
