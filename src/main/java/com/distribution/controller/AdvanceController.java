package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.service.AdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by WYN on 2017/9/03.
 */
@Controller
@RequestMapping("/advance")
public class AdvanceController extends BasicController {
    @Autowired
    private AdvanceService advanceService;

    /**
     * 查詢提现列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage advanceList(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());

        page = advanceService.advanceList(page);
        return successMsg(page);
    }
}
