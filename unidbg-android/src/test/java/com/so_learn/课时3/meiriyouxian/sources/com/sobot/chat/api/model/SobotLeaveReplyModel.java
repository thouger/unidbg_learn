package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLeaveReplyModel implements Serializable {
    public String customerId;
    public String replyContent;
    public long replyTime;
    public String serviceNick;
    public String ticketId;
    public String ticketTitle;

    public String getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(String str) {
        this.ticketId = str;
    }

    public String getTicketTitle() {
        return this.ticketTitle;
    }

    public void setTicketTitle(String str) {
        this.ticketTitle = str;
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

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public String getServiceNick() {
        return this.serviceNick;
    }

    public void setServiceNick(String str) {
        this.serviceNick = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLeaveReplyModel{ticketId='" + this.ticketId + DateFormat.QUOTE + ", ticketTitle='" + this.ticketTitle + DateFormat.QUOTE + ", replyContent='" + this.replyContent + DateFormat.QUOTE + ", replyTime='" + this.replyTime + DateFormat.QUOTE + ", customerId='" + this.customerId + DateFormat.QUOTE + ", serviceNick='" + this.serviceNick + DateFormat.QUOTE + '}';
    }
}
