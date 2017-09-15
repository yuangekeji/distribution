package com.distribution.dao.order.mapper.more;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.model.more.MoreOrderMaster;

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

    List<MoreOrderMaster> getOrderList(Page page);
    List<MoreOrderMaster> getExcelOrderList(Map map);
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

    /**
     * description 确认收货
     * @author WYN
     * */
    int confirmOrder(MoreOrderMaster moreOrderMaster);
    
    /**
     * Description 查询当日的所有销售额
     * @author su
     */
    double findCurrentDayOrderSales(String date);
}
