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
import java.util.List;

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

    public void addChinaPresidentBonusByDay() {
        List<Member> postLevel6MemberList = moreMemberMapper.getPostLevel6Member();
        if (postLevel6MemberList.size() > 0){
            DateBonusHistory dateBonusHistory = moreDateBonusHistoryMapper.getTotalSales(postLevel6MemberList.size());
            if (dateBonusHistory != null) {
                BigDecimal salesByMembers = dateBonusHistory.getTotalSales();
                Date date = new Date();
                for (int i=0; i<postLevel6MemberList.size(); i++){
                    ChinaPresidentBonus chinaPresidentBonus = new ChinaPresidentBonus();
                    chinaPresidentBonus.setMemberId(postLevel6MemberList.get(i).getId());
                    chinaPresidentBonus.setBonusAmout(salesByMembers);
                    chinaPresidentBonus.setBonusDate(date);
                    chinaPresidentBonus.setStatus("0");
                    chinaPresidentBonus.setCreateTime(date);
                    chinaPresidentBonus.setCreateBy(0);
                    chinaPresidentBonus.setUpdateTime(date);
                    chinaPresidentBonus.setUpdateBy(0);
                    int count = chinaPresidentBonusMapper.insert(chinaPresidentBonus);
                    if (count > 0) {
                        JobLogs jobLogs = new JobLogs();
                        jobLogs.setJobName("addChinaPresidentBonusByDay");
                        jobLogs.setRunTime(date);
                        jobLogs.setResult("sucessed");
                        jobLogs.setRemarks("china_president_bonus表:全国董事奖日job");
                        jobLogs.setCreateTime(date);
                        jobLogs.setCreateBy(0);
                        jobLogs.setUpdateTime(date);
                        jobLogs.setUpdateBy(0);
                        int countjobLog = jobLogsMapper.insert(jobLogs);
                        if (countjobLog == 0) {
                            throw new RuntimeException();
                        }
                    }else {
                        /*JobLogs jobLogs = new JobLogs();
                        jobLogs.setJobName("addChinaPresidentBonusByDay");
                        jobLogs.setRunTime(date);
                        jobLogs.setResult("faild");
                        jobLogs.setRemarks("china_president_bonus表:全国董事奖日job");
                        jobLogs.setCreateTime(date);
                        jobLogs.setCreateBy(0);
                        jobLogs.setUpdateTime(date);
                        jobLogs.setUpdateBy(0);
                        jobLogsMapper.insert(jobLogs);*/
                        throw new RuntimeException();
                    }
                }
            }
        }
    }
}
