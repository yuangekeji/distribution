package com.distribution.dao.order.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderMaster {
    private Integer id;

    private Long orderNo;

    private String orderCategory;

    private BigDecimal orderAmt;

    private Integer orderQty;

    private Integer discount;

    private BigDecimal actAmt;

    private BigDecimal expressFee;

    private Integer memberId;

    private String receiveName;

    private String expressAddress;

    private String recevivePhone;

    private String remark;

    private String memberLevel;

    private String orderStatues;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private String sendbypostyn;

    private String bonusAccountType;

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

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getActAmt() {
        return actAmt;
    }

    public void setActAmt(BigDecimal actAmt) {
        this.actAmt = actAmt;
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

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel == null ? null : memberLevel.trim();
    }

    public String getOrderStatues() {
        return orderStatues;
    }

    public void setOrderStatues(String orderStatues) {
        this.orderStatues = orderStatues == null ? null : orderStatues.trim();
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

    public String getBonusAccountType() {
        return bonusAccountType;
    }

    public void setBonusAccountType(String bonusAccountType) {
        this.bonusAccountType = bonusAccountType == null ? null : bonusAccountType.trim();
    }
}