package com.distribution.dao.memberBonus.model.more;

import com.distribution.dao.memberBonus.model.MemberBonus;

import java.math.BigDecimal;

/**
 * Created by jingxin on 2017/10/25.
 */
public class DividendExcelVO extends MemberBonus {
    private String memberName;
    private BigDecimal orderAmount;
    private Integer dividendCount;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getDividendCount() {
        return dividendCount;
    }

    public void setDividendCount(Integer dividendCount) {
        this.dividendCount = dividendCount;
    }
}
