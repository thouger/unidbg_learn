package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.util.z;

/* compiled from: SampleMetadataQueue */
/* access modifiers changed from: package-private */
public final class p {
    private int a = 1000;
    private int[] b;
    private long[] c;
    private int[] d;
    private int[] e;
    private long[] f;
    private q.a[] g;
    private Format[] h;
    private int i;
    private int j;
    private int k;
    private int l;
    private long m;
    private long n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Format r;
    private int s;

    /* compiled from: SampleMetadataQueue */
    public static final class a {
        public int a;
        public long b;
        public q.a c;
    }

    public p() {
        int i = this.a;
        this.b = new int[i];
        this.c = new long[i];
        this.f = new long[i];
        this.e = new int[i];
        this.d = new int[i];
        this.g = new q.a[i];
        this.h = new Format[i];
        this.m = Long.MIN_VALUE;
        this.n = Long.MIN_VALUE;
        this.q = true;
        this.p = true;
    }

    public void a(boolean z) {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.p = true;
        this.m = Long.MIN_VALUE;
        this.n = Long.MIN_VALUE;
        this.o = false;
        if (z) {
            this.r = null;
            this.q = true;
        }
    }

    public int a() {
        return this.j + this.i;
    }

    public long a(int i) {
        int a2 = a() - i;
        boolean z = false;
        com.google.android.exoplayer2.util.a.a(a2 >= 0 && a2 <= this.i - this.l);
        this.i -= a2;
        this.n = Math.max(this.m, e(this.i));
        if (a2 == 0 && this.o) {
            z = true;
        }
        this.o = z;
        int i2 = this.i;
        if (i2 == 0) {
            return 0;
        }
        int f = f(i2 - 1);
        return this.c[f] + ((long) this.d[f]);
    }

    public void b(int i) {
        this.s = i;
    }

    public int b() {
        return this.j;
    }

    public int c() {
        return this.j + this.l;
    }

    public int d() {
        return e() ? this.b[f(this.l)] : this.s;
    }

    public synchronized boolean e() {
        return this.l != this.i;
    }

    public synchronized Format f() {
        return this.q ? null : this.r;
    }

    public synchronized long g() {
        return this.n;
    }

    public synchronized boolean h() {
        return this.o;
    }

    public synchronized long i() {
        return this.i == 0 ? Long.MIN_VALUE : this.f[this.k];
    }

    public synchronized void j() {
        this.l = 0;
    }

    public synchronized int a(k kVar, e eVar, boolean z, boolean z2, Format format, a aVar) {
        if (!e()) {
            if (!z2) {
                if (!this.o) {
                    if (this.r == null || (!z && this.r == format)) {
                        return -3;
                    }
                    kVar.a = this.r;
                    return -5;
                }
            }
            eVar.b_(4);
            return -4;
        }
        int f = f(this.l);
        if (z || this.h[f] != format) {
            kVar.a = this.h[f];
            return -5;
        } else if (eVar.f()) {
            return -3;
        } else {
            eVar.c = this.f[f];
            eVar.b_(this.e[f]);
            aVar.a = this.d[f];
            aVar.b = this.c[f];
            aVar.c = this.g[f];
            this.l++;
            return -4;
        }
    }

    public synchronized int a(long j, boolean z, boolean z2) {
        int f = f(this.l);
        if (e() && j >= this.f[f]) {
            if (j <= this.n || z2) {
                int a2 = a(f, this.i - this.l, j, z);
                if (a2 == -1) {
                    return -1;
                }
                this.l += a2;
                return a2;
            }
        }
        return -1;
    }

    public synchronized int k() {
        int i;
        i = this.i - this.l;
        this.l = this.i;
        return i;
    }

    public synchronized boolean c(int i) {
        if (this.j > i || i > this.j + this.i) {
            return false;
        }
        this.l = i - this.j;
        return true;
    }

    public synchronized long b(long j, boolean z, boolean z2) {
        if (this.i != 0) {
            if (j >= this.f[this.k]) {
                int a2 = a(this.k, (!z2 || this.l == this.i) ? this.i : this.l + 1, j, z);
                if (a2 == -1) {
                    return -1;
                }
                return d(a2);
            }
        }
        return -1;
    }

    public synchronized long l() {
        if (this.l == 0) {
            return -1;
        }
        return d(this.l);
    }

    public synchronized long m() {
        if (this.i == 0) {
            return -1;
        }
        return d(this.i);
    }

    public synchronized boolean a(Format format) {
        if (format == null) {
            this.q = true;
            return false;
        }
        this.q = false;
        if (z.a(format, this.r)) {
            return false;
        }
        this.r = format;
        return true;
    }

    public synchronized void a(long j, int i, long j2, int i2, q.a aVar) {
        if (this.p) {
            if ((i & 1) != 0) {
                this.p = false;
            } else {
                return;
            }
        }
        com.google.android.exoplayer2.util.a.b(!this.q);
        this.o = (536870912 & i) != 0;
        this.n = Math.max(this.n, j);
        int f = f(this.i);
        this.f[f] = j;
        this.c[f] = j2;
        this.d[f] = i2;
        this.e[f] = i;
        this.g[f] = aVar;
        this.h[f] = this.r;
        this.b[f] = this.s;
        this.i++;
        if (this.i == this.a) {
            int i3 = this.a + 1000;
            int[] iArr = new int[i3];
            long[] jArr = new long[i3];
            long[] jArr2 = new long[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            q.a[] aVarArr = new q.a[i3];
            Format[] formatArr = new Format[i3];
            int i4 = this.a - this.k;
            System.arraycopy(this.c, this.k, jArr, 0, i4);
            System.arraycopy(this.f, this.k, jArr2, 0, i4);
            System.arraycopy(this.e, this.k, iArr2, 0, i4);
            System.arraycopy(this.d, this.k, iArr3, 0, i4);
            System.arraycopy(this.g, this.k, aVarArr, 0, i4);
            System.arraycopy(this.h, this.k, formatArr, 0, i4);
            System.arraycopy(this.b, this.k, iArr, 0, i4);
            int i5 = this.k;
            System.arraycopy(this.c, 0, jArr, i4, i5);
            System.arraycopy(this.f, 0, jArr2, i4, i5);
            System.arraycopy(this.e, 0, iArr2, i4, i5);
            System.arraycopy(this.d, 0, iArr3, i4, i5);
            System.arraycopy(this.g, 0, aVarArr, i4, i5);
            System.arraycopy(this.h, 0, formatArr, i4, i5);
            System.arraycopy(this.b, 0, iArr, i4, i5);
            this.c = jArr;
            this.f = jArr2;
            this.e = iArr2;
            this.d = iArr3;
            this.g = aVarArr;
            this.h = formatArr;
            this.b = iArr;
            this.k = 0;
            this.i = this.a;
            this.a = i3;
        }
    }

    public synchronized boolean a(long j) {
        boolean z = false;
        if (this.i == 0) {
            if (j > this.m) {
                z = true;
            }
            return z;
        } else if (Math.max(this.m, e(this.l)) >= j) {
            return false;
        } else {
            int i = this.i;
            int f = f(this.i - 1);
            while (i > this.l && this.f[f] >= j) {
                i--;
                f--;
                if (f == -1) {
                    f = this.a - 1;
                }
            }
            a(this.j + i);
            return true;
        }
    }

    private int a(int i, int i2, long j, boolean z) {
        int i3 = -1;
        int i4 = i;
        for (int i5 = 0; i5 < i2 && this.f[i4] <= j; i5++) {
            if (!z || (this.e[i4] & 1) != 0) {
                i3 = i5;
            }
            i4++;
            if (i4 == this.a) {
                i4 = 0;
            }
        }
        return i3;
    }

    private long d(int i) {
        this.m = Math.max(this.m, e(i));
        this.i -= i;
        this.j += i;
        this.k += i;
        int i2 = this.k;
        int i3 = this.a;
        if (i2 >= i3) {
            this.k = i2 - i3;
        }
        this.l -= i;
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.i != 0) {
            return this.c[this.k];
        }
        int i4 = this.k;
        if (i4 == 0) {
            i4 = this.a;
        }
        int i5 = i4 - 1;
        return this.c[i5] + ((long) this.d[i5]);
    }

    private long e(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int f = f(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.f[f]);
            if ((this.e[f] & 1) != 0) {
                break;
            }
            f--;
            if (f == -1) {
                f = this.a - 1;
            }
        }
        return j;
    }

    private int f(int i) {
        int i2 = this.k + i;
        int i3 = this.a;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
