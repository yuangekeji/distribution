package com.distribution.dao.apply.model.more;

import com.distribution.dao.apply.model.OperationApply;

import java.math.BigDecimal;
import java.util.Date;

public class MoreOperationApply extends OperationApply{
    private String memberPost;
    private String memberLevel;
    private String recommendName;
    private String memberName;

    public String getMemberPost() {
        return memberPost;
    }

    public void setMemberPost(String memberPost) {
        this.memberPost = memberPost;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}