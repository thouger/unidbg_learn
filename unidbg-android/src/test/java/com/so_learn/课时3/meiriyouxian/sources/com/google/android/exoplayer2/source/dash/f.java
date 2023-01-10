package com.google.android.exoplayer2.source.dash;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.o;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.a.d;
import com.google.android.exoplayer2.source.a.k;
import com.google.android.exoplayer2.source.a.l;
import com.google.android.exoplayer2.source.a.m;
import com.google.android.exoplayer2.source.a.n;
import com.google.android.exoplayer2.source.dash.a;
import com.google.android.exoplayer2.source.dash.a.i;
import com.google.android.exoplayer2.source.dash.h;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: DefaultDashChunkSource */
public class f implements a {
    protected final b[] a;
    private final p b;
    private final int[] c;
    private final e d;
    private final int e;
    private final com.google.android.exoplayer2.upstream.f f;
    private final long g;
    private final int h;
    private final h.c i;
    private com.google.android.exoplayer2.source.dash.a.b j;
    private int k;
    private IOException l;
    private boolean m;
    private long n = -9223372036854775807L;

    /* compiled from: DefaultDashChunkSource */
    public static final class a implements a.AbstractC0089a {
        private final f.a a;
        private final int b;

        public a(f.a aVar) {
            this(aVar, 1);
        }

        public a(f.a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.AbstractC0089a
        public a a(p pVar, com.google.android.exoplayer2.source.dash.a.b bVar, int i, int[] iArr, e eVar, int i2, long j, boolean z, boolean z2, h.c cVar, s sVar) {
            com.google.android.exoplayer2.upstream.f a = this.a.a();
            if (sVar != null) {
                a.a(sVar);
            }
            return new f(pVar, bVar, i, iArr, eVar, i2, a, j, this.b, z, z2, cVar);
        }
    }

    public f(p pVar, com.google.android.exoplayer2.source.dash.a.b bVar, int i, int[] iArr, e eVar, int i2, com.google.android.exoplayer2.upstream.f fVar, long j, int i3, boolean z, boolean z2, h.c cVar) {
        this.b = pVar;
        this.j = bVar;
        this.c = iArr;
        this.d = eVar;
        this.e = i2;
        this.f = fVar;
        this.k = i;
        this.g = j;
        this.h = i3;
        this.i = cVar;
        long c2 = bVar.c(i);
        ArrayList<i> b2 = b();
        this.a = new b[eVar.g()];
        for (int i4 = 0; i4 < this.a.length; i4++) {
            this.a[i4] = new b(c2, i2, b2.get(eVar.b(i4)), z, z2, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public long a(long j, x xVar) {
        b[] bVarArr = this.a;
        for (b bVar : bVarArr) {
            if (bVar.c != null) {
                long c2 = bVar.c(j);
                long a2 = bVar.a(c2);
                return z.a(j, xVar, a2, (a2 >= j || c2 >= ((long) (bVar.b() + -1))) ? a2 : bVar.a(c2 + 1));
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.dash.a
    public void a(com.google.android.exoplayer2.source.dash.a.b bVar, int i) {
        try {
            this.j = bVar;
            this.k = i;
            long c2 = this.j.c(this.k);
            ArrayList<i> b2 = b();
            for (int i2 = 0; i2 < this.a.length; i2++) {
                this.a[i2] = this.a[i2].a(c2, b2.get(this.d.b(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.l = e;
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public void a() throws IOException {
        IOException iOException = this.l;
        if (iOException == null) {
            this.b.a();
            return;
        }
        throw iOException;
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public int a(long j, List<? extends l> list) {
        if (this.l != null || this.d.g() < 2) {
            return list.size();
        }
        return this.d.a(j, list);
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public void a(long j, long j2, List<? extends l> list, com.google.android.exoplayer2.source.a.f fVar) {
        long j3;
        int i;
        m[] mVarArr;
        boolean z;
        if (this.l == null) {
            long j4 = j2 - j;
            long a2 = a(j);
            long b2 = com.google.android.exoplayer2.c.b(this.j.a) + com.google.android.exoplayer2.c.b(this.j.a(this.k).b) + j2;
            h.c cVar = this.i;
            if (cVar == null || !cVar.a(b2)) {
                long c2 = c();
                boolean z2 = true;
                l lVar = list.isEmpty() ? null : (l) list.get(list.size() - 1);
                m[] mVarArr2 = new m[this.d.g()];
                int i2 = 0;
                while (i2 < mVarArr2.length) {
                    b bVar = this.a[i2];
                    if (bVar.c == null) {
                        mVarArr2[i2] = m.a;
                        mVarArr = mVarArr2;
                        i = i2;
                        z = z2;
                        j3 = c2;
                    } else {
                        long a3 = bVar.a(this.j, this.k, c2);
                        long b3 = bVar.b(this.j, this.k, c2);
                        mVarArr = mVarArr2;
                        i = i2;
                        z = z2;
                        j3 = c2;
                        long a4 = a(bVar, lVar, j2, a3, b3);
                        if (a4 < a3) {
                            mVarArr[i] = m.a;
                        } else {
                            mVarArr[i] = new c(bVar, a4, b3);
                        }
                    }
                    i2 = i + 1;
                    z2 = z;
                    mVarArr2 = mVarArr;
                    c2 = j3;
                }
                this.d.a(j, j4, a2, list, mVarArr2);
                b bVar2 = this.a[this.d.a()];
                if (bVar2.a != null) {
                    i iVar = bVar2.b;
                    com.google.android.exoplayer2.source.dash.a.h c3 = bVar2.a.c() == null ? iVar.c() : null;
                    com.google.android.exoplayer2.source.dash.a.h d = bVar2.c == null ? iVar.d() : null;
                    if (!(c3 == null && d == null)) {
                        fVar.a = a(bVar2, this.f, this.d.h(), this.d.b(), this.d.c(), c3, d);
                        return;
                    }
                }
                long j5 = bVar2.d;
                long j6 = -9223372036854775807L;
                int i3 = (j5 > -9223372036854775807L ? 1 : (j5 == -9223372036854775807L ? 0 : -1));
                boolean z3 = i3 != 0 ? z2 : false;
                if (bVar2.b() == 0) {
                    fVar.b = z3;
                    return;
                }
                long a5 = bVar2.a(this.j, this.k, c2);
                long b4 = bVar2.b(this.j, this.k, c2);
                a(bVar2, b4);
                long a6 = a(bVar2, lVar, j2, a5, b4);
                if (a6 < a5) {
                    this.l = new BehindLiveWindowException();
                    return;
                }
                int i4 = (a6 > b4 ? 1 : (a6 == b4 ? 0 : -1));
                if (i4 > 0 || (this.m && i4 >= 0)) {
                    fVar.b = z3;
                } else if (!z3 || bVar2.a(a6) < j5) {
                    int min = (int) Math.min((long) this.h, (b4 - a6) + 1);
                    if (i3 != 0) {
                        while (min > 1 && bVar2.a((((long) min) + a6) - 1) >= j5) {
                            min--;
                        }
                    }
                    if (list.isEmpty()) {
                        j6 = j2;
                    }
                    fVar.a = a(bVar2, this.f, this.e, this.d.h(), this.d.b(), this.d.c(), a6, min, j6);
                } else {
                    fVar.b = true;
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public void a(d dVar) {
        o b2;
        if (dVar instanceof k) {
            int a2 = this.d.a(((k) dVar).e);
            b bVar = this.a[a2];
            if (bVar.c == null && (b2 = bVar.a.b()) != null) {
                this.a[a2] = bVar.a(new e((com.google.android.exoplayer2.extractor.b) b2, bVar.b.e));
            }
        }
        h.c cVar = this.i;
        if (cVar != null) {
            cVar.a(dVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.a.h
    public boolean a(d dVar, boolean z, Exception exc, long j) {
        b bVar;
        int b2;
        if (!z) {
            return false;
        }
        h.c cVar = this.i;
        if (cVar != null && cVar.b(dVar)) {
            return true;
        }
        if (!this.j.d && (dVar instanceof l) && (exc instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) exc).responseCode == 404 && (b2 = (bVar = this.a[this.d.a(dVar.e)]).b()) != -1 && b2 != 0) {
            if (((l) dVar).h() > (bVar.a() + ((long) b2)) - 1) {
                this.m = true;
                return true;
            }
        }
        if (j == -9223372036854775807L) {
            return false;
        }
        e eVar = this.d;
        return eVar.a(eVar.a(dVar.e), j);
    }

    private long a(b bVar, l lVar, long j, long j2, long j3) {
        if (lVar != null) {
            return lVar.h();
        }
        return z.a(bVar.c(j), j2, j3);
    }

    private ArrayList<i> b() {
        List<com.google.android.exoplayer2.source.dash.a.a> list = this.j.a(this.k).c;
        ArrayList<i> arrayList = new ArrayList<>();
        for (int i : this.c) {
            arrayList.addAll(list.get(i).c);
        }
        return arrayList;
    }

    private void a(b bVar, long j) {
        this.n = this.j.d ? bVar.b(j) : -9223372036854775807L;
    }

    private long c() {
        long currentTimeMillis;
        if (this.g != 0) {
            currentTimeMillis = SystemClock.elapsedRealtime() + this.g;
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis * 1000;
    }

    private long a(long j) {
        if (this.j.d && this.n != -9223372036854775807L) {
            return this.n - j;
        }
        return -9223372036854775807L;
    }

    /* access modifiers changed from: protected */
    public d a(b bVar, com.google.android.exoplayer2.upstream.f fVar, Format format, int i, Object obj, com.google.android.exoplayer2.source.dash.a.h hVar, com.google.android.exoplayer2.source.dash.a.h hVar2) {
        String str = bVar.b.d;
        if (hVar != null && (hVar2 = hVar.a(hVar2, str)) == null) {
            hVar2 = hVar;
        }
        return new k(fVar, new com.google.android.exoplayer2.upstream.h(hVar2.a(str), hVar2.a, hVar2.b, bVar.b.f()), format, i, obj, bVar.a);
    }

    /* access modifiers changed from: protected */
    public d a(b bVar, com.google.android.exoplayer2.upstream.f fVar, int i, Format format, int i2, Object obj, long j, int i3, long j2) {
        i iVar = bVar.b;
        long a2 = bVar.a(j);
        com.google.android.exoplayer2.source.dash.a.h d = bVar.d(j);
        String str = iVar.d;
        if (bVar.a == null) {
            return new n(fVar, new com.google.android.exoplayer2.upstream.h(d.a(str), d.a, d.b, iVar.f()), format, i2, obj, a2, bVar.b(j), j, i, format);
        }
        int i4 = 1;
        com.google.android.exoplayer2.source.dash.a.h hVar = d;
        int i5 = 1;
        while (i4 < i3) {
            com.google.android.exoplayer2.source.dash.a.h a3 = hVar.a(bVar.d(((long) i4) + j), str);
            if (a3 == null) {
                break;
            }
            i5++;
            i4++;
            hVar = a3;
        }
        long b2 = bVar.b((((long) i5) + j) - 1);
        long j3 = bVar.d;
        return new com.google.android.exoplayer2.source.a.i(fVar, new com.google.android.exoplayer2.upstream.h(hVar.a(str), hVar.a, hVar.b, iVar.f()), format, i2, obj, a2, b2, j2, (j3 == -9223372036854775807L || j3 > b2) ? -9223372036854775807L : j3, j, i5, -iVar.e, bVar.a);
    }

    /* compiled from: DefaultDashChunkSource */
    protected static final class c extends com.google.android.exoplayer2.source.a.b {
        private final b b;

        public c(b bVar, long j, long j2) {
            super(j, j2);
            this.b = bVar;
        }
    }

    /* compiled from: DefaultDashChunkSource */
    /* access modifiers changed from: protected */
    public static final class b {
        final com.google.android.exoplayer2.source.a.e a;
        public final i b;
        public final d c;
        private final long d;
        private final long e;

        b(long j, int i, i iVar, boolean z, boolean z2, q qVar) {
            this(j, iVar, a(i, iVar, z, z2, qVar), 0, iVar.e());
        }

        private b(long j, i iVar, com.google.android.exoplayer2.source.a.e eVar, long j2, d dVar) {
            this.d = j;
            this.b = iVar;
            this.e = j2;
            this.a = eVar;
            this.c = dVar;
        }

        /* access modifiers changed from: package-private */
        public b a(long j, i iVar) throws BehindLiveWindowException {
            long a;
            d e = this.b.e();
            d e2 = iVar.e();
            if (e == null) {
                return new b(j, iVar, this.a, this.e, e);
            }
            if (!e.b()) {
                return new b(j, iVar, this.a, this.e, e2);
            }
            int c = e.c(j);
            if (c == 0) {
                return new b(j, iVar, this.a, this.e, e2);
            }
            long a2 = (e.a() + ((long) c)) - 1;
            long a3 = e.a(a2) + e.b(a2, j);
            long a4 = e2.a();
            long a5 = e2.a(a4);
            long j2 = this.e;
            int i = (a3 > a5 ? 1 : (a3 == a5 ? 0 : -1));
            if (i == 0) {
                a = a2 + 1;
            } else if (i >= 0) {
                a = e.a(a5, j);
            } else {
                throw new BehindLiveWindowException();
            }
            return new b(j, iVar, this.a, j2 + (a - a4), e2);
        }

        /* access modifiers changed from: package-private */
        public b a(d dVar) {
            return new b(this.d, this.b, this.a, this.e, dVar);
        }

        public long a() {
            return this.c.a() + this.e;
        }

        public int b() {
            return this.c.c(this.d);
        }

        public long a(long j) {
            return this.c.a(j - this.e);
        }

        public long b(long j) {
            return a(j) + this.c.b(j - this.e, this.d);
        }

        public long c(long j) {
            return this.c.a(j, this.d) + this.e;
        }

        public com.google.android.exoplayer2.source.dash.a.h d(long j) {
            return this.c.b(j - this.e);
        }

        public long a(com.google.android.exoplayer2.source.dash.a.b bVar, int i, long j) {
            if (b() != -1 || bVar.f == -9223372036854775807L) {
                return a();
            }
            return Math.max(a(), c(((j - com.google.android.exoplayer2.c.b(bVar.a)) - com.google.android.exoplayer2.c.b(bVar.a(i).b)) - com.google.android.exoplayer2.c.b(bVar.f)));
        }

        public long b(com.google.android.exoplayer2.source.dash.a.b bVar, int i, long j) {
            long a;
            int b = b();
            if (b == -1) {
                a = c((j - com.google.android.exoplayer2.c.b(bVar.a)) - com.google.android.exoplayer2.c.b(bVar.a(i).b));
            } else {
                a = a() + ((long) b);
            }
            return a - 1;
        }

        private static boolean a(String str) {
            return str.startsWith("video/webm") || str.startsWith("audio/webm") || str.startsWith("application/webm");
        }

        private static boolean b(String str) {
            return com.google.android.exoplayer2.util.l.c(str) || "application/ttml+xml".equals(str);
        }

        private static com.google.android.exoplayer2.source.a.e a(int i, i iVar, boolean z, boolean z2, q qVar) {
            g gVar;
            List list;
            String str = iVar.c.f;
            if (b(str)) {
                return null;
            }
            if ("application/x-rawcc".equals(str)) {
                gVar = new com.google.android.exoplayer2.extractor.e.a(iVar.c);
            } else if (a(str)) {
                gVar = new com.google.android.exoplayer2.extractor.b.d(1);
            } else {
                int i2 = z ? 4 : 0;
                if (z2) {
                    list = Collections.singletonList(Format.a(null, "application/cea-608", 0, null));
                } else {
                    list = Collections.emptyList();
                }
                gVar = new com.google.android.exoplayer2.extractor.mp4.e(i2, null, null, null, list, qVar);
            }
            return new com.google.android.exoplayer2.source.a.e(gVar, i, iVar.c);
        }
    }
}
