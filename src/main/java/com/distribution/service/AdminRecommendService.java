package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRecommendService {

    @Autowired
    private MoreMemberMapper moreMemberMapper;

    /**
     * description 后台报单记录列表分页查询
     * @author Bright
     * */
    public Page selectList(Page page){
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/
        page.setTotalCount(moreMemberMapper.getMemberCount(page));
        page.setResult(moreMemberMapper.list(page));
        return page;
    }
}
