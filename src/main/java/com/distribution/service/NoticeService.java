package com.distribution.service;

import com.distribution.dao.notice.mapper.NoticeMapper;
import com.distribution.dao.notice.mapper.more.MoreNoticeMapper;
import com.distribution.dao.notice.model.Notice;
import com.distribution.dao.notice.model.more.MoreNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private MoreNoticeMapper moreNoticeMapper;

    /**
     * description 查询前天公告列表
     * @author Bright
     * */
    public List<MoreNotice> getList(){
        return moreNoticeMapper.getList();
    }

    /**
     * description 根据ID查询公告
     * @author Bright
     * */
    public Notice getNoticeById(Integer id){
        return moreNoticeMapper.selectByPrimaryKey(id);
    }
}
