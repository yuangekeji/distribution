package com.distribution.dao.pointMaster.mapper;

import com.distribution.dao.pointMaster.model.PointMaster;
import com.distribution.dao.pointMaster.model.PointMasterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointMasterMapper {
    int countByExample(PointMasterExample example);

    int deleteByExample(PointMasterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointMaster record);

    int insertSelective(PointMaster record);

    List<PointMaster> selectByExample(PointMasterExample example);

    PointMaster selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointMaster record, @Param("example") PointMasterExample example);

    int updateByExample(@Param("record") PointMaster record, @Param("example") PointMasterExample example);

    int updateByPrimaryKeySelective(PointMaster record);

    int updateByPrimaryKey(PointMaster record);
}