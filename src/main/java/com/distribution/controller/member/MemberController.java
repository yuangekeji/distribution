package com.distribution.controller.member;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
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
        if(null==memberService.insert(member,currentUser)) {
            return successMsg();
        }else{
            return failMsg();
        }
    }
}
