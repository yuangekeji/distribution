package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.pointMaster.model.PointMaster;
import com.distribution.dao.pointOrder.model.PointOrder;
import com.distribution.service.OrderService;
import com.distribution.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by sijeong on 2017/12/17.
 */
@Controller
@RequestMapping("/point")
public class PointController extends BasicController{
    @Autowired
    private PointService pointService;
    @Autowired
    private OrderService orderService;
    /**
     * description 积分兑换单列表查询
     * @author WYN
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage pointOrderList(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());
        page = pointService.pointOrderList(page);
        return successMsg(page);
    }

    /**
     * description 确认收货
     * @author WYN
     * */
    @RequestMapping("/confirmOrder")
    @ResponseBody
    public JsonMessage confirmOrder(@RequestBody PointOrder pointOrder, HttpSession session){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }

        pointOrder.setUpdateId(currentUser.getId());
        pointOrder.setUpdateTime(new Date());

        String result = pointService.confirmOrder(pointOrder);
        return successMsg("result",result);
    }
}
