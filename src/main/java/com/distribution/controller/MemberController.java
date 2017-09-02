package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.service.CommonService;
import com.distribution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jingxin on 2017/8/21.
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BasicController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/jump")
    @IgnoreLoginCheck
    public String init() {
        return "adminLogin/adminLogin";
    }


    /**
     * description 登录
     * @author Bright
     * */
    @RequestMapping("/login")
    @IgnoreLoginCheck
    @ResponseBody
    public JsonMessage login(String userName, String password, String remember, HttpSession session){
        Map param = new HashMap();
        param.put("userName",userName);
        param.put("password", CryptoUtil.md5ByHex(password));
        return memberService.login(param,remember,session);
    }

    /**
     * description 退出
     * @author Bright
     * */
    @RequestMapping("/logout")
    @ResponseBody
    @IgnoreLoginCheck
    public JsonMessage logout(HttpSession session){
        session.removeAttribute(Constant.SESSION_CURRENT_USER);
        return successMsg();
    }

    /**
     * description 会员列表查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page,HttpSession session){
        if(null!=page.getParameterMap().get("myRecord") && (Boolean) page.getParameterMap().get("myRecord")){
            Member m = (Member) getCurrentUser(session);
            page.getParameterMap().put("recommendId",m.getId());
        }else{
            page.getParameterMap().put("recommendId",null);
        }
        page = memberService.list(page);
        return successMsg(page);
    }
    /**
     * description 创建报单初始化，查询会员等级字典表
     * @author Bright
     * */
    @RequestMapping("/getDictionary/{dicType}")
    @ResponseBody
    public JsonMessage getDictionary(@PathVariable String dicType){
        List<Dictionary> list = commonService.getDictionary(dicType);
        return successMsg(list);
    }

    /**
     * description 插入报单数据
     * @author Bright
     * */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonMessage insert(@RequestBody MoreMember moreMember, HttpSession session){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }
        String result = memberService.insert(moreMember,currentUser);
        return successMsg(result);
    }

    /**
     * description 激活账户
     * @author Bright
     * */
    @RequestMapping("/activation")
    @ResponseBody
    public JsonMessage activation(@RequestBody Member member,HttpSession session){
        Integer it = memberService.activation(member);
        if(it>0) {
            session.setAttribute(Constant.SESSION_CURRENT_USER,member);
            return successMsg();
        }else {
            return failMsg();
        }
    }

    /**
     * description 获取会员详细信息
     * @author Bright
     * */
    @RequestMapping("/getMemberInfo/{id}")
    @ResponseBody
    public JsonMessage getMemberInfo(@PathVariable Integer id){
        Member member = memberService.getMemberInfo(id);
        List<Dictionary> list = commonService.getDictionary("bank_name");
        Map result= new HashMap();
        result.put("member",member);
        result.put("list",list);
        return successMsg(result);
    }
}
