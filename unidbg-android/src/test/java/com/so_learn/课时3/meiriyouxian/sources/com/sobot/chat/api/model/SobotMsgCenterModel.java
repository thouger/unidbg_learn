package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotMsgCenterModel implements Serializable {
    private static final long serialVersionUID = 3567312018034692181L;
    private String app_key;
    private String appkey = "";
    private String face = "";
    private String id = "";
    private Information info = null;
    private String lastDate = "";
    private String lastDateTime = "";
    private String lastMsg = "";
    private String name = "";
    private String senderFace;
    private String senderName;
    private int unreadCount = 0;

    public int getUnreadCount() {
        return this.unreadCount;
    }

    public void setUnreadCount(int i) {
        this.unreadCount = i;
    }

    public Information getInfo() {
        return this.info;
    }

    public void setInfo(Information information) {
        this.info = information;
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String str) {
        this.face = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLastMsg() {
        return this.lastMsg;
    }

    public void setLastMsg(String str) {
        this.lastMsg = str;
    }

    public String getLastDate() {
        return this.lastDate;
    }

    public void setLastDate(String str) {
        this.lastDate = str;
    }

    public String getLastDateTime() {
        return this.lastDateTime;
    }

    public void setLastDateTime(String str) {
        this.lastDateTime = str;
    }

    @Deprecated
    public void setAppkey(String str) {
        this.appkey = str;
        this.app_key = str;
    }

    public void setApp_key(String str) {
        this.appkey = str;
        this.app_key = str;
    }

    @Deprecated
    public String getAppkey() {
        return this.appkey;
    }

    public String getApp_key() {
        return this.app_key;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj instanceof SobotMsgCenterModel) {
            return ((SobotMsgCenterModel) obj).getApp_key().equals(getApp_key());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String str) {
        this.senderName = str;
    }

    public String getSenderFace() {
        return this.senderFace;
    }

    public void setSenderFace(String str) {
        this.senderFace = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotMsgCenterModel{app_key='" + this.app_key + DateFormat.QUOTE + ", info=" + this.info + ", appkey='" + this.appkey + DateFormat.QUOTE + ", id='" + this.id + DateFormat.QUOTE + ", face='" + this.face + DateFormat.QUOTE + ", name='" + this.name + DateFormat.QUOTE + ", lastMsg='" + this.lastMsg + DateFormat.QUOTE + ", lastDate='" + this.lastDate + DateFormat.QUOTE + ", lastDateTime='" + this.lastDateTime + DateFormat.QUOTE + ", unreadCount=" + this.unreadCount + ", senderName='" + this.senderName + DateFormat.QUOTE + ", senderFace='" + this.senderFace + DateFormat.QUOTE + '}';
    }
}
