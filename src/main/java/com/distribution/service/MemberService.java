package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.more.MoreAccountFlowHistoryMapper;
import com.distribution.dao.accountManager.mapper.AccountManagerMapper;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.apply.mapper.OperationApplyMapper;
import com.distribution.dao.apply.mapper.more.MoreOperationApplyMapper;
import com.distribution.dao.apply.model.OperationApply;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;
import com.distribution.dao.member.model.more.MoreMemberVO;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.order.mapper.more.MoreOrderMasterMapper;
import com.distribution.dao.order.model.more.MoreOrderMaster;
import com.distribution.dao.pointMaster.model.PointMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Calendar;
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
    @Autowired
    private AccountManagerMapper accountManagerMapper;
    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;
    @Autowired
    private MoreOrderMasterMapper moreOrderMasterMapper;
    @Autowired
    private OperationApplyMapper operationApplyMapper;
    @Autowired
    private MoreOperationApplyMapper moreOperationApplyMapper;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private MoreAccountFlowHistoryMapper moreAccountFlowHistoryMapper;
    @Autowired
    private OrderService orderService;
    /**
     * description 会员列表查询
     * @author Bright
     * */
    public Page findList(Page page){
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/
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
//                Member noteMember = moreMemberMapper.getMemberByPhone(moreMember.getNotePhone());
//                if(null==noteMember){//查询节点是否存在，否则报错
//                    return "NO_NODE_MEMBER";
//                } else {
//                    Integer it = nodeService.findNodeByParentNode(noteMember.getNodeId(),moreMember.getArea());
//                    if(it.intValue()!=0){//判断节点是否还可以放一代，否则报错
//                        if("left".equals(moreMember.getArea()))
//                            return "LEFT_NOTE_FULL";
//                        else
//                            return "RIGHT_NOTE_FULL";
//                    }else {
                        //保存节点信息
                        MemberNode memberNode = new MemberNode();
                        memberNode.setCreateBy(currentUser.getId());
//                        memberNode.setCreateTime(new Date());
                        memberNode.setParentId(recommendMember.getNodeId());
//                        memberNode.setUpdateBy(currentUser.getId());
//                        memberNode.setUpdateTime(new Date());

                        MemberNode _memberNode = nodeService.saveNode(memberNode);

                        //保存报单信息
                        Member member = new Member();
                        BeanUtils.copyProperties(moreMember,member);
                        member.setLoginPassword(CryptoUtil.md5ByHex(member.getLoginPassword()));
                        member.setStatus("N");
                        member.setDeleteFlag("N");
                        member.setRoleId(1);
                        member.setMemberPost("post_level1");
                        member.setCreateId(currentUser.getId());
                        member.setCreateTime(new Date());
                        member.setUpdateId(currentUser.getId());
                        member.setUpdateTime(new Date());
                        member.setMoneyStatus("N");
                        member.setRecommendName(recommendMember.getMemberName());
                        member.setNodeId(_memberNode.getId());
                        member.setParentId(_memberNode.getParentId());
//                        member.setNodeName(noteMember.getMemberName());
//                        if ("member_level1".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(600));
//                        } else if ("member_level2".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(1800));
//                        } else if ("member_level3".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(3000));
//                        } else if ("member_level4".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(9000));
//                        } else if ("member_level5".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(30000));
//                        } else if ("member_level6".equals(member.getMemberLevel())) {
//                            member.setOrderAmount(new BigDecimal(60000));
//                        }
                        if (null == member.getRecommendId())
                            member.setRecommendId(recommendMember.getId());
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
//                    }
//                }
            }
        }
    }
    /**
     * 验证码验证
     *
     * @param session
     * @param code
     */

    private boolean checkCode(HttpSession session, String code) {
        String codeSession = (String) session.getAttribute("code");
        if (codeSession ==null || "".equals(codeSession) || code==null || "".equals(code) ) {
           return false;
        }

        if (codeSession.equalsIgnoreCase(code)) {
            // 验证码通过
            return true;
        }else{
            return false;
        }

    }

    /**
     * description 登录
     * @author Bright
     * */
    public JsonMessage login(Map param, String remember,String code,  HttpSession session){

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < 4) {
            return new JsonMessage(false,"loginTimeCheck",null);
        }

        if(!checkCode(session,code)){
            return new JsonMessage(false,"codeError",null);
        }

        if(null!=remember){
            List<Admin> list = moreAdminMapper.login(param);
            if(!list.isEmpty()){
                Admin admin = list.get(0);
                session.setAttribute(Constant.SESSION_CURRENT_USER,admin);
                session.setAttribute(Constant.SESSION_CURRENT_ROLE,admin.getRoleId());

                return new JsonMessage(true,"success",null);
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }else{
            List<Member> list = moreMemberMapper.login(param);
            if(!list.isEmpty()){
                Member member = list.get(0);
                if(member.getDeleteFlag().equals("Y")) {

                    return new JsonMessage(false,"memberDeleted",null);
                } else {
                    session.setAttribute(Constant.SESSION_CURRENT_USER,member);
                    session.setAttribute(Constant.SESSION_CURRENT_ROLE,member.getRoleId());
                    session.setAttribute(Constant.SESSION_CURRENT_STATUS,member.getStatus());

                    return new JsonMessage(true,"success",null);
                }
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }
    }

    /**
     * description 激活账户
     *
     * 更新状态；
     * 补充用户银卡等身份信息；
     * 如果订单超过3w，就更新是否是工作中心字段；
     * 给推荐人的一代个数中 +1；
     * 创建账户信息；
     * @author Bright
     * */
    public Integer updateActivation(MoreMemberVO _member){
        Member member = _member;
        member.setQueryPassword(CryptoUtil.md5ByHex(member.getQueryPassword()));
        member.setPayPassword(CryptoUtil.md5ByHex(member.getPayPassword()));
//        if(member.getOrderAmount().compareTo(new BigDecimal(30000)) > -1){
//            member.setIsSalesDept("Y");
//        }
        member.setUpdateId(member.getId());
        member.setUpdateTime(new Date());
        Integer it = memberMapper.updateByPrimaryKeySelective(member);
        member = memberMapper.selectByPrimaryKey(member.getId());
        //给推荐人的一代个数中 +1
//         if(member.getRecommendId() != null && member.getRecommendId() > 0 ){
//            Member m = memberMapper.selectByPrimaryKey(member.getRecommendId());
//            m.setFirstAgentCnt(null!=m.getFirstAgentCnt()?(m.getFirstAgentCnt()+1):1);
//            memberMapper.updateByPrimaryKeySelective(m);
//        }

        MoreOrderMaster order = new MoreOrderMaster();
        order.setOrderCategory("1");
        order.setOrderAmt(member.getOrderAmount());
        order.setOrderQty(null!=member.getOrderAmount()?(member.getOrderAmount().intValue()/600):0);
        order.setDiscount(0);
        order.setActAmt(member.getOrderAmount());
        order.setExpressFee(new BigDecimal(0));
        order.setMemberId(member.getId());
        //1 自提 2-邮寄
       /* order.setSendbypostyn(_member.getSendbypostyn());
        order.setReceiveName(_member.getReceiveName());
        order.setRecevivePhone(_member.getRecevivePhone());
        order.setExpressAddress(_member.getReceviveAddress());*/
        order.setMemberLevel(member.getMemberLevel());
        order.setOrderStatues("4");
        order.setCreateId(member.getId());
        order.setCreateTime(new Date());
        order.setUpdateId(member.getId());
        order.setUpdateTime(new Date());
        order.setBonusAmt(member.getOrderAmount());
        orderService.insertOrder(order);

        return it;
    }

    /**
     * 通过手机号查询member add by jingxin
     * @param phone
     * @return
     */
    public Member selectMemberByPhone(String phone){
        return moreMemberMapper.getMemberByPhone(phone);
    }

    /**
     * description 获取会员详细信息
     * @author Bright
     * */
    public MoreMember selectMemberInfo(Integer id){
        MoreMember moreMember = moreAccountManagerMapper.getSeedsAndBondsByMemberId(id);
        Member member = memberMapper.selectByPrimaryKey(id);
        Integer recommendMemberCnt = moreMemberMapper.getRecommendMemberCnt(id);
        moreMember.setRecommendMemberCnt(recommendMemberCnt);
        BeanUtils.copyProperties(member,moreMember);
        Double orderTotalAmount = moreOrderMasterMapper.countOrderAmcountByMemberId(id);
        moreMember.setOrderTotalAmount(new BigDecimal(null!=orderTotalAmount?orderTotalAmount:0));
        return moreMember;
    }

    /**
     * description 申请成为运营中心
     * @author Bright
     * */
    public Integer insertApply(OperationApply operationApply,Member member){
        operationApply.setMemberId(member.getId());
        operationApply.setStatus("wait");
        operationApply.setCreateId(member.getId());
        operationApply.setCreateTime(new Date());
        operationApply.setUpdateId(member.getId());
        operationApply.setUpdateTime(new Date());
        return operationApplyMapper.insert(operationApply);
    }

    /**
     * description 查询到账多少金币
     * @author Bright
     * */
    public MoreMember findAccountManageByMemberId(Integer memberId){
        return moreAccountManagerMapper.getSeedsAndBondsByMemberId(memberId);
    }

    public Integer getByMemberId(Integer memberId){
        List<OperationApply> list = moreOperationApplyMapper.getByMemberId(memberId);
        if(list.isEmpty()){
            return 0;
        }else{
            return list.size();
        }
    }

    /**
     * description 修改会员信息
     * @author Bright
     * */
    public Integer updateMember(MoreMember moreMember){
        Member member = new Member();
        BeanUtils.copyProperties(moreMember,member);
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     * description 修改密码
     * @author Bright
     * */
    public String updatePwd(MoreMember moreMember){
        String pwdFlag = moreMember.getPwdFlag();
        String result = "SUCCESS";
        if("login".equals(pwdFlag)){
            moreMember.setOldLoginPwd(CryptoUtil.md5ByHex(moreMember.getOldLoginPwd()));
            if(null==moreMemberMapper.checkLoginPwd(moreMember)){
                result = "OLD_PWD_ERROR";
            }else{
                Member m = new Member();
                m.setId(moreMember.getId());
                m.setLoginPassword(CryptoUtil.md5ByHex(moreMember.getLoginPassword()));
                memberMapper.updateByPrimaryKeySelective(m);
            }
        }else if("query".equals(pwdFlag)){
            moreMember.setOldQueryPwd(CryptoUtil.md5ByHex(moreMember.getOldQueryPwd()));
            if(null==moreMemberMapper.checkQueryPwd(moreMember)){
                result = "OLD_PWD_ERROR";
            }else{
                Member m = new Member();
                m.setId(moreMember.getId());
                m.setQueryPassword(CryptoUtil.md5ByHex(moreMember.getQueryPassword()));
                memberMapper.updateByPrimaryKeySelective(m);
            }
        }else if("pay".equals(pwdFlag)){
            moreMember.setOldPayPwd(CryptoUtil.md5ByHex(moreMember.getOldPayPwd()));
            if(null==moreMemberMapper.checkPayPwd(moreMember)){
                result = "OLD_PWD_ERROR";
            }else{
                Member m = new Member();
                m.setId(moreMember.getId());
                m.setPayPassword(CryptoUtil.md5ByHex(moreMember.getPayPassword()));
                memberMapper.updateByPrimaryKeySelective(m);
            }
        }
        return result;
    }

    public Integer selectOrderTotalAmtByMemberId(Integer memberId){
        return moreMemberMapper.selectOrderTotalAmtByMemberId(memberId);
    }

    /**
     * 校验查询密码
     * @param moreMember
     * @return
     */
    public boolean searchPwdValidate(MoreMember moreMember){
        Member m =  moreMemberMapper.checkQueryPwd(moreMember);
        if( m != null && m.getId() > 0 ){
           return true;
        }
         return  false;
    }

    /**
     * description 订单列表查询
     * @author WYN
     * */
    public Page accountHistoryList(Page page){

        page.setTotalCount(moreAccountFlowHistoryMapper.getAccountHistoryListCount(page));
        page.setResult(moreAccountFlowHistoryMapper.getAccountHistoryList(page));
        return page;
    }
}
