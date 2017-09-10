package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.service.CommonService;
import com.distribution.service.MemberService;
import com.distribution.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    @Autowired
    private NodeService nodeService;

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
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());
        if(null!=page.getParameterMap().get("myRecord") && (Boolean) page.getParameterMap().get("myRecord")){
            page.getParameterMap().put("recommendId",m.getId());
        }else{
            page.getParameterMap().put("recommendId",null);
        }
        page = memberService.findList(page);
        return successMsg(page);
    }
    /**
     * description 创建报单初始化，查询会员等级字典表
     * @author Bright
     * */
    @RequestMapping("/getDictionary/{dicType}")
    @ResponseBody
    public JsonMessage getDictionary(@PathVariable String dicType,HttpSession session){
        Member member = (Member) getCurrentUser(session);
        List<Dictionary> list = commonService.selectDictionary(dicType);
        MoreMember moreMember = memberService.findAccountManageByMemberId(member.getId());
        Map result = new HashMap();
        result.put("list",list);
        result.put("moreMember",moreMember);
        return successMsg(result);
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
        Integer it = memberService.updateActivation(member);
        if(it>0) {
        	MoreMember m = memberService.selectMemberInfo(member.getId());
        	//处理会员晋升 当前节点的所有上级
    		nodeService.processMemberPromotion(m.getNodeId(),member.getId());
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
        MoreMember moreMember = memberService.selectMemberInfo(id);
        List<Dictionary> list = commonService.selectDictionary("bank_name");
        Integer it = memberService.getByMemberId(id);
        Map map = nodeService.getSubNodeNumberAndSales(moreMember.getNodeId());
        Integer ln = !"null".equals(map.get("leftNum"))?Integer.valueOf(map.get("leftNum").toString()):0;
        BigDecimal ls = !"null".equals(map.get("leftToalSales"))?new BigDecimal(map.get("leftToalSales").toString()):new BigDecimal(0);
        Integer rn = !"null".equals(map.get("rightNum"))?Integer.valueOf(map.get("rightNum").toString()):0;
        BigDecimal rs = !"null".equals(map.get("rightToalSales"))?new BigDecimal(map.get("rightToalSales").toString()):new BigDecimal(0);
        if(ls.compareTo(rs)==1 || ls.compareTo(rs)==0){//ls >= rs
            moreMember.setCityTotalAmount(ls);
            moreMember.setCityTotalPeople(ln);
            moreMember.setCountyTotalAmount(rs);
            moreMember.setCountyTotalPeople(rn);
        }else if(rs.compareTo(ls)==1){//rs > ls
            moreMember.setCityTotalAmount(rs);
            moreMember.setCityTotalPeople(rn);
            moreMember.setCountyTotalAmount(ls);
            moreMember.setCountyTotalPeople(ln);
        }

        Map result= new HashMap();
        result.put("member",moreMember);
        result.put("list",list);
        result.put("it",it);
        return successMsg(result);
    }

    /**
     * description 申请成为运营中心
     * @author Bright
     * */
    @RequestMapping("/apply")
    @ResponseBody
    public JsonMessage apply(@RequestBody OperationApply operationApply, HttpSession session){
        Member member = (Member) getCurrentUser(session);
        Integer it = memberService.insertApply(operationApply,member);
        if(it>0)
            return successMsg();
        else
            return failMsg();
    }

    /**
     * description 修改会员信息
     * @author Bright
     * */
    @RequestMapping("/updateMember")
    @ResponseBody
    public JsonMessage updateMember(@RequestBody MoreMember moreMember){
        Integer it = memberService.updateMember(moreMember);
        if(it>0){
            return successMsg();
        }else{
            return failMsg();
        }
    }
}
