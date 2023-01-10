package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.d;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;

/* compiled from: SingleSampleMediaChunk */
public final class n extends a {
    private final int l;
    private final Format m;
    private long n;
    private boolean o;

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void a() {
    }

    public n(f fVar, h hVar, Format format, int i, Object obj, long j, long j2, long j3, int i2, Format format2) {
        super(fVar, hVar, format, i, obj, j, j2, -9223372036854775807L, -9223372036854775807L, j3);
        this.l = i2;
        this.m = format2;
    }

    @Override // com.google.android.exoplayer2.source.a.l
    public boolean i() {
        return this.o;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void b() throws IOException, InterruptedException {
        try {
            long a = this.j.a(this.c.a(this.n));
            if (a != -1) {
                a += this.n;
            }
            d dVar = new d(this.j, this.n, a);
            c c = c();
            c.a(0);
            q a2 = c.a(0, this.l);
            a2.a(this.m);
            for (int i = 0; i != -1; i = a2.a(dVar, Integer.MAX_VALUE, true)) {
                this.n += (long) i;
            }
            a2.a(this.h, 1, (int) this.n, 0, null);
            z.a((f) this.j);
            this.o = true;
        } catch (Throwable th) {
            z.a((f) this.j);
            throw th;
        }
    }
}
