package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.advance.mapper.more.MoreAdvanceMapper;
import com.distribution.dao.dateBonusHistory.mapper.more.MoreDateBonusHistoryMapper;
import com.distribution.dao.dateBonusHistory.model.DateBonusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jingxin on 2017/9/7.
 */
@Service
public class AdmWarningService {

    @Autowired
    private MoreDateBonusHistoryMapper dateBonusHistoryMapper;

    public Integer selectFailJobCount(){
        return  dateBonusHistoryMapper.selectFailJobCount();
    }

    /**
     * Job发放记录
     * @param page
     * @return
     */
    public Page selectDateBonusHistoryList(Page page){
        /*if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("startTime",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("endTime",page.getParameterMap().get("endTime").toString()+" 23:59:59");*/

        page.setTotalCount(dateBonusHistoryMapper.selectDateBonusHistoryListCount(page));
        page.setResult(dateBonusHistoryMapper.selectDateBonusHistoryList(page));
        return page;

    }

}
