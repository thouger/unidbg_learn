package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SobotMultiDiaRespInfo implements Serializable {
    private String answer;
    private String answerStrip;
    private int clickFlag;
    private String conversationId;
    private boolean endFlag;
    private List<Map<String, String>> icLists;
    private String[] inputContentList;
    private List<Map<String, String>> interfaceRetList;
    private String level;
    private String[] outPutParamList;
    private int pageNum = 1;
    private String remindQuestion;
    private String retCode;
    private String retErrorMsg;
    private String template;

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int i) {
        this.pageNum = i;
    }

    public String getRemindQuestion() {
        return this.remindQuestion;
    }

    public void setRemindQuestion(String str) {
        this.remindQuestion = str;
    }

    public List<Map<String, String>> getInterfaceRetList() {
        return this.interfaceRetList;
    }

    public void setInterfaceRetList(List<Map<String, String>> list) {
        this.interfaceRetList = list;
    }

    public String[] getOutPutParamList() {
        return this.outPutParamList;
    }

    public void setOutPutParamList(String[] strArr) {
        this.outPutParamList = strArr;
    }

    public String[] getInputContentList() {
        return this.inputContentList;
    }

    public void setInputContentList(String[] strArr) {
        this.inputContentList = strArr;
    }

    public List<Map<String, String>> getIcLists() {
        return this.icLists;
    }

    public void setIcLists(List<Map<String, String>> list) {
        this.icLists = list;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String str) {
        this.retCode = str;
    }

    public String getRetErrorMsg() {
        return this.retErrorMsg;
    }

    public void setRetErrorMsg(String str) {
        this.retErrorMsg = str;
    }

    public boolean getEndFlag() {
        return this.endFlag;
    }

    public void setEndFlag(boolean z) {
        this.endFlag = z;
    }

    public String getAnswerStrip() {
        return this.answerStrip;
    }

    public void setAnswerStrip(String str) {
        this.answerStrip = str;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String str) {
        this.template = str;
    }

    public int getClickFlag() {
        return this.clickFlag;
    }

    public void setClickFlag(int i) {
        this.clickFlag = i;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotMultiDiaRespInfo{remindQuestion='" + this.remindQuestion + DateFormat.QUOTE + ", interfaceRetList=" + this.interfaceRetList + ", inputContentList=" + Arrays.toString(this.inputContentList) + ", outPutParamList=" + Arrays.toString(this.outPutParamList) + ", level='" + this.level + DateFormat.QUOTE + ", conversationId='" + this.conversationId + DateFormat.QUOTE + ", retCode='" + this.retCode + DateFormat.QUOTE + ", retErrorMsg='" + this.retErrorMsg + DateFormat.QUOTE + ", endFlag=" + this.endFlag + ", answerStrip='" + this.answerStrip + DateFormat.QUOTE + ", template='" + this.template + DateFormat.QUOTE + ", answer='" + this.answer + DateFormat.QUOTE + ", clickFlag=" + this.clickFlag + ", pageNum=" + this.pageNum + ", icLists=" + this.icLists + '}';
    }
}
