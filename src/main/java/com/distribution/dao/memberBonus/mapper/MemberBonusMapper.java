package com.distribution.dao.memberBonus.mapper;

import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberBonus.model.MemberBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberBonusMapper {
    int countByExample(MemberBonusExample example);

    int deleteByExample(MemberBonusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberBonus record);

    int insertSelective(MemberBonus record);

    List<MemberBonus> selectByExample(MemberBonusExample example);

    MemberBonus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberBonus record, @Param("example") MemberBonusExample example);

    int updateByExample(@Param("record") MemberBonus record, @Param("example") MemberBonusExample example);

    int updateByPrimaryKeySelective(MemberBonus record);

    int updateByPrimaryKey(MemberBonus record);
}