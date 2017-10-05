package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdminService;
import com.distribution.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    // private static final Log loger = LogFactory.getLog(DividendController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
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
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_ADMINSETTING);
        map.put("handleId", admin.getName());
        map.put("handleComment", "管理员名称: " + admin.getName() + ", 操作: 创建管理员");

        admHandleHistoryService.addAdminHandleHistory(a, map);
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
            //管理员操作记录
            Map map = new HashMap();
            map.put("handleType", Constant.ADMINHANDLETYPE_ADMINSETTING);
            map.put("handleId", admin.getId());
            if ("Y".equals(admin.getDeleteFlag())) {
                map.put("handleComment", "管理员名称: " + admin.getName() + ", 操作: 禁用");
            }else {
                map.put("handleComment", "管理员名称: " + admin.getName() + ", 操作: 启用");
            }
            admHandleHistoryService.addAdminHandleHistory(a, map);
            return successMsg();
        }else{
            return failMsg();
        }
    }

}
