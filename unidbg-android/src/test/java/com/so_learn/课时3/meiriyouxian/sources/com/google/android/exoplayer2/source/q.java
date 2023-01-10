package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.source.p;
import com.google.android.exoplayer2.util.o;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: SampleQueue */
public class q implements com.google.android.exoplayer2.extractor.q {
    private final com.google.android.exoplayer2.upstream.b a;
    private final int b;
    private final p c = new p();
    private final p.a d = new p.a();
    private final o e = new o(32);
    private a f = new a(0, this.b);
    private a g;
    private a h;
    private Format i;
    private boolean j;
    private Format k;
    private long l;
    private long m;
    private boolean n;
    private b o;

    /* compiled from: SampleQueue */
    public interface b {
        void a(Format format);
    }

    public q(com.google.android.exoplayer2.upstream.b bVar) {
        this.a = bVar;
        this.b = bVar.c();
        a aVar = this.f;
        this.g = aVar;
        this.h = aVar;
    }

    public void a() {
        a(false);
    }

    public void a(boolean z) {
        this.c.a(z);
        a(this.f);
        this.f = new a(0, this.b);
        a aVar = this.f;
        this.g = aVar;
        this.h = aVar;
        this.m = 0;
        this.a.b();
    }

    public void a(int i) {
        this.c.b(i);
    }

    public void b() {
        this.n = true;
    }

    public int c() {
        return this.c.a();
    }

    public void b(int i) {
        this.m = this.c.a(i);
        long j = this.m;
        if (j == 0 || j == this.f.a) {
            a(this.f);
            this.f = new a(this.m, this.b);
            a aVar = this.f;
            this.g = aVar;
            this.h = aVar;
            return;
        }
        a aVar2 = this.f;
        while (this.m > aVar2.b) {
            aVar2 = aVar2.e;
        }
        a aVar3 = aVar2.e;
        a(aVar3);
        aVar2.e = new a(aVar2.b, this.b);
        this.h = this.m == aVar2.b ? aVar2.e : aVar2;
        if (this.g == aVar3) {
            this.g = aVar2.e;
        }
    }

    public boolean d() {
        return this.c.e();
    }

    public int e() {
        return this.c.b();
    }

    public int f() {
        return this.c.c();
    }

    public int g() {
        return this.c.d();
    }

    public Format h() {
        return this.c.f();
    }

    public long i() {
        return this.c.g();
    }

    public boolean j() {
        return this.c.h();
    }

    public long k() {
        return this.c.i();
    }

    public void l() {
        this.c.j();
        this.g = this.f;
    }

    public void a(long j, boolean z, boolean z2) {
        c(this.c.b(j, z, z2));
    }

    public void m() {
        c(this.c.l());
    }

    public void n() {
        c(this.c.m());
    }

    public int o() {
        return this.c.k();
    }

    public int b(long j, boolean z, boolean z2) {
        return this.c.a(j, z, z2);
    }

    public boolean c(int i) {
        return this.c.c(i);
    }

    public int a(k kVar, e eVar, boolean z, boolean z2, long j) {
        int a2 = this.c.a(kVar, eVar, z, z2, this.i, this.d);
        if (a2 == -5) {
            this.i = kVar.a;
            return -5;
        } else if (a2 == -4) {
            if (!eVar.c()) {
                if (eVar.c < j) {
                    eVar.b(Integer.MIN_VALUE);
                }
                if (eVar.g()) {
                    a(eVar, this.d);
                }
                eVar.e(this.d.a);
                a(this.d.b, eVar.b, this.d.a);
            }
            return -4;
        } else if (a2 == -3) {
            return -3;
        } else {
            throw new IllegalStateException();
        }
    }

    private void a(e eVar, p.a aVar) {
        long j = aVar.b;
        int i = 1;
        this.e.a(1);
        a(j, this.e.a, 1);
        long j2 = j + 1;
        byte b2 = this.e.a[0];
        boolean z = (b2 & 128) != 0;
        int i2 = b2 & Byte.MAX_VALUE;
        if (eVar.a.a == null) {
            eVar.a.a = new byte[16];
        }
        a(j2, eVar.a.a, i2);
        long j3 = j2 + ((long) i2);
        if (z) {
            this.e.a(2);
            a(j3, this.e.a, 2);
            j3 += 2;
            i = this.e.i();
        }
        int[] iArr = eVar.a.d;
        if (iArr == null || iArr.length < i) {
            iArr = new int[i];
        }
        int[] iArr2 = eVar.a.e;
        if (iArr2 == null || iArr2.length < i) {
            iArr2 = new int[i];
        }
        if (z) {
            int i3 = i * 6;
            this.e.a(i3);
            a(j3, this.e.a, i3);
            j3 += (long) i3;
            this.e.c(0);
            for (int i4 = 0; i4 < i; i4++) {
                iArr[i4] = this.e.i();
                iArr2[i4] = this.e.v();
            }
        } else {
            iArr[0] = 0;
            iArr2[0] = aVar.a - ((int) (j3 - aVar.b));
        }
        q.a aVar2 = aVar.c;
        eVar.a.a(i, iArr, iArr2, aVar2.b, eVar.a.a, aVar2.a, aVar2.c, aVar2.d);
        int i5 = (int) (j3 - aVar.b);
        aVar.b += (long) i5;
        aVar.a -= i5;
    }

    private void a(long j, ByteBuffer byteBuffer, int i) {
        b(j);
        while (i > 0) {
            int min = Math.min(i, (int) (this.g.b - j));
            byteBuffer.put(this.g.d.a, this.g.a(j), min);
            i -= min;
            j += (long) min;
            if (j == this.g.b) {
                this.g = this.g.e;
            }
        }
    }

    private void a(long j, byte[] bArr, int i) {
        b(j);
        long j2 = j;
        int i2 = i;
        while (i2 > 0) {
            int min = Math.min(i2, (int) (this.g.b - j2));
            System.arraycopy(this.g.d.a, this.g.a(j2), bArr, i - i2, min);
            i2 -= min;
            j2 += (long) min;
            if (j2 == this.g.b) {
                this.g = this.g.e;
            }
        }
    }

    private void b(long j) {
        while (j >= this.g.b) {
            this.g = this.g.e;
        }
    }

    private void c(long j) {
        if (j != -1) {
            while (j >= this.f.b) {
                this.a.a(this.f.d);
                this.f = this.f.a();
            }
            if (this.g.a < this.f.a) {
                this.g = this.f;
            }
        }
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    public void a(long j) {
        if (this.l != j) {
            this.l = j;
            this.j = true;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.q
    public void a(Format format) {
        Format a2 = a(format, this.l);
        boolean a3 = this.c.a(a2);
        this.k = format;
        this.j = false;
        b bVar = this.o;
        if (bVar != null && a3) {
            bVar.a(a2);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.q
    public int a(h hVar, int i, boolean z) throws IOException, InterruptedException {
        int a2 = hVar.a(this.h.d.a, this.h.a(this.m), d(i));
        if (a2 != -1) {
            e(a2);
            return a2;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.q
    public void a(o oVar, int i) {
        while (i > 0) {
            int d = d(i);
            oVar.a(this.h.d.a, this.h.a(this.m), d);
            i -= d;
            e(d);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.q
    public void a(long j, int i, int i2, int i3, q.a aVar) {
        if (this.j) {
            a(this.k);
        }
        long j2 = j + this.l;
        if (this.n) {
            if ((i & 1) != 0 && this.c.a(j2)) {
                this.n = false;
            } else {
                return;
            }
        }
        this.c.a(j2, i, (this.m - ((long) i2)) - ((long) i3), i2, aVar);
    }

    private void a(a aVar) {
        if (aVar.c) {
            com.google.android.exoplayer2.upstream.a[] aVarArr = new com.google.android.exoplayer2.upstream.a[((this.h.c ? 1 : 0) + (((int) (this.h.a - aVar.a)) / this.b))];
            for (int i = 0; i < aVarArr.length; i++) {
                aVarArr[i] = aVar.d;
                aVar = aVar.a();
            }
            this.a.a(aVarArr);
        }
    }

    private int d(int i) {
        if (!this.h.c) {
            this.h.a(this.a.a(), new a(this.h.b, this.b));
        }
        return Math.min(i, (int) (this.h.b - this.m));
    }

    private void e(int i) {
        this.m += (long) i;
        if (this.m == this.h.b) {
            this.h = this.h.e;
        }
    }

    private static Format a(Format format, long j) {
        if (format == null) {
            return null;
        }
        return (j == 0 || format.k == Long.MAX_VALUE) ? format : format.a(format.k + j);
    }

    /* compiled from: SampleQueue */
    /* access modifiers changed from: private */
    public static final class a {
        public final long a;
        public final long b;
        public boolean c;
        public com.google.android.exoplayer2.upstream.a d;
        public a e;

        public a(long j, int i) {
            this.a = j;
            this.b = j + ((long) i);
        }

        public void a(com.google.android.exoplayer2.upstream.a aVar, a aVar2) {
            this.d = aVar;
            this.e = aVar2;
            this.c = true;
        }

        public int a(long j) {
            return ((int) (j - this.a)) + this.d.b;
        }

        public a a() {
            this.d = null;
            a aVar = this.e;
            this.e = null;
            return aVar;
        }
    }
}
