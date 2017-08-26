package com.distribution.dao.dividendHistory.mapper;

import com.distribution.dao.dividendHistory.model.dividendHistory;
import com.distribution.dao.dividendHistory.model.dividendHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dividendHistoryMapper {
    int countByExample(dividendHistoryExample example);

    int deleteByExample(dividendHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(dividendHistory record);

    int insertSelective(dividendHistory record);

    List<dividendHistory> selectByExample(dividendHistoryExample example);

    dividendHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") dividendHistory record, @Param("example") dividendHistoryExample example);

    int updateByExample(@Param("record") dividendHistory record, @Param("example") dividendHistoryExample example);

    int updateByPrimaryKeySelective(dividendHistory record);

    int updateByPrimaryKey(dividendHistory record);
}