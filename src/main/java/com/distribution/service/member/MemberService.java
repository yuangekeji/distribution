package com.distribution.service.member;

import com.distribution.common.utils.CryptoUtil;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MoreMemberMapper moreMemberMapper;

    /**
     * description 插入报单数据
     * @author Bright
     * */
    public Member insert(Member member, Member currentUser){
        Member historyMember = moreMemberMapper.getMemberByPhone(member.getMemberPhone());
        if(null==historyMember) {
            member.setLoginPassword(CryptoUtil.md5ByHex(member.getLoginPassword()));
            member.setStatus("N");
            member.setRoleId(1);
            member.setMemberPost("post_level1");
            member.setCreateId(currentUser.getId());
            member.setCreateTime(new Date());
            member.setUpdateId(currentUser.getId());
            member.setUpdateTime(new Date());
            member.setMoneyStatus("N");
            if ("member_level1".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(600));
            } else if ("member_level2".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(1800));
            } else if ("member_level3".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(3000));
            } else if ("member_level4".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(9000));
            } else if ("member_level5".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(30000));
            } else if ("member_level6".equals(member.getMemberLevel())) {
                member.setOrderAmount(new BigDecimal(60000));
            }
            if (null == member.getRecommendId())
                member.setRecommendId(currentUser.getId());
            return null;
        }else{
            return historyMember;
        }
    }
}
