package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.advance.mapper.AdvanceMapper;
import com.distribution.dao.advance.mapper.more.MoreAdvanceMapper;
import com.distribution.dao.advance.model.more.MoreAdvance;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmAdvanceService {
    @Autowired
    private MoreAdvanceMapper moreAdvanceMapper;

    @Autowired
    private MoreAccountManagerMapper moreAccountManagerMapper;

    @Autowired
    private AccountFlowHistoryMapper accountFlowHistoryMapper;

    @Autowired
    private MoreMemberMapper moreMemberMapper;

    @Autowired
    private AdvanceMapper advanceMapper;

    /**
     * description 提现列表
     * @author WYN
     * */
    public Page advanceList(Page page) {
        if(null!=page.getParameterMap().get("startTime") && !"".equals(page.getParameterMap().get("startTime")))
            page.getParameterMap().put("start",page.getParameterMap().get("startTime").toString()+" 00:00:00");
        if(null!=page.getParameterMap().get("endTime") && !"".equals(page.getParameterMap().get("endTime")))
            page.getParameterMap().put("end",page.getParameterMap().get("endTime").toString()+" 23:59:59");

        page.setTotalCount(moreAdvanceMapper.getAdvanceListCount(page));
        page.setResult( moreAdvanceMapper.getAdvanceList(page));
        return page;
    }

    /**
     * description 提现批准，驳回
     * @author WYN
     * */
    public String confirmAdvance(MoreAdvance moreAdvance) {
        int cnt = moreAdvanceMapper.confirmAdvance(moreAdvance);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }

}
