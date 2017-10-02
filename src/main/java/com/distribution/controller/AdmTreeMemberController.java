package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.service.AdmTreeMemberService;
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
 * Created by jingxin on 2017/10/2.
 */
@RequestMapping("/admTreeMember")
@Controller
public class AdmTreeMemberController extends BasicController {

    @Autowired
    private AdmTreeMemberService admTreeMemberService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MemberService memberService;
    /**
     * description 分支列表查询
     * @author lijingxin
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = admTreeMemberService.findTreeMember(page);
        return successMsg(page);
    }

    /**
     * description 创建报单初始化，查询会员等级字典表
     * @author lijingxin
     * */
    @RequestMapping("/getDictionary/{dicType}")
    @ResponseBody
    public JsonMessage getDictionary(@PathVariable String dicType, HttpSession session){

        List<Dictionary> list = commonService.selectDictionary(dicType);

        Map result = new HashMap();
        result.put("list",list);

        return successMsg(result);
    }


    /**
     * description 插入报单数据
     * @author lijingxin
     * */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonMessage insert(@RequestBody MoreMember moreMember, HttpSession session){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }
        String result = admTreeMemberService.insert(moreMember,currentUser);
        return successMsg(result);
    }

}
