package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.q;

/* compiled from: StandaloneMediaClock */
public final class s implements k {
    private final b a;
    private boolean b;
    private long c;
    private long d;
    private q e = q.a;

    public s(b bVar) {
        this.a = bVar;
    }

    public void a() {
        if (!this.b) {
            this.d = this.a.a();
            this.b = true;
        }
    }

    public void b() {
        if (this.b) {
            a(d());
            this.b = false;
        }
    }

    public void a(long j) {
        this.c = j;
        if (this.b) {
            this.d = this.a.a();
        }
    }

    @Override // com.google.android.exoplayer2.util.k
    public long d() {
        long j;
        long j2 = this.c;
        if (!this.b) {
            return j2;
        }
        long a = this.a.a() - this.d;
        if (this.e.b == 1.0f) {
            j = c.b(a);
        } else {
            j = this.e.a(a);
        }
        return j2 + j;
    }

    @Override // com.google.android.exoplayer2.util.k
    public q a(q qVar) {
        if (this.b) {
            a(d());
        }
        this.e = qVar;
        return qVar;
    }

    @Override // com.google.android.exoplayer2.util.k
    public q e() {
        return this.e;
    }
}
