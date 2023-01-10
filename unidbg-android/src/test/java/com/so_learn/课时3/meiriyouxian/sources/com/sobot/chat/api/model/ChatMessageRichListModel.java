package com.sobot.chat.api.model;

import java.io.Serializable;

public class ChatMessageRichListModel implements Serializable {
    private String msg;
    private String name;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
