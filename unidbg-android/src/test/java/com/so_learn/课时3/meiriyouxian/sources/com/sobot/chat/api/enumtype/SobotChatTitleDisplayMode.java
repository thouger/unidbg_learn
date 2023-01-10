package com.sobot.chat.api.enumtype;

public enum SobotChatTitleDisplayMode {
    Default(0),
    ShowFixedText(1),
    ShowCompanyName(2);
    
    private int value;

    private SobotChatTitleDisplayMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
