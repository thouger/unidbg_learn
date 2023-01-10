package com.sobot.chat.api.enumtype;

public enum SobotAutoSendMsgMode {
    Default(0),
    SendToRobot(1),
    SendToOperator(2),
    SendToAll(3);
    
    public static int ZCMessageTypeFile = 12;
    public static int ZCMessageTypePhoto = 1;
    public static int ZCMessageTypeText = 0;
    public static int ZCMessageTypeVideo = 23;
    private int auto_send_msgtype;
    private String content;
    private boolean isEveryTimeAutoSend = false;
    private int value;

    private SobotAutoSendMsgMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public boolean geIsEveryTimeAutoSend() {
        return this.isEveryTimeAutoSend;
    }

    public SobotAutoSendMsgMode setIsEveryTimeAutoSend(boolean z) {
        this.isEveryTimeAutoSend = z;
        return this;
    }

    public SobotAutoSendMsgMode setContent(String str) {
        this.content = str;
        return this;
    }

    public int getAuto_send_msgtype() {
        return this.auto_send_msgtype;
    }

    public SobotAutoSendMsgMode setAuto_send_msgtype(int i) {
        this.auto_send_msgtype = i;
        return this;
    }

    public String getContent() {
        return this.content;
    }
}
