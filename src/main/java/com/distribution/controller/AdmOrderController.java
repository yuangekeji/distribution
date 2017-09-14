package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import com.distribution.service.AdmOrderService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

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
    @RequestMapping(value = "/excelDownload",method = {RequestMethod.GET,RequestMethod.POST}, produces="application/octet-stream")
    @ResponseBody
    public void excelDownload(@RequestBody Page page, HttpSession session, HttpServletResponse response) throws IOException, InvocationTargetException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + new String("订单列表".getBytes("GBK"), "iso8859-1") + ".xlsx");

     /*   Pagination pagination = new Pagination();
        pagination.getParameterMap().put("name", name);
        pagination.getParameterMap().put("phone", phone);
        pagination.getParameterMap().put("goodsType", goodsType);
        pagination.getParameterMap().put("status", status);*/
        admOrderService.exportData(page,response);
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
