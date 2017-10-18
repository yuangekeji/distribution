package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.service.MemberChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by sijeong on 2017/10/14.
 */
@Controller
@RequestMapping("/memberCharge")
public class MemberChargeController extends BasicController {
    @Autowired
    private MemberChargeService memberChargeService;

    /**
     * 充值申请列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage memberChargeList(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());

        page = memberChargeService.memberChargeList(page);
        return successMsg(page);
    }

    /**
     * 个人账户余额查询
     */
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getAccountInfo(HttpSession session){
        Member m = (Member) getCurrentUser(session);
        //查询账户信息
        AccountManager accountManager = memberChargeService.selectAccountManager(m.getId());

        return successMsg("account",accountManager);
    }
    /**
     * 充值申请
     */
    @RequestMapping("/insertMemberCharge")
    @ResponseBody
    public JsonMessage insertMemberCharge(@RequestBody MoreMemberChargeApply moreMemberChargeApply, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        moreMemberChargeApply.setMemberId(m.getId());
        String result = memberChargeService.insertMemberCharge(moreMemberChargeApply);
        return successMsg("result",result);
    }
}
