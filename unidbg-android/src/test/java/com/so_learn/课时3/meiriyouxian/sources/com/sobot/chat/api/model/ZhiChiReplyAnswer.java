package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ZhiChiReplyAnswer implements Serializable {
    private static final long serialVersionUID = 1;
    private SobotCacheFile cacheFile;
    private String duration;
    private String id;
    private List<Map<String, String>> interfaceRetList;
    private SobotLocationModel locationData;
    private String msg;
    private String msgStripe;
    private String msgTransfer;
    private String msgType;
    private SobotMultiDiaRespInfo multiDiaRespInfo;
    private SobotQuestionRecommend questionRecommend;
    private int remindType = 0;
    private List<ChatMessageRichListModel> richList;

    public SobotCacheFile getCacheFile() {
        return this.cacheFile;
    }

    public void setCacheFile(SobotCacheFile sobotCacheFile) {
        this.cacheFile = sobotCacheFile;
    }

    public String getMsgStripe() {
        return this.msgStripe;
    }

    public void setMsgStripe(String str) {
        this.msgStripe = str;
    }

    public String getMsgTransfer() {
        return this.msgTransfer;
    }

    public void setMsgTransfer(String str) {
        this.msgTransfer = str;
    }

    public int getRemindType() {
        return this.remindType;
    }

    public void setRemindType(int i) {
        this.remindType = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public SobotMultiDiaRespInfo getMultiDiaRespInfo() {
        return this.multiDiaRespInfo;
    }

    public void setMultiDiaRespInfo(SobotMultiDiaRespInfo sobotMultiDiaRespInfo) {
        this.multiDiaRespInfo = sobotMultiDiaRespInfo;
    }

    public List<Map<String, String>> getInterfaceRetList() {
        return this.interfaceRetList;
    }

    public void setInterfaceRetList(List<Map<String, String>> list) {
        this.interfaceRetList = list;
    }

    public SobotQuestionRecommend getQuestionRecommend() {
        return this.questionRecommend;
    }

    public void setQuestionRecommend(SobotQuestionRecommend sobotQuestionRecommend) {
        this.questionRecommend = sobotQuestionRecommend;
    }

    public SobotLocationModel getLocationData() {
        return this.locationData;
    }

    public void setLocationData(SobotLocationModel sobotLocationModel) {
        this.locationData = sobotLocationModel;
    }

    public List<ChatMessageRichListModel> getRichList() {
        return this.richList;
    }

    public void setRichList(List<ChatMessageRichListModel> list) {
        this.richList = list;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiReplyAnswer{id='" + this.id + DateFormat.QUOTE + ", msgType='" + this.msgType + DateFormat.QUOTE + ", msg='" + this.msg + DateFormat.QUOTE + ", duration='" + this.duration + DateFormat.QUOTE + ", msgStripe='" + this.msgStripe + DateFormat.QUOTE + ", msgTransfer='" + this.msgTransfer + DateFormat.QUOTE + ", remindType=" + this.remindType + ", multiDiaRespInfo=" + this.multiDiaRespInfo + ", interfaceRetList=" + this.interfaceRetList + ", questionRecommend=" + this.questionRecommend + ", cacheFile=" + this.cacheFile + ", locationData=" + this.locationData + ", richList=" + this.richList + '}';
    }
}
