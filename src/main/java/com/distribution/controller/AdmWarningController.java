package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.model.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jingxin on 2017/9/6.
 */

@RequestMapping("/admWarning")
@Controller
public class AdmWarningController extends BasicController{

    private static final Log loger = LogFactory.getLog(AdmWarningController.class);

    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage orderList(HttpSession session){
        Admin m = (Admin) getCurrentUser(session);
        return successMsg();
    }

}
