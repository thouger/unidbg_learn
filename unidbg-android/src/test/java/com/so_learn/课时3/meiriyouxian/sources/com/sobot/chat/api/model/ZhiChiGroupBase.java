package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class ZhiChiGroupBase implements Serializable {
    private static final long serialVersionUID = 1;
    private String channelType;
    private String companyId;
    private String groupId;
    private String groupName;
    private String isOnline;
    private String recGroupName;

    public ZhiChiGroupBase() {
    }

    public ZhiChiGroupBase(String str, String str2) {
        this.groupName = str;
        this.isOnline = str2;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getChannelType() {
        return this.channelType;
    }

    public void setChannelType(String str) {
        this.channelType = str;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getRecGroupName() {
        return this.recGroupName;
    }

    public void setRecGroupName(String str) {
        this.recGroupName = str;
    }

    public String isOnline() {
        return this.isOnline;
    }

    public void setIsOnline(String str) {
        this.isOnline = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiGroupBase{groupId='" + this.groupId + DateFormat.QUOTE + ", channelType='" + this.channelType + DateFormat.QUOTE + ", groupName='" + this.groupName + DateFormat.QUOTE + ", companyId='" + this.companyId + DateFormat.QUOTE + ", recGroupName='" + this.recGroupName + DateFormat.QUOTE + ", isOnline=" + this.isOnline + '}';
    }
}
