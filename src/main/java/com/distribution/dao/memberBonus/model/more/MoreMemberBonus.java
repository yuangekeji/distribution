package com.distribution.dao.memberBonus.model.more;

import com.distribution.dao.memberBonus.model.MemberBonus;

import java.math.BigDecimal;
import java.util.Date;

public class MoreMemberBonus extends MemberBonus {
    private String memberName;
    private Integer recommendId;
    private String recommendName;
    private Double orderAmount;
    private Double amoutTotal;
    private Double manageFeeTotal;
    private Double actualAmoutTotal;
    private String orderStartDate;
    private String orderEndDate;
    private BigDecimal orderAmt;
    private Date createTime;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getAmoutTotal() {
        return amoutTotal;
    }

    public void setAmoutTotal(Double amoutTotal) {
        this.amoutTotal = amoutTotal;
    }

    public Double getManageFeeTotal() {
        return manageFeeTotal;
    }

    public void setManageFeeTotal(Double manageFeeTotal) {
        this.manageFeeTotal = manageFeeTotal;
    }

    public Double getActualAmoutTotal() {
        return actualAmoutTotal;
    }

    public void setActualAmoutTotal(Double actualAmoutTotal) {
        this.actualAmoutTotal = actualAmoutTotal;
    }

    public String getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public String getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(String orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
