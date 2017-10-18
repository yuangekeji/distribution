package com.distribution.dao.member.model.more;

import com.distribution.dao.member.model.Member;

/**
 * Created by jingxin on 2017/10/1.
 */
public class MoreMemberVO extends Member{

    private String sendbypostyn; //1自提 2是邮寄

    private String receviveAddress;

    private String receiveName;

    private String recevivePhone;
       //推荐者账号(手机号码)
    public String getSendbypostyn() {
        return sendbypostyn;
    }

    public void setSendbypostyn(String sendbypostyn) {
        this.sendbypostyn = sendbypostyn;
    }

    public String getRecevivePhone() {
        return recevivePhone;
    }

    public void setRecevivePhone(String recevivePhone) {
        this.recevivePhone = recevivePhone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceviveAddress() {
        return receviveAddress;
    }

    public void setReceviveAddress(String receviveAddress) {
        this.receviveAddress = receviveAddress;
    }


}
