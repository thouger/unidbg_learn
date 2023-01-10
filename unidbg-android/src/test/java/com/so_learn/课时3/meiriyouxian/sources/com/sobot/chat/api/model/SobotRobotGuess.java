package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.List;

public class SobotRobotGuess implements Serializable {
    private String originQuestion;
    private List<RespInfoListBean> respInfoList;

    public String getOriginQuestion() {
        return this.originQuestion;
    }

    public void setOriginQuestion(String str) {
        this.originQuestion = str;
    }

    public List<RespInfoListBean> getRespInfoList() {
        return this.respInfoList;
    }

    public void setRespInfoList(List<RespInfoListBean> list) {
        this.respInfoList = list;
    }

    public static class RespInfoListBean {
        private String docId;
        private String highlight;
        private String question;

        public String getDocId() {
            return this.docId;
        }

        public void setDocId(String str) {
            this.docId = str;
        }

        public String getHighlight() {
            return this.highlight;
        }

        public void setHighlight(String str) {
            this.highlight = str;
        }

        public String getQuestion() {
            return this.question;
        }

        public void setQuestion(String str) {
            this.question = str;
        }
    }
}
