package com.cmic.sso.wy.utils;

/* compiled from: LoginProxy */
public class h {
    private static h a;
    private a b;

    /* compiled from: LoginProxy */
    public interface a {
        void a();
    }

    public static h a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h();
                }
            }
        }
        return a;
    }

    public a b() {
        return this.b;
    }

    public void c() {
        if (this.b != null) {
            this.b = null;
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
