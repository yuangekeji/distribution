package com.distribution.dao.order.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.model.Order;

import java.util.List;

public interface MoreOrderMapper {
    /**
     * description 订单总数
     * @author WYN
     * */
    Integer getOrderCount(Page page);
    /**
     * description 订单列表
     * @author WYN
     * */
    List<Order> list(Page page);
}
