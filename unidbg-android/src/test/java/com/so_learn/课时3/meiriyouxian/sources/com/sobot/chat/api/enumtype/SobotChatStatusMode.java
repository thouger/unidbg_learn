package com.sobot.chat.api.enumtype;

public enum SobotChatStatusMode {
    ZCServerConnectOffline(0),
    ZCServerConnectRobot(1),
    ZCServerConnectArtificial(2),
    ZCServerConnectWaiting(3);
    
    private int value;

    private SobotChatStatusMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
