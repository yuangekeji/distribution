package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.member.model.Member;
import com.distribution.service.AdmAdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by WYN on 2017/09/03.
 */
@Controller
@RequestMapping("/admAdvance")
public class AdmAdvanceController extends BasicController {
    @Autowired
    private AdmAdvanceService admAdvanceService;

    /**
     * description 查詢提现列表
     * @author WYN
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage advanceList(@RequestBody Page page, HttpSession session){
        page = admAdvanceService.advanceList(page);
        return successMsg(page);
    }

    /**
     * description 确认
     * @author WYN
     * */
    @RequestMapping("/confirmAdvance")
    @ResponseBody
    public JsonMessage confirmAdvance(@RequestBody MoreAdvance moreAdvance, HttpSession session){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        moreAdvance.setApproveDate(new Date());
        moreAdvance.setUpdateId(currentUser.getId());
        moreAdvance.setUpdateTime(new Date());
        String result = admAdvanceService.confirmAdvance(moreAdvance);
        return successMsg("result",result);
    }

}
