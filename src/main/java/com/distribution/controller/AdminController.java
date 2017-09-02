package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jingxin on 2017/8/21.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BasicController {
    // private static final Log loger = LogFactory.getLog(DividendController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 查詢管理员列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage adminList(@RequestBody Page page, HttpSession session){
        page = adminService.adminList(page);
        return successMsg(page);
    }
}
