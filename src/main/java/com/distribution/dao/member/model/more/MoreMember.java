package com.distribution.dao.member.model.more;

import com.distribution.dao.member.model.Member;

public class MoreMember extends Member{
    private String recommendPhone;           //推荐者账号(手机号码)
    private String notePhone;                //节点手机号
    private String area;                     //放置的左右区

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
}
