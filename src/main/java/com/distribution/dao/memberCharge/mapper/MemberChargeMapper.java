package com.distribution.dao.memberCharge.mapper;

import com.distribution.dao.memberCharge.model.MemberCharge;
import com.distribution.dao.memberCharge.model.MemberChargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberChargeMapper {
    int countByExample(MemberChargeExample example);

    int deleteByExample(MemberChargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCharge record);

    int insertSelective(MemberCharge record);

    List<MemberCharge> selectByExample(MemberChargeExample example);

    MemberCharge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCharge record, @Param("example") MemberChargeExample example);

    int updateByExample(@Param("record") MemberCharge record, @Param("example") MemberChargeExample example);

    int updateByPrimaryKeySelective(MemberCharge record);

    int updateByPrimaryKey(MemberCharge record);
}