package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLeaveMsgParamBaseModel implements Serializable {
    private String code;
    private SobotLeaveMsgParamModel data;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public SobotLeaveMsgParamModel getData() {
        return this.data;
    }

    public void setData(SobotLeaveMsgParamModel sobotLeaveMsgParamModel) {
        this.data = sobotLeaveMsgParamModel;
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
