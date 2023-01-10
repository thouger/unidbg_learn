package cn.missfresh.basiclib.net.bean;

public class ErrorBean {
    private int code;
    private String errorMsg;

    public ErrorBean(int i, String str) {
        this.code = i;
        this.errorMsg = str;
    }

    public int getCode() {
        return this.code;
    }

    public ErrorBean setCode(int i) {
        this.code = i;
        return this;
    }

    public String getMessage() {
        return this.errorMsg;
    }

    public ErrorBean setMessage(String str) {
        this.errorMsg = str;
        return this;
    }
}
