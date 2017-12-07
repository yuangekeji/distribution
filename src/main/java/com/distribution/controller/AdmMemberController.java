package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    @Autowired
    private MemberService memberService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private DividendService dividendService;
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
    /**
     * description 获取会员详细信息
     * @author sijeong
     * */
    @RequestMapping("/getMemberInfo/{memberId}")
    @ResponseBody
    public JsonMessage getMemberInfo(@PathVariable Integer memberId){
        MoreMember moreMember = memberService.selectMemberInfo(memberId);
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
        return successMsg(result);
    }

    @RequestMapping("/getMemberDividendCount/{memberId}")
    @ResponseBody
    public JsonMessage getMemberDividendCount(@PathVariable Integer memberId){
        Map result = new HashMap();
        result= dividendService.memberDividendCount(memberId);
        return successMsg(result);
    }

    /**
     * description 获取会员详细信息
     * @author sijeong
     * */
    @RequestMapping("/getBankName/{memberId}")
    @ResponseBody
    public JsonMessage getBankName(@PathVariable Integer memberId){
        List<Dictionary> list = commonService.selectDictionary("bank_name");

        Map result= new HashMap();
        result.put("list",list);
        return successMsg(result);
    }
    /**
     * description 修改会员姓名
     * @author sijeong
     * */
    @RequestMapping("/updateMember")
    @ResponseBody
    public JsonMessage updateMember(@RequestBody MoreMember moreMember,HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        String result = admMemberService.updateMember(moreMember, admin);

        return successMsg("result",result);
    }
    /**
     * 会员禁用功能操作
     * @author sijeong
     * */
    @RequestMapping("/deleteMember")
    @ResponseBody
    public JsonMessage deleteMember(@RequestBody Member member, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);

        admMemberService.deleteMember(member, admin);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_ADDACCOUNT);
        map.put("handleId", member.getId());
        map.put("handleComment", "会员名称: " + member.getMemberName() + ", 操作: 会员禁用");
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg();
    }
    /**
     * 查詢推荐人推荐会员
     */
    @RequestMapping(value = "/recommendMemberInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage recommendMemberInfo(@RequestBody Page page){
        page = admMemberService.selectRecommendMemberInfo(page);
        return successMsg(page);
    }

    /**
     * 校验是否有孩子节点，如果有不可以删除。
     * @param
     * @return
     */
    @RequestMapping(value = "/checkMemberChild", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage checkMemberChild(Integer memberId){
        return successMsg(admMemberService.checkMemberChild(memberId));
    }

    @RequestMapping(value = "/deleteMemberNode", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage deleteMemberNode(Integer memberId){
        return successMsg(admMemberService.deleteMemberNode(memberId));
    }

}
