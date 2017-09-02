package com.distribution.dao.member.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.common.utils.Page;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.model.Member;

public interface MoreMemberMapper extends MemberMapper{
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
    /**
     * description 查询满足条件的会员有多少条
     * @author Bright
     * */
    Integer getMemberCount(Page page);
    /**
     * description 分页查询
     * @author Bright
     * */
    List<Member> list(Page page);

    /**
     * 校验支付密码
     * @param param
     * @return
     */
    Integer findMatchMemberQueryPwd(Map param);
}