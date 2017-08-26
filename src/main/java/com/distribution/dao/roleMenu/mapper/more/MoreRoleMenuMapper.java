package com.distribution.dao.roleMenu.mapper.more;

import com.distribution.dao.menu.model.Menu;

import java.util.List;

public interface MoreRoleMenuMapper {

    List<Menu> selectMenusByRoleId(String roleId);

}