package com.distribution.dao.transfer.model.more;
import com.distribution.dao.transfer.model.Transfer;


/**
 * Created by lijingx on 8/30/2017.
 */
public class MoreTransfer extends Transfer {

    //转账时需要输入支付密码
    private String payPassword;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    private String memberPhone;

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    private String receviedPhone;

    public String getReceviedPhone() {
        return receviedPhone;
    }

    public void setReceviedPhone(String receviedPhone) {
        this.receviedPhone = receviedPhone;
    }
}
