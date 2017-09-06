package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.service.BonusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by shiqing on 09/03/2017.
 */
@Controller
@RequestMapping("/adminBonus")
public class AdminBonusController extends BasicController {
    private static final Log loger = LogFactory.getLog(AdminBonusController.class);

    @Autowired
    private BonusService bonusService;

    /**
     * 查詢分销记录列表
     */
     @RequestMapping(value = "/list", method = RequestMethod.POST)
     @ResponseBody
     public JsonMessage selectMemberBonusList(@RequestBody Page page){
         page = bonusService.selectMemberBonusList(page);
         return successMsg(page);
     }
    /**
     * 查詢分销记录明细
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage selectMemberBonusDetail(@RequestBody Page page){
        page = bonusService.selectMemberBonusDetail(page);
        return successMsg(page);
    }
}