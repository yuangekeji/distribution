package com.distribution.dao.bonusPoolHistory.mapper;

import com.distribution.dao.bonusPoolHistory.model.BonusPoolHistory;
import com.distribution.dao.bonusPoolHistory.model.BonusPoolHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BonusPoolHistoryMapper {
    int countByExample(BonusPoolHistoryExample example);

    int deleteByExample(BonusPoolHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BonusPoolHistory record);

    int insertSelective(BonusPoolHistory record);

    List<BonusPoolHistory> selectByExample(BonusPoolHistoryExample example);

    BonusPoolHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BonusPoolHistory record, @Param("example") BonusPoolHistoryExample example);

    int updateByExample(@Param("record") BonusPoolHistory record, @Param("example") BonusPoolHistoryExample example);

    int updateByPrimaryKeySelective(BonusPoolHistory record);

    int updateByPrimaryKey(BonusPoolHistory record);
}