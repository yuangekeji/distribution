package com.distribution.dao.memberNode.mapper;

import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.MemberNodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberNodeMapper {
    int countByExample(MemberNodeExample example);

    int deleteByExample(MemberNodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberNode record);

    int insertSelective(MemberNode record);

    List<MemberNode> selectByExample(MemberNodeExample example);

    MemberNode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberNode record, @Param("example") MemberNodeExample example);

    int updateByExample(@Param("record") MemberNode record, @Param("example") MemberNodeExample example);

    int updateByPrimaryKeySelective(MemberNode record);

    int updateByPrimaryKey(MemberNode record);
}