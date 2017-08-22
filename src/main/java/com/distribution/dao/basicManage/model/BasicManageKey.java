package com.distribution.dao.basicManage.model;

import java.io.Serializable;

public class BasicManageKey implements Serializable {
    private String detailCode;

    private Integer id;

    private String typeCode;

    private static final long serialVersionUID = 1L;

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode == null ? null : detailCode.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }
}