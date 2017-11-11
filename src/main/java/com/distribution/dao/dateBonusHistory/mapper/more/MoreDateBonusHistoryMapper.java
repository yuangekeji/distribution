package com.distribution.dao.dateBonusHistory.mapper.more;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.distribution.common.utils.Page;
import com.distribution.dao.dateBonusHistory.mapper.DateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;

public interface MoreDateBonusHistoryMapper extends DateBonusHistoryMapper {

    /**
     * description 前一天营业额 * 0.01
     * @author shiqing
     * @param size*/
    DateBonusHistory getTotalSales(int size);
    
    DateBonusHistory selectCurrentDaySalesAndBonus(String date);
    List<DateBonusHistory> listFailureDividendBonus(String date);

    /**
     * 查询失败的条数
     * @return
     */
    Integer selectFailJobCount();

    /**
     * 查询列表
     * @param page
     * @return
     */
    List<DateBonusHistory> selectDateBonusHistoryList(Page page);

    /**
     * 查询列表总是数
     * @param page
     * @return
     */
    Integer selectDateBonusHistoryListCount(Page page);
    
    int updateDateBonusHistorySuccess(Map<String,Object> param);

}