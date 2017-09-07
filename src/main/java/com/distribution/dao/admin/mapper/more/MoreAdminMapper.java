package com.distribution.dao.admin.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MoreAdminMapper {
    /**
     * description 登录验证
     * @author Bright
     * */
    List<Admin> login(Map param);

    /**
     * 查詢管理员列表
     * @param page
     * @return
     */
    List<Admin> getAdminList(Page page);

    /**
     * 查詢總條數
     * @param page
     * @return
     */
    Integer getAdminListCount(Page page);

    /**
     * 通过手机号码查询
     * @author Bright
     * */
    List<Admin> getAdminByMobile(String mobile);
}