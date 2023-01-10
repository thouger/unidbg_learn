package com.sobot.chat.api.model;

import java.io.Serializable;

public class StUserDealTicketReply implements Serializable {
    private String replyContent;
    private long replyTime;
    private int startType;

    public int getStartType() {
        return this.startType;
    }

    public void setStartType(int i) {
        this.startType = i;
    }

    public String getReplyContent() {
        return this.replyContent;
    }

    public void setReplyContent(String str) {
        this.replyContent = str;
    }

    public long getReplyTime() {
        return this.replyTime;
    }

    public void setReplyTime(long j) {
        this.replyTime = j;
    }
}
