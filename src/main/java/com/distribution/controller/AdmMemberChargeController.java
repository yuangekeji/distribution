package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmMemberChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sijeong on 2017/10/15.
 */
@Controller
@RequestMapping("/admMemberCharge")
public class AdmMemberChargeController extends BasicController {
    @Autowired
    private AdmMemberChargeService admMemberChargeService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    /**
     * 充值申请列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage memberChargeList(@RequestBody Page page, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);

        page = admMemberChargeService.memberChargeList(page);
        return successMsg(page);
    }

    /**
     * description 批准
     * @author sijeong
     * */
    @RequestMapping("/confirmMemberCharge")
    @ResponseBody
    public JsonMessage confirmMemberCharge(@RequestBody MoreMemberChargeApply moreMemberChargeApply, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);

        String result = admMemberChargeService.confirmMemberCharge(moreMemberChargeApply, admin);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_MEMBERCHARGE);
        map.put("handleId", moreMemberChargeApply.getMemberId());
        if ("1".equals(moreMemberChargeApply.getStatus())) {
            map.put("handleComment", "申请人: " + moreMemberChargeApply.getMemberName() + ", 操作: 批准");
        }
        if ("2".equals(moreMemberChargeApply.getStatus())){
            map.put("handleComment", "申请人: " + moreMemberChargeApply.getMemberName() + ", 操作: 驳回");
        }
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg("result",result);
    }
    /**
     * description 充值
     * @author sijeong
     * */
    @RequestMapping("/AddMemberCharge")
    @ResponseBody
    public JsonMessage AddMemberCharge(@RequestBody MoreMemberChargeApply moreMemberChargeApply, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        String result = admMemberChargeService.AddMemberCharge(moreMemberChargeApply,admin);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_MEMBERCHARGE);
        map.put("handleId", moreMemberChargeApply.getMemberId());
        map.put("handleComment", "申请人: " + moreMemberChargeApply.getMemberName() + ", 充值金额: " + moreMemberChargeApply.getChargeAmt());
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg("result",result);
    }
}
