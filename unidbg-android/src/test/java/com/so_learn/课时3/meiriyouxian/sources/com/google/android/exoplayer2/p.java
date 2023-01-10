package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.trackselection.h;
import com.google.android.exoplayer2.z;

/* compiled from: PlaybackInfo */
/* access modifiers changed from: package-private */
public final class p {
    private static final m.a n = new m.a(new Object());
    public final z a;
    public final Object b;
    public final m.a c;
    public final long d;
    public final long e;
    public final int f;
    public final boolean g;
    public final TrackGroupArray h;
    public final h i;
    public final m.a j;
    public volatile long k;
    public volatile long l;
    public volatile long m;

    public static p a(long j, h hVar) {
        return new p(z.a, null, n, j, -9223372036854775807L, 1, false, TrackGroupArray.a, hVar, n, j, 0, j);
    }

    public p(z zVar, Object obj, m.a aVar, long j, long j2, int i, boolean z, TrackGroupArray trackGroupArray, h hVar, m.a aVar2, long j3, long j4, long j5) {
        this.a = zVar;
        this.b = obj;
        this.c = aVar;
        this.d = j;
        this.e = j2;
        this.f = i;
        this.g = z;
        this.h = trackGroupArray;
        this.i = hVar;
        this.j = aVar2;
        this.k = j3;
        this.l = j4;
        this.m = j5;
    }

    public m.a a(boolean z, z.b bVar) {
        if (this.a.a()) {
            return n;
        }
        z zVar = this.a;
        return new m.a(this.a.a(zVar.a(zVar.b(z), bVar).f));
    }

    public p a(m.a aVar, long j, long j2) {
        return new p(this.a, this.b, aVar, j, aVar.a() ? j2 : -9223372036854775807L, this.f, this.g, this.h, this.i, aVar, j, 0, j);
    }

    public p a(m.a aVar, long j, long j2, long j3) {
        return new p(this.a, this.b, aVar, j, aVar.a() ? j2 : -9223372036854775807L, this.f, this.g, this.h, this.i, this.j, this.k, j3, j);
    }

    public p a(z zVar, Object obj) {
        return new p(zVar, obj, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
    }

    public p a(int i) {
        return new p(this.a, this.b, this.c, this.d, this.e, i, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
    }

    public p a(boolean z) {
        return new p(this.a, this.b, this.c, this.d, this.e, this.f, z, this.h, this.i, this.j, this.k, this.l, this.m);
    }

    public p a(TrackGroupArray trackGroupArray, h hVar) {
        return new p(this.a, this.b, this.c, this.d, this.e, this.f, this.g, trackGroupArray, hVar, this.j, this.k, this.l, this.m);
    }

    public p a(m.a aVar) {
        return new p(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, aVar, this.k, this.l, this.m);
    }
}
