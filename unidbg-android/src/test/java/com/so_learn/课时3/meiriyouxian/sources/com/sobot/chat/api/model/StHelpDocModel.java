package com.sobot.chat.api.model;

import java.io.Serializable;

public class StHelpDocModel implements Serializable {
    private String answerDesc;
    private String companyId;
    private String docId;
    private String questionTitle;

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String str) {
        this.docId = str;
    }

    public String getQuestionTitle() {
        return this.questionTitle;
    }

    public void setQuestionTitle(String str) {
        this.questionTitle = str;
    }

    public String getAnswerDesc() {
        return this.answerDesc;
    }

    public void setAnswerDesc(String str) {
        this.answerDesc = str;
    }
}
