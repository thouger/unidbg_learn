package cn.missfresh.risk.bean;

public class RiskReqParam {
    private String devideinfo;
    private String pwd;
    private String token;

    public String getDevideinfo() {
        return this.devideinfo;
    }

    public void setDevideinfo(String str) {
        this.devideinfo = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }
}
