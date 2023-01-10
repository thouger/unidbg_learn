package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.a.d;
import com.google.android.exoplayer2.source.hls.d;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.q;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.source.s;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: HlsSampleStreamWrapper */
/* access modifiers changed from: package-private */
public final class l implements i, q.b, s, Loader.a<d>, Loader.e {
    private Format A;
    private Format B;
    private boolean C;
    private TrackGroupArray D;
    private TrackGroupArray E;
    private int[] F;
    private int G;
    private boolean H;
    private boolean[] I = new boolean[0];
    private boolean[] J = new boolean[0];
    private long K;
    private long L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private long Q;
    private int R;
    private final int a;
    private final a b;
    private final d c;
    private final com.google.android.exoplayer2.upstream.b d;
    private final Format e;
    private final o f;
    private final Loader g = new Loader("Loader:HlsSampleStreamWrapper");
    private final n.a h;
    private final d.b i = new d.b();
    private final ArrayList<h> j = new ArrayList<>();
    private final List<h> k = Collections.unmodifiableList(this.j);
    private final Runnable l = new $$Lambda$l$UekGCvVeQh2gOaB50EgbW1v6cwE(this);
    private final Runnable m = new $$Lambda$l$N0rM69Bd8LTbHP7fmqbQGOxJJdc(this);
    private final Handler n = new Handler();
    private final ArrayList<k> o = new ArrayList<>();
    private q[] p = new q[0];
    private int[] q = new int[0];
    private boolean r;
    private int s = -1;
    private boolean t;
    private int u = -1;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    /* compiled from: HlsSampleStreamWrapper */
    public interface a extends s.a<l> {
        void a(c.a aVar);

        void g();
    }

    private static int d(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 0 : 1;
        }
        return 3;
    }

    @Override // com.google.android.exoplayer2.source.s
    public void a(long j) {
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a(com.google.android.exoplayer2.extractor.o oVar) {
    }

    public l(int i, a aVar, d dVar, com.google.android.exoplayer2.upstream.b bVar, long j, Format format, o oVar, n.a aVar2) {
        this.a = i;
        this.b = aVar;
        this.c = dVar;
        this.d = bVar;
        this.e = format;
        this.f = oVar;
        this.h = aVar2;
        this.K = j;
        this.L = j;
    }

    public void b() {
        if (!this.y) {
            c(this.K);
        }
    }

    public void a(TrackGroupArray trackGroupArray, int i, TrackGroupArray trackGroupArray2) {
        this.y = true;
        this.D = trackGroupArray;
        this.E = trackGroupArray2;
        this.G = i;
        this.b.g();
    }

    public void c() throws IOException {
        i();
    }

    public TrackGroupArray f() {
        return this.D;
    }

    public int a(int i) {
        int i2 = this.F[i];
        if (i2 != -1) {
            boolean[] zArr = this.I;
            if (zArr[i2]) {
                return -2;
            }
            zArr[i2] = true;
            return i2;
        } else if (this.E.a(this.D.a(i)) == -1) {
            return -2;
        } else {
            return -3;
        }
    }

    public void b(int i) {
        int i2 = this.F[i];
        com.google.android.exoplayer2.util.a.b(this.I[i2]);
        this.I[i2] = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.google.android.exoplayer2.trackselection.e[] r20, boolean[] r21, com.google.android.exoplayer2.source.r[] r22, boolean[] r23, long r24, boolean r26) {
        /*
        // Method dump skipped, instructions count: 337
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.l.a(com.google.android.exoplayer2.trackselection.e[], boolean[], com.google.android.exoplayer2.source.r[], boolean[], long, boolean):boolean");
    }

    public void a(long j, boolean z) {
        if (this.x && !p()) {
            int length = this.p.length;
            for (int i = 0; i < length; i++) {
                this.p[i].a(j, z, this.I[i]);
            }
        }
    }

    public boolean b(long j, boolean z) {
        this.K = j;
        if (p()) {
            this.L = j;
            return true;
        } else if (this.x && !z && d(j)) {
            return false;
        } else {
            this.L = j;
            this.O = false;
            this.j.clear();
            if (this.g.b()) {
                this.g.c();
            } else {
                j();
            }
            return true;
        }
    }

    public void h() {
        if (this.y) {
            for (q qVar : this.p) {
                qVar.n();
            }
        }
        this.g.a(this);
        this.n.removeCallbacksAndMessages(null);
        this.C = true;
        this.o.clear();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.e
    public void g() {
        j();
    }

    public void a(boolean z) {
        this.c.a(z);
    }

    public boolean a(c.a aVar, long j) {
        return this.c.a(aVar, j);
    }

    public boolean c(int i) {
        return this.O || (!p() && this.p[i].d());
    }

    public void i() throws IOException {
        this.g.a();
        this.c.a();
    }

    public int a(int i, k kVar, e eVar, boolean z) {
        if (p()) {
            return -3;
        }
        int i2 = 0;
        if (!this.j.isEmpty()) {
            int i3 = 0;
            while (i3 < this.j.size() - 1 && a(this.j.get(i3))) {
                i3++;
            }
            z.a((List) this.j, 0, i3);
            h hVar = this.j.get(0);
            Format format = hVar.e;
            if (!format.equals(this.B)) {
                this.h.a(this.a, format, hVar.f, hVar.g, hVar.h);
            }
            this.B = format;
        }
        int a2 = this.p[i].a(kVar, eVar, z, this.O, this.K);
        if (a2 == -5 && i == this.w) {
            int g = this.p[i].g();
            while (i2 < this.j.size() && this.j.get(i2).a != g) {
                i2++;
            }
            kVar.a = kVar.a.a(i2 < this.j.size() ? this.j.get(i2).e : this.A);
        }
        return a2;
    }

    public int a(int i, long j) {
        if (p()) {
            return 0;
        }
        q qVar = this.p[i];
        if (this.O && j > qVar.i()) {
            return qVar.o();
        }
        int b2 = qVar.b(j, true, true);
        if (b2 == -1) {
            return 0;
        }
        return b2;
    }

    @Override // com.google.android.exoplayer2.source.s
    public long d() {
        if (this.O) {
            return Long.MIN_VALUE;
        }
        if (p()) {
            return this.L;
        }
        long j = this.K;
        h o = o();
        if (!o.i()) {
            if (this.j.size() > 1) {
                ArrayList<h> arrayList = this.j;
                o = arrayList.get(arrayList.size() - 2);
            } else {
                o = null;
            }
        }
        if (o != null) {
            j = Math.max(j, o.i);
        }
        if (this.x) {
            for (q qVar : this.p) {
                j = Math.max(j, qVar.i());
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.s
    public long e() {
        if (p()) {
            return this.L;
        }
        if (this.O) {
            return Long.MIN_VALUE;
        }
        return o().i;
    }

    @Override // com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        List<h> list;
        long max;
        if (this.O || this.g.b()) {
            return false;
        }
        if (p()) {
            list = Collections.emptyList();
            max = this.L;
        } else {
            list = this.k;
            h o = o();
            if (o.i()) {
                max = o.i;
            } else {
                max = Math.max(this.K, o.h);
            }
        }
        this.c.a(j, max, list, this.i);
        boolean z = this.i.b;
        com.google.android.exoplayer2.source.a.d dVar = this.i.a;
        c.a aVar = this.i.c;
        this.i.a();
        if (z) {
            this.L = -9223372036854775807L;
            this.O = true;
            return true;
        } else if (dVar == null) {
            if (aVar != null) {
                this.b.a(aVar);
            }
            return false;
        } else {
            if (a(dVar)) {
                this.L = -9223372036854775807L;
                h hVar = (h) dVar;
                hVar.a(this);
                this.j.add(hVar);
                this.A = hVar.e;
            }
            this.h.a(dVar.c, dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, this.g.a(dVar, this, this.f.a(dVar.d)));
            return true;
        }
    }

    public void a(com.google.android.exoplayer2.source.a.d dVar, long j, long j2) {
        this.c.a(dVar);
        this.h.a(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, dVar.e());
        if (!this.y) {
            c(this.K);
        } else {
            this.b.a((a) this);
        }
    }

    public void a(com.google.android.exoplayer2.source.a.d dVar, long j, long j2, boolean z) {
        this.h.b(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, dVar.e());
        if (!z) {
            j();
            if (this.z > 0) {
                this.b.a((a) this);
            }
        }
    }

    public Loader.b a(com.google.android.exoplayer2.source.a.d dVar, long j, long j2, IOException iOException, int i) {
        Loader.b a2;
        long e = dVar.e();
        boolean a3 = a(dVar);
        long a4 = this.f.a(dVar.d, j2, iOException, i);
        boolean z = false;
        boolean a5 = a4 != -9223372036854775807L ? this.c.a(dVar, a4) : false;
        if (a5) {
            if (a3 && e == 0) {
                ArrayList<h> arrayList = this.j;
                if (arrayList.remove(arrayList.size() - 1) == dVar) {
                    z = true;
                }
                com.google.android.exoplayer2.util.a.b(z);
                if (this.j.isEmpty()) {
                    this.L = this.K;
                }
            }
            a2 = Loader.c;
        } else {
            long b2 = this.f.b(dVar.d, j2, iOException, i);
            a2 = b2 != -9223372036854775807L ? Loader.a(false, b2) : Loader.d;
        }
        this.h.a(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, e, iOException, !a2.a());
        if (a5) {
            if (!this.y) {
                c(this.K);
            } else {
                this.b.a((a) this);
            }
        }
        return a2;
    }

    public void a(int i, boolean z, boolean z2) {
        if (!z2) {
            this.r = false;
            this.t = false;
        }
        this.R = i;
        for (q qVar : this.p) {
            qVar.a(i);
        }
        if (z) {
            for (q qVar2 : this.p) {
                qVar2.b();
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public com.google.android.exoplayer2.extractor.q a(int i, int i2) {
        q[] qVarArr = this.p;
        int length = qVarArr.length;
        boolean z = false;
        if (i2 == 1) {
            int i3 = this.s;
            if (i3 != -1) {
                if (!this.r) {
                    this.r = true;
                    this.q[i3] = i;
                    return qVarArr[i3];
                } else if (this.q[i3] == i) {
                    return qVarArr[i3];
                } else {
                    return b(i, i2);
                }
            } else if (this.P) {
                return b(i, i2);
            }
        } else if (i2 == 2) {
            int i4 = this.u;
            if (i4 != -1) {
                if (!this.t) {
                    this.t = true;
                    this.q[i4] = i;
                    return qVarArr[i4];
                } else if (this.q[i4] == i) {
                    return qVarArr[i4];
                } else {
                    return b(i, i2);
                }
            } else if (this.P) {
                return b(i, i2);
            }
        } else {
            for (int i5 = 0; i5 < length; i5++) {
                if (this.q[i5] == i) {
                    return this.p[i5];
                }
            }
            if (this.P) {
                return b(i, i2);
            }
        }
        b bVar = new b(this.d);
        bVar.a(this.Q);
        bVar.a(this.R);
        bVar.a(this);
        int i6 = length + 1;
        this.q = Arrays.copyOf(this.q, i6);
        this.q[length] = i;
        this.p = (q[]) Arrays.copyOf(this.p, i6);
        this.p[length] = bVar;
        this.J = Arrays.copyOf(this.J, i6);
        boolean[] zArr = this.J;
        if (i2 == 1 || i2 == 2) {
            z = true;
        }
        zArr[length] = z;
        this.H |= this.J[length];
        if (i2 == 1) {
            this.r = true;
            this.s = length;
        } else if (i2 == 2) {
            this.t = true;
            this.u = length;
        }
        if (d(i2) > d(this.v)) {
            this.w = length;
            this.v = i2;
        }
        this.I = Arrays.copyOf(this.I, i6);
        return bVar;
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a() {
        this.P = true;
        this.n.post(this.m);
    }

    @Override // com.google.android.exoplayer2.source.q.b
    public void a(Format format) {
        this.n.post(this.l);
    }

    public void b(long j) {
        this.Q = j;
        for (q qVar : this.p) {
            qVar.a(j);
        }
    }

    private void a(r[] rVarArr) {
        this.o.clear();
        for (r rVar : rVarArr) {
            if (rVar != null) {
                this.o.add((k) rVar);
            }
        }
    }

    private boolean a(h hVar) {
        int i = hVar.a;
        int length = this.p.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.I[i2] && this.p[i2].g() == i) {
                return false;
            }
        }
        return true;
    }

    private void j() {
        for (q qVar : this.p) {
            qVar.a(this.M);
        }
        this.M = false;
    }

    /* access modifiers changed from: private */
    public void k() {
        this.x = true;
        l();
    }

    /* access modifiers changed from: private */
    public void l() {
        if (!this.C && this.F == null && this.x) {
            for (q qVar : this.p) {
                if (qVar.h() == null) {
                    return;
                }
            }
            if (this.D != null) {
                m();
                return;
            }
            n();
            this.y = true;
            this.b.g();
        }
    }

    private void m() {
        int i = this.D.b;
        this.F = new int[i];
        Arrays.fill(this.F, -1);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            while (true) {
                q[] qVarArr = this.p;
                if (i3 >= qVarArr.length) {
                    break;
                } else if (a(qVarArr[i3].h(), this.D.a(i2).a(0))) {
                    this.F[i2] = i3;
                    break;
                } else {
                    i3++;
                }
            }
        }
        Iterator<k> it2 = this.o.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
    }

    private void n() {
        int length = this.p.length;
        boolean z = false;
        int i = 6;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int i4 = 2;
            if (i3 >= length) {
                break;
            }
            String str = this.p[i3].h().g;
            if (!com.google.android.exoplayer2.util.l.b(str)) {
                if (com.google.android.exoplayer2.util.l.a(str)) {
                    i4 = 1;
                } else {
                    i4 = com.google.android.exoplayer2.util.l.c(str) ? 3 : 6;
                }
            }
            if (d(i4) > d(i)) {
                i2 = i3;
                i = i4;
            } else if (i4 == i && i2 != -1) {
                i2 = -1;
            }
            i3++;
        }
        TrackGroup b2 = this.c.b();
        int i5 = b2.a;
        this.G = -1;
        this.F = new int[length];
        for (int i6 = 0; i6 < length; i6++) {
            this.F[i6] = i6;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        for (int i7 = 0; i7 < length; i7++) {
            Format h = this.p[i7].h();
            if (i7 == i2) {
                Format[] formatArr = new Format[i5];
                if (i5 == 1) {
                    formatArr[0] = h.a(b2.a(0));
                } else {
                    for (int i8 = 0; i8 < i5; i8++) {
                        formatArr[i8] = a(b2.a(i8), h, true);
                    }
                }
                trackGroupArr[i7] = new TrackGroup(formatArr);
                this.G = i7;
            } else {
                trackGroupArr[i7] = new TrackGroup(a((i != 2 || !com.google.android.exoplayer2.util.l.a(h.g)) ? null : this.e, h, false));
            }
        }
        this.D = new TrackGroupArray(trackGroupArr);
        if (this.E == null) {
            z = true;
        }
        com.google.android.exoplayer2.util.a.b(z);
        this.E = TrackGroupArray.a;
    }

    private h o() {
        ArrayList<h> arrayList = this.j;
        return arrayList.get(arrayList.size() - 1);
    }

    private boolean p() {
        return this.L != -9223372036854775807L;
    }

    private boolean d(long j) {
        int length = this.p.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length) {
                return true;
            }
            q qVar = this.p[i];
            qVar.l();
            if (qVar.b(j, true, false) == -1) {
                z = false;
            }
            if (z || (!this.J[i] && this.H)) {
                i++;
            }
        }
        return false;
    }

    private static Format a(Format format, Format format2, boolean z) {
        if (format == null) {
            return format2;
        }
        int i = z ? format.c : -1;
        String a2 = z.a(format.d, com.google.android.exoplayer2.util.l.g(format2.g));
        String f = com.google.android.exoplayer2.util.l.f(a2);
        if (f == null) {
            f = format2.g;
        }
        return format2.a(format.a, format.b, f, a2, i, format.l, format.m, format.y, format.z);
    }

    private static boolean a(com.google.android.exoplayer2.source.a.d dVar) {
        return dVar instanceof h;
    }

    private static boolean a(Format format, Format format2) {
        String str = format.g;
        String str2 = format2.g;
        int g = com.google.android.exoplayer2.util.l.g(str);
        if (g != 3) {
            return g == com.google.android.exoplayer2.util.l.g(str2);
        }
        if (!z.a((Object) str, (Object) str2)) {
            return false;
        }
        return (!"application/cea-608".equals(str) && !"application/cea-708".equals(str)) || format.A == format2.A;
    }

    private static f b(int i, int i2) {
        com.google.android.exoplayer2.util.i.c("HlsSampleStreamWrapper", "Unmapped track with id " + i + " of type " + i2);
        return new f();
    }

    /* compiled from: HlsSampleStreamWrapper */
    private static final class b extends q {
        public b(com.google.android.exoplayer2.upstream.b bVar) {
            super(bVar);
        }

        @Override // com.google.android.exoplayer2.source.q, com.google.android.exoplayer2.extractor.q
        public void a(Format format) {
            super.a(format.a(a(format.e)));
        }

        private Metadata a(Metadata metadata) {
            if (metadata == null) {
                return null;
            }
            int a = metadata.a();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= a) {
                    i2 = -1;
                    break;
                }
                Metadata.Entry a2 = metadata.a(i2);
                if ((a2 instanceof PrivFrame) && "com.apple.streaming.transportStreamTimestamp".equals(((PrivFrame) a2).a)) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return metadata;
            }
            if (a == 1) {
                return null;
            }
            Metadata.Entry[] entryArr = new Metadata.Entry[(a - 1)];
            while (i < a) {
                if (i != i2) {
                    entryArr[i < i2 ? i : i - 1] = metadata.a(i);
                }
                i++;
            }
            return new Metadata(entryArr);
        }
    }
}
