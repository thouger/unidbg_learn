package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLeaveMsgConfigResult implements Serializable {
    private String code;
    private SobotLeaveMsgConfig data;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public SobotLeaveMsgConfig getData() {
        return this.data;
    }

    public void setData(SobotLeaveMsgConfig sobotLeaveMsgConfig) {
        this.data = sobotLeaveMsgConfig;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLeaveMsgParamBaseModel{code=" + this.code + ", data=" + this.data + ", msg='" + this.msg + DateFormat.QUOTE + '}';
    }
}
