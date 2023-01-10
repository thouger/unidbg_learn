package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.List;

public class SobotUserTicketEvaluate implements Serializable {
    private boolean isEvalution;
    private boolean isOpen;
    private String remark;
    private int score;
    private List<TicketScoreInfooListBean> ticketScoreInfooList;
    private boolean txtFlag;

    public List<TicketScoreInfooListBean> getTicketScoreInfooList() {
        return this.ticketScoreInfooList;
    }

    public void setTicketScoreInfooList(List<TicketScoreInfooListBean> list) {
        this.ticketScoreInfooList = list;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public boolean isEvalution() {
        return this.isEvalution;
    }

    public void setEvalution(boolean z) {
        this.isEvalution = z;
    }

    public boolean isTxtFlag() {
        return this.txtFlag;
    }

    public void setTxtFlag(boolean z) {
        this.txtFlag = z;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public static class TicketScoreInfooListBean implements Serializable {
        private String companyId;
        private String configId;
        private String createId;
        private long createTime;
        private int score;
        private String scoreExplain;
        private String scoreId;
        private String updateId;
        private long updateTime;

        public String getCompanyId() {
            return this.companyId;
        }

        public void setCompanyId(String str) {
            this.companyId = str;
        }

        public String getConfigId() {
            return this.configId;
        }

        public void setConfigId(String str) {
            this.configId = str;
        }

        public String getCreateId() {
            return this.createId;
        }

        public void setCreateId(String str) {
            this.createId = str;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long j) {
            this.createTime = j;
        }

        public int getScore() {
            return this.score;
        }

        public void setScore(int i) {
            this.score = i;
        }

        public String getScoreExplain() {
            return this.scoreExplain;
        }

        public void setScoreExplain(String str) {
            this.scoreExplain = str;
        }

        public String getScoreId() {
            return this.scoreId;
        }

        public void setScoreId(String str) {
            this.scoreId = str;
        }

        public String getUpdateId() {
            return this.updateId;
        }

        public void setUpdateId(String str) {
            this.updateId = str;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long j) {
            this.updateTime = j;
        }
    }
}
