package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.memberChargeApply.mapper.more.MoreMemberChargeApplyMapper;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberChargeService {
    @Autowired
    private MoreMemberChargeApplyMapper moreMemberChargeApplyMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    /**
     * description 充值申请列表
     * @author sijeong
     * */
    public Page memberChargeList(Page page) {
        page.setTotalCount(moreMemberChargeApplyMapper.getMemberChargeListCount(page));
        page.setResult( moreMemberChargeApplyMapper.getMemberChargeList(page));
        return page;
    }

    /**
     * 查询账户信息
     * @param memberId
     * @return
     */
    public AccountManager selectAccountManager(Integer memberId){

        AccountManager account = new AccountManager();
        account.setMemberId(memberId);
        return moreAccountManagerMapper.selectAccountManager(account);
    }

    /**
     * 充值申请
     * @param moreMemberChargeApply
     * @return
     */
    public String insertMemberCharge(MoreMemberChargeApply moreMemberChargeApply){

        //插入充值申请
        Date date = new Date();
        MoreMemberChargeApply mmc = new MoreMemberChargeApply();
        mmc.setMemberId(moreMemberChargeApply.getMemberId());
        mmc.setStatus("0");
        mmc.setChargeRequestTime(date);
        mmc.setPayMoneyType(moreMemberChargeApply.getPayMoneyType());
        mmc.setPayMoneyTime(moreMemberChargeApply.getPayMoneyTime());
        mmc.setChargeAmt(moreMemberChargeApply.getChargeAmt());
        mmc.setApplyInfo(moreMemberChargeApply.getApplyInfo());
        mmc.setCreateId(moreMemberChargeApply.getMemberId());
        mmc.setCreateTime(date);
        mmc.setUpdateId(moreMemberChargeApply.getMemberId());
        mmc.setUpdateTime(date);
        int  cnt = moreMemberChargeApplyMapper.insertSelective(mmc);
        if(cnt >0){
            //充值申请成功
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
}