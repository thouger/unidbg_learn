package cn.com.chinatelecom.account.api;

public interface TraceLogger {
    void debug(String str, String str2);

    void info(String str, String str2);

    void warn(String str, String str2, Throwable th);
}
