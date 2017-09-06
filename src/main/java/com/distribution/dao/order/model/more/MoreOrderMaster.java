package com.distribution.dao.order.model.more;

import com.distribution.dao.order.model.OrderMaster;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;

public class MoreOrderMaster extends OrderMaster{
    private Integer goodsCd;

    private String goodsNm;

    private BigDecimal seedAmt;

    private BigDecimal bonusAmt;

    private String memberName;

    private Integer createId;

    private String payPassword;

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
}

