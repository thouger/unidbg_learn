package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLableInfoList implements Serializable {
    private String lableId;
    private String lableLink;
    private String lableName;

    public String getLableId() {
        return this.lableId;
    }

    public void setLableId(String str) {
        this.lableId = str;
    }

    public String getLableName() {
        return this.lableName;
    }

    public void setLableName(String str) {
        this.lableName = str;
    }

    public String getLableLink() {
        return this.lableLink;
    }

    public void setLableLink(String str) {
        this.lableLink = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLableInfoList{lableId='" + this.lableId + DateFormat.QUOTE + ", lableName='" + this.lableName + DateFormat.QUOTE + ", lableLink='" + this.lableLink + DateFormat.QUOTE + '}';
    }
}
