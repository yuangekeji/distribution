package com.distribution.dao.pointMaster.mapper.more;

import com.distribution.dao.pointMaster.mapper.PointMasterMapper;
import com.distribution.dao.pointMaster.model.PointMaster;

public interface MorePointMasterMapper extends PointMasterMapper {

    PointMaster selectByMemberId(Integer memberId);

    int updateByMemberId(PointMaster record);
}