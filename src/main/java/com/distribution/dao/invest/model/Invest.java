package com.distribution.dao.invest.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invest {
    private Integer id;

    private Integer memberId;

    private Integer investCnt;

    private BigDecimal seedAmt;

    private BigDecimal bonusAmt;

    private Integer createId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getInvestCnt() {
        return investCnt;
    }

    public void setInvestCnt(Integer investCnt) {
        this.investCnt = investCnt;
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
}