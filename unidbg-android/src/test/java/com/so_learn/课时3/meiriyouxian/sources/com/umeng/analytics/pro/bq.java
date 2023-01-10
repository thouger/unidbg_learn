package com.umeng.analytics.pro;

/* compiled from: TProtocolException */
public class bq extends aw {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static final long h = 1;
    protected int g = 0;

    public bq() {
    }

    public bq(int i) {
        this.g = i;
    }

    public bq(int i, String str) {
        super(str);
        this.g = i;
    }

    public bq(String str) {
        super(str);
    }

    public bq(int i, Throwable th) {
        super(th);
        this.g = i;
    }

    public bq(Throwable th) {
        super(th);
    }

    public bq(String str, Throwable th) {
        super(str, th);
    }

    public bq(int i, String str, Throwable th) {
        super(str, th);
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
