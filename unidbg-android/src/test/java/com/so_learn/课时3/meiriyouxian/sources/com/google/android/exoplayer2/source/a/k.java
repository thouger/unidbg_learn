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

/* compiled from: InitializationChunk */
public final class k extends d {
    private static final n a = new n();
    private final e b;
    private long k;
    private volatile boolean l;

    public k(f fVar, h hVar, Format format, int i, Object obj, e eVar) {
        super(fVar, hVar, 2, format, i, obj, -9223372036854775807L, -9223372036854775807L);
        this.b = eVar;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void a() {
        this.l = true;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void b() throws IOException, InterruptedException {
        h a2 = this.c.a(this.k);
        try {
            d dVar = new d(this.j, a2.e, this.j.a(a2));
            if (this.k == 0) {
                this.b.a(null, -9223372036854775807L, -9223372036854775807L);
            }
            try {
                g gVar = this.b.a;
                int i = 0;
                while (i == 0 && !this.l) {
                    i = gVar.a(dVar, a);
                }
                boolean z = true;
                if (i == 1) {
                    z = false;
                }
                a.b(z);
                this.k = dVar.c() - this.c.e;
            } catch (Throwable th) {
                this.k = dVar.c() - this.c.e;
                throw th;
            }
        } finally {
            z.a((f) this.j);
        }
    }
}
