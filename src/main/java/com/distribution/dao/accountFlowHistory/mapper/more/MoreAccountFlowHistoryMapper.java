package com.distribution.dao.accountFlowHistory.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.order.model.more.MoreOrderMaster;

import java.util.List;

public interface MoreAccountFlowHistoryMapper extends AccountFlowHistoryMapper{
    /**
     * description 明细总数
     * @author lijingxin
     * */
    Integer getAccountHistoryListCount(Page page);
    /**
     * description 账户明细列表
     * @author lijingxin
     * */

    List<MoreOrderMaster> getAccountHistoryList(Page page);
}