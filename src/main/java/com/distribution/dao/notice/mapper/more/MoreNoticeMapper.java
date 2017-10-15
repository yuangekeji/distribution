package com.distribution.dao.notice.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.notice.model.Notice;
import com.distribution.dao.notice.model.more.MoreNotice;

import java.util.List;

public interface MoreNoticeMapper {
    Integer getNoticeCount(Page page);
    List<MoreNotice> list(Page page);
}