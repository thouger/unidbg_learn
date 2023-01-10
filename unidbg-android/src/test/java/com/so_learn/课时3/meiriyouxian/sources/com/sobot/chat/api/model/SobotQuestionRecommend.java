package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.List;

public class SobotQuestionRecommend implements Serializable {
    private String guide;
    private List<SobotQRMsgBean> msg;

    public String getGuide() {
        return this.guide;
    }

    public void setGuide(String str) {
        this.guide = str;
    }

    public List<SobotQRMsgBean> getMsg() {
        return this.msg;
    }

    public void setMsg(List<SobotQRMsgBean> list) {
        this.msg = list;
    }

    public static class SobotQRMsgBean {
        private String icon;
        private String id;
        private String question;
        private String title;
        private String url;

        public String getQuestion() {
            return this.question;
        }

        public void setQuestion(String str) {
            this.question = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }
}
