package com.distribution.dao.advance.model.more;

import com.distribution.dao.advance.model.Advance;

public class MoreAdvance extends Advance {

    private String payPassword; //支付密码
    private String memberPhone;
    private String memberName;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
