package com.distribution.dao.adminHandleHistory.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.adminHandleHistory.mapper.AdminHandleHistoryMapper;
import com.distribution.dao.adminHandleHistory.model.AdminHandleHistory;

import java.util.List;

public interface MoreAdminHandleHistoryMapper extends AdminHandleHistoryMapper {
    /**
     * 查詢管理员操作记录列表
     * @param page
     * @return
     */
    List<AdminHandleHistory> getAdmHandleHistoryList(Page page);

    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer getAdmHandleHistoryCount(Page page);
}
