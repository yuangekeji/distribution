package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.transfer.model.Transfer;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmTransferService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sijeong on 2017/11/05.
 */
@Controller
@RequestMapping("/admTransfer")
public class AdmTransferController extends BasicController{

    @Autowired
    private AdmTransferService admTransferService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    /**
     * 转账记录查询
     */
    @RequestMapping(value = "/getTransferList")
    @ResponseBody
    public JsonMessage getTransferList(@RequestBody Page page){
        page = admTransferService.transferList(page);
        return successMsg(page);
    }

    /**
     * description 转账撤销
     * @author sijeong
     * */
    @RequestMapping("/CancelTransfer")
    @ResponseBody
    public JsonMessage CancelTransfer(@RequestBody Transfer transfer, HttpSession session) {
        Admin admin = (Admin) getCurrentUser(session);
        String result = admTransferService.CancelTransfer(transfer, admin);
        //管理员操作记录
        if ("success".equals(result)) {
            Map map = new HashMap();
            map.put("handleType", Constant.ADMINHANDLETYPE_TRANSFER);
            map.put("handleId", admin.getId());
            map.put("handleComment", "转账人: " + transfer.getMemberName() + " ,收款人:" + transfer.getReceiveName()
                    +", 转账金额: " + transfer.getTransferAmt() + " ,操作: 转账撤销");
            admHandleHistoryService.addAdminHandleHistory(admin, map);
        }
        return successMsg("result", result);
    }
    /**
     * description 转账列表下载
     * @author sijeong
     * */
    @RequestMapping(value = "/excelDownload")
    public void excelDownload(@RequestParam(value = "memberName", required = false) String memberName,
                              @RequestParam(value = "status", required = false) String status,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "cancelStartTime", required = false) String cancelStartTime,
                              @RequestParam(value = "cancelEndTime", required = false) String cancelEndTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberName", memberName);
        map.put("status", status);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("cancelStartTime", cancelStartTime);
        map.put("cancelEndTime", cancelEndTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = admTransferService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("转账列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        Map mapHandle = new HashMap();
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_TRANSFER);
        mapHandle.put("handleId", "admTransfer");
        mapHandle.put("handleComment", "操作: 转账列表下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

    }
}
