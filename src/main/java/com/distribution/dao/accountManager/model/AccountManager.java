package com.distribution.dao.accountManager.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountManager implements Serializable {
    private Integer id;

    private BigDecimal seedAmt;

    private BigDecimal bonusAmt;

    private BigDecimal advanceAmt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSeedAmt() {
        return seedAmt;
    }

    public void setSeedAmt(BigDecimal seedAmt) {
        this.seedAmt = seedAmt;
    }

    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    public BigDecimal getAdvanceAmt() {
        return advanceAmt;
    }

    public void setAdvanceAmt(BigDecimal advanceAmt) {
        this.advanceAmt = advanceAmt;
    }
}