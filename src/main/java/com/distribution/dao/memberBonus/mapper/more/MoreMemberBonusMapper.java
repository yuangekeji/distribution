package com.distribution.dao.memberBonus.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.memberBonus.mapper.MemberBonusMapper;
import com.distribution.dao.memberBonus.model.MemberBonus;


import java.util.List;

public interface MoreMemberBonusMapper extends MemberBonusMapper {

    /**
     * 查詢分紅包领取明细
     * @param page
     * @return
     */
    List<MemberBonus> getDividendDetails(Page page);
}