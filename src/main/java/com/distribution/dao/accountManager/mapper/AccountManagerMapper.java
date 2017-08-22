package com.distribution.dao.accountManager.mapper;

import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.accountManager.model.AccountManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountManagerMapper {
    int countByExample(AccountManagerExample example);

    int deleteByExample(AccountManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountManager record);

    int insertSelective(AccountManager record);

    List<AccountManager> selectByExample(AccountManagerExample example);

    AccountManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountManager record, @Param("example") AccountManagerExample example);

    int updateByExample(@Param("record") AccountManager record, @Param("example") AccountManagerExample example);

    int updateByPrimaryKeySelective(AccountManager record);

    int updateByPrimaryKey(AccountManager record);
}