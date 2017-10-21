package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmOrderService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WIYN on 2017/8/27.
 */
@Controller
@RequestMapping("/admOrder")
public class AdmOrderController extends BasicController{
    @Autowired
    private AdmOrderService admOrderService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    /**
     * description 订单列表查询
     * @author WYN
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage orderList(@RequestBody Page page, HttpSession session){
        page = admOrderService.orderList(page);
        return successMsg(page);
    }

    /**
     * description 订单列表下载(待发货)
     * @author WYN
     * */
    @RequestMapping(value = "/excelDownload")
    public void excelDownload(@RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "orderCategory", required = false) String orderCategory,
                              @RequestParam(value = "orderStatus", required = false) String orderStatus,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("orderCategory", orderCategory);
        map.put("orderStatus", orderStatus);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = admOrderService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("待发货列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        Map mapHandle = new HashMap();
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_APPLYORDER);
        mapHandle.put("handleId", "OrderDownload");
        mapHandle.put("handleComment", "操作: 待发货订单下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

    }

    /**
     * description 订单列表下载（已出库）
     * @author WYN
     * */
    @RequestMapping(value = "/excelDownload1")
    public void excelDownload1(@RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "orderCategory", required = false) String orderCategory,
                              @RequestParam(value = "orderStatus", required = false) String orderStatus,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("orderCategory", orderCategory);
        map.put("orderStatus", orderStatus);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = admOrderService.exportData1(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("已出库列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

        //管理员操作记录
        Map mapHandle = new HashMap();
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_APPLYORDER);
        mapHandle.put("handleId", "OrderDownload1");
        mapHandle.put("handleComment", "操作: 已出库订单下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

    }


    /**
     * description 确认发货
     * @author WYN
     * */
    @RequestMapping("/confirmSendOrder")
    @ResponseBody
    public JsonMessage confirmSendOrder(@RequestBody OrderMaster orderMaster, HttpSession session){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        orderMaster.setUpdateId(currentUser.getId());
        orderMaster.setUpdateTime(new Date());

        String result = admOrderService.confirmSendOrder(orderMaster);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_APPLYORDER);
        map.put("handleId", orderMaster.getOrderNo());
        map.put("handleComment", "订单号: " + orderMaster.getOrderNo() + ", 操作: 确认发货");
        admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        return successMsg("result",result);
    }
    /**
     * description 管理员确认收货
     * @author sijeong
     * */
    @RequestMapping("/confirmReceiveOrder")
    @ResponseBody
    public JsonMessage confirmReceiveOrder(@RequestBody OrderMaster orderMaster, HttpSession session){
        Admin currentUser = (Admin) getCurrentUser(session);

        String result = admOrderService.confirmReceiveOrder(orderMaster, currentUser);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_APPLYORDER);
        map.put("handleId", orderMaster.getOrderNo());
        map.put("handleComment", "订单号: " + orderMaster.getOrderNo() + ", 操作: 确认发货");
        admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        return successMsg("result",result);
    }
}
