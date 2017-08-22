package com.distribution.dao.memberLevel.model;

import java.io.Serializable;

public class MemberLevelKey implements Serializable {
    private Integer id;

    private Integer levelCategory;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelCategory() {
        return levelCategory;
    }

    public void setLevelCategory(Integer levelCategory) {
        this.levelCategory = levelCategory;
    }
}