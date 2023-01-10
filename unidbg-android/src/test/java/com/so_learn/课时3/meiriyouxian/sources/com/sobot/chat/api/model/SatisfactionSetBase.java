package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SatisfactionSetBase implements Serializable {
    private String companyId;
    private String configId;
    private String createTime;
    private int defaultType = -1;
    private String groupId;
    private String groupName;
    private String inputLanguage;
    private boolean isInputMust;
    private boolean isQuestionFlag;
    private boolean isTagMust;
    private String labelId;
    private String labelName;
    private String operateType;
    private String score;
    private String scoreExplain;
    private String settingMethod;
    private String tagTips;
    private String updateTime;

    public String getConfigId() {
        return this.configId;
    }

    public void setConfigId(String str) {
        this.configId = str;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
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

    public String getLabelId() {
        return this.labelId;
    }

    public void setLabelId(String str) {
        this.labelId = str;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public void setLabelName(String str) {
        this.labelName = str;
    }

    public boolean getIsQuestionFlag() {
        return this.isQuestionFlag;
    }

    public void setIsQuestionFlag(boolean z) {
        this.isQuestionFlag = z;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getScoreExplain() {
        return this.scoreExplain;
    }

    public void setScoreExplain(String str) {
        this.scoreExplain = str;
    }

    public boolean getIsTagMust() {
        return this.isTagMust;
    }

    public void setIsTagMust(boolean z) {
        this.isTagMust = z;
    }

    public boolean getIsInputMust() {
        return this.isInputMust;
    }

    public void setIsInputMust(boolean z) {
        this.isInputMust = z;
    }

    public String getInputLanguage() {
        return this.inputLanguage;
    }

    public void setInputLanguage(String str) {
        this.inputLanguage = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getSettingMethod() {
        return this.settingMethod;
    }

    public void setSettingMethod(String str) {
        this.settingMethod = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getOperateType() {
        return this.operateType;
    }

    public void setOperateType(String str) {
        this.operateType = str;
    }

    public int getDefaultType() {
        return this.defaultType;
    }

    public void setDefaultType(int i) {
        this.defaultType = i;
    }

    public String getTagTips() {
        return this.tagTips;
    }

    public void setTagTips(String str) {
        this.tagTips = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SatisfactionSetBase{configId='" + this.configId + DateFormat.QUOTE + ", companyId='" + this.companyId + DateFormat.QUOTE + ", groupId='" + this.groupId + DateFormat.QUOTE + ", groupName='" + this.groupName + DateFormat.QUOTE + ", labelId='" + this.labelId + DateFormat.QUOTE + ", labelName='" + this.labelName + DateFormat.QUOTE + ", isQuestionFlag='" + this.isQuestionFlag + DateFormat.QUOTE + ", score='" + this.score + DateFormat.QUOTE + ", scoreExplain='" + this.scoreExplain + DateFormat.QUOTE + ", isTagMust='" + this.isTagMust + DateFormat.QUOTE + ", isInputMust='" + this.isInputMust + DateFormat.QUOTE + ", inputLanguage='" + this.inputLanguage + DateFormat.QUOTE + ", createTime='" + this.createTime + DateFormat.QUOTE + ", settingMethod='" + this.settingMethod + DateFormat.QUOTE + ", updateTime='" + this.updateTime + DateFormat.QUOTE + ", operateType='" + this.operateType + DateFormat.QUOTE + ", defaultType='" + this.defaultType + '}';
    }
}
