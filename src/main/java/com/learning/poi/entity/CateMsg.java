package com.learning.poi.entity;

public class CateMsg {

    private String cateId;
    private String cateName;
    private String cateLevel;
    private String cateCndir;
    //开启1，关闭0；
    private String cateStatus;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateLevel() {
        return cateLevel;
    }

    public void setCateLevel(String cateLevel) {
        this.cateLevel = cateLevel;
    }

    public String getCateCndir() {
        return cateCndir;
    }

    public void setCateCndir(String cateCndir) {
        this.cateCndir = cateCndir;
    }

    public String getCateStatus() {
        return cateStatus;
    }

    public void setCateStatus(String cateStatus) {
        this.cateStatus = cateStatus;
    }

}
