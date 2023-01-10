package cn.missfresh.module.base.common.ministart.bean;

public class RiskBean {
    private int code;
    private RiskDataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public RiskDataBean getData() {
        return this.data;
    }

    public void setData(RiskDataBean riskDataBean) {
        this.data = riskDataBean;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public static class RiskDataBean {
        private String ccg;
        private String token;

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getCcg() {
            return this.ccg;
        }

        public void setCcg(String str) {
            this.ccg = str;
        }
    }
}
