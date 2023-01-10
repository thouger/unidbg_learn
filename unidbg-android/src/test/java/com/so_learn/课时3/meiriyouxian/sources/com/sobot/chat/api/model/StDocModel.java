package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class StDocModel implements Serializable {
    private String companyId;
    private String docId;
    private String questionId;
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

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String str) {
        this.questionId = str;
    }

    public String getQuestionTitle() {
        return this.questionTitle;
    }

    public void setQuestionTitle(String str) {
        this.questionTitle = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "StDocModel{companyId='" + this.companyId + DateFormat.QUOTE + ", docId='" + this.docId + DateFormat.QUOTE + ", questionId='" + this.questionId + DateFormat.QUOTE + ", questionTitle='" + this.questionTitle + DateFormat.QUOTE + '}';
    }
}
