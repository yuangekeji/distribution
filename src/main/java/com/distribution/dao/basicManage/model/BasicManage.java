package com.distribution.dao.basicManage.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasicManage extends BasicManageKey implements Serializable {
    private String detailNm;

    private BigDecimal maxPercent;

    private BigDecimal minPercent;

    private BigDecimal maxAmt;

    private BigDecimal minAmt;

    private static final long serialVersionUID = 1L;

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
}