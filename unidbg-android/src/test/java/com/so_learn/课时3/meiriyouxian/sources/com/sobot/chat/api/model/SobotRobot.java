package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotRobot implements Serializable {
    private int guideFlag;
    private boolean isSelected;
    private String operationRemark;
    private String robotFlag;
    private String robotHelloWord;
    private String robotLogo;
    private String robotName;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getRobotFlag() {
        return this.robotFlag;
    }

    public void setRobotFlag(String str) {
        this.robotFlag = str;
    }

    public String getRobotName() {
        return this.robotName;
    }

    public void setRobotName(String str) {
        this.robotName = str;
    }

    public String getRobotLogo() {
        return this.robotLogo;
    }

    public void setRobotLogo(String str) {
        this.robotLogo = str;
    }

    public int getGuideFlag() {
        return this.guideFlag;
    }

    public void setGuideFlag(int i) {
        this.guideFlag = i;
    }

    public String getOperationRemark() {
        return this.operationRemark;
    }

    public void setOperationRemark(String str) {
        this.operationRemark = str;
    }

    public String getRobotHelloWord() {
        return this.robotHelloWord;
    }

    public void setRobotHelloWord(String str) {
        this.robotHelloWord = str;
    }
}
