package com.distribution.dao.roleMenu.mapper;

import com.distribution.dao.roleMenu.model.roleMenu;
import com.distribution.dao.roleMenu.model.roleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface roleMenuMapper {
    int countByExample(roleMenuExample example);

    int deleteByExample(roleMenuExample example);

    int insert(roleMenu record);

    int insertSelective(roleMenu record);

    List<roleMenu> selectByExample(roleMenuExample example);

    int updateByExampleSelective(@Param("record") roleMenu record, @Param("example") roleMenuExample example);

    int updateByExample(@Param("record") roleMenu record, @Param("example") roleMenuExample example);
}