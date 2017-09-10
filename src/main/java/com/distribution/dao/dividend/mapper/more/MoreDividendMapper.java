package com.distribution.dao.dividend.mapper.more;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.DividendMapper;
import com.distribution.dao.dividend.model.Dividend;

@Repository
public interface MoreDividendMapper extends DividendMapper{

    /**
     * 查詢分紅包列表
     * @param page
     * @return
     */
    List<Dividend> getDividendList(Page page);

    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer getDividendListCount(Page page);
    
    Integer getAllNeedSendDividendCount();
    
    List<Dividend>listAllNeedSendDividends(String date);
    
    int updateAllNeedSendDividends(Map<String,Object> map);
}