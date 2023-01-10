package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StUserDealTicketInfo implements Serializable {
    private String content;
    private SobotUserTicketEvaluate evaluate;
    private ArrayList<SobotFileModel> fileList;
    private int flag;
    private StUserDealTicketReply reply;
    private int startType;
    private String time;
    private String timeStr;

    public SobotUserTicketEvaluate getEvaluate() {
        return this.evaluate;
    }

    public void setEvaluate(SobotUserTicketEvaluate sobotUserTicketEvaluate) {
        this.evaluate = sobotUserTicketEvaluate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getTimeStr() {
        return this.timeStr;
    }

    public void setTimeStr(String str) {
        this.timeStr = str;
    }

    public StUserDealTicketReply getReply() {
        return this.reply;
    }

    public void setReply(StUserDealTicketReply stUserDealTicketReply) {
        this.reply = stUserDealTicketReply;
    }

    public ArrayList<SobotFileModel> getFileList() {
        return this.fileList;
    }

    public void setFileList(ArrayList<SobotFileModel> arrayList) {
        this.fileList = arrayList;
    }

    public int getStartType() {
        return this.startType;
    }

    public void setStartType(int i) {
        this.startType = i;
    }
}
