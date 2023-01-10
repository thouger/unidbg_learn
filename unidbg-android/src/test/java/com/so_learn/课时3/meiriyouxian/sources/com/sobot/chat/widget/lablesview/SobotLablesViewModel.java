package com.sobot.chat.widget.lablesview;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotLablesViewModel implements Serializable {
    private String anchor;
    private Object data;
    private String title;

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAnchor() {
        return this.anchor;
    }

    public void setAnchor(String str) {
        this.anchor = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLablesViewModel{title='" + this.title + DateFormat.QUOTE + ", anchor='" + this.anchor + DateFormat.QUOTE + '}';
    }
}
