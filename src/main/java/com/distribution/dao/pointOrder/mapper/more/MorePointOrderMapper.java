package com.distribution.dao.pointOrder.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.pointOrder.mapper.PointOrderMapper;
import com.distribution.dao.pointOrder.model.PointOrder;
import com.distribution.dao.pointOrder.model.more.MorePointOrder;

import java.util.List;
import java.util.Map;

public interface MorePointOrderMapper extends PointOrderMapper{

    PointOrder selectByMemberId(Integer id);

    /**
     * description 兑换单总数
     * @author sijeong
     * */
    Integer getPointOrderListCount(Page page);
    /**
     * description 兑换单列表
     * @author sijeong
     * */

    List<MorePointOrder> getPointOrderList(Page page);
    /**
     * description 兑换单列表下载
     * @author sijeong
     * */

    List<MorePointOrder> getExcelPointOrderListAll(Map map);
}