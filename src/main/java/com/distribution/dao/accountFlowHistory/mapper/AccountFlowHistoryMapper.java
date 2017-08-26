package com.distribution.dao.accountFlowHistory.mapper;

import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountFlowHistoryMapper {
    int countByExample(AccountFlowHistoryExample example);

    int deleteByExample(AccountFlowHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountFlowHistory record);

    int insertSelective(AccountFlowHistory record);

    List<AccountFlowHistory> selectByExample(AccountFlowHistoryExample example);

    AccountFlowHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountFlowHistory record, @Param("example") AccountFlowHistoryExample example);

    int updateByExample(@Param("record") AccountFlowHistory record, @Param("example") AccountFlowHistoryExample example);

    int updateByPrimaryKeySelective(AccountFlowHistory record);

    int updateByPrimaryKey(AccountFlowHistory record);
}