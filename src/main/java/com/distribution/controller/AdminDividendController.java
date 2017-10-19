package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.service.DividendService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijingx on 8/28/2017.
 */
@Controller
@RequestMapping("/adminDividend")
public class AdminDividendController extends BasicController {

    //private static final Log loger = LogFactory.getLog(AdminDividendController.class);

    @Autowired
    private DividendService dividendService;

    /**
     * 查詢分紅包列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage dividendList(@RequestBody Page page, HttpSession session){

        page = dividendService.dividendList(page);
        return successMsg(page);
    }

    /**
     * 查詢分紅包明細Head
     */

    @RequestMapping(value = "/detailsTitleData", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage detailsTitleData(@RequestBody Page page){
        return successMsg(dividendService.dividendList(page));
    }

    /**
     * 查詢分紅包明細
     */

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage dividendDetails(@RequestBody Page page){
        return successMsg(dividendService.dividendDetails(page));
    }

    /**
     * description 红包列表下载
     * @author Bright
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
        XSSFWorkbook wb = dividendService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("红包列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        /*Map mapHandle = new HashMap();
        String orderNoHandle = orderNo;
        if (orderNo.isEmpty() || "".equals(orderNo)) {
            orderNoHandle = "全部";
        }
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_APPLYORDER);
        mapHandle.put("handleId", orderNoHandle);
        mapHandle.put("handleComment", "订单号: " + orderNoHandle + ", 操作: 订单下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);*/

    }

}
