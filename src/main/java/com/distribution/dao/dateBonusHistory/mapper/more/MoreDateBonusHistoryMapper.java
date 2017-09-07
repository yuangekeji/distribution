package com.distribution.dao.dateBonusHistory.mapper.more;

import java.util.Date;

import com.distribution.dao.dateBonusHistory.mapper.DateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;

public interface MoreDateBonusHistoryMapper extends DateBonusHistoryMapper {

    /**
     * description 前一天营业额 * 0.01
     * @author shiqing
     * @param size*/
    DateBonusHistory getTotalSales(int size);
    
    DateBonusHistory selectCurrentDaySalesAndBonus(String date);
}