package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.service.AdminRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description 后台报单记录
 * @author Bright
 * */
@RequestMapping("/admRecommend")
@Controller
public class AdmRecommendController extends BasicController{

    @Autowired
    private AdminRecommendService adminRecommendService;

    /**
     * description 后台报单记录列表分页查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = adminRecommendService.selectList(page);
        return successMsg(page);
    }
}
