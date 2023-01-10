package com.sobot.chat.api.model;

import java.util.List;

public class BaseListCode<T> {
    private String code;
    private List<T> data;
    private String ustatus;

    public String getUstatus() {
        return this.ustatus;
    }

    public void setUstatus(String str) {
        this.ustatus = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> list) {
        this.data = list;
    }
}
