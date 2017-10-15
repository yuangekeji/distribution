package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.memberChargeApply.mapper.more.MoreMemberChargeApplyMapper;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AdmMemberChargeService {
    @Autowired
    private MoreMemberChargeApplyMapper moreMemberChargeApplyMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

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
     * description 充值申请批准，驳回
     * @author sijeong
     * */
    public String confirmMemberCharge(MoreMemberChargeApply moreMemberChargeApply, Admin admin) {

        Date date = new Date();
        moreMemberChargeApply.setChargeApplyTime(date);
        moreMemberChargeApply.setUpdateId(admin.getId());
        moreMemberChargeApply.setUpdateTime(date);
        int cnt = moreMemberChargeApplyMapper.confirmMemberCharge(moreMemberChargeApply);
        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
    /**
     * description 充值
     * @author sijeong
     * */
    public String AddMemberCharge(MoreMemberChargeApply moreMemberChargeApply, Admin admin){

        AccountManager am = moreAccountManagerMapper.selectByMemberId(moreMemberChargeApply.getMemberId());
        Date date = new Date();
        am.setTotalBonus(am.getTotalBonus().add(moreMemberChargeApply.getChargeAmt()));
        am.setBonusAmt(am.getBonusAmt().add(moreMemberChargeApply.getChargeAmt()));
        am.setUpdateId(admin.getId());
        am.setUpdateTime(date);
        int am1 = moreAccountManagerMapper.updateByMemberId(am);
        if(am1 < 1){
            throw new RuntimeException();
        }
        MoreMemberChargeApply mmc = new MoreMemberChargeApply();
        mmc.setId(moreMemberChargeApply.getId());
        mmc.setStatus(moreMemberChargeApply.getStatus());
        mmc.setChargeTime(date);
        mmc.setUpdateTime(date);
        mmc.setUpdateId(admin.getId());
        int count = moreMemberChargeApplyMapper.updateByPrimaryKeySelective(mmc);
        if(count < 1){
            throw new RuntimeException();
        }
        AccountFlowHistory af = new AccountFlowHistory();
        af.setMemberId(moreMemberChargeApply.getMemberId());
        af.setCreateTime(date);
        af.setCreateId(admin.getId());
        af.setType("2");//转入
        af.setTotalAmt(moreMemberChargeApply.getChargeAmt());
        af.setBonusAmt(moreMemberChargeApply.getChargeAmt());
        af.setSeedAmt(new BigDecimal(0));
        af.setFlowType(Constant.MEMBERCHARGE);//管理员充值

        int af1 = accountFlowHistoryMapper.insert(af);
        if(af1 < 1){
            throw new RuntimeException();
        }
        return "success";
    }
}