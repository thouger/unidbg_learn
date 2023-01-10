package com.google.android.exoplayer2.source;

import android.app.job.JobInfo;
import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.o;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.q;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.r;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: ExtractorMediaPeriod */
final class i implements com.google.android.exoplayer2.extractor.i, l, q.b, Loader.a<a>, Loader.e {
    private int A;
    private long B;
    private long C;
    private long D;
    private long E;
    private boolean F;
    private int G;
    private boolean H;
    private boolean I;
    private final Uri a;
    private final f b;
    private final o c;
    private final n.a d;
    private final c e;
    private final com.google.android.exoplayer2.upstream.b f;
    private final String g;
    private final long h;
    private final Loader i = new Loader("Loader:ExtractorMediaPeriod");
    private final b j;
    private final com.google.android.exoplayer2.util.e k;
    private final Runnable l;
    private final Runnable m;
    private final Handler n;
    private l.a o;
    private com.google.android.exoplayer2.extractor.o p;
    private q[] q;
    private int[] r;
    private boolean s;
    private boolean t;
    private d u;
    private boolean v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* compiled from: ExtractorMediaPeriod */
    /* access modifiers changed from: package-private */
    public interface c {
        void a(long j, boolean z);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
    }

    public i(Uri uri, f fVar, g[] gVarArr, o oVar, n.a aVar, c cVar, com.google.android.exoplayer2.upstream.b bVar, String str, int i) {
        this.a = uri;
        this.b = fVar;
        this.c = oVar;
        this.d = aVar;
        this.e = cVar;
        this.f = bVar;
        this.g = str;
        this.h = (long) i;
        this.j = new b(gVarArr);
        this.k = new com.google.android.exoplayer2.util.e();
        this.l = new $$Lambda$i$ZbOtwl6VguGvU5TTjsmi7gywRdU(this);
        this.m = new $$Lambda$i$nY4C2wNmjpAA2Lc0Obqq0B3fs(this);
        this.n = new Handler();
        this.r = new int[0];
        this.q = new q[0];
        this.E = -9223372036854775807L;
        this.C = -1;
        this.B = -9223372036854775807L;
        this.w = 1;
        aVar.a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void p() {
        if (!this.I) {
            ((l.a) com.google.android.exoplayer2.util.a.a(this.o)).a((l.a) this);
        }
    }

    public void f() {
        if (this.t) {
            for (q qVar : this.q) {
                qVar.n();
            }
        }
        this.i.a(this);
        this.n.removeCallbacksAndMessages(null);
        this.o = null;
        this.I = true;
        this.d.b();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.e
    public void g() {
        for (q qVar : this.q) {
            qVar.a();
        }
        this.j.a();
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.o = aVar;
        this.k.a();
        l();
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        h();
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return k().b;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(com.google.android.exoplayer2.trackselection.e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j) {
        d k = k();
        TrackGroupArray trackGroupArray = k.b;
        boolean[] zArr3 = k.d;
        int i = this.A;
        int i2 = 0;
        for (int i3 = 0; i3 < eVarArr.length; i3++) {
            if (rVarArr[i3] != null && (eVarArr[i3] == null || !zArr[i3])) {
                int i4 = ((e) rVarArr[i3]).b;
                com.google.android.exoplayer2.util.a.b(zArr3[i4]);
                this.A--;
                zArr3[i4] = false;
                rVarArr[i3] = null;
            }
        }
        boolean z = !this.x ? j != 0 : i == 0;
        for (int i5 = 0; i5 < eVarArr.length; i5++) {
            if (rVarArr[i5] == null && eVarArr[i5] != null) {
                com.google.android.exoplayer2.trackselection.e eVar = eVarArr[i5];
                com.google.android.exoplayer2.util.a.b(eVar.g() == 1);
                com.google.android.exoplayer2.util.a.b(eVar.b(0) == 0);
                int a2 = trackGroupArray.a(eVar.f());
                com.google.android.exoplayer2.util.a.b(!zArr3[a2]);
                this.A++;
                zArr3[a2] = true;
                rVarArr[i5] = new e(a2);
                zArr2[i5] = true;
                if (!z) {
                    q qVar = this.q[a2];
                    qVar.l();
                    z = qVar.b(j, true, true) == -1 && qVar.f() != 0;
                }
            }
        }
        if (this.A == 0) {
            this.F = false;
            this.y = false;
            if (this.i.b()) {
                q[] qVarArr = this.q;
                int length = qVarArr.length;
                while (i2 < length) {
                    qVarArr[i2].n();
                    i2++;
                }
                this.i.c();
            } else {
                q[] qVarArr2 = this.q;
                int length2 = qVarArr2.length;
                while (i2 < length2) {
                    qVarArr2[i2].a();
                    i2++;
                }
            }
        } else if (z) {
            j = b(j);
            while (i2 < rVarArr.length) {
                if (rVarArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.x = true;
        return j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        if (!o()) {
            boolean[] zArr = k().d;
            int length = this.q.length;
            for (int i = 0; i < length; i++) {
                this.q[i].a(j, z, zArr[i]);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        if (this.H || this.F) {
            return false;
        }
        if (this.t && this.A == 0) {
            return false;
        }
        boolean a2 = this.k.a();
        if (this.i.b()) {
            return a2;
        }
        l();
        return true;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        if (this.A == 0) {
            return Long.MIN_VALUE;
        }
        return d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        if (!this.z) {
            this.d.c();
            this.z = true;
        }
        if (!this.y) {
            return -9223372036854775807L;
        }
        if (!this.H && m() <= this.G) {
            return -9223372036854775807L;
        }
        this.y = false;
        return this.D;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        long j;
        boolean[] zArr = k().c;
        if (this.H) {
            return Long.MIN_VALUE;
        }
        if (o()) {
            return this.E;
        }
        if (this.v) {
            int length = this.q.length;
            j = Long.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                if (zArr[i] && !this.q[i].j()) {
                    j = Math.min(j, this.q[i].i());
                }
            }
        } else {
            j = Long.MAX_VALUE;
        }
        if (j == Long.MAX_VALUE) {
            j = n();
        }
        return j == Long.MIN_VALUE ? this.D : j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        d k = k();
        com.google.android.exoplayer2.extractor.o oVar = k.a;
        boolean[] zArr = k.c;
        if (!oVar.a()) {
            j = 0;
        }
        this.y = false;
        this.D = j;
        if (o()) {
            this.E = j;
            return j;
        } else if (this.w != 7 && a(zArr, j)) {
            return j;
        } else {
            this.F = false;
            this.E = j;
            this.H = false;
            if (this.i.b()) {
                this.i.c();
            } else {
                for (q qVar : this.q) {
                    qVar.a();
                }
            }
            return j;
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        com.google.android.exoplayer2.extractor.o oVar = k().a;
        if (!oVar.a()) {
            return 0;
        }
        o.a a2 = oVar.a(j);
        return z.a(j, xVar, a2.a.b, a2.b.b);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i) {
        return !i() && (this.H || this.q[i].d());
    }

    /* access modifiers changed from: package-private */
    public void h() throws IOException {
        this.i.a(this.c.a(this.w));
    }

    /* access modifiers changed from: package-private */
    public int a(int i, k kVar, com.google.android.exoplayer2.b.e eVar, boolean z) {
        if (i()) {
            return -3;
        }
        b(i);
        int a2 = this.q[i].a(kVar, eVar, z, this.H, this.D);
        if (a2 == -3) {
            c(i);
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public int a(int i, long j) {
        int i2 = 0;
        if (i()) {
            return 0;
        }
        b(i);
        q qVar = this.q[i];
        if (!this.H || j <= qVar.i()) {
            int b2 = qVar.b(j, true, true);
            if (b2 != -1) {
                i2 = b2;
            }
        } else {
            i2 = qVar.o();
        }
        if (i2 == 0) {
            c(i);
        }
        return i2;
    }

    private void b(int i) {
        d k = k();
        boolean[] zArr = k.e;
        if (!zArr[i]) {
            Format a2 = k.b.a(i).a(0);
            this.d.a(com.google.android.exoplayer2.util.l.g(a2.g), a2, 0, (Object) null, this.D);
            zArr[i] = true;
        }
    }

    private void c(int i) {
        boolean[] zArr = k().c;
        if (this.F && zArr[i] && !this.q[i].d()) {
            this.E = 0;
            this.F = false;
            this.y = true;
            this.D = 0;
            this.G = 0;
            for (q qVar : this.q) {
                qVar.a();
            }
            ((l.a) com.google.android.exoplayer2.util.a.a(this.o)).a((l.a) this);
        }
    }

    private boolean i() {
        return this.y || o();
    }

    public void a(a aVar, long j, long j2) {
        if (this.B == -9223372036854775807L) {
            com.google.android.exoplayer2.extractor.o oVar = (com.google.android.exoplayer2.extractor.o) com.google.android.exoplayer2.util.a.a(this.p);
            long n = n();
            this.B = n == Long.MIN_VALUE ? 0 : n + JobInfo.MIN_BACKOFF_MILLIS;
            this.e.a(this.B, oVar.a());
        }
        this.d.a(aVar.k, aVar.c.f(), aVar.c.g(), 1, -1, null, 0, null, aVar.j, this.B, j, j2, aVar.c.e());
        a(aVar);
        this.H = true;
        ((l.a) com.google.android.exoplayer2.util.a.a(this.o)).a((l.a) this);
    }

    public void a(a aVar, long j, long j2, boolean z) {
        this.d.b(aVar.k, aVar.c.f(), aVar.c.g(), 1, -1, null, 0, null, aVar.j, this.B, j, j2, aVar.c.e());
        if (!z) {
            a(aVar);
            for (q qVar : this.q) {
                qVar.a();
            }
            if (this.A > 0) {
                ((l.a) com.google.android.exoplayer2.util.a.a(this.o)).a((l.a) this);
            }
        }
    }

    public Loader.b a(a aVar, long j, long j2, IOException iOException, int i) {
        Loader.b bVar;
        boolean z;
        a aVar2;
        a(aVar);
        long b2 = this.c.b(this.w, this.B, iOException, i);
        if (b2 == -9223372036854775807L) {
            bVar = Loader.d;
        } else {
            int m = m();
            if (m > this.G) {
                aVar2 = aVar;
                z = true;
            } else {
                z = false;
                aVar2 = aVar;
            }
            bVar = a(aVar2, m) ? Loader.a(z, b2) : Loader.c;
        }
        this.d.a(aVar.k, aVar.c.f(), aVar.c.g(), 1, -1, null, 0, null, aVar.j, this.B, j, j2, aVar.c.e(), iOException, !bVar.a());
        return bVar;
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public com.google.android.exoplayer2.extractor.q a(int i, int i2) {
        int length = this.q.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.r[i3] == i) {
                return this.q[i3];
            }
        }
        q qVar = new q(this.f);
        qVar.a(this);
        int i4 = length + 1;
        this.r = Arrays.copyOf(this.r, i4);
        this.r[length] = i;
        q[] qVarArr = (q[]) Arrays.copyOf(this.q, i4);
        qVarArr[length] = qVar;
        this.q = (q[]) z.a((Object[]) qVarArr);
        return qVar;
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a() {
        this.s = true;
        this.n.post(this.l);
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a(com.google.android.exoplayer2.extractor.o oVar) {
        this.p = oVar;
        this.n.post(this.l);
    }

    @Override // com.google.android.exoplayer2.source.q.b
    public void a(Format format) {
        this.n.post(this.l);
    }

    /* access modifiers changed from: private */
    public void j() {
        com.google.android.exoplayer2.extractor.o oVar = this.p;
        if (!(this.I || this.t || !this.s || oVar == null)) {
            for (q qVar : this.q) {
                if (qVar.h() == null) {
                    return;
                }
            }
            this.k.b();
            int length = this.q.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length];
            boolean[] zArr = new boolean[length];
            this.B = oVar.b();
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    break;
                }
                Format h = this.q[i].h();
                trackGroupArr[i] = new TrackGroup(h);
                String str = h.g;
                if (!com.google.android.exoplayer2.util.l.b(str) && !com.google.android.exoplayer2.util.l.a(str)) {
                    z = false;
                }
                zArr[i] = z;
                this.v = z | this.v;
                i++;
            }
            this.w = (this.C == -1 && oVar.b() == -9223372036854775807L) ? 7 : 1;
            this.u = new d(oVar, new TrackGroupArray(trackGroupArr), zArr);
            this.t = true;
            this.e.a(this.B, oVar.a());
            ((l.a) com.google.android.exoplayer2.util.a.a(this.o)).a((l) this);
        }
    }

    private d k() {
        return (d) com.google.android.exoplayer2.util.a.a(this.u);
    }

    private void a(a aVar) {
        if (this.C == -1) {
            this.C = aVar.l;
        }
    }

    private void l() {
        a aVar = new a(this.a, this.b, this.j, this, this.k);
        if (this.t) {
            com.google.android.exoplayer2.extractor.o oVar = k().a;
            com.google.android.exoplayer2.util.a.b(o());
            long j = this.B;
            if (j == -9223372036854775807L || this.E < j) {
                aVar.a(oVar.a(this.E).a.c, this.E);
                this.E = -9223372036854775807L;
            } else {
                this.H = true;
                this.E = -9223372036854775807L;
                return;
            }
        }
        this.G = m();
        this.d.a(aVar.k, 1, -1, (Format) null, 0, (Object) null, aVar.j, this.B, this.i.a(aVar, this, this.c.a(this.w)));
    }

    private boolean a(a aVar, int i) {
        com.google.android.exoplayer2.extractor.o oVar;
        if (this.C == -1 && ((oVar = this.p) == null || oVar.b() == -9223372036854775807L)) {
            if (!this.t || i()) {
                this.y = this.t;
                this.D = 0;
                this.G = 0;
                for (q qVar : this.q) {
                    qVar.a();
                }
                aVar.a(0, 0);
                return true;
            }
            this.F = true;
            return false;
        }
        this.G = i;
        return true;
    }

    private boolean a(boolean[] zArr, long j) {
        int length = this.q.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length) {
                return true;
            }
            q qVar = this.q[i];
            qVar.l();
            if (qVar.b(j, true, false) == -1) {
                z = false;
            }
            if (z || (!zArr[i] && this.v)) {
                i++;
            }
        }
        return false;
    }

    private int m() {
        int i = 0;
        for (q qVar : this.q) {
            i += qVar.c();
        }
        return i;
    }

    private long n() {
        long j = Long.MIN_VALUE;
        for (q qVar : this.q) {
            j = Math.max(j, qVar.i());
        }
        return j;
    }

    private boolean o() {
        return this.E != -9223372036854775807L;
    }

    /* compiled from: ExtractorMediaPeriod */
    private final class e implements r {
        private final int b;

        public e(int i) {
            this.b = i;
        }

        @Override // com.google.android.exoplayer2.source.r
        public boolean b() {
            return i.this.a(this.b);
        }

        @Override // com.google.android.exoplayer2.source.r
        public void c() throws IOException {
            i.this.h();
        }

        @Override // com.google.android.exoplayer2.source.r
        public int a(k kVar, com.google.android.exoplayer2.b.e eVar, boolean z) {
            return i.this.a(this.b, kVar, eVar, z);
        }

        @Override // com.google.android.exoplayer2.source.r
        public int b_(long j) {
            return i.this.a(this.b, j);
        }
    }

    /* compiled from: ExtractorMediaPeriod */
    /* access modifiers changed from: package-private */
    public final class a implements Loader.d {
        private final Uri b;
        private final r c;
        private final b d;
        private final com.google.android.exoplayer2.extractor.i e;
        private final com.google.android.exoplayer2.util.e f;
        private final com.google.android.exoplayer2.extractor.n g = new com.google.android.exoplayer2.extractor.n();
        private volatile boolean h;
        private boolean i = true;
        private long j;
        private h k;
        private long l = -1;

        public a(Uri uri, f fVar, b bVar, com.google.android.exoplayer2.extractor.i iVar, com.google.android.exoplayer2.util.e eVar) {
            this.b = uri;
            this.c = new r(fVar);
            this.d = bVar;
            this.e = iVar;
            this.f = eVar;
            this.k = new h(uri, this.g.a, -1, i.this.g);
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.d
        public void a() {
            this.h = true;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.d
        public void b() throws IOException, InterruptedException {
            Throwable th;
            int i = 0;
            while (i == 0 && !this.h) {
                com.google.android.exoplayer2.extractor.d dVar = null;
                try {
                    long j = this.g.a;
                    this.k = new h(this.b, j, -1, i.this.g);
                    this.l = this.c.a(this.k);
                    if (this.l != -1) {
                        this.l += j;
                    }
                    Uri uri = (Uri) com.google.android.exoplayer2.util.a.a(this.c.a());
                    com.google.android.exoplayer2.extractor.d dVar2 = new com.google.android.exoplayer2.extractor.d(this.c, j, this.l);
                    try {
                        g a = this.d.a(dVar2, this.e, uri);
                        if (this.i) {
                            a.a(j, this.j);
                            this.i = false;
                        }
                        while (i == 0 && !this.h) {
                            this.f.c();
                            i = a.a(dVar2, this.g);
                            if (dVar2.c() > i.this.h + j) {
                                j = dVar2.c();
                                this.f.b();
                                i.this.n.post(i.this.m);
                            }
                        }
                        if (i == 1) {
                            i = 0;
                        } else {
                            this.g.a = dVar2.c();
                        }
                        z.a((f) this.c);
                    } catch (Throwable th2) {
                        th = th2;
                        dVar = dVar2;
                        this.g.a = dVar.c();
                        z.a((f) this.c);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (!(i == 1 || dVar == null)) {
                        this.g.a = dVar.c();
                    }
                    z.a((f) this.c);
                    throw th;
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(long j, long j2) {
            this.g.a = j;
            this.j = j2;
            this.i = true;
        }
    }

    /* compiled from: ExtractorMediaPeriod */
    /* access modifiers changed from: private */
    public static final class b {
        private final g[] a;
        private g b;

        public b(g[] gVarArr) {
            this.a = gVarArr;
        }

        public g a(com.google.android.exoplayer2.extractor.h hVar, com.google.android.exoplayer2.extractor.i iVar, Uri uri) throws IOException, InterruptedException {
            g gVar = this.b;
            if (gVar != null) {
                return gVar;
            }
            g[] gVarArr = this.a;
            int length = gVarArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                g gVar2 = gVarArr[i];
                try {
                    if (gVar2.a(hVar)) {
                        this.b = gVar2;
                        hVar.a();
                        break;
                    }
                    hVar.a();
                    i++;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    hVar.a();
                    throw th;
                }
            }
            g gVar3 = this.b;
            if (gVar3 != null) {
                gVar3.a(iVar);
                return this.b;
            }
            throw new UnrecognizedInputFormatException("None of the available extractors (" + z.b(this.a) + ") could read the stream.", uri);
        }

        public void a() {
            g gVar = this.b;
            if (gVar != null) {
                gVar.c();
                this.b = null;
            }
        }
    }

    /* compiled from: ExtractorMediaPeriod */
    /* access modifiers changed from: private */
    public static final class d {
        public final com.google.android.exoplayer2.extractor.o a;
        public final TrackGroupArray b;
        public final boolean[] c;
        public final boolean[] d;
        public final boolean[] e;

        public d(com.google.android.exoplayer2.extractor.o oVar, TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.a = oVar;
            this.b = trackGroupArray;
            this.c = zArr;
            this.d = new boolean[trackGroupArray.b];
            this.e = new boolean[trackGroupArray.b];
        }
    }
}
