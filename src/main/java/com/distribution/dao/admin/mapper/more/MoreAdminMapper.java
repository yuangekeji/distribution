package com.distribution.dao.admin.mapper.more;

import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.admin.model.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoreAdminMapper {
    /**
     * description 登录验证
     * @author Bright
     * */
    List<Admin> login(Map param);
}