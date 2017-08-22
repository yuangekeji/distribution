package com.distribution.dao.memberLevel.mapper;

import com.distribution.dao.memberLevel.model.MemberLevel;
import com.distribution.dao.memberLevel.model.MemberLevelExample;
import com.distribution.dao.memberLevel.model.MemberLevelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberLevelMapper {
    int countByExample(MemberLevelExample example);

    int deleteByExample(MemberLevelExample example);

    int deleteByPrimaryKey(MemberLevelKey key);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    List<MemberLevel> selectByExample(MemberLevelExample example);

    MemberLevel selectByPrimaryKey(MemberLevelKey key);

    int updateByExampleSelective(@Param("record") MemberLevel record, @Param("example") MemberLevelExample example);

    int updateByExample(@Param("record") MemberLevel record, @Param("example") MemberLevelExample example);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}