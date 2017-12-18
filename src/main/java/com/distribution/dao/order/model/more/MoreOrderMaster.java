package com.distribution.dao.order.model.more;

import com.distribution.dao.order.model.OrderMaster;

import java.math.BigDecimal;

public class MoreOrderMaster extends OrderMaster{
    private Integer goodsCd;

    private String goodsNm;

    private BigDecimal amt;

    private String bonusType;

    private BigDecimal seedAmt;

    private BigDecimal bonusAmt;

    private String memberName;

    private String memberPhone;

    private Integer createId;

    private String payPassword;

    private Integer goodsNum;

    private BigDecimal goodsPrice;

    private String buyType;

    public Integer getGoodsCd() {
        return goodsCd;
    }

    public void setGoodsCd(Integer goodsCd) {
        this.goodsCd = goodsCd;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm == null ? null : goodsNm.trim();
    }

    public BigDecimal getSeedAmt() {
        return seedAmt;
    }

    public void setSeedAmt(BigDecimal seedAmt) {
        this.seedAmt = seedAmt;
    }

    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    @Override
    public Integer getCreateId() {
        return createId;
    }

    @Override
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }
}

