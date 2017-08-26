package com.distribution.dao.memberLevel.model;

import java.math.BigDecimal;

public class MemberLevel extends MemberLevelKey {
    private BigDecimal buyAmt;

    private Integer buyQty;

    private Integer devidendCnt;

    private BigDecimal salesBonusPer;

    private BigDecimal salesBonusPer1;

    private BigDecimal salesBonusPer2;

    private BigDecimal profitPer;

    private BigDecimal discount;

    public BigDecimal getBuyAmt() {
        return buyAmt;
    }

    public void setBuyAmt(BigDecimal buyAmt) {
        this.buyAmt = buyAmt;
    }

    public Integer getBuyQty() {
        return buyQty;
    }

    public void setBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
    }

    public Integer getDevidendCnt() {
        return devidendCnt;
    }

    public void setDevidendCnt(Integer devidendCnt) {
        this.devidendCnt = devidendCnt;
    }

    public BigDecimal getSalesBonusPer() {
        return salesBonusPer;
    }

    public void setSalesBonusPer(BigDecimal salesBonusPer) {
        this.salesBonusPer = salesBonusPer;
    }

    public BigDecimal getSalesBonusPer1() {
        return salesBonusPer1;
    }

    public void setSalesBonusPer1(BigDecimal salesBonusPer1) {
        this.salesBonusPer1 = salesBonusPer1;
    }

    public BigDecimal getSalesBonusPer2() {
        return salesBonusPer2;
    }

    public void setSalesBonusPer2(BigDecimal salesBonusPer2) {
        this.salesBonusPer2 = salesBonusPer2;
    }

    public BigDecimal getProfitPer() {
        return profitPer;
    }

    public void setProfitPer(BigDecimal profitPer) {
        this.profitPer = profitPer;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}