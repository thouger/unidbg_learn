package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;

/* compiled from: BaseMediaChunk */
public abstract class a extends l {
    public final long a;
    public final long b;
    private c l;
    private int[] m;

    public a(f fVar, h hVar, Format format, int i, Object obj, long j, long j2, long j3, long j4, long j5) {
        super(fVar, hVar, format, i, obj, j, j2, j5);
        this.a = j3;
        this.b = j4;
    }

    public void a(c cVar) {
        this.l = cVar;
        this.m = cVar.a();
    }

    public final int a(int i) {
        return this.m[i];
    }

    /* access modifiers changed from: protected */
    public final c c() {
        return this.l;
    }
}
