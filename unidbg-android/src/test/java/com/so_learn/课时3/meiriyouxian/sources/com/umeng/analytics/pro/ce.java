package com.umeng.analytics.pro;

/* compiled from: TTransportException */
public class ce extends aw {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static final long g = 1;
    protected int f = 0;

    public ce() {
    }

    public ce(int i) {
        this.f = i;
    }

    public ce(int i, String str) {
        super(str);
        this.f = i;
    }

    public ce(String str) {
        super(str);
    }

    public ce(int i, Throwable th) {
        super(th);
        this.f = i;
    }

    public ce(Throwable th) {
        super(th);
    }

    public ce(String str, Throwable th) {
        super(str, th);
    }

    public ce(int i, String str, Throwable th) {
        super(str, th);
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
