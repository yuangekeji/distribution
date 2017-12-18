package com.distribution.dao.pointOrder.model;

import java.math.BigDecimal;
import java.util.Date;

public class PointOrder {
    private Integer id;

    private Long orderNo;

    private String orderCategory;

    private BigDecimal orderAmt;

    private BigDecimal pointAmt;

    private String pointType;

    private Integer orderQty;

    private BigDecimal expressFee;

    private Integer memberId;

    private String receiveName;

    private String expressAddress;

    private String recevivePhone;

    private String remark;

    private String orderStatues;

    private String expressNo;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private String sendbypostyn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(String orderCategory) {
        this.orderCategory = orderCategory == null ? null : orderCategory.trim();
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getPointAmt() {
        return pointAmt;
    }

    public void setPointAmt(BigDecimal pointAmt) {
        this.pointAmt = pointAmt;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType == null ? null : pointType.trim();
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(BigDecimal expressFee) {
        this.expressFee = expressFee;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress == null ? null : expressAddress.trim();
    }

    public String getRecevivePhone() {
        return recevivePhone;
    }

    public void setRecevivePhone(String recevivePhone) {
        this.recevivePhone = recevivePhone == null ? null : recevivePhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOrderStatues() {
        return orderStatues;
    }

    public void setOrderStatues(String orderStatues) {
        this.orderStatues = orderStatues == null ? null : orderStatues.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
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

    public String getSendbypostyn() {
        return sendbypostyn;
    }

    public void setSendbypostyn(String sendbypostyn) {
        this.sendbypostyn = sendbypostyn == null ? null : sendbypostyn.trim();
    }
}