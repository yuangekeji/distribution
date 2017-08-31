package com.distribution.dao.chinaPresidentBonus.model;

import java.util.Date;

public class ChinaPresidentBonus {
    private Integer id;

    private Integer memberId;

    private Double bonusAmout;

    private Date bonusDate;

    private String status;

    private Date balanceTime;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer dayBonusId;

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

    public Double getBonusAmout() {
        return bonusAmout;
    }

    public void setBonusAmout(Double bonusAmout) {
        this.bonusAmout = bonusAmout;
    }

    public Date getBonusDate() {
        return bonusDate;
    }

    public void setBonusDate(Date bonusDate) {
        this.bonusDate = bonusDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getBalanceTime() {
        return balanceTime;
    }

    public void setBalanceTime(Date balanceTime) {
        this.balanceTime = balanceTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getDayBonusId() {
        return dayBonusId;
    }

    public void setDayBonusId(Integer dayBonusId) {
        this.dayBonusId = dayBonusId;
    }
}