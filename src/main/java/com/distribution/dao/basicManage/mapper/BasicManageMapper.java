package com.distribution.dao.basicManage.mapper;

import com.distribution.dao.basicManage.model.basicManage;
import com.distribution.dao.basicManage.model.basicManageExample;
import com.distribution.dao.basicManage.model.basicManageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface basicManageMapper {
    int countByExample(basicManageExample example);

    int deleteByExample(basicManageExample example);

    int deleteByPrimaryKey(basicManageKey key);

    int insert(basicManage record);

    int insertSelective(basicManage record);

    List<basicManage> selectByExample(basicManageExample example);

    basicManage selectByPrimaryKey(basicManageKey key);

    int updateByExampleSelective(@Param("record") basicManage record, @Param("example") basicManageExample example);

    int updateByExample(@Param("record") basicManage record, @Param("example") basicManageExample example);

    int updateByPrimaryKeySelective(basicManage record);

    int updateByPrimaryKey(basicManage record);
}