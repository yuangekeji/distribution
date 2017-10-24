package com.distribution.dao.memberChargeApply.model;

import java.math.BigDecimal;
import java.util.Date;

public class MemberChargeApply {
    private Integer id;

    private Integer memberId;

    private String status;

    private Date chargeRequestTime;

    private String payMoneyType;

    private Date payMoneyTime;

    private BigDecimal chargeAmt;

    private String chargeMoneyType;

    private Date chargeApplyTime;

    private Date chargeTime;

    private String applyInfo;

    private String remarks;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getChargeRequestTime() {
        return chargeRequestTime;
    }

    public void setChargeRequestTime(Date chargeRequestTime) {
        this.chargeRequestTime = chargeRequestTime;
    }

    public String getPayMoneyType() {
        return payMoneyType;
    }

    public void setPayMoneyType(String payMoneyType) {
        this.payMoneyType = payMoneyType == null ? null : payMoneyType.trim();
    }

    public Date getPayMoneyTime() {
        return payMoneyTime;
    }

    public void setPayMoneyTime(Date payMoneyTime) {
        this.payMoneyTime = payMoneyTime;
    }

    public BigDecimal getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(BigDecimal chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getChargeMoneyType() {
        return chargeMoneyType;
    }

    public void setChargeMoneyType(String chargeMoneyType) {
        this.chargeMoneyType = chargeMoneyType == null ? null : chargeMoneyType.trim();
    }

    public Date getChargeApplyTime() {
        return chargeApplyTime;
    }

    public void setChargeApplyTime(Date chargeApplyTime) {
        this.chargeApplyTime = chargeApplyTime;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo == null ? null : applyInfo.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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