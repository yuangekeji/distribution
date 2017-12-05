/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;
import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.chinaPresidentBonus.mapper.ChinaPresidentBonusMapper;
import com.distribution.dao.chinaPresidentBonus.mapper.more.MoreChinaPresidentBonusMapper;
import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonus;
import com.distribution.dao.chinaPresidentBonus.model.more.MoreChinaPresidentBonus;
import com.distribution.dao.dateBonusHistory.mapper.more.MoreDateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.jobLogs.mapper.JobLogsMapper;
import com.distribution.dao.jobLogs.model.JobLogs;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberBonus.mapper.MemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
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
    private MoreChinaPresidentBonusMapper moreChinaPresidentBonusMapper;
    @Autowired
    private MemberBonusMapper memberBonusMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;
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

    public void jobLogsByDay(Map map) {
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

    /**
     * 全国董事奖月结算job
     * @return
     */
    public Map addChinaPresidentBonusByMonth() {
        Map map = new HashMap();
        List<MoreChinaPresidentBonus> bonusAmoutByMonthList = moreChinaPresidentBonusMapper.selectBonusAmoutByMonth();
        int bonusAmoutByMonthSize = bonusAmoutByMonthList.size();
        double totalactualAmout = 0;
        try {
            Date date = new Date();
            if (bonusAmoutByMonthSize > 0) {
                map.put("postLevel6MemberCount", bonusAmoutByMonthSize);
                map.put("status", "success");
                for (int i=0; i<bonusAmoutByMonthSize; i++) {
                    double bonusAmout = bonusAmoutByMonthList.get(i).getBonusAmout().doubleValue();
                    double manageFee = bonusAmoutByMonthList.get(i).getManageFee().doubleValue();
                    double actualAmout = bonusAmoutByMonthList.get(i).getActualAmout().doubleValue();
                    totalactualAmout += actualAmout;
                    //memberBonus 记录
                    MemberBonus memberBonus = new MemberBonus();
                    memberBonus.setMemberId(bonusAmoutByMonthList.get(i).getMemberId());
                    memberBonus.setBonusDate(date);
                    memberBonus.setBonusType(BonusConstant.BONUS_TYPE_6);
                    memberBonus.setAmout(bonusAmout);
                    memberBonus.setManageFee(manageFee);
                    memberBonus.setActualAmout(actualAmout);
                    memberBonus.setRemarks("全国董事奖");
                    memberBonus.setCreateBy(0);
                    memberBonus.setCreateDate(date);
                    memberBonus.setUpdateBy(0);
                    memberBonus.setUpdateTime(date);
                    int countInsert = memberBonusMapper.insert(memberBonus);
                    if (countInsert == 0) {
                        map.put("status", "error");
                        throw new RuntimeException();
                    }
                    //ChinaPresidentBonus 更新
                    ChinaPresidentBonus chinaPresidentBonus = new ChinaPresidentBonus();
                    chinaPresidentBonus.setStatus("1");
                    chinaPresidentBonus.setBalanceTime(date);
                    chinaPresidentBonus.setUpdateBy(0);
                    chinaPresidentBonus.setUpdateTime(date);
                    int countUpdate = moreChinaPresidentBonusMapper.updateBalanceTime(chinaPresidentBonus);
                    if (countUpdate == 0) {
                        map.put("status", "error");
                        throw new RuntimeException();
                    }
                    //AccountManage 记录
                    //根据实际获得奖金数量计算种子币
                    double seedBonus = commonService.multiply(actualAmout,BonusConstant.SEED_PERCENT);
                    //根据实际获得奖金数量计算奖金币
                    double bonusRest = commonService.multiply(actualAmout,BonusConstant.BONUS_PERCENT);
                    //查询现有账号信息
                    AccountManager param = new AccountManager();
                    param.setMemberId(bonusAmoutByMonthList.get(i).getMemberId());
                    AccountFlowHistory flow = new AccountFlowHistory();
                    AccountManager account = moreAccountManagerMapper.selectAccountManager(param);
                    if(null == account){
                        account = param;
                        account.setTotalBonus(new BigDecimal(0));
                        account.setSeedAmt(new BigDecimal(0));
                        account.setBonusAmt(new BigDecimal(0));
                        account.setAdvanceAmt(new BigDecimal(0));
                        account.setCreateId(0);
                        account.setCreateTime(date);
                        account.setUpdateId(0);
                        account.setUpdateTime(date);
                    }

                    flow.setOldTotalSeedAmt(account.getSeedAmt());
                    flow.setOldTotalBonusAmt(account.getBonusAmt());

                    //对现有余额进行相加
                    BigDecimal total = account.getTotalBonus().add(new BigDecimal(actualAmout));
                    BigDecimal seedAmt = account.getSeedAmt().add(new BigDecimal(seedBonus));
                    BigDecimal bonusAmt = account.getBonusAmt().add(new BigDecimal(bonusRest));
                    //对账户余额进行赋值
                    account.setBonusAmt(bonusAmt);
                    account.setSeedAmt(seedAmt);
                    account.setTotalBonus(total);
                    account.setUpdateId(0);
                    account.setUpdateTime(date);

                    flow.setNewTotalSeedAmt(account.getSeedAmt());
                    flow.setNewTotalBonusAmt(account.getBonusAmt());

                    if(null != account.getId() && account.getId() > 0){
                        //更新账户余额
                        int count = moreAccountManagerMapper.updateByPrimaryKeySelective(account);
                        if (count == 0) {
                            map.put("status", "error");
                            throw new RuntimeException();
                        }
                    }else{
                        //创建账户
                        int count = moreAccountManagerMapper.insert(account);
                        if (count == 0) {
                            map.put("status", "error");
                            throw new RuntimeException();
                        }
                    }
                    //AccountFlowHistory 记录

                    flow.setCreateId(0);
                    flow.setCreateTime(new Date());
                    //奖金类型，参照奖金表设置
                    flow.setFlowType(BonusConstant.BONUS_TYPE_6);
                    flow.setMemberId(bonusAmoutByMonthList.get(i).getMemberId());
                    //设置入账
                    flow.setSeedAmt(new BigDecimal(seedBonus));
                    flow.setBonusAmt(new BigDecimal(bonusRest));
                    flow.setTotalAmt(new BigDecimal(actualAmout));
                    flow.setType(BonusConstant.ACCOUNT_TYPE_IN);


                    //记录账号资金出入情况
                    int countHistory = accountFlowHistoryMapper.insert(flow);
                    if (countHistory == 0) {
                        map.put("status", "error");
                        throw new RuntimeException();
                    }
                }
                map.put("totalactualAmout", totalactualAmout);
            }else {
                map.put("postLevel6MemberCount", 0);
                map.put("totalactualAmout", 0);
                map.put("status", "success");
            }
        }catch (Exception e){
            map.put("postLevel6MemberCount", bonusAmoutByMonthSize);
            map.put("totalactualAmout", totalactualAmout);
            map.put("status", "error");
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            return map;
        }
    }
    public void jobLogsByMonth(Map map) {
        Date date = new Date();
        JobLogs jobLogs = new JobLogs();
        jobLogs.setJobName("全国董事奖月Job");
        jobLogs.setRunTime(date);
        if (map.get("status").equals("error")) {
            jobLogs.setResult("error");
        }else {
            jobLogs.setResult("sucessed");
        }
        jobLogs.setRemarks("全国董事奖人数："+ map.get("postLevel6MemberCount") +
                ", 实得奖金总金额：" + map.get("totalactualAmout") +
                ", 状态：已经算");
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
