package com.distribution.dao.memberChargeApply.mapper;

import com.distribution.dao.memberChargeApply.model.MemberChargeApply;
import com.distribution.dao.memberChargeApply.model.MemberChargeApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberChargeApplyMapper {
    int countByExample(MemberChargeApplyExample example);

    int deleteByExample(MemberChargeApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberChargeApply record);

    int insertSelective(MemberChargeApply record);

    List<MemberChargeApply> selectByExample(MemberChargeApplyExample example);

    MemberChargeApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberChargeApply record, @Param("example") MemberChargeApplyExample example);

    int updateByExample(@Param("record") MemberChargeApply record, @Param("example") MemberChargeApplyExample example);

    int updateByPrimaryKeySelective(MemberChargeApply record);

    int updateByPrimaryKey(MemberChargeApply record);
}