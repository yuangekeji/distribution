package com.distribution.dao.orderDetail.model;

public class orderDetailKey {
    private String goodsCd;

    private Integer id;

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