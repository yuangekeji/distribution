package com.distribution.dao.dateBonusHistory.mapper;

import com.distribution.dao.dateBonusHistory.model.dateBonusHistory;
import com.distribution.dao.dateBonusHistory.model.dateBonusHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dateBonusHistoryMapper {
    int countByExample(dateBonusHistoryExample example);

    int deleteByExample(dateBonusHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(dateBonusHistory record);

    int insertSelective(dateBonusHistory record);

    List<dateBonusHistory> selectByExample(dateBonusHistoryExample example);

    dateBonusHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") dateBonusHistory record, @Param("example") dateBonusHistoryExample example);

    int updateByExample(@Param("record") dateBonusHistory record, @Param("example") dateBonusHistoryExample example);

    int updateByPrimaryKeySelective(dateBonusHistory record);

    int updateByPrimaryKey(dateBonusHistory record);
}