package com.distribution.dao.platformAccountHistory.mapper;

import com.distribution.dao.platformAccountHistory.model.PlatformAccountHistory;
import com.distribution.dao.platformAccountHistory.model.PlatformAccountHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformAccountHistoryMapper {
    long countByExample(PlatformAccountHistoryExample example);

    int deleteByExample(PlatformAccountHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformAccountHistory record);

    int insertSelective(PlatformAccountHistory record);

    List<PlatformAccountHistory> selectByExample(PlatformAccountHistoryExample example);

    PlatformAccountHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformAccountHistory record, @Param("example") PlatformAccountHistoryExample example);

    int updateByExample(@Param("record") PlatformAccountHistory record, @Param("example") PlatformAccountHistoryExample example);

    int updateByPrimaryKeySelective(PlatformAccountHistory record);

    int updateByPrimaryKey(PlatformAccountHistory record);
}