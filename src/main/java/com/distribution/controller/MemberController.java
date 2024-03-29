package com.distribution.controller;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import com.distribution.common.utils.CaptchaUtil;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.dao.member.model.more.MoreMemberVO;
import com.distribution.dao.pointMaster.model.PointMaster;
import com.distribution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    @Autowired
    private DividendService dividendService;
    @Autowired
    private PointService pointService;

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
    public JsonMessage login(String userName, String password, String remember,String code,  HttpSession session){
        Map param = new HashMap();
        param.put("userName",userName);
        param.put("password", CryptoUtil.md5ByHex(password));
        return memberService.login(param,remember,code,session);
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
    public JsonMessage activation(@RequestBody MoreMemberVO member, HttpSession session){
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
    public JsonMessage getBankName(@PathVariable Integer id){
        MoreMember moreMember = memberService.selectMemberInfo(id);
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
        PointMaster pointMaster = pointService.selectByMemberId(id);
        if (pointMaster != null) {
            moreMember.setPointAmt(pointMaster.getPointAmt());
        }else {
            moreMember.setPointAmt(new BigDecimal("0"));
        }
        Map result= new HashMap();
        result.put("member",moreMember);
        return successMsg(result);
    }


    @RequestMapping("/getMemberDividendCount/{memberId}")
    @ResponseBody
    public JsonMessage getMemberDividendCount(@PathVariable Integer memberId){
        Map result= new HashMap();
        result= dividendService.memberDividendCount(memberId);
        return successMsg(result);
    }

    /**
     * description 获取会员详细信息
     * @author Bright
     * */
    @RequestMapping("/getBankName/{id}")
    @ResponseBody
    public JsonMessage getIt(@PathVariable Integer id){
        List<Dictionary> list = commonService.selectDictionary("bank_name");

        Map result= new HashMap();
        result.put("list",list);
        return successMsg(result);
    }

    /**
     * description 获取会员详细信息
     * @author Bright
     * */
    @RequestMapping("/getIt/{id}")
    @ResponseBody
    public JsonMessage getMemberInfo(@PathVariable Integer id){
        Integer it = memberService.getByMemberId(id);

        Map result= new HashMap();
        result.put("it",it);
        return successMsg(result);
    }


    /**
     * description 获取会员详细信息
     * @author Bright
     * */
    @RequestMapping("/getMemberInfoById/{id}")
    @ResponseBody
    public JsonMessage getMemberInfoById(@PathVariable Integer id){
        MoreMember moreMember = memberService.selectMemberInfo(id);
        List<Dictionary> list = commonService.selectDictionary("bank_name");
        double maxPercent = commonService.getMaxPercent(BonusConstant.D08,BonusConstant.CODE_00);
        Map result= new HashMap();
        result.put("member",moreMember);
        result.put("list",list);
        result.put("maxPercent", BigDecimal.valueOf(maxPercent).multiply(new BigDecimal(100)));

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

    @RequestMapping("/getOrderTotalAmt")
    @ResponseBody
    public JsonMessage getOrderTotalAmt(HttpSession session){
        Member member = (Member) getCurrentUser(session);
        Integer orderTotalAmt = memberService.selectOrderTotalAmtByMemberId(member.getId());
        if(orderTotalAmt > 0 )
            return successMsg(orderTotalAmt);
        else
            return failMsg();
    }
    /**
     * description 修改会员信息
     * @author Bright
     * */
    @RequestMapping("/updateMember")
    @ResponseBody
    public JsonMessage updateMember(@RequestBody MoreMember moreMember,HttpSession session){
        Member m = (Member)getCurrentUser(session);
        Integer it = memberService.updateMember(moreMember);
        if(it>0){
            if(null!=moreMember.getLinkmanPhone() && !"".equals(moreMember.getLinkmanPhone())) {
                m.setLinkmanPhone(moreMember.getLinkmanPhone());
            }
            session.setAttribute(Constant.SESSION_CURRENT_USER,m);
            return successMsg();
        }else{
            return failMsg();
        }
    }

    /**
     * description 修改密码
     * @author Bright
     * */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public JsonMessage updatePwd(@RequestBody MoreMember moreMember,HttpSession session){
        Member m = (Member)getCurrentUser(session);
        if(m != null && m.getId() != null){
            moreMember.setId(m.getId());
            String str = memberService.updatePwd(moreMember);
            return successMsg(str);

        }else{
            return failMsg();
        }

    }
    /**
     * description 验证查询密码
     * @author Bright
     * */
    @RequestMapping("/searchPwdValidate")
    @ResponseBody
    public JsonMessage searchPwdValidate(@RequestBody Map param, HttpSession session){
        Member m = (Member)getCurrentUser(session);
        if(m != null && m.getId() != null){

            String searchPwd = (String) param.get("searchPwd");
            MoreMember moreMember = new MoreMember();
            moreMember.setOldQueryPwd(CryptoUtil.md5ByHex(searchPwd));
            moreMember.setId(m.getId());
            Boolean returnFlag = memberService.searchPwdValidate(moreMember);

            if(returnFlag){
                return successMsg(returnFlag);
            }else{
                return failMsg();
            }

        }else{
            return failMsg();
        }

    }

    @RequestMapping("/check")
    @IgnoreLoginCheck
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        CaptchaUtil util = CaptchaUtil.Instance();
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        request.getSession().setAttribute("code", code);
        // 输出打web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }

    @RequestMapping("/accountHistory")
    @ResponseBody
    public JsonMessage accountHistoryList(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());
        page = memberService.accountHistoryList(page);
        return successMsg(page);
    }

    @RequestMapping("/accountHistoryTotal")
    @ResponseBody
    public JsonMessage accountHistoryTotal(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());
        page = memberService.accountHistoryList(page);
        return successMsg(page);
    }

}
