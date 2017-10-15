package com.distribution.dao.advance.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.advance.model.Advance;
import com.distribution.dao.advance.model.more.MoreAdvance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MoreAdvanceMapper {


    /**
     * description 查詢總條數
     * @param page
     * @return
     */
    Integer getAdvanceListCount(Page page);

    /**
     * description 查询提现列表
     * @param page
     * @return
     */
    List<Advance> getAdvanceList(Page page);

    /**
     * description 提现批准，驳回
     * @author WYN
     * */
    int confirmAdvance(MoreAdvance moreAdvance);

    List<MoreAdvance> getExcelAdvanceList(Map map);

}
