package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.service.AdmAdvanceService;
import com.distribution.service.AdmHandleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WYN on 2017/09/03.
 */
@Controller
@RequestMapping("/admAdvance")
public class AdmAdvanceController extends BasicController {
    @Autowired
    private AdmAdvanceService admAdvanceService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;

    /**
     * description 查詢提现列表
     * @author WYN
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage advanceList(@RequestBody Page page, HttpSession session){
        page = admAdvanceService.advanceList(page);
        return successMsg(page);
    }

    /**
     * description 确认
     * @author WYN
     * */
    @RequestMapping("/confirmAdvance")
    @ResponseBody
    public JsonMessage confirmAdvance(@RequestBody MoreAdvance moreAdvance, HttpSession session){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        moreAdvance.setApproveDate(new Date());
        moreAdvance.setUpdateId(currentUser.getId());
        moreAdvance.setUpdateTime(new Date());
        String result = admAdvanceService.confirmAdvance(moreAdvance);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_APPLYADVANCE);
        map.put("handleId", moreAdvance.getMemberId());
        if ("2".equals(moreAdvance.getStatues())) {
            map.put("handleComment", "会员名称: " + moreAdvance.getMemberName() + ", 提现金额: " + moreAdvance.getReqAmt() + ", 操作: 批准");
        }else {
            map.put("handleComment", "会员名称: " + moreAdvance.getMemberName() + ", 提现金额: " + moreAdvance.getReqAmt() + ", 操作: 驳回");
        }
        admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        return successMsg("result",result);
    }

}
