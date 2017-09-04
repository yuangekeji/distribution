package com.distribution.dao.memberBonus.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.memberBonus.mapper.MemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;
import com.distribution.dao.memberBonus.model.more.MoreMemberBonus;


import java.util.List;

public interface MoreMemberBonusMapper extends MemberBonusMapper {

    /**
     * 查詢分紅包领取明细
     * @param page
     * @return
     */
    List<MemberBonus> getDividendDetails(Page page);

    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer getDividendDetailsCount(Page page);
    /**
     * 查詢奖金明细列表
     * @param page
     * @return
     */
    List<MoreMemberBonus> selectMemberBonusList(Page page);
    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer selectMemberBonusListCount(Page page);
    /**
     * 查詢获奖明细
     * @param page
     * @return
     */
    List<MoreMemberBonus> selectMemberBonusDetail(Page page);
}