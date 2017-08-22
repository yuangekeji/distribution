package com.distribution.dao.orderDetail.model;

import java.io.Serializable;

public class OrderDetailKey implements Serializable {
    private String goodsCd;

    private Integer id;

    private static final long serialVersionUID = 1L;

    public String getGoodsCd() {
        return goodsCd;
    }

    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd == null ? null : goodsCd.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}