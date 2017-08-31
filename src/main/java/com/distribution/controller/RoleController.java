package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.controller.BasicController;
import com.distribution.service.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingxin on 2017/8/22.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BasicController {

    private static final Log loger = LogFactory.getLog(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getUserRole")
    @ResponseBody
    public Map<String, Object> getCurrentUserRole(HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("currentUser", session.getAttribute(Constant.SESSION_CURRENT_USER));
        return result;
    }



}