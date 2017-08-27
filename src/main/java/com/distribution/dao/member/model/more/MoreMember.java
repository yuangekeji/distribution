package com.distribution.dao.member.model.more;

import com.distribution.dao.member.model.Member;

public class MoreMember extends Member{
    public String recommendPhone;           //推荐者账号(手机号码)
    public String notePhone;                //节点手机号

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
}
