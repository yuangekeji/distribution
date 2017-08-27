package com.distribution.service.order;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.mapper.OrderMapper;
import com.distribution.dao.order.mapper.more.MoreOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MoreOrderMapper moreOrderMapper;

    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page list(Page page){
        if(null!=page.getParameterMap().get("startTime"))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime"))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");
        page.setTotalCount(moreOrderMapper.getOrderCount(page));
        page.setResult(moreOrderMapper.list(page));
        return page;
    }

}
