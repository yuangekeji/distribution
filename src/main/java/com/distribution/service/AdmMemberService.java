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
    public Page list(Page page){
        /*if(null!=page.getParameterMap().get("startTime"))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime"))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/
        page.setTotalCount(moreMemberMapper.getMemberCount(page));
        page.setResult(moreMemberMapper.list(page));
        return page;
    }
}
