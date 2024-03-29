/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.distribution.common.utils.DateHelper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
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
	 * 处理历史发放失败的数据分红包奖
	 * @author su
	 * @date 2017年11月11日 上午11:13:42
	 */
	@Scheduled(cron ="0 10 1 * * ?" )//每天早上1点10分钟执行
	public void sendFailureDividendBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时发放失败分红包奖/DividendBonusDayJob/sendFailureDividendBonus");
		//先处理昨天发放失败的数据
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		//查找昨天所有失败的分红奖数据
		List<DateBonusHistory> list = bonusService.listFailureDividendBonus(date);
		//循环处理发放失败的数据
		if(list.size() > 0){
			for(int i=0;i<list.size();i++){
				DateBonusHistory history = list.get(i);
				result = bonusService.saveFailureDividendBonus(result,history);
				this.saveDividendBonusLog(result);
			}
		}else{
			result.put("result", "No failuer dividend bonus.");
		}
	}
	/**
	 * 发放分红包奖
	 * @author su
	 * @date 2017年9月7日 上午11:13:42
	 */
	@Scheduled(cron ="0 30 1 * * ?" )//每天早上1点30分钟执行
	public void sendDividendBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		//数据查询范围昨天
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		result.put("date", date);
		result.put("jobName", "定时发放分红包奖/DividendBonusDayJob/sendDividendBonus");
		result = bonusService.saveDividendBonus(result,date);
		this.saveDividendBonusLog(result);
	}
	/**
	 * 结算分红包奖，结算昨天的分红包。
	 * @author su
	 * @date 2017年9月7日 上午11:14:03
	 */
	@Scheduled(cron ="0 30 2 * * ?" )//每天2点30钟执行
	public void balanceDividendBonus(){
		Map<String,Object> result = new HashMap<String,Object>();
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		result.put("date", date);
		result.put("jobName", "定时结算分红包奖/DividendBonusDayJob/balanceDividendBonus");
		result = bonusService.saveBalanceMemberDividendBonus(result,date);
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
	public void sendDividendBonusByMtUser(String date) {
		Map<String,Object> result = new HashMap<String,Object>();
		//数据查询范围昨天
		result.put("date", date);
		result.put("jobName", "手动补发放分红包奖/DividendBonusDayJob/sendDividendBonus");
		result = bonusService.saveDividendBonus(result,date);
		this.saveDividendBonusLog(result);
	}
	public void banlanceDividendBonusByMtUser(String date) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("date", date);
		result.put("jobName", "手动补结算分红包奖/DividendBonusDayJob/balanceDividendBonus");
		result = bonusService.saveBalanceMemberDividendBonus(result,date);
		this.saveBlanceDividendBonusLog(result);
	}
}
