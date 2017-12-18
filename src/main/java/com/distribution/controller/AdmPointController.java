package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.pointOrder.model.PointOrder;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmOrderService;
import com.distribution.service.AdmPointService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sijeong on 2017/12/17.
 */
@Controller
@RequestMapping("/admPoint")
public class AdmPointController extends BasicController{
    @Autowired
    private AdmOrderService admOrderService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    @Autowired
    private AdmPointService admPointService;
    /**
     * description 兑换单列表查询
     * @author sijeong
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage pointOrderList(@RequestBody Page page, HttpSession session){
        page = admPointService.pointOrderList(page);
        return successMsg(page);
    }

    /**
     * description 兑换单列表下载(全部)
     * @author sijeong
     * */
    @RequestMapping(value = "/excelDownloadAll")
    public void excelDownloadAll(@RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "orderStatus", required = false) String orderStatus,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              HttpSession session,
                              HttpServletResponse response) throws IOException, InvocationTargetException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("orderStatus", orderStatus);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        Admin admin = (Admin) getCurrentUser(session);
        XSSFWorkbook wb = admPointService.exportDataAll(map,response);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode("积分兑换单列表", "UTF-8") + ".xlsx");
        OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        wb.write(outStream); // 输出数据
        outStream.flush();
        outStream.close();
    }

    /**
     * description 确认发货
     * @author sijeong
     * */
    @RequestMapping("/confirmSendOrder")
    @ResponseBody
    public JsonMessage confirmSendOrder(@RequestBody PointOrder pointOrder, HttpSession session){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        pointOrder.setUpdateId(currentUser.getId());
        pointOrder.setUpdateTime(new Date());

        String result = admPointService.confirmSendOrder(pointOrder);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_APPLYPOINT);
        map.put("handleId", pointOrder.getOrderNo());
        map.put("handleComment", "兑换单号: " + pointOrder.getOrderNo() + ", 操作: 确认发货");
        admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        return successMsg("result",result);
    }
    /**
     * description 管理员确认收货
     * @author sijeong
     * */
    @RequestMapping("/confirmReceiveOrder")
    @ResponseBody
    public JsonMessage confirmReceiveOrder(@RequestBody PointOrder pointOrder, HttpSession session){
        Admin currentUser = (Admin) getCurrentUser(session);

        String result = admPointService.confirmReceiveOrder(pointOrder, currentUser);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_APPLYPOINT);
        map.put("handleId", pointOrder.getOrderNo());
        map.put("handleComment", "兑换单号: " + pointOrder.getOrderNo() + ", 操作: 确认发货");
        admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        return successMsg("result",result);
    }
}
