package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.ads.a;

/* compiled from: Timeline */
public abstract class z {
    public static final z a = new AnonymousClass1();

    public abstract int a(Object obj);

    public abstract a a(int i, a aVar, boolean z);

    public abstract b a(int i, b bVar, boolean z, long j);

    public abstract Object a(int i);

    public abstract int b();

    public abstract int c();

    /* compiled from: Timeline */
    public static final class b {
        public Object a;
        public long b;
        public long c;
        public boolean d;
        public boolean e;
        public int f;
        public int g;
        public long h;
        public long i;
        public long j;

        public b a(Object obj, long j, long j2, boolean z, boolean z2, long j3, long j4, int i, int i2, long j5) {
            this.a = obj;
            this.b = j;
            this.c = j2;
            this.d = z;
            this.e = z2;
            this.h = j3;
            this.i = j4;
            this.f = i;
            this.g = i2;
            this.j = j5;
            return this;
        }

        public long a() {
            return c.a(this.h);
        }

        public long b() {
            return this.h;
        }

        public long c() {
            return c.a(this.i);
        }

        public long d() {
            return this.j;
        }
    }

    /* compiled from: Timeline */
    public static final class a {
        public Object a;
        public Object b;
        public int c;
        public long d;
        private long e;
        private com.google.android.exoplayer2.source.ads.a f;

        public a a(Object obj, Object obj2, int i, long j, long j2) {
            return a(obj, obj2, i, j, j2, com.google.android.exoplayer2.source.ads.a.a);
        }

        public a a(Object obj, Object obj2, int i, long j, long j2, com.google.android.exoplayer2.source.ads.a aVar) {
            this.a = obj;
            this.b = obj2;
            this.c = i;
            this.d = j;
            this.e = j2;
            this.f = aVar;
            return this;
        }

        public long a() {
            return this.d;
        }

        public long b() {
            return c.a(this.e);
        }

        public long c() {
            return this.e;
        }

        public int d() {
            return this.f.b;
        }

        public long a(int i) {
            return this.f.c[i];
        }

        public int b(int i) {
            return this.f.d[i].a();
        }

        public int a(int i, int i2) {
            return this.f.d[i].a(i2);
        }

        public boolean c(int i) {
            return !this.f.d[i].b();
        }

        public int a(long j) {
            return this.f.a(j);
        }

        public int b(long j) {
            return this.f.b(j);
        }

        public int d(int i) {
            return this.f.d[i].a;
        }

        public boolean b(int i, int i2) {
            a.C0087a aVar = this.f.d[i];
            return (aVar.a == -1 || aVar.c[i2] == 0) ? false : true;
        }

        public long c(int i, int i2) {
            a.C0087a aVar = this.f.d[i];
            if (aVar.a != -1) {
                return aVar.d[i2];
            }
            return -9223372036854775807L;
        }

        public long e() {
            return this.f.e;
        }
    }

    /* compiled from: Timeline */
    /* renamed from: com.google.android.exoplayer2.z$1  reason: invalid class name */
    static class AnonymousClass1 extends z {
        @Override // com.google.android.exoplayer2.z
        public int a(Object obj) {
            return -1;
        }

        @Override // com.google.android.exoplayer2.z
        public int b() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.z
        public int c() {
            return 0;
        }

        AnonymousClass1() {
        }

        @Override // com.google.android.exoplayer2.z
        public b a(int i, b bVar, boolean z, long j) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.z
        public a a(int i, a aVar, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.z
        public Object a(int i) {
            throw new IndexOutOfBoundsException();
        }
    }

    public final boolean a() {
        return b() == 0;
    }

    public int a(int i, int i2, boolean z) {
        if (i2 != 0) {
            if (i2 == 1) {
                return i;
            }
            if (i2 == 2) {
                return i == a(z) ? b(z) : i + 1;
            }
            throw new IllegalStateException();
        } else if (i == a(z)) {
            return -1;
        } else {
            return i + 1;
        }
    }

    public int a(boolean z) {
        if (a()) {
            return -1;
        }
        return b() - 1;
    }

    public int b(boolean z) {
        return a() ? -1 : 0;
    }

    public final b a(int i, b bVar) {
        return a(i, bVar, false);
    }

    public final b a(int i, b bVar, boolean z) {
        return a(i, bVar, z, 0);
    }

    public final int a(int i, a aVar, b bVar, int i2, boolean z) {
        int i3 = a(i, aVar).c;
        if (a(i3, bVar).g != i) {
            return i + 1;
        }
        int a2 = a(i3, i2, z);
        if (a2 == -1) {
            return -1;
        }
        return a(a2, bVar).f;
    }

    public final boolean b(int i, a aVar, b bVar, int i2, boolean z) {
        return a(i, aVar, bVar, i2, z) == -1;
    }

    public final Pair<Object, Long> a(b bVar, a aVar, int i, long j) {
        return a(bVar, aVar, i, j, 0);
    }

    public final Pair<Object, Long> a(b bVar, a aVar, int i, long j, long j2) {
        com.google.android.exoplayer2.util.a.a(i, 0, b());
        a(i, bVar, false, j2);
        if (j == -9223372036854775807L) {
            j = bVar.b();
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        int i2 = bVar.f;
        long d = bVar.d() + j;
        long a2 = a(i2, aVar, true).a();
        while (a2 != -9223372036854775807L && d >= a2 && i2 < bVar.g) {
            d -= a2;
            i2++;
            a2 = a(i2, aVar, true).a();
        }
        return Pair.create(aVar.b, Long.valueOf(d));
    }

    public a a(Object obj, a aVar) {
        return a(a(obj), aVar, true);
    }

    public final a a(int i, a aVar) {
        return a(i, aVar, false);
    }
}
