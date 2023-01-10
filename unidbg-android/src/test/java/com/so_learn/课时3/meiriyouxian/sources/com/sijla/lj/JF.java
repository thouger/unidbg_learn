package com.sijla.lj;

public abstract class JF {
    protected L L;

    public abstract int execute();

    public JF(L l) {
        this.L = l;
    }

    public c getParam(int i) {
        return this.L.C(i);
    }

    public void register(String str) {
        synchronized (this.L) {
            this.L.a(this);
            this.L.d(str);
        }
    }
}
