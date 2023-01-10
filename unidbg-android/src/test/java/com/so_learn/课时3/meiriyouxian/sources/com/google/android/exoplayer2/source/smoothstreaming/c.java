package com.google.android.exoplayer2.source.smoothstreaming;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.a.g;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.source.s;
import com.google.android.exoplayer2.source.smoothstreaming.b;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.a;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: SsMediaPeriod */
/* access modifiers changed from: package-private */
public final class c implements l, s.a<g<b>> {
    private final b.a a;
    private final com.google.android.exoplayer2.upstream.s b;
    private final p c;
    private final o d;
    private final n.a e;
    private final com.google.android.exoplayer2.upstream.b f;
    private final TrackGroupArray g;
    private final e h;
    private l.a i;
    private a j;
    private g<b>[] k = a(0);
    private s l;
    private boolean m;

    public c(a aVar, b.a aVar2, com.google.android.exoplayer2.upstream.s sVar, e eVar, o oVar, n.a aVar3, p pVar, com.google.android.exoplayer2.upstream.b bVar) {
        this.j = aVar;
        this.a = aVar2;
        this.b = sVar;
        this.c = pVar;
        this.d = oVar;
        this.e = aVar3;
        this.f = bVar;
        this.h = eVar;
        this.g = b(aVar);
        this.l = eVar.a(this.k);
        aVar3.a();
    }

    public void a(a aVar) {
        this.j = aVar;
        for (g<b> gVar : this.k) {
            ((b) gVar.a()).a(aVar);
        }
        this.i.a((l.a) this);
    }

    public void f() {
        for (g<b> gVar : this.k) {
            gVar.f();
        }
        this.i = null;
        this.e.b();
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.i = aVar;
        aVar.a((l) this);
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        this.c.a();
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.g;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(com.google.android.exoplayer2.trackselection.e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < eVarArr.length; i++) {
            if (rVarArr[i] != null) {
                g gVar = (g) rVarArr[i];
                if (eVarArr[i] == null || !zArr[i]) {
                    gVar.f();
                    rVarArr[i] = null;
                } else {
                    arrayList.add(gVar);
                }
            }
            if (rVarArr[i] == null && eVarArr[i] != null) {
                g<b> a = a(eVarArr[i], j);
                arrayList.add(a);
                rVarArr[i] = a;
                zArr2[i] = true;
            }
        }
        this.k = a(arrayList.size());
        arrayList.toArray(this.k);
        this.l = this.h.a(this.k);
        return j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        for (g<b> gVar : this.k) {
            gVar.a(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.l.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        return this.l.c(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        return this.l.e();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        if (this.m) {
            return -9223372036854775807L;
        }
        this.e.c();
        this.m = true;
        return -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        return this.l.d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        for (g<b> gVar : this.k) {
            gVar.b(j);
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        g<b>[] gVarArr = this.k;
        for (g<b> gVar : gVarArr) {
            if (gVar.a == 2) {
                return gVar.a(j, xVar);
            }
        }
        return j;
    }

    public void a(g<b> gVar) {
        this.i.a((l.a) this);
    }

    private g<b> a(com.google.android.exoplayer2.trackselection.e eVar, long j) {
        int a = this.g.a(eVar.f());
        return new g<>(this.j.f[a].a, null, null, this.a.a(this.c, this.j, a, eVar, this.b), this, this.f, j, this.d, this.e);
    }

    private static TrackGroupArray b(a aVar) {
        TrackGroup[] trackGroupArr = new TrackGroup[aVar.f.length];
        for (int i = 0; i < aVar.f.length; i++) {
            trackGroupArr[i] = new TrackGroup(aVar.f[i].j);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    private static g<b>[] a(int i) {
        return new g[i];
    }
}
