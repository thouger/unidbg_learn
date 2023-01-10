package com.google.android.exoplayer2;

import com.google.android.exoplayer2.z;

/* compiled from: BasePlayer */
public abstract class a implements r {
    protected final z.b a = new z.b();

    public final void a(long j) {
        a(h(), j);
    }

    public final void a() {
        a(false);
    }

    public final long b() {
        z r = r();
        if (r.a()) {
            return -9223372036854775807L;
        }
        return r.a(h(), this.a).c();
    }
}
