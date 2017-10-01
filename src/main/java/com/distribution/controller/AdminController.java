package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.service.AdminService;
import com.distribution.service.BonusService;
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

    /**
     * description 添加管理员
     * @author Bright
     * */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonMessage insertAdmin(@RequestBody Admin admin,HttpSession session){
        Admin a = (Admin) getCurrentUser(session);
        String str = adminService.insertAdmin(admin,a);
        return successMsg(str);
    }

    /**
     * description 管理员禁用/启用功能操作
     * @author shiqing.dong
     * */
    @RequestMapping("/updateAdminDeleteFlag")
    @ResponseBody
    public JsonMessage updateAdminDeleteFlag(@RequestBody Admin admin, HttpSession session){
        Admin a = (Admin) getCurrentUser(session);
        Integer it = adminService.updateAdminDeleteFlag(admin, a);
        if(it>0){
            return successMsg();
        }else{
            return failMsg();
        }
    }

}
