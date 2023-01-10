package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLeaveMsgFieldModel implements Serializable {
    private String id;
    private String params;
    private String value;

    public SobotLeaveMsgFieldModel(String str, String str2, String str3) {
        this.id = str;
        this.value = str2;
        this.params = str3;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String str) {
        this.params = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLeaveMsgFieldModel{id='" + this.id + DateFormat.QUOTE + ", value='" + this.value + DateFormat.QUOTE + ", params='" + this.params + DateFormat.QUOTE + '}';
    }
}
