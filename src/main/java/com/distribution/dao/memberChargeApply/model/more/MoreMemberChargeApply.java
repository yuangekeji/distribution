package com.distribution.dao.memberChargeApply.model.more;

import com.distribution.dao.memberCharge.mapper.MemberChargeMapper;
import com.distribution.dao.memberChargeApply.model.MemberChargeApply;

import java.math.BigDecimal;
import java.util.Date;

public class MoreMemberChargeApply extends MemberChargeApply{

    private String memberName;
    private String memberPhone;
    private String payTime;
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}