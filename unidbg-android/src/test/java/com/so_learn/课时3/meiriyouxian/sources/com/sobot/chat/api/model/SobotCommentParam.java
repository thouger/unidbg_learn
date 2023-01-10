package com.sobot.chat.api.model;

public class SobotCommentParam {
    int commentType;
    int isresolve = 0;
    String problem;
    String robotFlag;
    String score;
    String suggest;
    String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setProblem(String str) {
        this.problem = str;
    }

    public String getSuggest() {
        return this.suggest;
    }

    public void setSuggest(String str) {
        this.suggest = str;
    }

    public int getIsresolve() {
        return this.isresolve;
    }

    public void setIsresolve(int i) {
        this.isresolve = i;
    }

    public int getCommentType() {
        return this.commentType;
    }

    public void setCommentType(int i) {
        this.commentType = i;
    }

    public String getRobotFlag() {
        return this.robotFlag;
    }

    public void setRobotFlag(String str) {
        this.robotFlag = str;
    }
}
