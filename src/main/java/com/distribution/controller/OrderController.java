package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import com.distribution.service.NodeService;
import com.distribution.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

/**
 * Created by WIYN on 2017/8/27.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BasicController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private NodeService nodeService;

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
     * description 创建订单
     * @author WYN
     * */
    @RequestMapping("/insertOrder")
    @ResponseBody
    public JsonMessage insertOrder(@RequestBody MoreOrderMaster moreOrderMaster, HttpSession session, HttpServletRequest request){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }


        String result = orderService.insertOrder(moreOrderMaster);
        return successMsg("result",result);
    }

    /**
     * description 确认收货
     * @author WYN
     * */
    @RequestMapping("/confirmOrder")
    @ResponseBody
    public JsonMessage confirmOrder(@RequestBody OrderMaster orderMaster, HttpSession session){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }

        orderMaster.setUpdateId(currentUser.getId());
        orderMaster.setUpdateTime(new Date());

        String result = orderService.confirmOrder(orderMaster);
        return successMsg("result",result);
    }

    @RequestMapping("/reOrder")
    @ResponseBody
    public JsonMessage reOrder(@RequestBody MoreOrderMaster moreOrderMaster, HttpSession session, HttpServletRequest request) {
        Member currentUser = null;
        if (getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }
        String result = orderService.insertReOrder(moreOrderMaster,currentUser);
        if("success".equals(result)){
        	//处理会员晋升
        	nodeService.processMemberPromotion(currentUser.getNodeId(), currentUser.getId());
        }
        return successMsg("result",result);

    }
}
