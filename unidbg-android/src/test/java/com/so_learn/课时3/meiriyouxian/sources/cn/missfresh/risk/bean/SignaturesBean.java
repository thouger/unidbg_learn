package cn.missfresh.risk.bean;

public class SignaturesBean {
    private String signDn;
    private String signMD5;

    public SignaturesBean(String str, String str2) {
        this.signDn = str;
        this.signMD5 = str2;
    }

    public String getSignDn() {
        return this.signDn;
    }

    public void setSignDn(String str) {
        this.signDn = str;
    }

    public String getSignMD5() {
        return this.signMD5;
    }

    public void setSignMD5(String str) {
        this.signMD5 = str;
    }
}
