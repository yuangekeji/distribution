package com.distribution.dao.accountFlowHistory.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFlowHistory {
    private Integer id;

    private Integer memberId;

    private Date createTime;

    private Integer createId;

    private String type;

    private BigDecimal totalAmt;

    private BigDecimal bonusAmt;

    private BigDecimal seedAmt;

    private String flowType;

    private String remark;

    private BigDecimal oldTotalBonusAmt;

    private BigDecimal newTotalBonusAmt;

    private BigDecimal oldTotalSeedAmt;

    private BigDecimal newTotalSeedAmt;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    public BigDecimal getSeedAmt() {
        return seedAmt;
    }

    public void setSeedAmt(BigDecimal seedAmt) {
        this.seedAmt = seedAmt;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getOldTotalBonusAmt() {
        return oldTotalBonusAmt;
    }

    public void setOldTotalBonusAmt(BigDecimal oldTotalBonusAmt) {
        this.oldTotalBonusAmt = oldTotalBonusAmt;
    }

    public BigDecimal getNewTotalBonusAmt() {
        return newTotalBonusAmt;
    }

    public void setNewTotalBonusAmt(BigDecimal newTotalBonusAmt) {
        this.newTotalBonusAmt = newTotalBonusAmt;
    }

    public BigDecimal getOldTotalSeedAmt() {
        return oldTotalSeedAmt;
    }

    public void setOldTotalSeedAmt(BigDecimal oldTotalSeedAmt) {
        this.oldTotalSeedAmt = oldTotalSeedAmt;
    }

    public BigDecimal getNewTotalSeedAmt() {
        return newTotalSeedAmt;
    }

    public void setNewTotalSeedAmt(BigDecimal newTotalSeedAmt) {
        this.newTotalSeedAmt = newTotalSeedAmt;
    }
}