package cn.missfresh.buttomline.logtrace.bean;

public class ResponseBean {
    private static final int RESPONSE_CODE_SUCCESS = 0;
    private int code;
    private String token;

    public String getToken() {
        return this.token;
    }

    public boolean responseSuccess() {
        return this.code == 0;
    }
}
