package com.distribution.service.role;

import com.distribution.dao.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 * 主要功能是根据用户id获取其role_id
 *
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;


}
