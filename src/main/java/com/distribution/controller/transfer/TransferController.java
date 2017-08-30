package com.distribution.controller.transfer;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.transfer.model.more.MoreTransfer;
import com.distribution.service.admin.AdminService;
import com.distribution.service.member.MemberService;
import com.distribution.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * Created by jingxin on 2017/8/26.
 */
@Controller
@RequestMapping("/transfer")
public class TransferController extends BasicController{


    @Autowired
    private MemberService memberService;

    @Autowired
    private TransferService transferService;

    /**
     * 账号验证，通过手机号验证该账户是否存在
     */

    @RequestMapping(value = "/getMemberByPhone", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getUser(String phone){

        Member m =  memberService.getMemberByPhone(phone);
        if(m !=null){

            return successMsg("member", m);
        }else{
            return successMsg("member", new Member());
        }
    }

    /**
     * 轉賬冗餘 手機號和姓名
     * 支付密碼的校驗
     * 轉賬存入麗水表里和轉賬表，并計算賬戶餘額，更新賬戶
     */
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getAccountInfo(HttpSession session){
        Member m = (Member) getCurrentUser(session);
        AccountManager account = new AccountManager();
        account.setMemberId(m.getId());
        //查询账户信息
        return successMsg("account",transferService.selectAccountManager(account));
    }


    @RequestMapping(value = "/transferProcess", method = RequestMethod.POST)
    public JsonMessage transferProcess(MoreTransfer transfer,HttpSession session){
        //校验支付密码是否正确
        Member m = (Member) getCurrentUser(session);
        transfer.setMemberId(m.getId());
        //密码正确处理转账
//        return failMsg(e.getMessage());
        return successMsg("result",transferService.insertTransferProcess(transfer));
    }


}
