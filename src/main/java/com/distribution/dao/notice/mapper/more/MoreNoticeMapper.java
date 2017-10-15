package com.distribution.dao.notice.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.notice.model.Notice;
import com.distribution.dao.notice.model.more.MoreNotice;

import java.util.List;

public interface MoreNoticeMapper {
    /**后台*/
    Integer getNoticeCount(Page page);
    List<MoreNotice> list(Page page);
    /**前台*/
    List<MoreNotice> getList();
    MoreNotice selectByPrimaryKey(Integer id);
}