package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.advance.mapper.AdvanceMapper;
import com.distribution.dao.advance.mapper.more.MoreAdvanceMapper;
import com.distribution.dao.advance.model.Advance;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdvanceService {
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
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/

        page.setTotalCount(moreAdvanceMapper.getAdvanceListCount(page));
        page.setResult( moreAdvanceMapper.getAdvanceList(page));
        return page;
    }

    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    public AccountManager selectAccountManager(AccountManager accountManager){
        return moreAccountManagerMapper.selectAccountManager(accountManager);
    }


    /**
     * 处理提现
     * @param moreAdvance
     * @return
     */
    public String insertAdvance(MoreAdvance moreAdvance){

        //根据member_id 和 paypwd 查询会员是否存在
        Map param = new HashMap();
        param.put("memberId",moreAdvance.getMemberId());
        param.put("memberPhone",moreAdvance.getMemberPhone());
        param.put("payPassword", CryptoUtil.md5ByHex(moreAdvance.getPayPassword()));

        Integer count = moreMemberMapper.findMatchMemberQueryPwd(param);

        //step 3)账户流水
        AccountFlowHistory historyout = new AccountFlowHistory();

        if(count != null  && count >0 ){

            //step1) 插入提现明细表
            Advance advance = new Advance();
            advance.setMemberId(moreAdvance.getMemberId());
            advance.setBankName(moreAdvance.getBankName());
            advance.setCardName(moreAdvance.getCardName());
            advance.setCardNo(moreAdvance.getCardNo());
            advance.setReqAmt(moreAdvance.getReqAmt());
            advance.setFeeAmt(moreAdvance.getFeeAmt());
            advance.setActAmt(moreAdvance.getActAmt());
            //advance.setApproveDate(new Date());
            advance.setRequestDate(new Date());
            advance.setStatues("1");
            advance.setCreateId(moreAdvance.getMemberId());
            advance.setCreateTime(new Date());
            advance.setUpdateId(moreAdvance.getMemberId());
            advance.setUpdateTime(new Date());

            //step 2)提现账户余额查询与计算
            AccountManager advanceAccount = new AccountManager();
            advanceAccount.setMemberId(moreAdvance.getMemberId());

            advanceAccount = this.selectAccountManager(advanceAccount);

            //判断如果账户余额小于提现金额就失败
            if(advanceAccount.getBonusAmt().compareTo(moreAdvance.getReqAmt()) == -1){
                throw new RuntimeException();
            }
            historyout.setOldTotalBonusAmt(advanceAccount.getBonusAmt());
            advanceAccount.setBonusAmt(advanceAccount.getBonusAmt().subtract(moreAdvance.getReqAmt()));//从奖金币中扣除提现申请金额（提现申请金额=实际提现金额+手续费）
            historyout.setNewTotalBonusAmt(advanceAccount.getBonusAmt());
            advanceAccount.setTotalBonus(advanceAccount.getBonusAmt().add(advanceAccount.getSeedAmt()));//计算总奖金字段
            advanceAccount.setAdvanceAmt(advanceAccount.getAdvanceAmt().add(moreAdvance.getReqAmt()));//提现总额 = 原提现总额 + 实际提现金额
            advanceAccount.setUpdateId(moreAdvance.getMemberId());
            advanceAccount.setUpdateTime(new Date());


            historyout.setMemberId(moreAdvance.getMemberId());
            historyout.setCreateTime(new Date());
            historyout.setCreateId(moreAdvance.getMemberId());
            historyout.setType("1");      //1支出 进账2
            historyout.setFlowType(Constant.ADVANCE); //提现
            historyout.setBonusAmt(moreAdvance.getReqAmt());
            historyout.setSeedAmt(new BigDecimal(0));
            historyout.setTotalAmt(moreAdvance.getReqAmt());
            historyout.setNewTotalSeedAmt(advanceAccount.getSeedAmt());
            historyout.setOldTotalSeedAmt(advanceAccount.getSeedAmt());

            //step 3) 插入提现明细表，账户表，账户流水表
            int  cnt1 = advanceMapper.insert(advance);

            int  cnt2 = moreAccountManagerMapper.updateAccountManagerAmtWhileAdvance(advanceAccount);

            int  cnt3= accountFlowHistoryMapper.insert(historyout);

            if(cnt1 >0){
                //提现申请成功
                return "success";
            }else{
                throw new RuntimeException();
            }
        }

        //密码错误
        return  "pwdWrong";
    }
}
