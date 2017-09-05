package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.member.model.Member;
import com.distribution.service.AdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 个人账户余额查询
     */
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getAccountInfo(HttpSession session){
        Member m = (Member) getCurrentUser(session);
        AccountManager account = new AccountManager();
        account.setMemberId(m.getId());
        //查询账户信息
        account = advanceService.selectAccountManager(account);

        return successMsg("account",account);
    }

    @RequestMapping(value = "/insertAdvance")
    @ResponseBody
    public JsonMessage insertAdvance(@RequestBody MoreAdvance moreAdvance, HttpSession session, HttpServletRequest request){
        //校验支付密码是否正确
        request.getParameterMap();
        Member m = (Member) getCurrentUser(session);
        moreAdvance.setMemberId(m.getId());
        moreAdvance.setMemberPhone(m.getMemberPhone());
        moreAdvance.setMemberName(m.getMemberName());

        String result =advanceService.insertAdvance(moreAdvance);
        return successMsg("result",result);
    }

}
