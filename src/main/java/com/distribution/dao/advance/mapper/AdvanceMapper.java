package com.distribution.dao.advance.mapper;

import com.distribution.dao.advance.model.advance;
import com.distribution.dao.advance.model.advanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface advanceMapper {
    int countByExample(advanceExample example);

    int deleteByExample(advanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(advance record);

    int insertSelective(advance record);

    List<advance> selectByExample(advanceExample example);

    advance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") advance record, @Param("example") advanceExample example);

    int updateByExample(@Param("record") advance record, @Param("example") advanceExample example);

    int updateByPrimaryKeySelective(advance record);

    int updateByPrimaryKey(advance record);
}