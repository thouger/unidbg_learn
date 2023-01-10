package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class ZhiChiHistorySDKMsg implements Serializable {
    private static final long serialVersionUID = 1;
    private ZhiChiReplyAnswer answer;
    private String answerType;

    public ZhiChiReplyAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(ZhiChiReplyAnswer zhiChiReplyAnswer) {
        this.answer = zhiChiReplyAnswer;
    }

    public String getAnswerType() {
        return this.answerType;
    }

    public void setAnswerType(String str) {
        this.answerType = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiHistorySDKMsg{answer=" + this.answer + ", answerType='" + this.answerType + DateFormat.QUOTE + '}';
    }
}
