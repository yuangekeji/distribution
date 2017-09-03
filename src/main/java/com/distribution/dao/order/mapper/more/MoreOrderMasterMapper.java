package com.distribution.dao.order.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoreOrderMasterMapper {
    /**
     * description 订单总数
     * @author WYN
     * */
    Integer getOrderListCount(Page page);
    /**
     * description 订单列表
     * @author WYN
     * */
    List<OrderMaster> getOrderList(Page page);

    /**
     * description 订单插入
     * @author WYN
     * */
    int insertOrder(MoreOrderMaster moreOrderMaster);

    /**
     * description 订单详细插入
     * @author WYN
     * */
    int insertOrderDetail(MoreOrderMaster moreOrderMaster);

    /**
     * description 查询订单总金额
     * @author Bright
     * */
    Double countOrderAmcountByMemberId(Integer memberId);
}
