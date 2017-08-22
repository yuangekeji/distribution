package com.distribution.dao.dividendHistory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DividendHistory implements Serializable {
    private Integer id;

    private Integer dividendId;

    private Date receivedTime;

    private Integer devidendCount;

    private BigDecimal amount;

    private BigDecimal totalReceived;

    private static final long serialVersionUID = 1L;

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
}