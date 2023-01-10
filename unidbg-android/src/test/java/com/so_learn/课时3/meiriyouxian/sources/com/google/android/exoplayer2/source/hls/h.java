package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.d;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.metadata.id3.a;
import com.google.android.exoplayer2.source.a.l;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.w;
import com.google.android.exoplayer2.util.z;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: HlsMediaChunk */
/* access modifiers changed from: package-private */
public final class h extends l {
    private static final AtomicInteger m = new AtomicInteger();
    private g A;
    private l B;
    private int C;
    private int D;
    private boolean E;
    private volatile boolean F;
    private boolean G;
    public final int a;
    public final int b;
    public final c.a l;
    private final f n;
    private final com.google.android.exoplayer2.upstream.h o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final w s;
    private final boolean t;
    private final f u;
    private final List<Format> v;
    private final DrmInitData w;
    private final g x;
    private final a y;
    private final o z;

    public h(f fVar, f fVar2, com.google.android.exoplayer2.upstream.h hVar, com.google.android.exoplayer2.upstream.h hVar2, c.a aVar, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z, boolean z2, w wVar, h hVar3, DrmInitData drmInitData, byte[] bArr, byte[] bArr2) {
        super(a(fVar2, bArr, bArr2), hVar, aVar.b, i, obj, j, j2, j3);
        this.b = i2;
        this.o = hVar2;
        this.l = aVar;
        this.q = z2;
        this.s = wVar;
        boolean z3 = true;
        this.p = bArr != null;
        this.r = z;
        this.u = fVar;
        this.v = list;
        this.w = drmInitData;
        g gVar = null;
        if (hVar3 != null) {
            this.y = hVar3.y;
            this.z = hVar3.z;
            if (hVar3.l == aVar && hVar3.G) {
                z3 = false;
            }
            this.t = z3;
            if (hVar3.b == i2 && !this.t) {
                gVar = hVar3.A;
            }
        } else {
            this.y = new a();
            this.z = new o(10);
            this.t = false;
        }
        this.x = gVar;
        this.n = fVar2;
        this.a = m.getAndIncrement();
    }

    public void a(l lVar) {
        this.B = lVar;
    }

    @Override // com.google.android.exoplayer2.source.a.l
    public boolean i() {
        return this.G;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void a() {
        this.F = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public void b() throws IOException, InterruptedException {
        c();
        if (!this.F) {
            if (!this.r) {
                j();
            }
            this.G = true;
        }
    }

    /* JADX INFO: finally extract failed */
    private void c() throws IOException, InterruptedException {
        com.google.android.exoplayer2.upstream.h hVar;
        if (!this.E && (hVar = this.o) != null) {
            try {
                d a = a(this.n, hVar.a((long) this.C));
                int i = 0;
                while (i == 0) {
                    try {
                        if (this.F) {
                            break;
                        }
                        i = this.A.a(a, (n) null);
                    } catch (Throwable th) {
                        this.C = (int) (a.c() - this.o.e);
                        throw th;
                    }
                }
                this.C = (int) (a.c() - this.o.e);
                z.a(this.n);
                this.E = true;
            } catch (Throwable th2) {
                z.a(this.n);
                throw th2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f A[Catch:{ all -> 0x0072 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[SYNTHETIC, Splitter:B:18:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void j() throws java.io.IOException, java.lang.InterruptedException {
        /*
            r7 = this;
            boolean r0 = r7.p
            r1 = 0
            if (r0 == 0) goto L_0x000d
            com.google.android.exoplayer2.upstream.h r0 = r7.c
            int r2 = r7.D
            if (r2 == 0) goto L_0x0016
            r2 = 1
            goto L_0x0017
        L_0x000d:
            com.google.android.exoplayer2.upstream.h r0 = r7.c
            int r2 = r7.D
            long r2 = (long) r2
            com.google.android.exoplayer2.upstream.h r0 = r0.a(r2)
        L_0x0016:
            r2 = r1
        L_0x0017:
            boolean r3 = r7.q
            if (r3 != 0) goto L_0x0021
            com.google.android.exoplayer2.util.w r3 = r7.s
            r3.e()
            goto L_0x0037
        L_0x0021:
            com.google.android.exoplayer2.util.w r3 = r7.s
            long r3 = r3.a()
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0037
            com.google.android.exoplayer2.util.w r3 = r7.s
            long r4 = r7.h
            r3.a(r4)
        L_0x0037:
            com.google.android.exoplayer2.upstream.r r3 = r7.j     // Catch:{ all -> 0x0072 }
            com.google.android.exoplayer2.extractor.d r0 = r7.a(r3, r0)     // Catch:{ all -> 0x0072 }
            if (r2 == 0) goto L_0x0044
            int r2 = r7.D     // Catch:{ all -> 0x0072 }
            r0.b(r2)     // Catch:{ all -> 0x0072 }
        L_0x0044:
            if (r1 != 0) goto L_0x0060
            boolean r1 = r7.F     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0060
            com.google.android.exoplayer2.extractor.g r1 = r7.A     // Catch:{ all -> 0x0052 }
            r2 = 0
            int r1 = r1.a(r0, r2)     // Catch:{ all -> 0x0052 }
            goto L_0x0044
        L_0x0052:
            r1 = move-exception
            long r2 = r0.c()
            com.google.android.exoplayer2.upstream.h r0 = r7.c
            long r4 = r0.e
            long r2 = r2 - r4
            int r0 = (int) r2
            r7.D = r0
            throw r1
        L_0x0060:
            long r0 = r0.c()
            com.google.android.exoplayer2.upstream.h r2 = r7.c
            long r2 = r2.e
            long r0 = r0 - r2
            int r0 = (int) r0
            r7.D = r0
            com.google.android.exoplayer2.upstream.r r0 = r7.j
            com.google.android.exoplayer2.util.z.a(r0)
            return
        L_0x0072:
            r0 = move-exception
            com.google.android.exoplayer2.upstream.r r1 = r7.j
            com.google.android.exoplayer2.util.z.a(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.h.j():void");
    }

    private d a(f fVar, com.google.android.exoplayer2.upstream.h hVar) throws IOException, InterruptedException {
        d dVar = new d(fVar, hVar.e, fVar.a(hVar));
        if (this.A != null) {
            return dVar;
        }
        long a = a(dVar);
        dVar.a();
        Pair<g, Boolean> a2 = this.u.a(this.x, hVar.a, this.e, this.v, this.w, this.s, fVar.b(), dVar);
        this.A = a2.first;
        boolean z = true;
        boolean z2 = this.A == this.x;
        if (a2.second.booleanValue()) {
            this.B.b(a != -9223372036854775807L ? this.s.b(a) : this.h);
        }
        if (!z2 || this.o == null) {
            z = false;
        }
        this.E = z;
        this.B.a(this.a, this.t, z2);
        if (z2) {
            return dVar;
        }
        this.A.a(this.B);
        return dVar;
    }

    private long a(com.google.android.exoplayer2.extractor.h hVar) throws IOException, InterruptedException {
        hVar.a();
        try {
            hVar.c(this.z.a, 0, 10);
            this.z.a(10);
            if (this.z.l() != a.b) {
                return -9223372036854775807L;
            }
            this.z.d(3);
            int u = this.z.u();
            int i = u + 10;
            if (i > this.z.e()) {
                byte[] bArr = this.z.a;
                this.z.a(i);
                System.arraycopy(bArr, 0, this.z.a, 0, 10);
            }
            hVar.c(this.z.a, 10, u);
            Metadata a = this.y.a(this.z.a, u);
            if (a == null) {
                return -9223372036854775807L;
            }
            int a2 = a.a();
            for (int i2 = 0; i2 < a2; i2++) {
                Metadata.Entry a3 = a.a(i2);
                if (a3 instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) a3;
                    if ("com.apple.streaming.transportStreamTimestamp".equals(privFrame.a)) {
                        System.arraycopy(privFrame.b, 0, this.z.a, 0, 8);
                        this.z.a(8);
                        return this.z.r() & 8589934591L;
                    }
                }
            }
            return -9223372036854775807L;
        } catch (EOFException unused) {
        }
    }

    private static f a(f fVar, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new a(fVar, bArr, bArr2) : fVar;
    }
}
