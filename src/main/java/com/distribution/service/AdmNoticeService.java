package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.notice.mapper.NoticeMapper;
import com.distribution.dao.notice.mapper.more.MoreNoticeMapper;
import com.distribution.dao.notice.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdmNoticeService {

    @Autowired
    private MoreNoticeMapper moreNoticeMapper;
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * description 公告管理列表查询
     * @author Bright
     * */
    public Page selectList(Page page){
        page.setTotalCount(moreNoticeMapper.getNoticeCount(page));
        page.setResult(moreNoticeMapper.list(page));
        return page;
    }

    /**
     * description 添加或者更新公告
     * @author Bright
     * */
    public Integer insertOrUpdate(Notice notice,Admin admin){
        if(null!=notice.getId()){
            return noticeMapper.updateByPrimaryKeySelective(notice);
        }else{
            notice.setPublishingState("N");
            notice.setDeteleFlag("N");
            notice.setCreateBy(admin.getId().toString());
            notice.setCreateTime(new Date());
            notice.setUpdateBy(admin.getId().toString());
            notice.setUpdateTime(new Date());
            return noticeMapper.insert(notice);
        }
    }

    /**
     * description 根据ID查询公告
     * @author Bright
     * */
    public Notice getNoticeById(Integer id){
        return noticeMapper.selectByPrimaryKey(id);
    }
}
