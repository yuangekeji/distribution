package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.basicManage.mapper.more.MoreBasicManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmBasicSettingService {
    @Autowired
    private MoreBasicManageMapper moreBasicManageMapper;

    /**
     * description 基本配置会员奖励
     * @author WYN
     * */
    public Page getBasicMemberBonusList(Page page) {
       // page.setResult( moreBasicManageMapper.getBasicMemberBonusList());
        page.setResult( moreBasicManageMapper.listAll());
        return page;
    }
}
