package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.basicManage.model.BasicManage;
import com.distribution.service.AdmBasicSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admBasicSetting")
public class AdmBasicSettingController extends BasicController {
    @Autowired
    private AdmBasicSettingService admBasicSettingService;

    /**
     * description 基本配置会员奖励
     * @author WYN
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage getBasicMemberBonusList(@RequestBody Page page, HttpSession session){
        page = admBasicSettingService.getBasicMemberBonusList(page);
        return successMsg(page);
    }

    @RequestMapping("/updateBasicSetting")
    @ResponseBody
    public JsonMessage updateBasicSetting(@RequestBody BasicManage basicManage, HttpSession session, HttpServletRequest request){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        basicManage.setUpdateId(currentUser.getId());
        basicManage.setUpdateTime(new Date());

        String result =admBasicSettingService.updateBasicSetting(basicManage);
        return successMsg("result",result);
    }

}
