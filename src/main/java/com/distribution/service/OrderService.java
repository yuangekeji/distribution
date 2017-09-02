package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.mapper.more.MoreOrderMapper;
import com.distribution.dao.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private MoreOrderMapper moreOrderMapper;

    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreOrderMapper.getOrderListCount(page));
        page.setResult(moreOrderMapper.getOrderList(page));
        return page;
    }

    /**
     * description 插入订单
     * @author Bright
     * */
    public String insertOrder(Order order){
        int cnt1 = 0;
        int cnt2 = 1;
        //order_master insert
        int orderNo = moreOrderMapper.insertOrder(order);

        if(orderNo > 0){
            order.setOrderNo(orderNo);

            //order_detail_insert
            cnt1 = moreOrderMapper.insertOrderDetail(order);
        }else{
            throw new RuntimeException();
        }


        //报单，复投做奖金红包处理
        if(cnt1 > 0 && ("1".equals(order.getOrderCategory()) || "2".equals(order.getOrderCategory()))){

        }

        return "success";

    }
}
