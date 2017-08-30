package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lijingx on 8/28/2017.
 */

@Service
public class DividendService {

    @Autowired
    private MoreDividendMapper dividendMapper;

    @Autowired
    private MoreMemberBonusMapper memberBonusMapper;



    /**
     * description 分紅包列表
     * @author jingxin
     * */
    public Page dividendList(Page page){

        page.setTotalCount(dividendMapper.getDividendListCount(page));
        page.setResult( dividendMapper.getDividendList(page));
        return page;
    }

    /**
     * 查詢分紅包領取明細
     * @param page
     * @return
     */
    public Page dividendDetails(Page page){

        page.setTotalCount(dividendMapper.getDividendListCount(page));
        page.setResult( memberBonusMapper.getDividendDetails(page));
        return page;
    }
}
