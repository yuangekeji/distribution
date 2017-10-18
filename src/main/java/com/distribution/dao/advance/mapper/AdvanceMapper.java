package com.distribution.dao.advance.mapper;

import com.distribution.dao.advance.model.Advance;
import com.distribution.dao.advance.model.AdvanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvanceMapper {
    int countByExample(AdvanceExample example);


    int deleteByExample(AdvanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advance record);

    int insertSelective(Advance record);

    List<Advance> selectByExample(AdvanceExample example);

    Advance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advance record, @Param("example") AdvanceExample example);

    int updateByExample(@Param("record") Advance record, @Param("example") AdvanceExample example);

    int updateByPrimaryKeySelective(Advance record);

    int updateByPrimaryKey(Advance record);
}