package com.distribution.dao.dateBonusHistory.mapper;

import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DateBonusHistoryMapper {
    int countByExample(DateBonusHistoryExample example);

    int deleteByExample(DateBonusHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DateBonusHistory record);

    int insertSelective(DateBonusHistory record);

    List<DateBonusHistory> selectByExample(DateBonusHistoryExample example);

    DateBonusHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DateBonusHistory record, @Param("example") DateBonusHistoryExample example);

    int updateByExample(@Param("record") DateBonusHistory record, @Param("example") DateBonusHistoryExample example);

    int updateByPrimaryKeySelective(DateBonusHistory record);

    int updateByPrimaryKey(DateBonusHistory record);
}