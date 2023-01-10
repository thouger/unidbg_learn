package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.util.a;

/* compiled from: MediaChunk */
public abstract class l extends d {
    public final long k;

    public abstract boolean i();

    public l(f fVar, h hVar, Format format, int i, Object obj, long j, long j2, long j3) {
        super(fVar, hVar, 1, format, i, obj, j, j2);
        a.a(format);
        this.k = j3;
    }

    public long h() {
        long j = this.k;
        if (j != -1) {
            return 1 + j;
        }
        return -1;
    }
}
