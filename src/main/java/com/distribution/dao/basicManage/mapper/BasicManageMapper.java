package com.distribution.dao.basicManage.mapper;

import com.distribution.dao.basicManage.model.BasicManage;
import com.distribution.dao.basicManage.model.BasicManageExample;
import com.distribution.dao.basicManage.model.BasicManageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicManageMapper {
    int countByExample(BasicManageExample example);

    int deleteByExample(BasicManageExample example);

    int deleteByPrimaryKey(BasicManageKey key);

    int insert(BasicManage record);

    int insertSelective(BasicManage record);

    List<BasicManage> selectByExample(BasicManageExample example);

    BasicManage selectByPrimaryKey(BasicManageKey key);

    int updateByExampleSelective(@Param("record") BasicManage record, @Param("example") BasicManageExample example);

    int updateByExample(@Param("record") BasicManage record, @Param("example") BasicManageExample example);

    int updateByPrimaryKeySelective(BasicManage record);

    int updateByPrimaryKey(BasicManage record);
}