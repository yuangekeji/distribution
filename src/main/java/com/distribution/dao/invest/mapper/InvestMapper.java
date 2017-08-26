package com.distribution.dao.invest.mapper;

import com.distribution.dao.invest.model.invest;
import com.distribution.dao.invest.model.investExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface investMapper {
    int countByExample(investExample example);

    int deleteByExample(investExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(invest record);

    int insertSelective(invest record);

    List<invest> selectByExample(investExample example);

    invest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") invest record, @Param("example") investExample example);

    int updateByExample(@Param("record") invest record, @Param("example") investExample example);

    int updateByPrimaryKeySelective(invest record);

    int updateByPrimaryKey(invest record);
}