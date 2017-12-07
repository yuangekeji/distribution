package com.distribution.dao.member.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.common.utils.Page;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.member.model.more.MoreMember;

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

    int updateMemberMoneyStatusY(Integer memberId);
    /**
     * description 插入返回id
     * @author Bright
     * */
    int insert(Member member);
    /**
     * description 查询全国董事会员人数
     * @author shiqing
     * */
    List<Member> getPostLevel6Member();
    /**
     * 批量更新会员爵位级别
     */
    int updateMemberPostLevelBatch(Map<String,Object> map);

    int updateMemberSalesDept(Integer memberId);

    /**
     * 验证登录密码
     * */
    Member checkLoginPwd(MoreMember moreMember);

    /**
     * 验证查询密码
     * */
    Member checkQueryPwd(MoreMember moreMember);

    /**
     * 验证支付密码
     * */
    Member checkPayPwd(MoreMember moreMember);

    /**
     * 修改会员姓名
     * */
    int updateByRecommendId(MoreMember moreMember);

    Integer selectOrderTotalAmtByMemberId(Integer memberId);

    /**
     * 查詢推荐人推荐会员
     * @param page
     * @return
     */
    List<Member> selectRecommendMemberInfo(Page page);
    /**
     * description 查詢推荐人推荐会员有多少条
     * @author sijeong
     * */
    Integer selectRecommendMemberCnt(Page page);

    /**
     * 删除会员根据id
     * @param memberId
     * @return
     */
    int deleteMemberById(Integer memberId);
}