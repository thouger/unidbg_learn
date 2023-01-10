package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.mp4.j;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.a.d;
import com.google.android.exoplayer2.source.a.i;
import com.google.android.exoplayer2.source.a.l;
import com.google.android.exoplayer2.source.a.m;
import com.google.android.exoplayer2.source.smoothstreaming.b;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.a;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.List;

/* compiled from: DefaultSsChunkSource */
public class a implements b {
    private final p a;
    private final int b;
    private final e c;
    private final com.google.android.exoplayer2.source.a.e[] d;
    private final f e;
    private com.google.android.exoplayer2.source.smoothstreaming.manifest.a f;
    private int g;
    private IOException h;

    @Override // com.google.android.exoplayer2.source.a.h
    public void a(d dVar) {
    }

    /* compiled from: DefaultSsChunkSource */
    /* renamed from: com.google.android.exoplayer2.source.smoothstreaming.a$a  reason: collision with other inner class name */
    public static final class C0093a implements b.a {
        private final f.a a;

        public C0093a(f.a aVar) {
            this.a = aVar;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.b.a
        public b a(p pVar, com.google.android.exoplayer2.source.smoothstreaming.manifest.a aVar, int i, e eVar, s sVar) {
            f a = this.a.a();
            if (sVar != null) {
                a.a(sVar);
            }
            return new a(pVar, aVar, i, eVar, a);
        }
    }

    public a(p pVar, com.google.android.exoplayer2.source.smoothstreaming.manifest.a aVar, int i, e eVar, f fVar) {
        this.a = pVar;
        this.f = aVar;
        this.b = i;
        this.c = eVar;
        this.e = fVar;
        a.b bVar = aVar.f[i];
        this.d = new com.google.android.exoplayer2.source.a.e[eVar.g()];
        for (int i2 = 0; i2 < this.d.length; i2++) {
            int b2 = eVar.b(i2);
            Format format = bVar.j[b2];
            this.d[i2] = new com.google.android.exoplayer2.source.a.e(new com.google.android.exoplayer2.extractor.mp4.e(3, null, new j(b2, bVar.a, bVar.c, -9223372036854775807L, aVar.g, format, 0, format.j != null ? aVar.e.c : null, bVar.a == 2 ? 4 : 0, null, null), null), bVar.a, format);
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public long a(long j, x xVar) {
        a.b bVar = this.f.f[this.b];
        int a = bVar.a(j);
        long a2 = bVar.a(a);
        return z.a(j, xVar, a2, (a2 >= j || a >= bVar.k + -1) ? a2 : bVar.a(a + 1));
    }

    @Override // com.google.android.exoplayer2.source.smoothstreaming.b
    public void a(com.google.android.exoplayer2.source.smoothstreaming.manifest.a aVar) {
        a.b bVar = this.f.f[this.b];
        int i = bVar.k;
        a.b bVar2 = aVar.f[this.b];
        if (i == 0 || bVar2.k == 0) {
            this.g += i;
        } else {
            int i2 = i - 1;
            long a = bVar2.a(0);
            if (bVar.a(i2) + bVar.b(i2) <= a) {
                this.g += i;
            } else {
                this.g += bVar.a(a);
            }
        }
        this.f = aVar;
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public void a() throws IOException {
        IOException iOException = this.h;
        if (iOException == null) {
            this.a.a();
            return;
        }
        throw iOException;
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public int a(long j, List<? extends l> list) {
        if (this.h != null || this.c.g() < 2) {
            return list.size();
        }
        return this.c.a(j, list);
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public final void a(long j, long j2, List<? extends l> list, com.google.android.exoplayer2.source.a.f fVar) {
        int i;
        long j3 = j2;
        if (this.h == null) {
            a.b bVar = this.f.f[this.b];
            if (bVar.k == 0) {
                fVar.b = !this.f.d;
                return;
            }
            if (list.isEmpty()) {
                i = bVar.a(j3);
            } else {
                i = (int) (((l) list.get(list.size() - 1)).h() - ((long) this.g));
                if (i < 0) {
                    this.h = new BehindLiveWindowException();
                    return;
                }
            }
            if (i >= bVar.k) {
                fVar.b = !this.f.d;
                return;
            }
            long j4 = j3 - j;
            long a = a(j);
            m[] mVarArr = new m[this.c.g()];
            for (int i2 = 0; i2 < mVarArr.length; i2++) {
                mVarArr[i2] = new b(bVar, this.c.b(i2), i);
            }
            this.c.a(j, j4, a, list, mVarArr);
            long a2 = bVar.a(i);
            long b2 = a2 + bVar.b(i);
            if (!list.isEmpty()) {
                j3 = -9223372036854775807L;
            }
            int i3 = i + this.g;
            int a3 = this.c.a();
            com.google.android.exoplayer2.source.a.e eVar = this.d[a3];
            fVar.a = a(this.c.h(), this.e, bVar.a(this.c.b(a3), i), null, i3, a2, b2, j3, this.c.b(), this.c.c(), eVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public boolean a(d dVar, boolean z, Exception exc, long j) {
        if (z && j != -9223372036854775807L) {
            e eVar = this.c;
            if (eVar.a(eVar.a(dVar.e), j)) {
                return true;
            }
        }
        return false;
    }

    private static l a(Format format, f fVar, Uri uri, String str, int i, long j, long j2, long j3, int i2, Object obj, com.google.android.exoplayer2.source.a.e eVar) {
        return new i(fVar, new h(uri, 0, -1, str), format, i2, obj, j, j2, j3, -9223372036854775807L, (long) i, 1, j, eVar);
    }

    private long a(long j) {
        if (!this.f.d) {
            return -9223372036854775807L;
        }
        a.b bVar = this.f.f[this.b];
        int i = bVar.k - 1;
        return (bVar.a(i) + bVar.b(i)) - j;
    }

    /* compiled from: DefaultSsChunkSource */
    private static final class b extends com.google.android.exoplayer2.source.a.b {
        private final a.b b;
        private final int c;

        public b(a.b bVar, int i, int i2) {
            super((long) i2, (long) (bVar.k - 1));
            this.b = bVar;
            this.c = i;
        }
    }
}
