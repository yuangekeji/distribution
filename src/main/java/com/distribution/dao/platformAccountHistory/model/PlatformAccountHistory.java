package com.distribution.dao.platformAccountHistory.model;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformAccountHistory {
    private Integer id;

    private BigDecimal totalSales;

    private BigDecimal totalBonus;

    private BigDecimal platformAmount;

    private BigDecimal accountAmount;

    private BigDecimal accountAmountOld;

    private BigDecimal poolAmount;

    private BigDecimal flowAmount;

    private BigDecimal accountAmountNew;

    private Date createDate;

    private String createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(BigDecimal totalBonus) {
        this.totalBonus = totalBonus;
    }

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getAccountAmountOld() {
        return accountAmountOld;
    }

    public void setAccountAmountOld(BigDecimal accountAmountOld) {
        this.accountAmountOld = accountAmountOld;
    }

    public BigDecimal getPoolAmount() {
        return poolAmount;
    }

    public void setPoolAmount(BigDecimal poolAmount) {
        this.poolAmount = poolAmount;
    }

    public BigDecimal getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }

    public BigDecimal getAccountAmountNew() {
        return accountAmountNew;
    }

    public void setAccountAmountNew(BigDecimal accountAmountNew) {
        this.accountAmountNew = accountAmountNew;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}