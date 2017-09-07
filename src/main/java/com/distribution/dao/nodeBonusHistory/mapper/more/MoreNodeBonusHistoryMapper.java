package com.distribution.dao.nodeBonusHistory.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.nodeBonusHistory.mapper.NodeBonusHistoryMapper;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;

public interface MoreNodeBonusHistoryMapper extends NodeBonusHistoryMapper{

    int insertBatch(List<NodeBonusHistory> list);
    double findCurrentDayNodeBonus(String date);
    int updateNodeBonusHistory(Map<String,Object> map);
}