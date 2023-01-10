package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotUserTicketInfoFlag implements Serializable {
    private String code;
    private boolean existFlag;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public boolean isExistFlag() {
        return this.existFlag;
    }

    public void setExistFlag(boolean z) {
        this.existFlag = z;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
