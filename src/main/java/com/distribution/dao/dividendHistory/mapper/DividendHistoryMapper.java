package com.distribution.dao.dividendHistory.mapper;

import com.distribution.dao.dividendHistory.model.DividendHistory;
import com.distribution.dao.dividendHistory.model.DividendHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DividendHistoryMapper {
    int countByExample(DividendHistoryExample example);

    int deleteByExample(DividendHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DividendHistory record);

    int insertSelective(DividendHistory record);

    List<DividendHistory> selectByExample(DividendHistoryExample example);

    DividendHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DividendHistory record, @Param("example") DividendHistoryExample example);

    int updateByExample(@Param("record") DividendHistory record, @Param("example") DividendHistoryExample example);

    int updateByPrimaryKeySelective(DividendHistory record);

    int updateByPrimaryKey(DividendHistory record);
}