package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.source.a.h;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.q;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.source.s;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ChunkSampleStream */
public class g<T extends h> implements r, s, Loader.a<d>, Loader.e {
    public final int a;
    long b;
    boolean c;
    private final int[] d;
    private final Format[] e;
    private final boolean[] f;
    private final T g;
    private final s.a<g<T>> h;
    private final n.a i;
    private final o j;
    private final Loader k = new Loader("Loader:ChunkSampleStream");
    private final f l = new f();
    private final ArrayList<a> m = new ArrayList<>();
    private final List<a> n = Collections.unmodifiableList(this.m);
    private final q o;
    private final q[] p;
    private final c q;
    private Format r;
    private b<T> s;
    private long t;
    private long u;
    private int v;

    /* compiled from: ChunkSampleStream */
    public interface b<T extends h> {
        void a(g<T> gVar);
    }

    public g(int i, int[] iArr, Format[] formatArr, T t, s.a<g<T>> aVar, com.google.android.exoplayer2.upstream.b bVar, long j, o oVar, n.a aVar2) {
        int i2;
        this.a = i;
        this.d = iArr;
        this.e = formatArr;
        this.g = t;
        this.h = aVar;
        this.i = aVar2;
        this.j = oVar;
        int i3 = 0;
        if (iArr == null) {
            i2 = 0;
        } else {
            i2 = iArr.length;
        }
        this.p = new q[i2];
        this.f = new boolean[i2];
        int i4 = i2 + 1;
        int[] iArr2 = new int[i4];
        q[] qVarArr = new q[i4];
        this.o = new q(bVar);
        iArr2[0] = i;
        qVarArr[0] = this.o;
        while (i3 < i2) {
            q qVar = new q(bVar);
            this.p[i3] = qVar;
            int i5 = i3 + 1;
            qVarArr[i5] = qVar;
            iArr2[i5] = iArr[i3];
            i3 = i5;
        }
        this.q = new c(iArr2, qVarArr);
        this.t = j;
        this.u = j;
    }

    public void a(long j, boolean z) {
        if (!h()) {
            int e = this.o.e();
            this.o.a(j, z, true);
            int e2 = this.o.e();
            if (e2 > e) {
                long k = this.o.k();
                int i = 0;
                while (true) {
                    q[] qVarArr = this.p;
                    if (i >= qVarArr.length) {
                        break;
                    }
                    qVarArr[i].a(k, z, this.f[i]);
                    i++;
                }
            }
            b(e2);
        }
    }

    public g<T>.a a(long j, int i) {
        for (int i2 = 0; i2 < this.p.length; i2++) {
            if (this.d[i2] == i) {
                com.google.android.exoplayer2.util.a.b(!this.f[i2]);
                this.f[i2] = true;
                this.p[i2].l();
                this.p[i2].b(j, true, true);
                return new a(this, this.p[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T a() {
        return this.g;
    }

    @Override // com.google.android.exoplayer2.source.s
    public long d() {
        if (this.c) {
            return Long.MIN_VALUE;
        }
        if (h()) {
            return this.t;
        }
        long j = this.u;
        a j2 = j();
        if (!j2.i()) {
            if (this.m.size() > 1) {
                ArrayList<a> arrayList = this.m;
                j2 = arrayList.get(arrayList.size() - 2);
            } else {
                j2 = null;
            }
        }
        if (j2 != null) {
            j = Math.max(j, j2.i);
        }
        return Math.max(j, this.o.i());
    }

    public long a(long j, x xVar) {
        return this.g.a(j, xVar);
    }

    public void b(long j) {
        boolean z;
        this.u = j;
        if (h()) {
            this.t = j;
            return;
        }
        a aVar = null;
        int i = 0;
        while (true) {
            if (i >= this.m.size()) {
                break;
            }
            a aVar2 = this.m.get(i);
            int i2 = (aVar2.h > j ? 1 : (aVar2.h == j ? 0 : -1));
            if (i2 == 0 && aVar2.a == -9223372036854775807L) {
                aVar = aVar2;
                break;
            } else if (i2 > 0) {
                break;
            } else {
                i++;
            }
        }
        this.o.l();
        if (aVar != null) {
            z = this.o.c(aVar.a(0));
            this.b = 0;
        } else {
            z = this.o.b(j, true, (j > e() ? 1 : (j == e() ? 0 : -1)) < 0) != -1;
            this.b = this.u;
        }
        if (z) {
            this.v = a(this.o.f(), 0);
            q[] qVarArr = this.p;
            for (q qVar : qVarArr) {
                qVar.l();
                qVar.b(j, true, false);
            }
            return;
        }
        this.t = j;
        this.c = false;
        this.m.clear();
        this.v = 0;
        if (this.k.b()) {
            this.k.c();
            return;
        }
        this.o.a();
        for (q qVar2 : this.p) {
            qVar2.a();
        }
    }

    public void f() {
        a((b) null);
    }

    public void a(b<T> bVar) {
        this.s = bVar;
        this.o.n();
        for (q qVar : this.p) {
            qVar.n();
        }
        this.k.a(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.e
    public void g() {
        this.o.a();
        for (q qVar : this.p) {
            qVar.a();
        }
        b<T> bVar = this.s;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    @Override // com.google.android.exoplayer2.source.r
    public boolean b() {
        return this.c || (!h() && this.o.d());
    }

    @Override // com.google.android.exoplayer2.source.r
    public void c() throws IOException {
        this.k.a();
        if (!this.k.b()) {
            this.g.a();
        }
    }

    @Override // com.google.android.exoplayer2.source.r
    public int a(k kVar, e eVar, boolean z) {
        if (h()) {
            return -3;
        }
        i();
        return this.o.a(kVar, eVar, z, this.c, this.b);
    }

    @Override // com.google.android.exoplayer2.source.r
    public int b_(long j) {
        int i = 0;
        if (h()) {
            return 0;
        }
        if (!this.c || j <= this.o.i()) {
            int b2 = this.o.b(j, true, true);
            if (b2 != -1) {
                i = b2;
            }
        } else {
            i = this.o.o();
        }
        i();
        return i;
    }

    public void a(d dVar, long j, long j2) {
        this.g.a(dVar);
        this.i.a(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, dVar.e());
        this.h.a(this);
    }

    public void a(d dVar, long j, long j2, boolean z) {
        this.i.b(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, dVar.e());
        if (!z) {
            this.o.a();
            for (q qVar : this.p) {
                qVar.a();
            }
            this.h.a(this);
        }
    }

    public Loader.b a(d dVar, long j, long j2, IOException iOException, int i) {
        long e = dVar.e();
        boolean a2 = a(dVar);
        int size = this.m.size() - 1;
        boolean z = e == 0 || !a2 || !a(size);
        Loader.b bVar = null;
        if (this.g.a(dVar, z, iOException, z ? this.j.a(dVar.d, j2, iOException, i) : -9223372036854775807L)) {
            if (z) {
                bVar = Loader.c;
                if (a2) {
                    com.google.android.exoplayer2.util.a.b(d(size) == dVar);
                    if (this.m.isEmpty()) {
                        this.t = this.u;
                    }
                }
            } else {
                i.c("ChunkSampleStream", "Ignoring attempt to cancel non-cancelable load.");
            }
        }
        if (bVar == null) {
            long b2 = this.j.b(dVar.d, j2, iOException, i);
            bVar = b2 != -9223372036854775807L ? Loader.a(false, b2) : Loader.d;
        }
        boolean z2 = !bVar.a();
        this.i.a(dVar.c, dVar.f(), dVar.g(), dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, j, j2, e, iOException, z2);
        if (z2) {
            this.h.a(this);
        }
        return bVar;
    }

    @Override // com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        long j2;
        List<a> list;
        long j3;
        boolean z = false;
        if (this.c || this.k.b()) {
            return false;
        }
        boolean h = h();
        if (h) {
            list = Collections.emptyList();
            j2 = this.t;
        } else {
            list = this.n;
            j2 = j().i;
        }
        this.g.a(j, j2, list, this.l);
        boolean z2 = this.l.b;
        d dVar = this.l.a;
        this.l.a();
        if (z2) {
            this.t = -9223372036854775807L;
            this.c = true;
            return true;
        } else if (dVar == null) {
            return false;
        } else {
            if (a(dVar)) {
                a aVar = (a) dVar;
                if (h) {
                    if (aVar.h == this.t) {
                        z = true;
                    }
                    if (z) {
                        j3 = 0;
                    } else {
                        j3 = this.t;
                    }
                    this.b = j3;
                    this.t = -9223372036854775807L;
                }
                aVar.a(this.q);
                this.m.add(aVar);
            }
            this.i.a(dVar.c, dVar.d, this.a, dVar.e, dVar.f, dVar.g, dVar.h, dVar.i, this.k.a(dVar, this, this.j.a(dVar.d)));
            return true;
        }
    }

    @Override // com.google.android.exoplayer2.source.s
    public long e() {
        if (h()) {
            return this.t;
        }
        if (this.c) {
            return Long.MIN_VALUE;
        }
        return j().i;
    }

    @Override // com.google.android.exoplayer2.source.s
    public void a(long j) {
        int size;
        int a2;
        if (!this.k.b() && !h() && (size = this.m.size()) > (a2 = this.g.a(j, this.n))) {
            while (true) {
                if (a2 >= size) {
                    a2 = size;
                    break;
                } else if (!a(a2)) {
                    break;
                } else {
                    a2++;
                }
            }
            if (a2 != size) {
                long j2 = j().i;
                a d = d(a2);
                if (this.m.isEmpty()) {
                    this.t = this.u;
                }
                this.c = false;
                this.i.a(this.a, d.h, j2);
            }
        }
    }

    private boolean a(d dVar) {
        return dVar instanceof a;
    }

    private boolean a(int i) {
        int f;
        a aVar = this.m.get(i);
        if (this.o.f() > aVar.a(0)) {
            return true;
        }
        int i2 = 0;
        do {
            q[] qVarArr = this.p;
            if (i2 >= qVarArr.length) {
                return false;
            }
            f = qVarArr[i2].f();
            i2++;
        } while (f <= aVar.a(i2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.t != -9223372036854775807L;
    }

    private void b(int i) {
        int min = Math.min(a(i, 0), this.v);
        if (min > 0) {
            z.a((List) this.m, 0, min);
            this.v -= min;
        }
    }

    private void i() {
        int a2 = a(this.o.f(), this.v - 1);
        while (true) {
            int i = this.v;
            if (i <= a2) {
                this.v = i + 1;
                c(i);
            } else {
                return;
            }
        }
    }

    private void c(int i) {
        a aVar = this.m.get(i);
        Format format = aVar.e;
        if (!format.equals(this.r)) {
            this.i.a(this.a, format, aVar.f, aVar.g, aVar.h);
        }
        this.r = format;
    }

    private int a(int i, int i2) {
        do {
            i2++;
            if (i2 >= this.m.size()) {
                return this.m.size() - 1;
            }
        } while (this.m.get(i2).a(0) <= i);
        return i2 - 1;
    }

    private a j() {
        ArrayList<a> arrayList = this.m;
        return arrayList.get(arrayList.size() - 1);
    }

    private a d(int i) {
        a aVar = this.m.get(i);
        ArrayList<a> arrayList = this.m;
        z.a((List) arrayList, i, arrayList.size());
        this.v = Math.max(this.v, this.m.size());
        int i2 = 0;
        this.o.b(aVar.a(0));
        while (true) {
            q[] qVarArr = this.p;
            if (i2 >= qVarArr.length) {
                return aVar;
            }
            q qVar = qVarArr[i2];
            i2++;
            qVar.b(aVar.a(i2));
        }
    }

    /* compiled from: ChunkSampleStream */
    public final class a implements r {
        public final g<T> a;
        private final q c;
        private final int d;
        private boolean e;

        @Override // com.google.android.exoplayer2.source.r
        public void c() throws IOException {
        }

        public a(g<T> gVar, q qVar, int i) {
            this.a = gVar;
            this.c = qVar;
            this.d = i;
        }

        @Override // com.google.android.exoplayer2.source.r
        public boolean b() {
            return g.this.c || (!g.this.h() && this.c.d());
        }

        @Override // com.google.android.exoplayer2.source.r
        public int b_(long j) {
            if (g.this.h()) {
                return 0;
            }
            d();
            if (g.this.c && j > this.c.i()) {
                return this.c.o();
            }
            int b = this.c.b(j, true, true);
            if (b == -1) {
                return 0;
            }
            return b;
        }

        @Override // com.google.android.exoplayer2.source.r
        public int a(k kVar, e eVar, boolean z) {
            if (g.this.h()) {
                return -3;
            }
            d();
            return this.c.a(kVar, eVar, z, g.this.c, g.this.b);
        }

        public void a() {
            com.google.android.exoplayer2.util.a.b(g.this.f[this.d]);
            g.this.f[this.d] = false;
        }

        private void d() {
            if (!this.e) {
                g.this.i.a(g.this.d[this.d], g.this.e[this.d], 0, (Object) null, g.this.u);
                this.e = true;
            }
        }
    }
}
