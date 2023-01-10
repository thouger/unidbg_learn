package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.m;

/* compiled from: MediaPeriodInfo */
/* access modifiers changed from: package-private */
public final class n {
    public final m.a a;
    public final long b;
    public final long c;
    public final long d;
    public final boolean e;
    public final boolean f;

    n(m.a aVar, long j, long j2, long j3, boolean z, boolean z2) {
        this.a = aVar;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = z;
        this.f = z2;
    }

    public n a(long j) {
        return new n(this.a, j, this.c, this.d, this.e, this.f);
    }
}
