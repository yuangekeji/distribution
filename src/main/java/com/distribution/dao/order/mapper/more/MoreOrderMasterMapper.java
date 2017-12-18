package com.distribution.dao.order.mapper.more;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.model.OrderMaster;
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
    List<MoreOrderMaster> getExcelOrderList1(Map map);
    List<MoreOrderMaster> getExcelOrderListAll(Map map);
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

    /**
     * 查找会员的当日总投资呃 （报单和复投）
     * @param memberId
     * @return
     */
    double selectMaxOrderAmt(Integer memberId);
    
    double selectTotalSalesAmount(String date);
    double selectDayDiscountSalesAmount(String date);
    
    List<OrderMaster> listOrdersByMemberId(Map<String,Object> map);
}
