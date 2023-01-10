package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.Map;

public class SobotTransferOperatorParam implements Serializable {
    private ConsultingContent consultingContent;
    private String groupId;
    private String groupName;
    private boolean isShowTips;
    private String keyword;
    private String keywordId;
    private Map<String, String> summaryParams;
    private Map<String, String> summary_params;
    private int transferType;

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

    public boolean isShowTips() {
        return this.isShowTips;
    }

    public void setShowTips(boolean z) {
        this.isShowTips = z;
    }

    public ConsultingContent getConsultingContent() {
        return this.consultingContent;
    }

    public void setConsultingContent(ConsultingContent consultingContent) {
        this.consultingContent = consultingContent;
    }

    public int getTransferType() {
        return this.transferType;
    }

    public void setTransferType(int i) {
        this.transferType = i;
    }

    @Deprecated
    public Map<String, String> getSummaryParams() {
        return this.summaryParams;
    }

    @Deprecated
    public void setSummaryParams(Map<String, String> map) {
        this.summaryParams = map;
        this.summary_params = map;
    }

    public void setSummary_params(Map<String, String> map) {
        this.summary_params = map;
        this.summaryParams = map;
    }

    public Map<String, String> getSummary_params() {
        return this.summary_params;
    }
}
