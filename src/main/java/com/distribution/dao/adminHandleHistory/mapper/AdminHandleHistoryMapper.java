package com.distribution.dao.adminHandleHistory.mapper;

import com.distribution.dao.adminHandleHistory.model.AdminHandleHistory;
import com.distribution.dao.adminHandleHistory.model.AdminHandleHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminHandleHistoryMapper {
    int countByExample(AdminHandleHistoryExample example);

    int deleteByExample(AdminHandleHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminHandleHistory record);

    int insertSelective(AdminHandleHistory record);

    List<AdminHandleHistory> selectByExample(AdminHandleHistoryExample example);

    AdminHandleHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminHandleHistory record, @Param("example") AdminHandleHistoryExample example);

    int updateByExample(@Param("record") AdminHandleHistory record, @Param("example") AdminHandleHistoryExample example);

    int updateByPrimaryKeySelective(AdminHandleHistory record);

    int updateByPrimaryKey(AdminHandleHistory record);
}