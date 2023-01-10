package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotEvaluateModel implements Serializable {
    private int evaluateStatus = 0;
    private boolean isQuestionFlag;
    private int isResolved = 0;
    private String problem;
    private int score = 0;

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getIsResolved() {
        return this.isResolved;
    }

    public void setIsResolved(int i) {
        this.isResolved = i;
    }

    public boolean getIsQuestionFlag() {
        return this.isQuestionFlag;
    }

    public void setIsQuestionFlag(boolean z) {
        this.isQuestionFlag = z;
    }

    public int getEvaluateStatus() {
        return this.evaluateStatus;
    }

    public void setEvaluateStatus(int i) {
        this.evaluateStatus = i;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setProblem(String str) {
        this.problem = str;
    }
}
