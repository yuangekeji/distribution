package com.distribution.dao.bonusCachePool.mapper;

import com.distribution.dao.bonusCachePool.model.BonusCachePool;
import com.distribution.dao.bonusCachePool.model.BonusCachePoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BonusCachePoolMapper {
    int countByExample(BonusCachePoolExample example);

    int deleteByExample(BonusCachePoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BonusCachePool record);

    int insertSelective(BonusCachePool record);

    List<BonusCachePool> selectByExample(BonusCachePoolExample example);

    BonusCachePool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BonusCachePool record, @Param("example") BonusCachePoolExample example);

    int updateByExample(@Param("record") BonusCachePool record, @Param("example") BonusCachePoolExample example);

    int updateByPrimaryKeySelective(BonusCachePool record);

    int updateByPrimaryKey(BonusCachePool record);
}