package com.distribution.controller.admin;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingxin on 2017/8/21.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BasicController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/jump")
    @IgnoreLoginCheck
    public String init() {
        return "adminLogin/adminLogin";
    }

    @RequestMapping("/login")
    @IgnoreLoginCheck
    @ResponseBody
    public JsonMessage login(String userName, String password, String remember, HttpSession session){
        Map param = new HashMap();
        param.put("userName",userName);
        param.put("password", CryptoUtil.md5ByHex(password));
        return adminService.login(param,remember,session);
    }

}
