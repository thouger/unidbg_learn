package com.sobot.chat.api.enumtype;

public enum SobotChatAvatarDisplayMode {
    Default(0),
    ShowFixedAvatar(1),
    ShowCompanyAvatar(2);
    
    private int value;

    private SobotChatAvatarDisplayMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
