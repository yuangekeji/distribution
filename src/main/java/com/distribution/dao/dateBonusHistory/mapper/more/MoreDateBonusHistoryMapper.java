package com.distribution.dao.dateBonusHistory.mapper.more;

import com.distribution.dao.dateBonusHistory.mapper.DateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;

public interface MoreDateBonusHistoryMapper extends DateBonusHistoryMapper {

    /**
     * description 前一天营业额 * 0.01
     * @author shiqing
     * @param size*/
    DateBonusHistory getTotalSales(int size);
}