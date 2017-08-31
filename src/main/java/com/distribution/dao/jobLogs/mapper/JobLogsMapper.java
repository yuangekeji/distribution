package com.distribution.dao.jobLogs.mapper;

import com.distribution.dao.jobLogs.model.JobLogs;
import com.distribution.dao.jobLogs.model.JobLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobLogsMapper {
    int countByExample(JobLogsExample example);

    int deleteByExample(JobLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobLogs record);

    int insertSelective(JobLogs record);

    List<JobLogs> selectByExample(JobLogsExample example);

    JobLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobLogs record, @Param("example") JobLogsExample example);

    int updateByExample(@Param("record") JobLogs record, @Param("example") JobLogsExample example);

    int updateByPrimaryKeySelective(JobLogs record);

    int updateByPrimaryKey(JobLogs record);
}