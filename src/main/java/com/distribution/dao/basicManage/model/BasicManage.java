package com.distribution.dao.basicManage.model;

import java.math.BigDecimal;
import java.util.Date;

public class basicManage extends basicManageKey {
    private String detailNm;

    private BigDecimal maxPercent;

    private BigDecimal minPercent;

    private BigDecimal maxAmt;

    private BigDecimal minAmt;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    public String getDetailNm() {
        return detailNm;
    }

    public void setDetailNm(String detailNm) {
        this.detailNm = detailNm == null ? null : detailNm.trim();
    }

    public BigDecimal getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(BigDecimal maxPercent) {
        this.maxPercent = maxPercent;
    }

    public BigDecimal getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(BigDecimal minPercent) {
        this.minPercent = minPercent;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
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