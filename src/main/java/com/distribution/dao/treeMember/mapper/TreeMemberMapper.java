package com.distribution.dao.treeMember.mapper;

import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;

import java.util.List;
import java.util.Map;

/**
 * Created by jingxin on 2017/10/2.
 */
public interface TreeMemberMapper {
    /**
     * description 登录
     * @author lijingxin
     * */
    List<Member> findTreeMember(Page page);

    Integer findTreeMemberCount(Page page);
}
