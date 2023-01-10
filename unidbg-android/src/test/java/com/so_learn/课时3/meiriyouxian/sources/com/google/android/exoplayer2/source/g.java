package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.x;
import java.io.IOException;

/* compiled from: DeferredMediaPeriod */
public final class g implements l, l.a {
    public final m a;
    public final m.a b;
    private final b c;
    private l d;
    private l.a e;
    private long f;
    private a g;
    private boolean h;
    private long i = -9223372036854775807L;

    /* compiled from: DeferredMediaPeriod */
    public interface a {
        void a(m.a aVar, IOException iOException);
    }

    public g(m mVar, m.a aVar, b bVar, long j) {
        this.b = aVar;
        this.c = bVar;
        this.a = mVar;
        this.f = j;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(m.a aVar) {
        long d = d(this.f);
        this.d = this.a.a(aVar, this.c, d);
        if (this.e != null) {
            this.d.a(this, d);
        }
    }

    public void f() {
        l lVar = this.d;
        if (lVar != null) {
            this.a.a(lVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.e = aVar;
        l lVar = this.d;
        if (lVar != null) {
            lVar.a(this, d(this.f));
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        try {
            if (this.d != null) {
                this.d.ae_();
            } else {
                this.a.b();
            }
        } catch (IOException e) {
            a aVar = this.g;
            if (aVar == null) {
                throw e;
            } else if (!this.h) {
                this.h = true;
                aVar.a(this.b, e);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.d.b();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j) {
        long j2;
        long j3 = this.i;
        if (j3 == -9223372036854775807L || j != this.f) {
            j2 = j;
        } else {
            this.i = -9223372036854775807L;
            j2 = j3;
        }
        return this.d.a(eVarArr, zArr, rVarArr, zArr2, j2);
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        this.d.a(j, z);
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        return this.d.c();
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        return this.d.d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        return this.d.b(j);
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        return this.d.a(j, xVar);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        return this.d.e();
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.d.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        l lVar = this.d;
        return lVar != null && lVar.c(j);
    }

    /* renamed from: b */
    public void a(l lVar) {
        this.e.a((l.a) this);
    }

    @Override // com.google.android.exoplayer2.source.l.a
    public void a(l lVar) {
        this.e.a((l) this);
    }

    private long d(long j) {
        long j2 = this.i;
        return j2 != -9223372036854775807L ? j2 : j;
    }
}
