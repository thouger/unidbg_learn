package com.sobot.chat.api.model;

import java.io.Serializable;

public class StPostMsgParam implements Serializable {
    private String companyId;
    private boolean flagExitSdk;
    private int flagExitType = -1;
    private String groupId;
    private String msgTmp;
    private String msgTxt;
    private String uid;

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getPartnerid() {
        return this.uid;
    }

    public void setPartnerid(String str) {
        this.uid = str;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getMsgTmp() {
        return this.msgTmp;
    }

    public void setMsgTmp(String str) {
        this.msgTmp = str;
    }

    public String getMsgTxt() {
        return this.msgTxt;
    }

    public void setMsgTxt(String str) {
        this.msgTxt = str;
    }

    public int getFlagExitType() {
        return this.flagExitType;
    }

    public void setFlagExitType(int i) {
        this.flagExitType = i;
    }

    public boolean isFlagExitSdk() {
        return this.flagExitSdk;
    }

    public void setFlagExitSdk(boolean z) {
        this.flagExitSdk = z;
    }
}
