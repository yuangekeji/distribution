package com.distribution.service;


import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.memberCharge.mapper.MemberChargeMapper;
import com.distribution.dao.memberCharge.model.MemberCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * description 后台会员管理
 * @author Bright
 * */
@Service
public class AdmMemberService {

    @Autowired
    private MoreMemberMapper moreMemberMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private MemberChargeMapper memberChargeMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    /**
     * description 后台会员列表分页查询
     * @author Bright
     * */
    public Page selectList(Page page){
        if(null!=page.getParameterMap().get("startTime"))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime"))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");
        page.setTotalCount(moreMemberMapper.getMemberCount(page));
        page.setResult(moreMemberMapper.list(page));
        return page;
    }

    /**
     * description 充值
     * @author Bright
     * */
    public Integer addAccount(MemberCharge memberCharge,Admin admin){
        AccountManager am = moreAccountManagerMapper.selectByMemberId(memberCharge.getMemberId());
        am.setTotalBonus(am.getTotalBonus().add(memberCharge.getChargeAmt()));
        am.setBonusAmt(am.getBonusAmt().add(memberCharge.getChargeAmt()));
        am.setUpdateId(admin.getId());
        am.setUpdateTime(new Date());
        Integer am1 = moreAccountManagerMapper.updateByMemberId(am);

        memberCharge.setCreateId(admin.getId());
        memberCharge.setCreateTime(new Date());
        Integer me1 = memberChargeMapper.insert(memberCharge);

        AccountFlowHistory af = new AccountFlowHistory();
        af.setMemberId(memberCharge.getMemberId());
        af.setCreateTime(new Date());
        af.setCreateId(admin.getId());
        af.setType("2"); //转入
        af.setTotalAmt(memberCharge.getChargeAmt());
        af.setBonusAmt(memberCharge.getChargeAmt());
        af.setSeedAmt(new BigDecimal(0));
        af.setFlowType("9");//管理员充值

        Integer af1 = accountFlowHistoryMapper.insert(af);

        return am1 + me1 + af1;
    }
}
