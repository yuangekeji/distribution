package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.BonusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * Created by shiqing on 09/03/2017.
 */
@Controller
@RequestMapping("/adminBonus")
public class AdminBonusController extends BasicController {
    private static final Log loger = LogFactory.getLog(AdminBonusController.class);

    @Autowired
    private BonusService bonusService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    /**
     * 查詢分销记录列表
     */
     @RequestMapping(value = "/list", method = RequestMethod.POST)
     @ResponseBody
     public JsonMessage selectMemberBonusList(@RequestBody Page page){
         page = bonusService.selectMemberBonusList(page);
         return successMsg(page);
     }
    /**
     * 查詢分销记录明细
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage selectMemberBonusDetail(@RequestBody Page page){
        page = bonusService.selectMemberBonusDetail(page);
        return successMsg(page);
    }

    /**
     * description 分销记录详情列表下载
     * @author sijeong
     * */
    @RequestMapping(value = "/excelDownload")
    public void excelDownload(@RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = bonusService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("分销记录详情列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        Map mapHandle = new HashMap();
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_BONUS);
        mapHandle.put("handleId", "Bonus");
        mapHandle.put("handleComment", "操作: 分销记录详情列表");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

    }
}
