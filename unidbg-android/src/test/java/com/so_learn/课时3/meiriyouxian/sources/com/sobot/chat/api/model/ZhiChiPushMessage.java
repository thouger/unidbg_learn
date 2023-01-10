package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class ZhiChiPushMessage implements Serializable {
    private static final long serialVersionUID = 1;
    private String adminHelloWord;
    private String aface;
    private String aname;
    private ZhiChiReplyAnswer answer;
    private String appId;
    private ConsultingContent consultingContent;
    private String content;
    private String count;
    private String face;
    private boolean isQuestionFlag;
    private int lockType;
    private String message;
    private String msgId;
    private String msgType;
    private String name;
    private OrderCardContentModel orderCardContent;
    private String queueDoc;
    private String revokeMsgId;
    private String serviceOutDoc;
    private String serviceOutTime;
    private String status;
    private int type;

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public String getRevokeMsgId() {
        return this.revokeMsgId;
    }

    public void setRevokeMsgId(String str) {
        this.revokeMsgId = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAdminHelloWord() {
        return this.adminHelloWord;
    }

    public void setAdminHelloWord(String str) {
        this.adminHelloWord = str;
    }

    public String getServiceOutTime() {
        return this.serviceOutTime;
    }

    public void setServiceOutTime(String str) {
        this.serviceOutTime = str;
    }

    public String getServiceOutDoc() {
        return this.serviceOutDoc;
    }

    public void setServiceOutDoc(String str) {
        this.serviceOutDoc = str;
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

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getAname() {
        return this.aname;
    }

    public void setAname(String str) {
        this.aname = str;
    }

    public String getAface() {
        return this.aface;
    }

    public void setAface(String str) {
        this.aface = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public boolean getIsQuestionFlag() {
        return this.isQuestionFlag;
    }

    public void setIsQuestionFlag(boolean z) {
        this.isQuestionFlag = z;
    }

    public String getQueueDoc() {
        return this.queueDoc;
    }

    public void setQueueDoc(String str) {
        this.queueDoc = str;
    }

    public int getLockType() {
        return this.lockType;
    }

    public void setLockType(int i) {
        this.lockType = i;
    }

    public ZhiChiReplyAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(ZhiChiReplyAnswer zhiChiReplyAnswer) {
        this.answer = zhiChiReplyAnswer;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiPushMessage{type=" + this.type + ", aname='" + this.aname + DateFormat.QUOTE + ", aface='" + this.aface + DateFormat.QUOTE + ", content='" + this.content + DateFormat.QUOTE + ", status='" + this.status + DateFormat.QUOTE + ", msgType='" + this.msgType + DateFormat.QUOTE + ", count='" + this.count + DateFormat.QUOTE + ", name='" + this.name + DateFormat.QUOTE + ", face='" + this.face + DateFormat.QUOTE + ", isQuestionFlag=" + this.isQuestionFlag + ", adminHelloWord='" + this.adminHelloWord + DateFormat.QUOTE + ", serviceOutTime='" + this.serviceOutTime + DateFormat.QUOTE + ", serviceOutDoc='" + this.serviceOutDoc + DateFormat.QUOTE + ", queueDoc='" + this.queueDoc + DateFormat.QUOTE + ", appId='" + this.appId + DateFormat.QUOTE + ", lockType=" + this.lockType + ", msgId='" + this.msgId + DateFormat.QUOTE + ", revokeMsgId='" + this.revokeMsgId + DateFormat.QUOTE + ", answer=" + this.answer + ", consultingContent=" + this.consultingContent + ", orderCardContent=" + this.orderCardContent + '}';
    }

    public ConsultingContent getConsultingContent() {
        return this.consultingContent;
    }

    public void setConsultingContent(ConsultingContent consultingContent) {
        this.consultingContent = consultingContent;
    }

    public OrderCardContentModel getOrderCardContent() {
        return this.orderCardContent;
    }

    public void setOrderCardContent(OrderCardContentModel orderCardContentModel) {
        this.orderCardContent = orderCardContentModel;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
