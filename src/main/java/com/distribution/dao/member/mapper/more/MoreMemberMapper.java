package com.distribution.dao.member.mapper.more;

import java.util.List;
import java.util.Map;
import com.distribution.dao.member.model.Member;

public interface MoreMemberMapper {
    /**
     * description 登录
     * @author Bright
     * */
    List<Member> login(Map param);
    /**
     * description 根据手机号查询已有会员
     * @author Bright
     * */
    Member getMemberByPhone(String phone);
}