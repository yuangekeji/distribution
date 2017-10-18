package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.adminHandleHistory.mapper.AdminHandleHistoryMapper;
import com.distribution.dao.adminHandleHistory.mapper.more.MoreAdminHandleHistoryMapper;
import com.distribution.dao.adminHandleHistory.model.AdminHandleHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdmHandleHistoryService {
    @Autowired
    private AdminHandleHistoryMapper adminHandleHistoryMapper;
    @Autowired
    private MoreAdminHandleHistoryMapper moreAdminHandleHistoryMapper;

    /**
     * description 管理员操作管理
     * @author shiqing.dong
     * */
    public Integer addAdminHandleHistory(Admin admin, Map map) {
        //管理员操作记录
        AdminHandleHistory ahh = new AdminHandleHistory();
        Date date = new Date();
        ahh.setAdminId(admin.getId());
        ahh.setAdminMobile(admin.getMobile());
        ahh.setAdminName(admin.getName());
        ahh.setAdminRoleId(admin.getRoleId());
        ahh.setHandleType(map.get("handleType").toString());
        ahh.setHandleId(map.get("handleId").toString());
        ahh.setHandleComment(map.get("handleComment").toString());
        ahh.setHandleTime(date);
        ahh.setCreateId(admin.getId());
        ahh.setCreateTime(date);

        int cnt = adminHandleHistoryMapper.insert(ahh);
        if(cnt > 0){
            return cnt;
        }else{
            throw new RuntimeException();
        }
    }
    /**
     * description 查詢管理员操作记录列表
     * @author sijeong
     * */
    public Page admHandleHistoryList(Page page){

        page.setTotalCount(moreAdminHandleHistoryMapper.getAdmHandleHistoryCount(page));
        page.setResult( moreAdminHandleHistoryMapper.getAdmHandleHistoryList(page));
        return page;
    }
}
