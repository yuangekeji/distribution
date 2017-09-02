package com.distribution.dao.apply.mapper;

import com.distribution.dao.apply.model.operationApply;
import com.distribution.dao.apply.model.operationApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface operationApplyMapper {
    int countByExample(operationApplyExample example);

    int deleteByExample(operationApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(operationApply record);

    int insertSelective(operationApply record);

    List<operationApply> selectByExample(operationApplyExample example);

    operationApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") operationApply record, @Param("example") operationApplyExample example);

    int updateByExample(@Param("record") operationApply record, @Param("example") operationApplyExample example);

    int updateByPrimaryKeySelective(operationApply record);

    int updateByPrimaryKey(operationApply record);
}