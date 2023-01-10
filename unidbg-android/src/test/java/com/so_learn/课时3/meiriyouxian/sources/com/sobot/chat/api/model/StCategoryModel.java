package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class StCategoryModel implements Serializable {
    private String appId;
    private String categoryDetail;
    private String categoryId;
    private String categoryName;
    private String categoryUrl;
    private int sortNo;

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getCategoryDetail() {
        return this.categoryDetail;
    }

    public void setCategoryDetail(String str) {
        this.categoryDetail = str;
    }

    public String getCategoryUrl() {
        return this.categoryUrl;
    }

    public void setCategoryUrl(String str) {
        this.categoryUrl = str;
    }

    public int getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(int i) {
        this.sortNo = i;
    }

    @Override // java.lang.Object
    public String toString() {
        return "StCategoryModel{categoryId='" + this.categoryId + DateFormat.QUOTE + ", appId='" + this.appId + DateFormat.QUOTE + ", categoryName='" + this.categoryName + DateFormat.QUOTE + ", categoryDetail='" + this.categoryDetail + DateFormat.QUOTE + ", categoryUrl='" + this.categoryUrl + DateFormat.QUOTE + ", sortNo=" + this.sortNo + '}';
    }
}
