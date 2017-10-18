package com.distribution.dao.basicManage.model;

import java.math.BigDecimal;
import java.util.Date;

public class BasicManage extends BasicManageKey {
    private String detailNm;

    private BigDecimal buyAmt;

    private Integer buyQty;

    private Integer devidendCnt;

    private BigDecimal salesBonusPer;

    private BigDecimal salesBonusPer1;

    private BigDecimal salesBonusPer2;

    private BigDecimal profitPer;

    private BigDecimal discount;

    private BigDecimal maxPercent;

    private BigDecimal minPercent;

    private BigDecimal maxAmt;

    private BigDecimal minAmt;

    private Integer recommendCnt;

    private Integer remainCnt;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    public String getDetailNm() {
        return detailNm;
    }

    public void setDetailNm(String detailNm) {
        this.detailNm = detailNm == null ? null : detailNm.trim();
    }

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

    public BigDecimal getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(BigDecimal maxPercent) {
        this.maxPercent = maxPercent;
    }

    public BigDecimal getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(BigDecimal minPercent) {
        this.minPercent = minPercent;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public Integer getRecommendCnt() {
        return recommendCnt;
    }

    public void setRecommendCnt(Integer recommendCnt) {
        this.recommendCnt = recommendCnt;
    }

    public Integer getRemainCnt() {
        return remainCnt;
    }

    public void setRemainCnt(Integer remainCnt) {
        this.remainCnt = remainCnt;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}