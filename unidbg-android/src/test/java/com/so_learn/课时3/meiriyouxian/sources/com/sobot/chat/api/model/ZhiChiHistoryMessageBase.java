package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.List;

public class ZhiChiHistoryMessageBase implements Serializable {
    private static final long serialVersionUID = 1;
    private List<ZhiChiMessageBase> content;
    private String date;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public List<ZhiChiMessageBase> getContent() {
        return this.content;
    }

    public void setContent(List<ZhiChiMessageBase> list) {
        this.content = list;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiHistoryMessageBase{date='" + this.date + DateFormat.QUOTE + ", content=" + this.content + '}';
    }
}
