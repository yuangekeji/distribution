package com.distribution.dao.apply.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.apply.model.OperationApply;

import java.util.List;

public interface MoreOperationApplyMapper {
    /**
     * description 查询满足条件的会员有多少条
     * @author Bright
     * */
    Integer getOperationCount(Page page);
    /**
     * description 分页查询
     * @author Bright
     * */
    List<OperationApply> list(Page page);
}