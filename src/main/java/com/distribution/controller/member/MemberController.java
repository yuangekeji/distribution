package com.distribution.controller.member;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.intercept.IgnoreLoginCheck;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.service.common.CommonService;
import com.distribution.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

/*    @RequestMapping(value = "/login")
    @IgnoreLoginCheck
    public String init() {
        return "adminLogin/adminLogin";
    }


    @RequestMapping(value = "/getUserFromSession")
    public Member getUser(){

        return new Member();
    }
    */
    /**
     * description 创建报单初始化，查询会员等级字典表
     * @author Bright
     * */
    @RequestMapping("/getDictionary")
    @ResponseBody
    public JsonMessage getDictionary(){
        List<Dictionary> list = commonService.getDictionary("member_level");
        return successMsg(list);
    }

    /**
     * description 插入报单数据
     * @author Bright
     * */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonMessage insert(@RequestBody Member member, HttpSession session){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }
        ;
        if(null==memberService.insert(member,currentUser)) {
            return successMsg();
        }else{
            return failMsg();
        }
    }
}
