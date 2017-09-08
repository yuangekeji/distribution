package com.distribution.dao.chinaPresidentBonus.mapper.more;

import com.distribution.dao.chinaPresidentBonus.model.ChinaPresidentBonus;
import com.distribution.dao.chinaPresidentBonus.model.more.MoreChinaPresidentBonus;

import java.util.List;

public interface MoreChinaPresidentBonusMapper {

    List<MoreChinaPresidentBonus> selectBonusAmoutByMonth();

    int updateBalanceTime(ChinaPresidentBonus chinaPresidentBonus);
}