package com.distribution.controller.admin;

import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jingxin on 2017/8/21.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BasicController {


    @RequestMapping(value = "/login")
    @IgnoreLoginCheck
    public String init() {
        return "adminLogin/adminLogin";
    }


}
