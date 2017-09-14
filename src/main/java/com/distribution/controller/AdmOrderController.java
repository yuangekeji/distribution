package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import com.distribution.service.AdmOrderService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WIYN on 2017/8/27.
 */
@Controller
@RequestMapping("/admOrder")
public class AdmOrderController extends BasicController{
    @Autowired
    private AdmOrderService admOrderService;

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
     * description 订单列表下载
     * @author WYN
     * */
    @RequestMapping(value = "/excelDownload")
    public void excelDownload(@RequestParam(value = "memberId", required = false) Integer memberId,
                              @RequestParam(value = "orderStatus", required = false) String orderStatus,
                              @RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

     /*   Pagination pagination = new Pagination();
        pagination.getParameterMap().put("name", name);
        pagination.getParameterMap().put("phone", phone);
        pagination.getParameterMap().put("goodsType", goodsType);
        pagination.getParameterMap().put("status", status);*/

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        map.put("orderStatus", orderStatus);
        map.put("orderNo", orderNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);


        XSSFWorkbook wb = admOrderService.exportData(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("订单列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();

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
        return successMsg("result",result);
    }
}
