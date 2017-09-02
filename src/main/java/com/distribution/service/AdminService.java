package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private MoreAdminMapper moreAdminMapper;

    /**
     * description 管理员列表
     * @author WYB
     * */
    public Page adminList(Page page){

        page.setTotalCount(moreAdminMapper.getAdminListCount(page));
        page.setResult( moreAdminMapper.getAdminList(page));
        return page;
    }
}
