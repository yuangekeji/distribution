package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.advance.mapper.AdvanceMapper;
import com.distribution.dao.advance.mapper.more.MoreAdvanceMapper;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdmAdvanceService {
    @Autowired
    private MoreAdvanceMapper moreAdvanceMapper;

    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    @Autowired
    private MoreMemberMapper moreMemberMapper;

    @Autowired
    private AdvanceMapper advanceMapper;

    /**
     * description 提现列表
     * @author WYN
     * */
    public Page advanceList(Page page) {
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreAdvanceMapper.getAdvanceListCount(page));
        page.setResult( moreAdvanceMapper.getAdvanceList(page));
        return page;
    }

    /**
     * description 提现批准，驳回
     * @author WYN
     * */
    public String confirmAdvance(MoreAdvance moreAdvance) {
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        //提现批准入账
        if("2".equals(moreAdvance.getStatues())){
            //提现账户余额查询与计算
            AccountManager advanceAccount = new AccountManager();
            advanceAccount.setMemberId(moreAdvance.getMemberId());

            advanceAccount = this.selectAccountManager(advanceAccount);

            //判断如果账户余额小于提现金额就失败
            if(advanceAccount.getBonusAmt().compareTo(moreAdvance.getReqAmt()) == -1){
                throw new RuntimeException();
            }

            advanceAccount.setBonusAmt(advanceAccount.getBonusAmt().subtract(moreAdvance.getReqAmt()));//从奖金币中扣除提现申请金额（提现申请金额=实际提现金额+手续费）
            advanceAccount.setTotalBonus(advanceAccount.getBonusAmt().add(advanceAccount.getSeedAmt()));//计算总奖金字段
            advanceAccount.setAdvanceAmt(advanceAccount.getAdvanceAmt().add(moreAdvance.getReqAmt()));//提现总额 = 原提现总额 + 实际提现金额
            advanceAccount.setUpdateId(moreAdvance.getMemberId());
            advanceAccount.setUpdateTime(new Date());


            //step 3)账户流水
            AccountFlowHistory historyout = new AccountFlowHistory();
            historyout.setMemberId(moreAdvance.getMemberId());
            historyout.setCreateTime(new Date());
            historyout.setCreateId(moreAdvance.getMemberId());
            historyout.setType("1");      //1支出 进账2
            historyout.setFlowType(Constant.ADVANCE); //提现
            historyout.setBonusAmt(moreAdvance.getReqAmt());
            historyout.setTotalAmt(moreAdvance.getReqAmt());

            cnt1 = moreAccountManagerMapper.updateAccountManagerAmt(advanceAccount);
            cnt2= accountFlowHistoryMapper.insert(historyout);

        }else {
            cnt1 = 1;
            cnt2 = 1;
        }

        cnt3 = moreAdvanceMapper.confirmAdvance(moreAdvance);

        if(cnt1 > 0 && cnt2 > 0 && cnt3 > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    public AccountManager selectAccountManager(AccountManager accountManager){
        return moreAccountManagerMapper.selectAccountManager(accountManager);
    }
}
