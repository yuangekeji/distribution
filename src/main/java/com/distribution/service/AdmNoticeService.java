package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.notice.mapper.NoticeMapper;
import com.distribution.dao.notice.mapper.more.MoreNoticeMapper;
import com.distribution.dao.notice.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdmNoticeService {

    @Autowired
    private MoreNoticeMapper moreNoticeMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;

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
        Integer it;
        Integer id;
        if(null!=notice.getId()){
            it = noticeMapper.updateByPrimaryKeySelective(notice);
        }else{
            notice.setPublishingState("N");
            notice.setDeteleFlag("N");
            notice.setCreateBy(admin.getId().toString());
            notice.setCreateTime(new Date());
            notice.setUpdateBy(admin.getId().toString());
            notice.setUpdateTime(new Date());
            it = moreNoticeMapper.insert(notice);
        }

        id = notice.getId();
        //管理员操作记录
        Map mapHandle = new HashMap();
        String orderNoHandle = id+"";
        mapHandle.put("handleType", Constant.ADMINHANDLETYPE_ADDNOTICE);
        mapHandle.put("handleId", orderNoHandle);
        mapHandle.put("handleComment", "订单号: " + orderNoHandle + ", 操作: 订单下载");
        admHandleHistoryService.addAdminHandleHistory(admin, mapHandle);

        return it;
    }

    /**
     * description 根据ID查询公告
     * @author Bright
     * */
    public Notice getNoticeById(Integer id){
        return noticeMapper.selectByPrimaryKey(id);
    }
}
