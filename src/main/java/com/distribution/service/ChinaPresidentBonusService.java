/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;
import com.distribution.dao.chinaPresidentBonus.mapper.ChinaPresidentBonusMapper;
import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonus;
import com.distribution.dao.dateBonusHistory.mapper.more.MoreDateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.dao.jobLogs.model.JobLogs;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChinaPresidentBonusService {
    @Autowired
    private MoreMemberMapper moreMemberMapper;
    @Autowired
    private MoreDateBonusHistoryMapper moreDateBonusHistoryMapper;
    @Autowired
    private ChinaPresidentBonusMapper chinaPresidentBonusMapper;
    @Autowired
    private JobLogsMapper jobLogsMapper;

    public Map addChinaPresidentBonusByDay() {
        List<Member> postLevel6MemberList = moreMemberMapper.getPostLevel6Member();
        Map map = new HashMap();
        BigDecimal bonusAmout =  new BigDecimal("0");
        int postLevel6MemberSize = postLevel6MemberList.size();
        try {
            if (postLevel6MemberSize > 0) {
                map.put("postLevel6MemberCount", postLevel6MemberSize);
                DateBonusHistory dateBonusHistory = moreDateBonusHistoryMapper.getTotalSales(postLevel6MemberSize);
                if (dateBonusHistory == null) {
                    map.put("bonusAmout", 0);
                    map.put("status", "error");
                }else {
                    bonusAmout = dateBonusHistory.getTotalSales();
                    map.put("bonusAmout", bonusAmout);
                    map.put("status", "success");
                }
                Date date = new Date();
                for (int i = 0; i < postLevel6MemberSize; i++) {
                    ChinaPresidentBonus chinaPresidentBonus = new ChinaPresidentBonus();
                    chinaPresidentBonus.setMemberId(postLevel6MemberList.get(i).getId());
                    chinaPresidentBonus.setBonusAmout(bonusAmout);
                    chinaPresidentBonus.setBonusDate(date);
                    chinaPresidentBonus.setStatus("0");
                    chinaPresidentBonus.setCreateTime(date);
                    chinaPresidentBonus.setCreateBy(0);
                    chinaPresidentBonus.setUpdateTime(date);
                    chinaPresidentBonus.setUpdateBy(0);
                    int count = chinaPresidentBonusMapper.insert(chinaPresidentBonus);
                    if (count == 0) {
                        map.put("status", "error");
                        throw new RuntimeException();
                    }
                }
            } else {
                map.put("postLevel6MemberCount", 0);
                map.put("bonusAmout", 0);
                map.put("status", "success");
            }
        }catch (Exception e){
            map.put("postLevel6MemberCount", postLevel6MemberSize);
            map.put("bonusAmout", bonusAmout);
            map.put("status", "error");
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            return map;
        }
    }

    public void jobLogs(Map map) {
        Date date = new Date();
        JobLogs jobLogs = new JobLogs();
        jobLogs.setJobName("全国董事奖日Job");
        jobLogs.setRunTime(date);
        if (map.get("status").equals("error")) {
            jobLogs.setResult("error");
        }else {
            jobLogs.setResult("sucessed");
        }
        jobLogs.setRemarks("全国董事奖人数："+ map.get("postLevel6MemberCount") +
                ", 人均奖金金额：" + map.get("bonusAmout") +
                ", 状态：未结算");
        jobLogs.setCreateTime(date);
        jobLogs.setCreateBy(0);
        jobLogs.setUpdateTime(date);
        jobLogs.setUpdateBy(0);
        int countjobLog = jobLogsMapper.insert(jobLogs);
        if (countjobLog == 0) {
            throw new RuntimeException();
        }
    }
}
