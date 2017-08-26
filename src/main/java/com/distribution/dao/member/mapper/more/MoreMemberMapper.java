package com.distribution.dao.member.mapper.more;

import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.MemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoreMemberMapper {
    /**
     * description 登录
     * @author Bright
     * */
    List<Member> login(Map param);
}