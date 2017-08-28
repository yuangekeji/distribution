package com.distribution.controller.transfer;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.dao.member.model.Member;
import com.distribution.service.admin.AdminService;
import com.distribution.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by jingxin on 2017/8/26.
 */
public class TransferController extends BasicController{


    @Autowired
    private MemberService memberService;

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

}
