package com.distribution.dao.dividend.mapper;

import com.distribution.dao.dividend.model.dividend;
import com.distribution.dao.dividend.model.dividendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dividendMapper {
    int countByExample(dividendExample example);

    int deleteByExample(dividendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(dividend record);

    int insertSelective(dividend record);

    List<dividend> selectByExample(dividendExample example);

    dividend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") dividend record, @Param("example") dividendExample example);

    int updateByExample(@Param("record") dividend record, @Param("example") dividendExample example);

    int updateByPrimaryKeySelective(dividend record);

    int updateByPrimaryKey(dividend record);
}