package com.distribution.dao.admin.model.more;

import com.distribution.dao.admin.model.Admin;

public class MoreAdmin extends Admin{

    private String oldLoginPwd;             //原始登录密码

    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }
}