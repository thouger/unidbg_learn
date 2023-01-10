package cn.missfresh.basiclib.net.error;

public class ApiException extends RuntimeException {
    private int errorCode;

    public ApiException(int i, String str) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
