package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class Suggestions implements Serializable {
    private String answer;
    private String docId;
    private String question;

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String str) {
        this.docId = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "Suggestions{question='" + this.question + DateFormat.QUOTE + ", docId='" + this.docId + DateFormat.QUOTE + ", answer='" + this.answer + DateFormat.QUOTE + '}';
    }
}
