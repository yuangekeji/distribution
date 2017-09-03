package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.apply.mapper.more.MoreOperationApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description 运营中心管理
 * @author Bright
 * */
@Service
public class AdmOperatorService {

    @Autowired
    private MoreOperationApplyMapper moreOperationApplyMapper;
    /**
     * description 后台运营中心列表分页查询
     * @author Bright
     * */
    public Page selectList(Page page){
        page.setTotalCount(moreOperationApplyMapper.getOperationCount(page));
        page.setResult(moreOperationApplyMapper.list(page));
        return page;
    }
}
