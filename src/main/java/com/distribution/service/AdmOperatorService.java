package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.apply.mapper.OperationApplyMapper;
import com.distribution.dao.apply.mapper.more.MoreOperationApplyMapper;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.model.Member;
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
    @Autowired
    private OperationApplyMapper operationApplyMapper;
    @Autowired
    private MemberMapper memberMapper;
    /**
     * description 后台运营中心列表分页查询
     * @author Bright
     * */
    public Page selectList(Page page){
        page.setTotalCount(moreOperationApplyMapper.getOperationCount(page));
        page.setResult(moreOperationApplyMapper.list(page));
        return page;
    }

    /**
     * description 审批
     * @author Bright
     * */
    public Integer approval(OperationApply apply){
        Integer it = operationApplyMapper.updateByPrimaryKeySelective(apply);
        if("pass".equals(apply.getStatus())) {
            Member m = new Member();
            m.setId(apply.getMemberId());
            m.setIsOperator("Y");
            memberMapper.updateByPrimaryKeySelective(m);
        }
        return it;
    }
}
