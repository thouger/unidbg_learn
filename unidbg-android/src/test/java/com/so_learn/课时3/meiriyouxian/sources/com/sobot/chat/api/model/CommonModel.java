package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class CommonModel extends BaseCode<CommonModelBase> implements Serializable {
    private static final long serialVersionUID = 1;
    private String msg;

    @Override // com.sobot.chat.api.model.BaseCode
    public String getMsg() {
        return this.msg;
    }

    @Override // com.sobot.chat.api.model.BaseCode
    public void setMsg(String str) {
        this.msg = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "CommonModel{msg='" + this.msg + DateFormat.QUOTE + '}';
    }
}
