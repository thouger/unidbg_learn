package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.trackselection.f;
import com.google.android.exoplayer2.trackselection.g;
import com.google.android.exoplayer2.trackselection.h;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.i;

/* compiled from: MediaPeriodHolder */
/* access modifiers changed from: package-private */
public final class m {
    public final l a;
    public final Object b;
    public final r[] c;
    public final boolean[] d;
    public boolean e;
    public boolean f;
    public n g;
    public m h;
    public TrackGroupArray i;
    public h j;
    private final u[] k;
    private final g l;
    private final com.google.android.exoplayer2.source.m m;
    private long n;
    private h o;

    public m(u[] uVarArr, long j, g gVar, b bVar, com.google.android.exoplayer2.source.m mVar, n nVar) {
        this.k = uVarArr;
        this.n = j - nVar.b;
        this.l = gVar;
        this.m = mVar;
        this.b = a.a(nVar.a.a);
        this.g = nVar;
        this.c = new r[uVarArr.length];
        this.d = new boolean[uVarArr.length];
        l a = mVar.a(nVar.a, bVar, nVar.b);
        this.a = nVar.a.e != Long.MIN_VALUE ? new com.google.android.exoplayer2.source.b(a, true, 0, nVar.a.e) : a;
    }

    public long a(long j) {
        return j + a();
    }

    public long b(long j) {
        return j - a();
    }

    public long a() {
        return this.n;
    }

    public long b() {
        return this.g.b + this.n;
    }

    public boolean c() {
        return this.e && (!this.f || this.a.d() == Long.MIN_VALUE);
    }

    public long d() {
        if (!this.e) {
            return this.g.b;
        }
        long d = this.f ? this.a.d() : Long.MIN_VALUE;
        return d == Long.MIN_VALUE ? this.g.d : d;
    }

    public long e() {
        if (!this.e) {
            return 0;
        }
        return this.a.e();
    }

    public void a(float f) throws ExoPlaybackException {
        this.e = true;
        this.i = this.a.b();
        b(f);
        long a = a(this.g.b, false);
        this.n += this.g.b - a;
        this.g = this.g.a(a);
    }

    public void c(long j) {
        if (this.e) {
            this.a.a(b(j));
        }
    }

    public void d(long j) {
        this.a.c(b(j));
    }

    public boolean b(float f) throws ExoPlaybackException {
        h a = this.l.a(this.k, this.i);
        if (a.a(this.o)) {
            return false;
        }
        this.j = a;
        e[] a2 = this.j.c.a();
        for (e eVar : a2) {
            if (eVar != null) {
                eVar.a(f);
            }
        }
        return true;
    }

    public long a(long j, boolean z) {
        return a(j, z, new boolean[this.k.length]);
    }

    public long a(long j, boolean z, boolean[] zArr) {
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= this.j.a) {
                break;
            }
            boolean[] zArr2 = this.d;
            if (z || !this.j.a(this.o, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        a(this.c);
        a(this.j);
        f fVar = this.j.c;
        long a = this.a.a(fVar.a(), this.d, this.c, zArr, j);
        b(this.c);
        this.f = false;
        int i2 = 0;
        while (true) {
            r[] rVarArr = this.c;
            if (i2 >= rVarArr.length) {
                return a;
            }
            if (rVarArr[i2] != null) {
                a.b(this.j.a(i2));
                if (this.k[i2].a() != 6) {
                    this.f = true;
                }
            } else {
                a.b(fVar.a(i2) == null);
            }
            i2++;
        }
    }

    public void f() {
        a((h) null);
        try {
            if (this.g.a.e != Long.MIN_VALUE) {
                this.m.a(((com.google.android.exoplayer2.source.b) this.a).a);
            } else {
                this.m.a(this.a);
            }
        } catch (RuntimeException e) {
            i.b("MediaPeriodHolder", "Period release failed.", e);
        }
    }

    private void a(h hVar) {
        h hVar2 = this.o;
        if (hVar2 != null) {
            c(hVar2);
        }
        this.o = hVar;
        h hVar3 = this.o;
        if (hVar3 != null) {
            b(hVar3);
        }
    }

    private void b(h hVar) {
        for (int i = 0; i < hVar.a; i++) {
            boolean a = hVar.a(i);
            e a2 = hVar.c.a(i);
            if (a && a2 != null) {
                a2.d();
            }
        }
    }

    private void c(h hVar) {
        for (int i = 0; i < hVar.a; i++) {
            boolean a = hVar.a(i);
            e a2 = hVar.c.a(i);
            if (a && a2 != null) {
                a2.e();
            }
        }
    }

    private void a(r[] rVarArr) {
        int i = 0;
        while (true) {
            u[] uVarArr = this.k;
            if (i < uVarArr.length) {
                if (uVarArr[i].a() == 6) {
                    rVarArr[i] = null;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void b(r[] rVarArr) {
        int i = 0;
        while (true) {
            u[] uVarArr = this.k;
            if (i < uVarArr.length) {
                if (uVarArr[i].a() == 6 && this.j.a(i)) {
                    rVarArr[i] = new com.google.android.exoplayer2.source.h();
                }
                i++;
            } else {
                return;
            }
        }
    }
}
