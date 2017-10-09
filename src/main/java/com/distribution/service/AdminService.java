package com.distribution.service;

import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.mapper.AdminMapper;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.admin.model.more.MoreAdmin;
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
                admin.setDeleteFlag("N");
                admin.setCreateId(a.getId());
                admin.setCreateTime(new Date());
                admin.setUpdateId(a.getId());
                admin.setUpdateTime(new Date());
                adminMapper.insert(admin);
                return "SUCCESS";
            }
        }
    }

    /**
     * description 管理员禁用/启用功能操作
     * @author shiqing.dong
     * */
    public Integer updateAdminDeleteFlag(Admin admin, Admin a){

        admin.setUpdateId(a.getId());
        admin.setUpdateTime(new Date());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 管理员密码初始化
     * @author sijeong
     * */

    public Integer initAdminPassword(Admin ad, Admin a){

        Admin admin = new Admin();
        admin.setId(ad.getId());
        admin.setPassword(CryptoUtil.md5ByHex("111111"));
        admin.setUpdateId(a.getId());
        admin.setUpdateTime(new Date());
        Integer count = adminMapper.updateByPrimaryKeySelective(admin);
        return count;
    }
    /**
     * description 修改密码
     * @author sijeong
     * */
    public String updatePwd(MoreAdmin moreAdmin){

        String result = "SUCCESS";
        moreAdmin.setOldLoginPwd(CryptoUtil.md5ByHex(moreAdmin.getOldLoginPwd()));
        if(null == moreAdminMapper.checkLoginPwd(moreAdmin)){
            result = "OLD_PWD_ERROR";
        }else{
            Admin admin = new Admin();
            admin.setId(moreAdmin.getId());
            admin.setPassword(CryptoUtil.md5ByHex(moreAdmin.getPassword()));
            admin.setUpdateTime(new Date());
            adminMapper.updateByPrimaryKeySelective(admin);
        }
        return result;
    }
}
