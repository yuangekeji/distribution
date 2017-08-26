package com.distribution.service.menu;

import com.distribution.dao.roleMenu.mapper.more.MoreRoleMenuMapper;
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
