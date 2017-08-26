package com.distribution.dao.accountManager.mapper;

import com.distribution.dao.accountManager.model.accountManager;
import com.distribution.dao.accountManager.model.accountManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface accountManagerMapper {
    int countByExample(accountManagerExample example);

    int deleteByExample(accountManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(accountManager record);

    int insertSelective(accountManager record);

    List<accountManager> selectByExample(accountManagerExample example);

    accountManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") accountManager record, @Param("example") accountManagerExample example);

    int updateByExample(@Param("record") accountManager record, @Param("example") accountManagerExample example);

    int updateByPrimaryKeySelective(accountManager record);

    int updateByPrimaryKey(accountManager record);
}