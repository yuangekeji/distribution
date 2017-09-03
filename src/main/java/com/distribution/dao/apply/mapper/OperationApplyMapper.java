package com.distribution.dao.apply.mapper;

import com.distribution.dao.apply.model.OperationApply;
import com.distribution.dao.apply.model.OperationApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationApplyMapper {
    int countByExample(OperationApplyExample example);

    int deleteByExample(OperationApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperationApply record);

    int insertSelective(OperationApply record);

    List<OperationApply> selectByExample(OperationApplyExample example);

    OperationApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperationApply record, @Param("example") OperationApplyExample example);

    int updateByExample(@Param("record") OperationApply record, @Param("example") OperationApplyExample example);

    int updateByPrimaryKeySelective(OperationApply record);

    int updateByPrimaryKey(OperationApply record);
}