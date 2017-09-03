package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.service.AdmOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description 运营中心管理
 * @author Bright
 * */
@RequestMapping("/admOperator")
@Controller
public class AdmOperatorController extends BasicController{

    @Autowired
    private AdmOperatorService admOperatorService;

    /**
     * description 后台运营中心列表分页查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = admOperatorService.selectList(page);
        return successMsg(page);
    }


    /**
     * description 审批
     * @author Bright
     * */
    @RequestMapping("/approval")
    @ResponseBody
    public JsonMessage approval(@RequestBody OperationApply apply){
        Integer it = admOperatorService.approval(apply);
        if(it>0){
            return successMsg();
        }else {
            return failMsg();
        }
    }
}
