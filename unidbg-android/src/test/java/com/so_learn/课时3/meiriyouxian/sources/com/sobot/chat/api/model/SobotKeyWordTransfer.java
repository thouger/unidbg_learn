package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.util.List;

public class SobotKeyWordTransfer {
    private String groupId;
    private List<ZhiChiGroupBase> groupList;
    private String keyword;
    private String keywordId;
    private boolean queueFlag;
    private String tipsMessage;
    private int transferFlag;

    public String toString() {
        return "SobotKeyWordTransfer{keywordId='" + this.keywordId + DateFormat.QUOTE + ", keyword='" + this.keyword + DateFormat.QUOTE + ", transferFlag=" + this.transferFlag + ", groupId='" + this.groupId + DateFormat.QUOTE + ", tipsMessage='" + this.tipsMessage + DateFormat.QUOTE + ", groupList=" + this.groupList + '}';
    }

    public String getKeywordId() {
        return this.keywordId;
    }

    public void setKeywordId(String str) {
        this.keywordId = str;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public int getTransferFlag() {
        return this.transferFlag;
    }

    public void setTransferFlag(int i) {
        this.transferFlag = i;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getTipsMessage() {
        return this.tipsMessage;
    }

    public void setTipsMessage(String str) {
        this.tipsMessage = str;
    }

    public List<ZhiChiGroupBase> getGroupList() {
        return this.groupList;
    }

    public void setGroupList(List<ZhiChiGroupBase> list) {
        this.groupList = list;
    }

    public boolean isQueueFlag() {
        return this.queueFlag;
    }

    public void setQueueFlag(boolean z) {
        this.queueFlag = z;
    }
}
