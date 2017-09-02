package com.distribution.dao.memberBonus.model.more;

import com.distribution.dao.memberBonus.model.MemberBonus;

public class MoreMemberBonus extends MemberBonus {
    private String memberName;
    private Integer recommendId;
    private String recommendName;
    private Double orderAmount;
    private Double amoutTotal;
    private Double manageFeeTotal;
    private Double actualAmoutTotal;
    private Double bonusFirstAgentAmout;
    private Double bonusSecondAgentAmout;
    private Double bonusDividendAmout;
    private Double bonusPointAmout;
    private Double bonusGradedAmout;
    private Double bonusWorkroomAmout;
    private Double bonusWorkcenterAmout;

    public Double getBonusFirstAgentAmout() {
        return bonusFirstAgentAmout;
    }

    public void setBonusFirstAgentAmout(Double bonusFirstAgentAmout) {
        this.bonusFirstAgentAmout = bonusFirstAgentAmout;
    }

    public Double getBonusSecondAgentAmout() {
        return bonusSecondAgentAmout;
    }

    public void setBonusSecondAgentAmout(Double bonusSecondAgentAmout) {
        this.bonusSecondAgentAmout = bonusSecondAgentAmout;
    }

    public Double getBonusDividendAmout() {
        return bonusDividendAmout;
    }

    public void setBonusDividendAmout(Double bonusDividendAmout) {
        this.bonusDividendAmout = bonusDividendAmout;
    }

    public Double getBonusPointAmout() {
        return bonusPointAmout;
    }

    public void setBonusPointAmout(Double bonusPointAmout) {
        this.bonusPointAmout = bonusPointAmout;
    }

    public Double getBonusGradedAmout() {
        return bonusGradedAmout;
    }

    public void setBonusGradedAmout(Double bonusGradedAmout) {
        this.bonusGradedAmout = bonusGradedAmout;
    }

    public Double getBonusWorkroomAmout() {
        return bonusWorkroomAmout;
    }

    public void setBonusWorkroomAmout(Double bonusWorkroomAmout) {
        this.bonusWorkroomAmout = bonusWorkroomAmout;
    }

    public Double getBonusWorkcenterAmout() {
        return bonusWorkcenterAmout;
    }

    public void setBonusWorkcenterAmout(Double bonusWorkcenterAmout) {
        this.bonusWorkcenterAmout = bonusWorkcenterAmout;
    }

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
}
