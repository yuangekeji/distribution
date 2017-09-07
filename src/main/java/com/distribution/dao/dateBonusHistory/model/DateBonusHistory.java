package com.distribution.dao.dateBonusHistory.model;

import java.math.BigDecimal;
import java.util.Date;

public class DateBonusHistory {
    private Integer id;

    private Date date;

    private BigDecimal totalSales;

    private BigDecimal dividendTotal;

    private BigDecimal jdBonusTotal;

    private BigDecimal useDividendTotal;

    private Long useJdBonusTotal;

    private Long remainDividend;

    private Long remainJdBonus;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer alarmStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getDividendTotal() {
        return dividendTotal;
    }

    public void setDividendTotal(BigDecimal dividendTotal) {
        this.dividendTotal = dividendTotal;
    }

    public BigDecimal getJdBonusTotal() {
        return jdBonusTotal;
    }

    public void setJdBonusTotal(BigDecimal jdBonusTotal) {
        this.jdBonusTotal = jdBonusTotal;
    }

    public BigDecimal getUseDividendTotal() {
        return useDividendTotal;
    }

    public void setUseDividendTotal(BigDecimal useDividendTotal) {
        this.useDividendTotal = useDividendTotal;
    }

    public Long getUseJdBonusTotal() {
        return useJdBonusTotal;
    }

    public void setUseJdBonusTotal(Long useJdBonusTotal) {
        this.useJdBonusTotal = useJdBonusTotal;
    }

    public Long getRemainDividend() {
        return remainDividend;
    }

    public void setRemainDividend(Long remainDividend) {
        this.remainDividend = remainDividend;
    }

    public Long getRemainJdBonus() {
        return remainJdBonus;
    }

    public void setRemainJdBonus(Long remainJdBonus) {
        this.remainJdBonus = remainJdBonus;
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

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }
}