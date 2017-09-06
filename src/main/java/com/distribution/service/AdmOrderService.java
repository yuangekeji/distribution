package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmOrderService {
    @Autowired
    private MoreOrderMasterMapper moreOrderMasterMapper;
    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page orderList(Page page){
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreOrderMasterMapper.getOrderListCount(page));
        page.setResult(moreOrderMasterMapper.getOrderList(page));
        return page;
    }
}
