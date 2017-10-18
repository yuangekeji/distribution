package com.distribution.dao.member.model.more;

import com.distribution.dao.member.model.Member;

import java.math.BigDecimal;

public class MoreMember extends Member{
    private String recommendPhone;           //推荐者账号(手机号码)
    private String notePhone;                //节点手机号
    private String area;                     //放置的左右区

    private BigDecimal cityTotalAmount;      //市级市场业绩
    private Integer cityTotalPeople;         //市级市场人数
    private BigDecimal countyTotalAmount;    //县级市场业绩
    private Integer countyTotalPeople;       //县级市场人数
    private BigDecimal bonusAmount;          //奖金币
    private BigDecimal seedAmount;           //种子币
    private BigDecimal actualAmount;         //提现总额
    private BigDecimal orderTotalAmount;     //累计订单金额

    private String oldLoginPwd;             //原始登录密码
    private String oldQueryPwd;             //原始查询密码
    private String oldPayPwd;               //原始支付密码

    private String pwdFlag;                 //密码修改标识位

    private String parentMemberName;


    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public String getNotePhone() {
        return notePhone;
    }

    public void setNotePhone(String notePhone) {
        this.notePhone = notePhone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getCityTotalAmount() {
        return cityTotalAmount;
    }

    public void setCityTotalAmount(BigDecimal cityTotalAmount) {
        this.cityTotalAmount = cityTotalAmount;
    }

    public Integer getCityTotalPeople() {
        return cityTotalPeople;
    }

    public void setCityTotalPeople(Integer cityTotalPeople) {
        this.cityTotalPeople = cityTotalPeople;
    }

    public BigDecimal getCountyTotalAmount() {
        return countyTotalAmount;
    }

    public void setCountyTotalAmount(BigDecimal countyTotalAmount) {
        this.countyTotalAmount = countyTotalAmount;
    }

    public Integer getCountyTotalPeople() {
        return countyTotalPeople;
    }

    public void setCountyTotalPeople(Integer countyTotalPeople) {
        this.countyTotalPeople = countyTotalPeople;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getSeedAmount() {
        return seedAmount;
    }

    public void setSeedAmount(BigDecimal seedAmount) {
        this.seedAmount = seedAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }

    public String getOldQueryPwd() {
        return oldQueryPwd;
    }

    public void setOldQueryPwd(String oldQueryPwd) {
        this.oldQueryPwd = oldQueryPwd;
    }

    public String getOldPayPwd() {
        return oldPayPwd;
    }

    public void setOldPayPwd(String oldPayPwd) {
        this.oldPayPwd = oldPayPwd;
    }

    public String getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(String pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public String getParentMemberName() {
        return parentMemberName;
    }

    public void setParentMemberName(String parentMemberName) {
        this.parentMemberName = parentMemberName;
    }


}
