package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SobotUserTicketInfo implements Serializable {
    private String content;
    private ArrayList<SobotFileModel> fileList;
    private int flag;
    private boolean newFlag;
    private String ticketCode;
    private String ticketId;
    private String time;
    private String timeStr;

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public boolean isNewFlag() {
        return this.newFlag;
    }

    public void setNewFlag(boolean z) {
        this.newFlag = z;
    }

    public String getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(String str) {
        this.timeStr = str;
    }

    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String str) {
        this.ticketCode = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(String str) {
        this.ticketId = str;
    }

    public ArrayList<SobotFileModel> getFileList() {
        return this.fileList;
    }

    public void setFileList(ArrayList<SobotFileModel> arrayList) {
        this.fileList = arrayList;
    }
}
