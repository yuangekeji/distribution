package com.distribution.dao.member.mapper;

import com.distribution.dao.member.model.member;
import com.distribution.dao.member.model.memberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface memberMapper {
    int countByExample(memberExample example);

    int deleteByExample(memberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(member record);

    int insertSelective(member record);

    List<member> selectByExample(memberExample example);

    member selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") member record, @Param("example") memberExample example);

    int updateByExample(@Param("record") member record, @Param("example") memberExample example);

    int updateByPrimaryKeySelective(member record);

    int updateByPrimaryKey(member record);
}