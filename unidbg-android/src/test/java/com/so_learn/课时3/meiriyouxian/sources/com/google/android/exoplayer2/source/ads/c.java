package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.source.k;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.z;

/* compiled from: SinglePeriodAdTimeline */
public final class c extends k {
    private final a c;

    public c(z zVar, a aVar) {
        super(zVar);
        boolean z = false;
        a.b(zVar.c() == 1);
        a.b(zVar.b() == 1 ? true : z);
        this.c = aVar;
    }

    @Override // com.google.android.exoplayer2.source.k, com.google.android.exoplayer2.z
    public z.a a(int i, z.a aVar, boolean z) {
        this.b.a(i, aVar, z);
        aVar.a(aVar.a, aVar.b, aVar.c, aVar.d, aVar.c(), this.c);
        return aVar;
    }

    @Override // com.google.android.exoplayer2.source.k, com.google.android.exoplayer2.z
    public z.b a(int i, z.b bVar, boolean z, long j) {
        z.b a = super.a(i, bVar, z, j);
        if (a.i == -9223372036854775807L) {
            a.i = this.c.f;
        }
        return a;
    }
}
