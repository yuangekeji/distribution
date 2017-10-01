package com.distribution.dao.nodeBonusHistory.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.nodeBonusHistory.mapper.NodeBonusHistoryMapper;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;
import com.distribution.dao.nodeBonusHistory.model.more.MoreNodeBonusHistory;

public interface MoreNodeBonusHistoryMapper extends NodeBonusHistoryMapper{

    int insertBatch(List<NodeBonusHistory> list);
    /**
     * 
     * 当日需要发放的见点奖总数,
     * 所有奖金生成日期小于等于今天未发放的数据。
     * @author su
     * @date 2017年9月8日 下午2:21:40
     * @param date
     * @return
     */
    double findCurrentDayNodeBonus(String date);
    int updateNodeBonusHistory(Map<String,Object> map);
    List<NodeBonusHistory> listAllYesterdayNodeBonusHistory(String yesterday);
    int updateNodeBonusHistoryStatusEnd(Map<String,Object> map);
    List<MoreNodeBonusHistory> listCurrentDayNodeBonus(String date);
}