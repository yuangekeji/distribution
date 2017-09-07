package com.distribution.dao.chinaPresidentBonus.model.more;

import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonus;

import java.math.BigDecimal;


public class MoreChinaPresidentBonus extends ChinaPresidentBonus{
    private BigDecimal manageFee;
    private BigDecimal actualAmout;

    public BigDecimal getManageFee() {
        return manageFee;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    public BigDecimal getActualAmout() {
        return actualAmout;
    }

    public void setActualAmout(BigDecimal actualAmout) {
        this.actualAmout = actualAmout;
    }
}