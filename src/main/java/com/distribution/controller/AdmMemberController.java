package com.distribution.controller;


import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.service.AdmMemberService;
import com.distribution.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * description 页面初始化，查询下俩列表数据
     * @author Bright
     * */
    @RequestMapping("/init")
    @ResponseBody
    public JsonMessage init(){
        List<Dictionary> post = commonService.getDictionary("post_level");
        List<Dictionary> level = commonService.getDictionary("member_level");
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
        page = admMemberService.list(page);
        return successMsg(page);
    }
}
