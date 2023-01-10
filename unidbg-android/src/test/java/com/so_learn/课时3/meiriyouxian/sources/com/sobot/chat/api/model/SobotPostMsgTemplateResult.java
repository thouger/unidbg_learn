package com.sobot.chat.api.model;

import java.util.ArrayList;

public class SobotPostMsgTemplateResult {
    private String code;
    private ArrayList<SobotPostMsgTemplate> data;
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

    public ArrayList<SobotPostMsgTemplate> getData() {
        return this.data;
    }

    public void setData(ArrayList<SobotPostMsgTemplate> arrayList) {
        this.data = arrayList;
    }
}
