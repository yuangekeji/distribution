package com.distribution.dao.menu.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private Integer id;

    private String name;

    private String parentMenu;

    private String menuLink;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu == null ? null : parentMenu.trim();
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink == null ? null : menuLink.trim();
    }
}