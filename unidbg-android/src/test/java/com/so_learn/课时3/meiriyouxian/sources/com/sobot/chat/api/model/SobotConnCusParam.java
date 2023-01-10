package com.sobot.chat.api.model;

import android.text.format.DateFormat;

public class SobotConnCusParam {
    private String chooseAdminId;
    private String cid;
    private boolean currentFlag;
    private String groupId;
    private String groupName;
    private boolean isQueueFirst = false;
    private String keyword;
    private String keywordId;
    private String offlineMsgAdminId;
    private int offlineMsgConnectFlag;
    private String partnerid;
    private boolean queue_first = false;
    private String summaryParams;
    private String summary_params;
    private int tranFlag;
    private int tran_flag;
    private String transferAction;
    private int transferType;
    private String uid;

    @Deprecated
    public int getTranFlag() {
        return this.tranFlag;
    }

    @Deprecated
    public void setTranFlag(int i) {
        this.tranFlag = i;
        this.tran_flag = i;
    }

    public void setTran_flag(int i) {
        this.tran_flag = i;
        this.tranFlag = i;
    }

    public int getTran_flag() {
        return this.tran_flag;
    }

    @Deprecated
    public boolean isQueueFirst() {
        return this.isQueueFirst;
    }

    @Deprecated
    public void setQueueFirst(boolean z) {
        this.isQueueFirst = z;
        this.queue_first = z;
    }

    public void setIs_Queue_First(boolean z) {
        this.queue_first = z;
        this.isQueueFirst = z;
    }

    public boolean is_queue_first() {
        return this.queue_first;
    }

    public String getTransferAction() {
        return this.transferAction;
    }

    public void setTransferAction(String str) {
        this.transferAction = str;
    }

    @Deprecated
    public void setSummaryParams(String str) {
        this.summaryParams = str;
        this.summary_params = str;
    }

    @Deprecated
    public String getSummaryParams() {
        return this.summaryParams;
    }

    public String getSummary_params() {
        return this.summary_params;
    }

    public void setSummary_params(String str) {
        this.summary_params = str;
        this.summaryParams = str;
    }

    @Deprecated
    public String getUid() {
        return this.uid;
    }

    @Deprecated
    public void setUid(String str) {
        this.uid = str;
        this.partnerid = str;
    }

    public void setPartnerid(String str) {
        this.partnerid = str;
        this.uid = str;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public int getTransferType() {
        return this.transferType;
    }

    public void setTransferType(int i) {
        this.transferType = i;
    }

    public String getChooseAdminId() {
        return this.chooseAdminId;
    }

    public void setChooseAdminId(String str) {
        this.chooseAdminId = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public boolean isCurrentFlag() {
        return this.currentFlag;
    }

    public void setCurrentFlag(boolean z) {
        this.currentFlag = z;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public String getKeywordId() {
        return this.keywordId;
    }

    public void setKeywordId(String str) {
        this.keywordId = str;
    }

    public int getOfflineMsgConnectFlag() {
        return this.offlineMsgConnectFlag;
    }

    public void setOfflineMsgConnectFlag(int i) {
        this.offlineMsgConnectFlag = i;
    }

    public String getOfflineMsgAdminId() {
        return this.offlineMsgAdminId;
    }

    public void setOfflineMsgAdminId(String str) {
        this.offlineMsgAdminId = str;
    }

    public String toString() {
        return "SobotConnCusParam{chooseAdminId='" + this.chooseAdminId + DateFormat.QUOTE + ", tranFlag=" + this.tranFlag + ", uid='" + this.uid + DateFormat.QUOTE + ", cid='" + this.cid + DateFormat.QUOTE + ", groupId='" + this.groupId + DateFormat.QUOTE + ", groupName='" + this.groupName + DateFormat.QUOTE + ", currentFlag=" + this.currentFlag + ", keyword=" + this.keyword + ", keywordId=" + this.keywordId + ", keywordId=" + this.summaryParams + '}';
    }
}
