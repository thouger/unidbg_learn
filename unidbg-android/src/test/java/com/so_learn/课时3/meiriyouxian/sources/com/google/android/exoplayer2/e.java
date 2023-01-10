package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.b;
import com.google.android.exoplayer2.util.k;
import com.google.android.exoplayer2.util.s;

/* compiled from: DefaultMediaClock */
/* access modifiers changed from: package-private */
public final class e implements k {
    private final s a;
    private final a b;
    private t c;
    private k d;

    /* compiled from: DefaultMediaClock */
    public interface a {
        void a(q qVar);
    }

    public e(a aVar, b bVar) {
        this.b = aVar;
        this.a = new s(bVar);
    }

    public void a() {
        this.a.a();
    }

    public void b() {
        this.a.b();
    }

    public void a(long j) {
        this.a.a(j);
    }

    public void a(t tVar) throws ExoPlaybackException {
        k kVar;
        k c = tVar.c();
        if (c != null && c != (kVar = this.d)) {
            if (kVar == null) {
                this.d = c;
                this.c = tVar;
                this.d.a(this.a.e());
                f();
                return;
            }
            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
        }
    }

    public void b(t tVar) {
        if (tVar == this.c) {
            this.d = null;
            this.c = null;
        }
    }

    public long c() {
        if (!g()) {
            return this.a.d();
        }
        f();
        return this.d.d();
    }

    @Override // com.google.android.exoplayer2.util.k
    public long d() {
        if (g()) {
            return this.d.d();
        }
        return this.a.d();
    }

    @Override // com.google.android.exoplayer2.util.k
    public q a(q qVar) {
        k kVar = this.d;
        if (kVar != null) {
            qVar = kVar.a(qVar);
        }
        this.a.a(qVar);
        this.b.a(qVar);
        return qVar;
    }

    @Override // com.google.android.exoplayer2.util.k
    public q e() {
        k kVar = this.d;
        if (kVar != null) {
            return kVar.e();
        }
        return this.a.e();
    }

    private void f() {
        this.a.a(this.d.d());
        q e = this.d.e();
        if (!e.equals(this.a.e())) {
            this.a.a(e);
            this.b.a(e);
        }
    }

    private boolean g() {
        t tVar = this.c;
        return tVar != null && !tVar.v() && (this.c.u() || !this.c.g());
    }
}
