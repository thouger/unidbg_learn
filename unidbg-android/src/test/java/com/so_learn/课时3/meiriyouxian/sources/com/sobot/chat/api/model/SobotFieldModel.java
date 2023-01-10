package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.List;

public class SobotFieldModel implements Serializable {
    private SobotCusFieldConfig cusFieldConfig;
    private List<SobotCusFieldDataInfo> cusFieldDataInfoList;

    public SobotCusFieldConfig getCusFieldConfig() {
        return this.cusFieldConfig;
    }

    public void setCusFieldConfig(SobotCusFieldConfig sobotCusFieldConfig) {
        this.cusFieldConfig = sobotCusFieldConfig;
    }

    public List<SobotCusFieldDataInfo> getCusFieldDataInfoList() {
        return this.cusFieldDataInfoList;
    }

    public void setCusFieldDataInfoList(List<SobotCusFieldDataInfo> list) {
        this.cusFieldDataInfoList = list;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotFieldModel{cusFieldConfig=" + this.cusFieldConfig + ", cusFieldDataInfoList=" + this.cusFieldDataInfoList + '}';
    }
}
