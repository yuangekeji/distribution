package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.service.AdmBasicSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
}
