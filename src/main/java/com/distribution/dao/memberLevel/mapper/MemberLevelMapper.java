package com.distribution.dao.memberLevel.mapper;

import com.distribution.dao.memberLevel.model.memberLevel;
import com.distribution.dao.memberLevel.model.memberLevelExample;
import com.distribution.dao.memberLevel.model.memberLevelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface memberLevelMapper {
    int countByExample(memberLevelExample example);

    int deleteByExample(memberLevelExample example);

    int deleteByPrimaryKey(memberLevelKey key);

    int insert(memberLevel record);

    int insertSelective(memberLevel record);

    List<memberLevel> selectByExample(memberLevelExample example);

    memberLevel selectByPrimaryKey(memberLevelKey key);

    int updateByExampleSelective(@Param("record") memberLevel record, @Param("example") memberLevelExample example);

    int updateByExample(@Param("record") memberLevel record, @Param("example") memberLevelExample example);

    int updateByPrimaryKeySelective(memberLevel record);

    int updateByPrimaryKey(memberLevel record);
}