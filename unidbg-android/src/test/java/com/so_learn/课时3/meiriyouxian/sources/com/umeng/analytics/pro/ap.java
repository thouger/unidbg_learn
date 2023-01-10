package com.umeng.analytics.pro;

/* compiled from: TApplicationException */
public class ap extends aw {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final bu j = new bu("TApplicationException");
    private static final bk k = new bk("message", (byte) 11, 1);
    private static final bk l = new bk("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i = 0;

    public ap() {
    }

    public ap(int i) {
        this.i = i;
    }

    public ap(int i, String str) {
        super(str);
        this.i = i;
    }

    public ap(String str) {
        super(str);
    }

    public int a() {
        return this.i;
    }

    public static ap a(bp bpVar) throws aw {
        bpVar.j();
        String str = null;
        int i = 0;
        while (true) {
            bk l2 = bpVar.l();
            if (l2.b == 0) {
                bpVar.k();
                return new ap(i, str);
            }
            short s = l2.c;
            if (s != 1) {
                if (s != 2) {
                    bs.a(bpVar, l2.b);
                } else if (l2.b == 8) {
                    i = bpVar.w();
                } else {
                    bs.a(bpVar, l2.b);
                }
            } else if (l2.b == 11) {
                str = bpVar.z();
            } else {
                bs.a(bpVar, l2.b);
            }
            bpVar.m();
        }
    }

    public void b(bp bpVar) throws aw {
        bpVar.a(j);
        if (getMessage() != null) {
            bpVar.a(k);
            bpVar.a(getMessage());
            bpVar.c();
        }
        bpVar.a(l);
        bpVar.a(this.i);
        bpVar.c();
        bpVar.d();
        bpVar.b();
    }
}
