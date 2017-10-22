package com.distribution.controller;


import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmMemberService;
import com.distribution.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * description 后台会员管理
 * @author Bright
 * */
@Controller
@RequestMapping("/admMember")
public class AdmMemberController extends BasicController{

    @Autowired
    private AdmMemberService admMemberService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;

    /**
     * description 页面初始化，查询下俩列表数据
     * @author Bright
     * */
    @RequestMapping("/init")
    @ResponseBody
    public JsonMessage init(){
        List<Dictionary> post = commonService.selectDictionary("post_level");
        List<Dictionary> level = commonService.selectDictionary("member_level");
        Map result = new HashMap();
        result.put("post",post);
        result.put("level",level);
        return successMsg(result);
    }

    /**
     * description 后台会员列表分页查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = admMemberService.selectList(page);
        return successMsg(page);
    }

    /**
     * description 充值
     * @author Bright
     * */
    @RequestMapping("/addAccount")
    @ResponseBody
    public JsonMessage addAccount(@RequestBody MoreMemberChargeApply moreMemberChargeApply, @RequestParam String memberName, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        admMemberService.addAccount(moreMemberChargeApply,admin);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_ADDACCOUNT);
        map.put("handleId", moreMemberChargeApply.getMemberId());
        map.put("handleComment", "会员名称: " + memberName + ", 充值金额: " + moreMemberChargeApply.getChargeAmt());
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg();
    }

    /**
     * 会员密码初始化
     * @author sijeong
     * */
    @RequestMapping("/initMemberPassword")
    @ResponseBody
    public JsonMessage initMemberPassword(@RequestBody Member member, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);

        admMemberService.initMemberPassword(member, admin);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_ADDACCOUNT);
        map.put("handleId", member.getId());
        map.put("handleComment", "会员名称: " + member.getMemberName() + ", 操作: 密码初始化");
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg();
    }
}
