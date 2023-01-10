package com.sina.weibo.sdk.statistic;

/* compiled from: PageLog */
/* access modifiers changed from: package-private */
public class e {
    private static String f = "session";
    private static long g = 1000;
    protected LogType a;
    protected String b;
    protected long c = System.currentTimeMillis();
    private long d;
    private long e;

    public e(String str) {
        this.b = str;
    }

    public LogType c() {
        return this.a;
    }

    public void a(LogType logType) {
        this.a = logType;
    }

    public String d() {
        return this.b;
    }

    public long e() {
        return this.c;
    }

    public long f() {
        return this.d;
    }

    public long g() {
        return this.e;
    }
}
