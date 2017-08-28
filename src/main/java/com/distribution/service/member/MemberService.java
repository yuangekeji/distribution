package com.distribution.service.member;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MoreMemberMapper moreMemberMapper;
    @Autowired
    private MoreAdminMapper moreAdminMapper;

    /**
     * description 会员列表查询
     * @author Bright
     * */
    public Page list(Page page){
        if(null!=page.getParameterMap().get("startTime"))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime"))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");
        page.setTotalCount(moreMemberMapper.getMemberCount(page));
        page.setResult(moreMemberMapper.list(page));
        return page;
    }

    /**
     * description 插入报单数据
     * @author Bright
     * */
    public String insert(MoreMember moreMember, Member currentUser){
        Member recommendMember = moreMemberMapper.getMemberByPhone(moreMember.getRecommendPhone());
        if(null==recommendMember){//查询推荐者，没有则报错
            return "NO_RECOMMENDER";
        } else {
            Member historyMember = moreMemberMapper.getMemberByPhone(moreMember.getMemberPhone());
            if(null!=historyMember){//查询手机号是否被注册，是则报错
                return "PHONE_EXISTENCE";
            } else {
                Member noteMember = moreMemberMapper.getMemberByPhone(moreMember.getNotePhone());
                if(null==noteMember){//查询节点是否存在，否则报错
                    return "NO_NODE_MEMBER";
                } else {
                    //TODO
                    if(false){//判断节点是否还可以放一代，否则报错
                        return "NOTE_FULL";
                    }else {
                        Member member = new Member();
                        BeanUtils.copyProperties(moreMember,member);
                        member.setLoginPassword(CryptoUtil.md5ByHex(member.getLoginPassword()));
                        member.setStatus("N");
                        member.setRoleId(1);
                        member.setMemberPost("post_level1");
                        member.setCreateId(currentUser.getId());
                        member.setCreateTime(new Date());
                        member.setUpdateId(currentUser.getId());
                        member.setUpdateTime(new Date());
                        member.setMoneyStatus("N");
                        member.setRecommendName(recommendMember.getMemberName());
                        member.setNodeName(noteMember.getMemberName());
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
                        memberMapper.insert(member);
                        return "SUCCESS";
                    }
                }
            }
        }
    }

    /**
     * description 登录
     * @author Bright
     * */
    public JsonMessage login(Map param, String remember, HttpSession session){
        if(null!=remember){
            List<Admin> list = moreAdminMapper.login(param);
            if(!list.isEmpty()){
                session.setAttribute(Constant.SESSION_CURRENT_USER,list.get(0));
                return new JsonMessage(true,"success",null);
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }else{
            List<Member> list = moreMemberMapper.login(param);
            if(!list.isEmpty()){
                session.setAttribute(Constant.SESSION_CURRENT_USER,list.get(0));
                return new JsonMessage(true,"success",null);
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }
    }

    /**
     * description 激活账户
     * @author Bright
     * */
    public Integer activation(Member member){
        member.setQueryPassword(CryptoUtil.md5ByHex(member.getQueryPassword()));
        member.setPayPassword(CryptoUtil.md5ByHex(member.getPayPassword()));
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * 通过手机号查询member add by jingxin
     * @param phone
     * @return
     */
    public Member getMemberByPhone(String phone){
        return moreMemberMapper.getMemberByPhone(phone);
    }
}
