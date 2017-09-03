package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.order.model.more.MoreOrder;
import com.distribution.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

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
    @RequestMapping("/insertOrder")
    @ResponseBody
    public JsonMessage insertOrder(@RequestBody MoreOrder moreOrder, HttpSession session, HttpServletRequest request){
        Member currentUser = null;
        if(getCurrentUser(session) instanceof Member) {
            currentUser = (Member) getCurrentUser(session);
        }

        /*test
        moreOrder.setOrderCategory("1");
        moreOrder.setOrderAmt(new BigDecimal(600));
        moreOrder.setOrderQty(1);
        moreOrder.setActAmt(new BigDecimal(600));
        moreOrder.setBonusAmt(new BigDecimal(0));
        moreOrder.setSeedAmt(new BigDecimal(0));
        moreOrder.setGoodsCd(1);
        moreOrder.setMemberId(currentUser.getId());
        moreOrder.setMemberLevel(currentUser.getMemberLevel());
        moreOrder.setExpressFee(new BigDecimal(10));
        moreOrder.setExpressAddress("辽宁省大连市");*/



        String result = orderService.insertOrder(moreOrder,currentUser);
        return successMsg("result",result);
    }

}
