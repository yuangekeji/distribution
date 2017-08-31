package com.distribution.dao.chinaPresidentBonus.mapper;

import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonus;
import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChinaPresidentBonusMapper {
    int countByExample(ChinaPresidentBonusExample example);

    int deleteByExample(ChinaPresidentBonusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChinaPresidentBonus record);

    int insertSelective(ChinaPresidentBonus record);

    List<ChinaPresidentBonus> selectByExample(ChinaPresidentBonusExample example);

    ChinaPresidentBonus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChinaPresidentBonus record, @Param("example") ChinaPresidentBonusExample example);

    int updateByExample(@Param("record") ChinaPresidentBonus record, @Param("example") ChinaPresidentBonusExample example);

    int updateByPrimaryKeySelective(ChinaPresidentBonus record);

    int updateByPrimaryKey(ChinaPresidentBonus record);
}