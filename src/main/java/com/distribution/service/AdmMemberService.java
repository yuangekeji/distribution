package com.distribution.service;


import com.distribution.common.utils.Page;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description 后台会员管理
 * @author Bright
 * */
@Service
public class AdmMemberService {

    @Autowired
    private MoreMemberMapper moreMemberMapper;

    /**
     * description 后台会员列表分页查询
     * @author Bright
     * */
    public Page selectList(Page page){
        page.setTotalCount(moreMemberMapper.getMemberCount(page));
        page.setResult(moreMemberMapper.list(page));
        return page;
    }
}
