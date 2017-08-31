package com.distribution.dao.bonusPool.mapper;

import com.distribution.dao.bonusPool.model.BonusPool;
import com.distribution.dao.bonusPool.model.BonusPoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BonusPoolMapper {
    int countByExample(BonusPoolExample example);

    int deleteByExample(BonusPoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BonusPool record);

    int insertSelective(BonusPool record);

    List<BonusPool> selectByExample(BonusPoolExample example);

    BonusPool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BonusPool record, @Param("example") BonusPoolExample example);

    int updateByExample(@Param("record") BonusPool record, @Param("example") BonusPoolExample example);

    int updateByPrimaryKeySelective(BonusPool record);

    int updateByPrimaryKey(BonusPool record);
}