package com.distribution.dao.dictionary.model;

public class Dictionary {
    private Integer id;

    private String dicCode;

    private String dicName;

    private String dicType;

    private String dicDes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }

    public String getDicDes() {
        return dicDes;
    }

    public void setDicDes(String dicDes) {
        this.dicDes = dicDes == null ? null : dicDes.trim();
    }
}