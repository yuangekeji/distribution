package com.distribution.service;

import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.mapper.AdminMapper;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import com.distribution.dao.admin.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private MoreAdminMapper moreAdminMapper;
    @Autowired
    private AdminMapper adminMapper;

    /**
     * description 管理员列表
     * @author WYN
     * */
    public Page adminList(Page page){

        page.setTotalCount(moreAdminMapper.getAdminListCount(page));
        page.setResult( moreAdminMapper.getAdminList(page));
        return page;
    }

    /**
     * description 添加管理员
     * @author Bright
     * */
    public String insertAdmin(Admin admin, Admin a){
        if(null==admin.getMobile()){
            return "PHONE_NULL";
        }else{
            List list = moreAdminMapper.getAdminByMobile(admin.getMobile());
            if(!list.isEmpty()){
                return "PHONE_EXIST";
            }else{
                admin.setPassword(CryptoUtil.md5ByHex(admin.getPassword()));
                admin.setCreateId(a.getId());
                admin.setCreateTime(new Date());
                admin.setUpdateId(a.getId());
                admin.setUpdateTime(new Date());
                adminMapper.insert(admin);
                return "SUCCESS";
            }
        }
    }
}
