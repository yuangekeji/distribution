package com.distribution.dao.nodeBonusHistory.model;

import java.util.Date;

public class NodeBonusHistory {
    private Integer id;

    private Integer mebmerId;

    private Double bonusAmount;

    private Integer fromNodeId;

    private Integer status;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private Date updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMebmerId() {
        return mebmerId;
    }

    public void setMebmerId(Integer mebmerId) {
        this.mebmerId = mebmerId;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Integer getFromNodeId() {
        return fromNodeId;
    }

    public void setFromNodeId(Integer fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }
}