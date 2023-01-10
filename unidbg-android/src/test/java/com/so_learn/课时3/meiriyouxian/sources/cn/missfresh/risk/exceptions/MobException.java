package cn.missfresh.risk.exceptions;

public class MobException extends Exception {
    public MobException(String str) {
        super(str);
    }

    public MobException(Throwable th) {
        super(th);
    }
}
