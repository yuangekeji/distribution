package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.menu.model.Menu;
import com.distribution.service.MenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lijingx on 8/23/2017.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BasicController {

    private static final Log loger = LogFactory.getLog(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/getMenuByRoleId", method = RequestMethod.GET)
    @ResponseBody
//    @IgnoreLoginCheck
    public JsonMessage getMenuByRoleId(String roleId,HttpSession session){

        if(getCurrentUser(session) instanceof Member) {
            Member member = (Member) getCurrentUser(session);
            if("N".equals(member.getStatus())){
                return failMsg();
            }
        }

        List<Menu> menus = menuService.getMenuByRoleId(roleId);
        return successMsg("menus", menus);
    }


}
