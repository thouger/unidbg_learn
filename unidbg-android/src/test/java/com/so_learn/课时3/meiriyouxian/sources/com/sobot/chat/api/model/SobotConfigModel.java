package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotConfigModel implements Serializable {
    public boolean collectFlag;
    public String companyId;
    public boolean dataFlag;
    public int reqFrequency = 2;
    public boolean support;
}
