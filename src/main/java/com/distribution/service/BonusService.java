/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusService {

    @Autowired
    private MoreMemberBonusMapper moreMemberBonusMapper;

    /**
     * description 查詢奖金明细列表
     * @author shiqing
     * */
    public Page selectMemberBonusList(Page page){
        page.setTotalCount(moreMemberBonusMapper.selectMemberBonusListCount(page));
        page.setResult( moreMemberBonusMapper.selectMemberBonusList(page));
        return page;
    }
    /**
     * description 查詢获奖明细
     * @author shiqing
     * */
    public Page selectMemberBonusDetail(Page page){
        page.setResult( moreMemberBonusMapper.selectMemberBonusDetail(page));
        return page;
    }
}
