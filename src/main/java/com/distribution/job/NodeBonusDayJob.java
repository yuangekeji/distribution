/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.distribution.dao.chinaPresidentBonus.mapper.more.MoreChinaPresidentBonusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.distribution.common.utils.DateHelper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.dao.jobLogs.model.JobLogs;
import com.distribution.service.BonusService;

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
	@Scheduled(cron ="0 0 1 * * ?" )//每天早上1点钟执行
	public void sendNodeBonusFromNodeHistory(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("jobName", "定时发放见点奖/NodeBonusDayJob/sendNodeBonusFromNodeHistory");
		result = bonusService.saveNodeBonusFromNodeHistory(result);
//		System.out.println(result.toString());
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
	public void saveJobLog(Map<String,Object> map){
		JobLogs job = convertToJobLogsFromMap(map);
		jobLogsMapper.insert(job);
	}
	@Scheduled(cron ="0 30 2 * * ?" )//每天2点30钟执行
	public void savePlatformCashFlow(){
		String date = DateHelper.formatDate(DateHelper.getYesterDay(), DateHelper.YYYY_MM_DD);
		savePlatformCashFlow(date);
	}
	public void savePlatformCashFlow(String date) {
		Map<String,Object> result = null;
		result = bonusService.getDaySalesAndBonusAmount(date);
		DateBonusHistory dateHistory= bonusService.findCurrentDayBonusHistory(date);
		dateHistory.setAllTotalBonus(new BigDecimal((Double)result.get("totalMemberBonusAmount")));
		dateHistory.setDayActualBonus(new BigDecimal((Double)result.get("dayMemberBonusAmount")));
		dateHistory.setAllTotalAdvance(new BigDecimal((Double)result.get("totalAdvanceAmount")));
		dateHistory.setDayAdvance(new BigDecimal((Double)result.get("dayAdvanceAmount")));
		dateHistory.setDayDiscountSales(new BigDecimal((Double)result.get("dayDiscountSalesAmount")));
		dateHistory.setAllTotalSales(new BigDecimal((Double)result.get("totalSalesAmount")));
		dateHistory.setUpdateId(0);
		dateHistory.setUpdateTime(new Date());
		bonusService.saveOrUpdateDateHistory(dateHistory);
		result.put("result", result.toString());
		result.put("jobName", "定时结算平台资金/NodeBonusDayJob/selectPlatformCashFlow");
		saveJobLog(result);
	}

	@Scheduled(cron ="0 30 14 * * ?" )//数据mergejob 只运行一次 每天14点30钟执行
	public void mergerPlatformCashFlow(){
		//获取max(id) 以外的所有数据，因为max(id)留给明天凌晨处理
		List  dateList = bonusService.selectNeedMergeDates();
		Object[] dates = dateList.toArray();
		for( int i =0 ; i < dates.length ; i++){
			String date = (String) dates[i];
			System.out.println(date);
			savePlatformCashFlow(date);		
		}
	}
}







