package com.sobot.chat.api.model;

import android.text.format.DateFormat;

public class CommonModelBase {
    private String msg;
    private String status;
    private String switchFlag;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getSwitchFlag() {
        return this.switchFlag;
    }

    public void setSwitchFlag(String str) {
        this.switchFlag = str;
    }

    public String toString() {
        return "CommonModelBase{status='" + this.status + DateFormat.QUOTE + "switchFlag='" + this.switchFlag + DateFormat.QUOTE + '}';
    }
}
