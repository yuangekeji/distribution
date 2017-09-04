package com.distribution.dao.advance.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.advance.model.Advance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoreAdvanceMapper {

    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer getAdvanceListCount(Page page);

    /**
     * 查詢提现列表
     * @param page
     * @return
     */
    List<Advance> getAdvanceList(Page page);

}
