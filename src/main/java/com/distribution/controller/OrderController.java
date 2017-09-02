package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.Order;
import com.distribution.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by WIYN on 2017/8/27.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BasicController{
    @Autowired
    private OrderService orderService;

    /**
     * description 订单列表查询
     * @author WYN
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage orderList(@RequestBody Page page, HttpSession session){
        Member m = (Member) getCurrentUser(session);
        page.getParameterMap().put("memberId",m.getId());
        page = orderService.orderList(page);
        return successMsg(page);
    }

    /**
     * description 订单列表查询
     * @author WYN
     * */
    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage insertOrder(Order order, HttpSession session){
        String result = orderService.insertOrder(order);
        return successMsg("result",result);
    }

}
