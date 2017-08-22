package com.distribution.controller.user;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.dao.user.model.User;
import com.distribution.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by jingxin on 2017/8/13.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BasicController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "list" )
    public ModelAndView userList(Model model){
        User user = userService.findUser();
        System.out.println(user.getName()+','+user.getMail());
        return new ModelAndView("test/list");
    }

//
//    @RequestMapping(value = "add",method = RequestMethod.POST )
//    public ModelAndView addUser(User user, Model model){
//        boolean flag =  userService.addUser(user);
//        model.addAttribute("actionStatus",flag+"");
//        return new ModelAndView("test/success");
//    }
    /**
     * description 从session中获取用户信息
     * @author bright
     * */
    @RequestMapping("/getUserFromSession")
    @ResponseBody
    public JsonMessage getUserFromSession(HttpSession session){
//        User user = getCurrentUserBySession(session);
        return successMsg(new User());
    }

}
