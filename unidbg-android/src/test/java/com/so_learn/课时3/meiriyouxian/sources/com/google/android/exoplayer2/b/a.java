package com.google.android.exoplayer2.b;

/* compiled from: Buffer */
public abstract class a {
    private int a;

    public void a() {
        this.a = 0;
    }

    public final boolean af_() {
        return d(Integer.MIN_VALUE);
    }

    public final boolean c() {
        return d(4);
    }

    public final boolean d() {
        return d(1);
    }

    public final void b_(int i) {
        this.a = i;
    }

    public final void b(int i) {
        this.a = i | this.a;
    }

    public final void c(int i) {
        this.a = (~i) & this.a;
    }

    /* access modifiers changed from: protected */
    public final boolean d(int i) {
        return (this.a & i) == i;
    }
}
