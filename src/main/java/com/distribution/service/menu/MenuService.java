package com.distribution.service.menu;

import com.distribution.dao.menu.mapper.MenuMapper;
import com.distribution.dao.menu.model.MenuExample;
import com.distribution.dao.role.mapper.RoleMapper;
import com.distribution.dao.role.model.RoleExample;
import com.distribution.dao.roleMenu.more.mapper.MoreRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lijingx on 8/23/2017.
 */
@Service
public class MenuService {


    @Autowired
    private MoreRoleMenuMapper roleMenuMapper;

    /**
     * 根据roleid 查询菜单
     * @param roleId
     * @return
     */
    public List getMenuByRoleId(String roleId){
        return  roleMenuMapper.selectMenusByRoleId(roleId);
    }
}
