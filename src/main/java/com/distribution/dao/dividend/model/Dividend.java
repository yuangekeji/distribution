package com.distribution.dao.dividend.model;

import java.math.BigDecimal;
import java.util.Date;

public class Dividend {
    private Integer id;

    private Integer memberId;

    private Integer orderId;

    private String orderNo;

    private BigDecimal orderAmount;

    private Integer dividendCount;

    private BigDecimal receivedAmount;

    private BigDecimal remainAmount;

    private BigDecimal dividendLimit;

    private String dividendStatus;

    private BigDecimal mgmtFee;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getDividendLimit() {
        return dividendLimit;
    }

    public void setDividendLimit(BigDecimal dividendLimit) {
        this.dividendLimit = dividendLimit;
    }

    public String getDividendStatus() {
        return dividendStatus;
    }

    public void setDividendStatus(String dividendStatus) {
        this.dividendStatus = dividendStatus == null ? null : dividendStatus.trim();
    }

    public BigDecimal getMgmtFee() {
        return mgmtFee;
    }

    public void setMgmtFee(BigDecimal mgmtFee) {
        this.mgmtFee = mgmtFee;
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