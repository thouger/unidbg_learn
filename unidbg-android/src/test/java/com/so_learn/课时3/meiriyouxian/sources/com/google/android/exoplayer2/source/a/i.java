package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.d;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;

/* compiled from: ContainerMediaChunk */
public class i extends a {
    private static final n l = new n();
    private final int m;
    private final long n;
    private final e o;
    private long p;
    private volatile boolean q;
    private boolean r;

    public i(f fVar, h hVar, Format format, int i, Object obj, long j, long j2, long j3, long j4, long j5, int i2, long j6, e eVar) {
        super(fVar, hVar, format, i, obj, j, j2, j3, j4, j5);
        this.m = i2;
        this.n = j6;
        this.o = eVar;
    }

    @Override // com.google.android.exoplayer2.source.a.l
    public long h() {
        return this.k + ((long) this.m);
    }

    @Override // com.google.android.exoplayer2.source.a.l
    public boolean i() {
        return this.r;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public final void a() {
        this.q = true;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public final void b() throws IOException, InterruptedException {
        h a = this.c.a(this.p);
        try {
            d dVar = new d(this.j, a.e, this.j.a(a));
            if (this.p == 0) {
                c c = c();
                c.a(this.n);
                e eVar = this.o;
                long j = -9223372036854775807L;
                long j2 = this.a == -9223372036854775807L ? -9223372036854775807L : this.a - this.n;
                if (this.b != -9223372036854775807L) {
                    j = this.b - this.n;
                }
                eVar.a(c, j2, j);
            }
            try {
                g gVar = this.o.a;
                boolean z = false;
                int i = 0;
                while (i == 0 && !this.q) {
                    i = gVar.a(dVar, l);
                }
                if (i != 1) {
                    z = true;
                }
                a.b(z);
                this.p = dVar.c() - this.c.e;
                z.a((f) this.j);
                this.r = true;
            } catch (Throwable th) {
                this.p = dVar.c() - this.c.e;
                throw th;
            }
        } catch (Throwable th2) {
            z.a((f) this.j);
            throw th2;
        }
    }
}
