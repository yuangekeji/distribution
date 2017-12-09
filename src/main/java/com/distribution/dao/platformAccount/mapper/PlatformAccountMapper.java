package com.distribution.dao.platformAccount.mapper;

import com.distribution.dao.platformAccount.model.PlatformAccount;
import com.distribution.dao.platformAccount.model.PlatformAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformAccountMapper {
    long countByExample(PlatformAccountExample example);

    int deleteByExample(PlatformAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformAccount record);

    int insertSelective(PlatformAccount record);

    List<PlatformAccount> selectByExample(PlatformAccountExample example);

    PlatformAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformAccount record, @Param("example") PlatformAccountExample example);

    int updateByExample(@Param("record") PlatformAccount record, @Param("example") PlatformAccountExample example);

    int updateByPrimaryKeySelective(PlatformAccount record);

    int updateByPrimaryKey(PlatformAccount record);
}