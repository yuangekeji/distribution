package com.distribution.service;


import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.dao.memberChargeApply.mapper.more.MoreMemberChargeApplyMapper;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import com.distribution.dao.memberNode.mapper.more.MoreMemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description 后台会员管理
 * @author Bright
 * */
@Service
public class AdmMemberService {

    @Autowired
    private MoreMemberMapper moreMemberMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;
    @Autowired
    private MoreMemberMapper memberMapper;
    @Autowired
    private MoreMemberChargeApplyMapper moreMemberChargeApplyMapper;
    @Autowired
    private MoreMemberNodeMapper moreMemberNodeMapper;
    /**
     * description 后台会员列表分页查询
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

    /**
     * description 充值
     * @author Bright
     * */
    public Integer addAccount(MoreMemberChargeApply moreMemberChargeApply, Admin admin){


        AccountManager am = moreAccountManagerMapper.selectByMemberId(moreMemberChargeApply.getMemberId());

        Member receivedMember = memberMapper.selectByPrimaryKey(moreMemberChargeApply.getMemberId());
        AccountFlowHistory af = new AccountFlowHistory();

        am.setTotalBonus(am.getTotalBonus().add(moreMemberChargeApply.getChargeAmt()));
        //充值前余额
        af.setOldTotalBonusAmt(am.getBonusAmt());
        am.setBonusAmt(am.getBonusAmt().add(moreMemberChargeApply.getChargeAmt()));
        //充值后余额
        af.setNewTotalBonusAmt(am.getBonusAmt());
        am.setUpdateId(admin.getId());
        am.setUpdateTime(new Date());
        Integer am1 = moreAccountManagerMapper.updateByMemberId(am);

        Date date  = new Date();
        MoreMemberChargeApply mmca = new MoreMemberChargeApply();
        mmca.setMemberId(moreMemberChargeApply.getMemberId());
        mmca.setStatus("3");
        mmca.setChargeRequestTime(date);
        mmca.setPayMoneyType("管理员充值");
        mmca.setPayMoneyTime(date);
        mmca.setChargeAmt(moreMemberChargeApply.getChargeAmt());
        mmca.setChargeMoneyType("1");
        mmca.setChargeApplyTime(date);
        mmca.setChargeTime(date);
        mmca.setApplyInfo("管理员充值");
        mmca.setCreateTime(date);
        mmca.setCreateId(admin.getId());
        mmca.setUpdateId(admin.getId());
        mmca.setUpdateTime(date);
        Integer me1 = moreMemberChargeApplyMapper.insert(mmca);

        af.setMemberId(moreMemberChargeApply.getMemberId());
        af.setCreateTime(new Date());
        af.setCreateId(admin.getId());
        af.setType("2");//转入
        af.setTotalAmt(moreMemberChargeApply.getChargeAmt());
        af.setBonusAmt(moreMemberChargeApply.getChargeAmt());
        af.setSeedAmt(new BigDecimal(0));
        af.setFlowType(Constant.MEMBERCHARGE);//管理员充值
        af.setNewTotalSeedAmt(am.getSeedAmt());
        af.setOldTotalSeedAmt(am.getSeedAmt());

        Integer af1 = accountFlowHistoryMapper.insert(af);

        //该逻辑在管理员充值和转账时都做判断
        //判断如果会员状态未激活，并且账户的余额和订单金额相同，更新会员款状态
        if("N".equals(receivedMember.getStatus()) && "N".equals(receivedMember.getMoneyStatus())){
            //账户余额 大于或者等于 订单金额时
            if(am.getBonusAmt().compareTo(receivedMember.getOrderAmount()) > -1){
                memberMapper.updateMemberMoneyStatusY(receivedMember.getId());
            }
        }

        return am1 + me1 + af1;
    }

    /**
     * 会员密码初始化
     * @author sijeong
     * */

    public Integer initMemberPassword(Member m, Admin admin){

        Member member = new Member();
        member.setId(m.getId());
        member.setLoginPassword(CryptoUtil.md5ByHex("111111"));
        member.setQueryPassword(CryptoUtil.md5ByHex("222222"));
        member.setPayPassword(CryptoUtil.md5ByHex("333333"));
        member.setUpdateId(admin.getId());
        member.setUpdateTime(new Date());
        Integer count = memberMapper.updateByPrimaryKeySelective(member);
        return count;
    }
    /**
     * description 修改会员姓名
     * @author sijeng
     * */
    public String updateMember(MoreMember moreMember, Admin admin){
        Date date = new Date();
        Member member = new Member();
        member.setId(moreMember.getId());
        member.setMemberName(moreMember.getMemberName());
        member.setUpdateId(admin.getId());
        member.setUpdateTime(date);
        int cnt = memberMapper.updateByPrimaryKeySelective(member);
        if (cnt > 0) {
            MoreMember mm = new MoreMember();
            mm.setRecommendId(moreMember.getId());
            mm.setRecommendName(moreMember.getMemberName());
            mm.setUpdateId(admin.getId());
            mm.setUpdateTime(date);
            int cnt2 = memberMapper.updateByRecommendId(mm);
        }else {
            throw new RuntimeException();
        }
        return "success";
    }
    /**
     * 会员禁用功能操作
     * @author sijeong
     * */

    public Integer deleteMember(Member m, Admin admin){

        Member member = new Member();
        member.setId(m.getId());
        member.setDeleteFlag(m.getDeleteFlag());
        member.setUpdateId(admin.getId());
        member.setUpdateTime(new Date());
        Integer count = memberMapper.updateByPrimaryKeySelective(member);
        return count;
    }
    /**
     * description 查詢推荐人推荐会员
     * @author shiqing
     * */
    public Page selectRecommendMemberInfo(Page page){
        page.setTotalCount(moreMemberMapper.selectRecommendMemberCnt(page));
        page.setResult( memberMapper.selectRecommendMemberInfo(page));
        return page;
    }

    /**
     * 校验是否有孩子节点
     * @param memberId
     * @return
     */
    public boolean checkMemberChild(Integer memberId){

        MemberNode mn =  moreMemberNodeMapper.getMemberNodeByMemberId(memberId);

        if(mn.getRightId() == null && mn.getLeftId() == null){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 删除未激活的会员
     * @param memberId
     * @return
     */
    public boolean deleteMemberNode(Integer memberId){

        Member m = moreMemberMapper.selectByPrimaryKey(memberId);
        MemberNode n = moreMemberNodeMapper.selectByPrimaryKey(m.getParentId());

        Map param = new HashMap<>();
        param.put("id", m.getParentId());

        if(n.getRightId() != null && n.getRightId().equals(m.getNodeId())) {

            param.put("rightId",n.getRightId());

        }else if(n.getLeftId() != null && n.getLeftId().equals(m.getNodeId())){

            param.put("leftId",n.getLeftId());
        }


        //删除在父节点中的位置
        int updateCnt = moreMemberNodeMapper.updateRemoveMemberNode(param);
        //删除节点
        int delNodeCnt =  moreMemberNodeMapper.deleteByPrimaryKey(m.getNodeId());
        //删除账户信息
        int delAccountCnt = moreAccountManagerMapper.deleteAccountManager(memberId);
        //删除用户
        int delMemberCnt =  moreMemberMapper.deleteMemberById(memberId);


        if(updateCnt == 1 && delNodeCnt == 1 && delMemberCnt == 1 && delAccountCnt == 1 ){
            return true;
        }else{
            return false;
        }
    }
}
