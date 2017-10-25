package com.distribution.service;

import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountManager.mapper.AccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.treeMember.mapper.TreeMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jingxin on 2017/10/2.
 */

@Service
public class AdmTreeMemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MoreMemberMapper moreMemberMapper;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private TreeMemberMapper treeMemberMapper;
    @Autowired
    private AccountManagerMapper accountManagerMapper;
    @Autowired
    private MoreMemberNodeMapper moreNodeMapper;

    public Page findTreeMember(Page page){
        page.setTotalCount(treeMemberMapper.findTreeMemberCount(page));
        page.setResult(treeMemberMapper.findTreeMember(page));
        return page;
    }

    /**
     * description 插入报单数据
     * @author lijingxin
     * */
    public String insert(MoreMember moreMember, Admin currentUser){

            Member historyMember = moreMemberMapper.getMemberByPhone(moreMember.getMemberPhone());

            if(null!=historyMember){//查询手机号是否被注册，是则报错
                return "PHONE_EXISTENCE";
            } else {

                //保存节点信息
                MemberNode memberNode = new MemberNode();
                memberNode.setCreateBy(currentUser.getId());
                memberNode.setCreateTime(new Date());
                memberNode.setParentId(0);
               moreNodeMapper.insertBackId(memberNode);

                //保存报单信息
                Member member = new Member();
                BeanUtils.copyProperties(moreMember,member);
                member.setLoginPassword(CryptoUtil.md5ByHex(member.getLoginPassword()));
                member.setStatus("N");
                member.setRoleId(1);
                member.setMemberPost("post_level1");
                member.setCreateId(0);
                member.setCreateTime(new Date());
                member.setUpdateId(0);
                member.setUpdateTime(new Date());
                member.setMoneyStatus("N");
                member.setNodeId(memberNode.getId());
                //多分支 parentId 和 recommedId 为0
                member.setRecommendName("ADMIN");
                member.setParentId(0);
                member.setRecommendId(0);
//
//                if ("member_level1".equals(member.getMemberLevel())) {
//                    member.setOrderAmount(new BigDecimal(600));
//                } else if ("member_level2".equals(member.getMemberLevel())) {
//                    member.setOrderAmount(new BigDecimal(1800));
//                } else if ("member_level3".equals(member.getMemberLevel())) {
//                    member.setOrderAmount(new BigDecimal(3000));
//                } else if ("member_level4".equals(member.getMemberLevel())) {
//                    member.setOrderAmount(new BigDecimal(9000));
//                } else if ("member_level5".equals(member.getMemberLevel())) {
//                    member.setOrderAmount(new BigDecimal(30000));
//                }

                moreMemberMapper.insert(member);

                //创建账户信息
                AccountManager accountManager = new AccountManager();
                accountManager.setMemberId(member.getId());
                accountManager.setTotalBonus(new BigDecimal(0));
                accountManager.setSeedAmt(new BigDecimal(0));
                accountManager.setBonusAmt(new BigDecimal(0));
                accountManager.setAdvanceAmt(new BigDecimal(0));
                accountManager.setCreateId(member.getId());
                accountManager.setCreateTime(new Date());
                accountManager.setUpdateId(member.getId());
                accountManager.setUpdateTime(new Date());
                accountManagerMapper.insert(accountManager);

                return "SUCCESS";
        }
    }
}
