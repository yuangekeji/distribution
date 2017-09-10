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
import com.distribution.service.BonusPoolService;
import com.distribution.service.BonusService;
import com.distribution.service.CommonService;

@Component
public class NodeBonusDayJob {
	@Autowired
	private BonusService bonusService;
	@Autowired
    private JobLogsMapper jobLogsMapper;
	
	/**
	 * 发放见点奖
	 * @author su
	 * @date 2017年9月7日 上午11:05:16
	 */
	@Scheduled(cron ="0 27 10 * * ?" )//每天早上1点钟执行
	public void sendNodeBonusFromNodeHistory(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时发放见点奖/NodeBonusDayJob/sendNodeBonusFromNodeHistory");
		result = bonusService.saveNodeBonusFromNodeHistory(result);
		System.out.println(result.toString());
		this.saveNodeBonusFromNodeHistoryLog(result);
		
	}
	/**
	 * 见点奖结算，结算昨天的见点奖。
	 * @author su
	 * @date 2017年9月7日 上午11:11:03
	 */
	@Scheduled(cron ="0 10 2 * * ?" )//每天2点10钟执行
	public void balanceNodeBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时结算见点奖/NodeBonusDayJob/balanceNodeBonus");
		result = bonusService.saveBalanceMemberNodeBonus(result);
		saveBalanceMemberNodeBonusLog(result);
	}
	/**
	 * 保存分红包发放执行记录 
	 * @author su
	 * @date 2017年9月8日 下午5:07:30
	 * @param map
	 */
	public void saveNodeBonusFromNodeHistoryLog(Map<String,Object> map){
		JobLogs job = convertToJobLogsFromMap(map);
		jobLogsMapper.insert(job);
	}
	/**
	 * 保存分红包结算执行记录 
	 * @author su
	 * @date 2017年9月8日 下午5:07:30
	 * @param map
	 */
	public void saveBalanceMemberNodeBonusLog(Map<String,Object> map){
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







