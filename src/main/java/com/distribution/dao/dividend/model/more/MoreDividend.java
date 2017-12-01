package com.distribution.dao.dividend.model.more;

import com.distribution.dao.dividend.model.Dividend;

/**
 * Created by lijingx on 11/21/2017.
 */
public class MoreDividend extends Dividend {

    private String memberName;
    private String memberPhone;

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
}
