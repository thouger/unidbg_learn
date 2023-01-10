package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import java.util.Arrays;

/* compiled from: ObjectCountHashMap */
/* access modifiers changed from: package-private */
public class al<K> {
    transient Object[] a;
    transient int[] b;
    transient int c;
    transient int d;
    transient long[] e;
    private transient int[] f;
    private transient float g;
    private transient int h;

    private static int a(long j) {
        return (int) (j >>> 32);
    }

    private static long a(long j, int i) {
        return (j & -4294967296L) | (((long) i) & 4294967295L);
    }

    private static int b(long j) {
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        return i - 1;
    }

    public static <K> al<K> a() {
        return new al<>();
    }

    public static <K> al<K> a(int i) {
        return new al<>(i);
    }

    al() {
        a(3, 1.0f);
    }

    al(al<? extends K> alVar) {
        a(alVar.c(), 1.0f);
        int b = alVar.b();
        while (b != -1) {
            a((al<K>) alVar.c(b), alVar.d(b));
            b = alVar.b(b);
        }
    }

    al(int i) {
        this(i, 1.0f);
    }

    al(int i, float f) {
        a(i, f);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, float f) {
        boolean z = false;
        m.a(i >= 0, "Initial capacity must be non-negative");
        if (f > 0.0f) {
            z = true;
        }
        m.a(z, "Illegal load factor");
        int a2 = af.a(i, (double) f);
        this.f = j(a2);
        this.g = f;
        this.a = new Object[i];
        this.b = new int[i];
        this.e = k(i);
        this.h = Math.max(1, (int) (((float) a2) * f));
    }

    private static int[] j(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] k(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private int e() {
        return this.f.length - 1;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.c == 0 ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int b(int i) {
        int i2 = i + 1;
        if (i2 < this.c) {
            return i2;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public K c(int i) {
        m.a(i, this.c);
        return (K) this.a[i];
    }

    /* access modifiers changed from: package-private */
    public int d(int i) {
        m.a(i, this.c);
        return this.b[i];
    }

    /* access modifiers changed from: package-private */
    public void b(int i, int i2) {
        m.a(i, this.c);
        this.b[i] = i2;
    }

    /* access modifiers changed from: package-private */
    public aj.a<K> e(int i) {
        m.a(i, this.c);
        return new a(i);
    }

    /* compiled from: ObjectCountHashMap */
    class a extends Multisets.a<K> {
        final K a;
        int b;

        a(int i) {
            this.a = (K) al.this.a[i];
            this.b = i;
        }

        @Override // com.google.common.collect.aj.a
        public K getElement() {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            int i = this.b;
            if (i == -1 || i >= al.this.c() || !j.a(this.a, al.this.a[this.b])) {
                this.b = al.this.a(this.a);
            }
        }

        @Override // com.google.common.collect.aj.a
        public int getCount() {
            a();
            if (this.b == -1) {
                return 0;
            }
            return al.this.b[this.b];
        }
    }

    /* access modifiers changed from: package-private */
    public void f(int i) {
        if (i > this.e.length) {
            g(i);
        }
        if (i >= this.h) {
            m(Math.max(2, Integer.highestOneBit(i - 1) << 1));
        }
    }

    public int a(K k, int i) {
        n.b(i, "count");
        long[] jArr = this.e;
        Object[] objArr = this.a;
        int[] iArr = this.b;
        int a2 = af.a(k);
        int e = e() & a2;
        int i2 = this.c;
        int[] iArr2 = this.f;
        int i3 = iArr2[e];
        if (i3 == -1) {
            iArr2[e] = i2;
        } else {
            while (true) {
                long j = jArr[i3];
                if (a(j) != a2 || !j.a(k, objArr[i3])) {
                    int b = b(j);
                    if (b == -1) {
                        jArr[i3] = a(j, i2);
                        break;
                    }
                    i3 = b;
                } else {
                    int i4 = iArr[i3];
                    iArr[i3] = i;
                    return i4;
                }
            }
        }
        if (i2 != Integer.MAX_VALUE) {
            int i5 = i2 + 1;
            l(i5);
            a(i2, k, i, a2);
            this.c = i5;
            if (i2 >= this.h) {
                m(this.f.length * 2);
            }
            this.d++;
            return 0;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    /* access modifiers changed from: package-private */
    public void a(int i, K k, int i2, int i3) {
        this.e[i] = (((long) i3) << 32) | 4294967295L;
        this.a[i] = k;
        this.b[i] = i2;
    }

    private void l(int i) {
        int length = this.e.length;
        if (i > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                g(max);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g(int i) {
        this.a = Arrays.copyOf(this.a, i);
        this.b = Arrays.copyOf(this.b, i);
        long[] jArr = this.e;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.e = copyOf;
    }

    private void m(int i) {
        if (this.f.length >= 1073741824) {
            this.h = Integer.MAX_VALUE;
            return;
        }
        int i2 = ((int) (((float) i) * this.g)) + 1;
        int[] j = j(i);
        long[] jArr = this.e;
        int length = j.length - 1;
        for (int i3 = 0; i3 < this.c; i3++) {
            int a2 = a(jArr[i3]);
            int i4 = a2 & length;
            int i5 = j[i4];
            j[i4] = i3;
            jArr[i3] = (((long) a2) << 32) | (((long) i5) & 4294967295L);
        }
        this.h = i2;
        this.f = j;
    }

    /* access modifiers changed from: package-private */
    public int a(Object obj) {
        int a2 = af.a(obj);
        int i = this.f[e() & a2];
        while (i != -1) {
            long j = this.e[i];
            if (a(j) == a2 && j.a(obj, this.a[i])) {
                return i;
            }
            i = b(j);
        }
        return -1;
    }

    public int b(Object obj) {
        int a2 = a(obj);
        if (a2 == -1) {
            return 0;
        }
        return this.b[a2];
    }

    public int c(Object obj) {
        return b(obj, af.a(obj));
    }

    private int b(Object obj, int i) {
        int e = e() & i;
        int i2 = this.f[e];
        if (i2 == -1) {
            return 0;
        }
        int i3 = -1;
        while (true) {
            if (a(this.e[i2]) != i || !j.a(obj, this.a[i2])) {
                int b = b(this.e[i2]);
                if (b == -1) {
                    return 0;
                }
                i3 = i2;
                i2 = b;
            } else {
                int i4 = this.b[i2];
                if (i3 == -1) {
                    this.f[e] = b(this.e[i2]);
                } else {
                    long[] jArr = this.e;
                    jArr[i3] = a(jArr[i3], b(jArr[i2]));
                }
                i(i2);
                this.c--;
                this.d++;
                return i4;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int h(int i) {
        return b(this.a[i], a(this.e[i]));
    }

    /* access modifiers changed from: package-private */
    public void i(int i) {
        int c = c() - 1;
        if (i < c) {
            Object[] objArr = this.a;
            objArr[i] = objArr[c];
            int[] iArr = this.b;
            iArr[i] = iArr[c];
            objArr[c] = null;
            iArr[c] = 0;
            long[] jArr = this.e;
            long j = jArr[c];
            jArr[i] = j;
            jArr[c] = -1;
            int a2 = a(j) & e();
            int[] iArr2 = this.f;
            int i2 = iArr2[a2];
            if (i2 == c) {
                iArr2[a2] = i;
                return;
            }
            while (true) {
                long j2 = this.e[i2];
                int b = b(j2);
                if (b == c) {
                    this.e[i2] = a(j2, i);
                    return;
                }
                i2 = b;
            }
        } else {
            this.a[i] = null;
            this.b[i] = 0;
            this.e[i] = -1;
        }
    }

    public void d() {
        this.d++;
        Arrays.fill(this.a, 0, this.c, (Object) null);
        Arrays.fill(this.b, 0, this.c, 0);
        Arrays.fill(this.f, -1);
        Arrays.fill(this.e, -1L);
        this.c = 0;
    }
}
