package com.distribution.dao.dividendHistory.model;

import java.math.BigDecimal;
import java.util.Date;

public class DividendHistory {
    private Integer id;

    private Integer dividendId;

    private Date receivedTime;

    private Integer devidendCount;

    private BigDecimal amount;

    private BigDecimal totalReceived;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private BigDecimal mgmtFee;

    private Integer balanceStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDividendId() {
        return dividendId;
    }

    public void setDividendId(Integer dividendId) {
        this.dividendId = dividendId;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Integer getDevidendCount() {
        return devidendCount;
    }

    public void setDevidendCount(Integer devidendCount) {
        this.devidendCount = devidendCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(BigDecimal totalReceived) {
        this.totalReceived = totalReceived;
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

    public BigDecimal getMgmtFee() {
        return mgmtFee;
    }

    public void setMgmtFee(BigDecimal mgmtFee) {
        this.mgmtFee = mgmtFee;
    }

    public Integer getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(Integer balanceStatus) {
        this.balanceStatus = balanceStatus;
    }
}