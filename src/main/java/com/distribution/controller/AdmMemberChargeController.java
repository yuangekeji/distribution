package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmMemberChargeService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
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
    /**
     * description 充值列表下载
     * @author sijeong
     * */
    @RequestMapping(value = "/excelDownload")
    public void excelDownload(@RequestParam(value = "memberName", required = false) String memberName,
                              @RequestParam(value = "status", required = false) String status,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "chargeStartTime", required = false) String chargeStartTime,
                              @RequestParam(value = "chargeEndTime", required = false) String chargeEndTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberName", memberName);
        map.put("status", "3");
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("chargeStartTime", chargeStartTime);
        map.put("chargeEndTime", chargeEndTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = admMemberChargeService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("已充值会员列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        Map mapHandle = new HashMap();
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_MEMBERCHARGE);
        mapHandle.put("handleId", "admMemberCharge");
        mapHandle.put("handleComment", "操作: 已充值会员列表下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

    }
}
