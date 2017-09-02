package com.distribution.dao.order.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoreOrderMapper{
    /**
     * description 订单总数
     * @author WYN
     * */
    Integer getOrderListCount(Page page);
    /**
     * description 订单列表
     * @author WYN
     * */
    List<Order> getOrderList(Page page);

    /**
     * description 订单插入
     * @author WYN
     * */
    int insertOrder(Order order);

    /**
     * description 订单详细插入
     * @author WYN
     * */
    int insertOrderDetail(Order order);
}
