package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * description 运营中心管理
 * @author Bright
 * */
@RequestMapping("/admOperator")
@Controller
public class AdmOperatorController extends BasicController{

    @Autowired
    private AdmOperatorService admOperatorService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
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
    public JsonMessage approval(@RequestBody OperationApply apply, @RequestParam String memberName, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        Integer it = admOperatorService.approval(apply);
        if(it>0){
            //管理员操作记录
            Map map = new HashMap();
            map.put("handleType", Constant.ADMINHANDLETYPE_APPLYSTATUS);
            map.put("handleId", apply.getMemberId());
            if ("pass".equals(apply.getStatus())) {
                map.put("handleComment", "会员名称: " + memberName + ", 操作: 审批通过");
            }else {
                map.put("handleComment", "会员名称: " + memberName + ", 操作: 审批驳回");
            }
            admHandleHistoryService.addAdminHandleHistory(admin, map);
            return successMsg();
        }else {
            return failMsg();
        }
    }
}
