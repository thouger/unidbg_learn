package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import java.util.Arrays;

/* compiled from: AdPlaybackState */
public final class a {
    public static final a a = new a(new long[0]);
    public final int b;
    public final long[] c;
    public final C0087a[] d;
    public final long e;
    public final long f;

    /* compiled from: AdPlaybackState */
    /* renamed from: com.google.android.exoplayer2.source.ads.a$a  reason: collision with other inner class name */
    public static final class C0087a {
        public final int a;
        public final Uri[] b;
        public final int[] c;
        public final long[] d;

        public C0087a() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private C0087a(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
            com.google.android.exoplayer2.util.a.a(iArr.length == uriArr.length);
            this.a = i;
            this.c = iArr;
            this.b = uriArr;
            this.d = jArr;
        }

        public int a() {
            return a(-1);
        }

        public int a(int i) {
            int i2 = i + 1;
            while (true) {
                int[] iArr = this.c;
                if (i2 >= iArr.length || iArr[i2] == 0 || iArr[i2] == 1) {
                    break;
                }
                i2++;
            }
            return i2;
        }

        public boolean b() {
            return this.a == -1 || a() < this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0087a aVar = (C0087a) obj;
            return this.a == aVar.a && Arrays.equals(this.b, aVar.b) && Arrays.equals(this.c, aVar.c) && Arrays.equals(this.d, aVar.d);
        }

        public int hashCode() {
            return (((((this.a * 31) + Arrays.hashCode(this.b)) * 31) + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d);
        }

        public C0087a a(long[] jArr) {
            com.google.android.exoplayer2.util.a.a(this.a == -1 || jArr.length <= this.b.length);
            int length = jArr.length;
            Uri[] uriArr = this.b;
            if (length < uriArr.length) {
                jArr = a(jArr, uriArr.length);
            }
            return new C0087a(this.a, this.c, this.b, jArr);
        }

        private static long[] a(long[] jArr, int i) {
            int length = jArr.length;
            int max = Math.max(i, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, -9223372036854775807L);
            return copyOf;
        }
    }

    public a(long... jArr) {
        int length = jArr.length;
        this.b = length;
        this.c = Arrays.copyOf(jArr, length);
        this.d = new C0087a[length];
        for (int i = 0; i < length; i++) {
            this.d[i] = new C0087a();
        }
        this.e = 0;
        this.f = -9223372036854775807L;
    }

    private a(long[] jArr, C0087a[] aVarArr, long j, long j2) {
        this.b = aVarArr.length;
        this.c = jArr;
        this.d = aVarArr;
        this.e = j;
        this.f = j2;
    }

    public int a(long j) {
        int length = this.c.length - 1;
        while (length >= 0 && a(j, length)) {
            length--;
        }
        if (length < 0 || !this.d[length].b()) {
            return -1;
        }
        return length;
    }

    public int b(long j) {
        int i = 0;
        while (true) {
            long[] jArr = this.c;
            if (i >= jArr.length || jArr[i] == Long.MIN_VALUE || (j < jArr[i] && this.d[i].b())) {
                break;
            }
            i++;
        }
        if (i < this.c.length) {
            return i;
        }
        return -1;
    }

    public a a(long[][] jArr) {
        C0087a[] aVarArr = this.d;
        C0087a[] aVarArr2 = (C0087a[]) Arrays.copyOf(aVarArr, aVarArr.length);
        for (int i = 0; i < this.b; i++) {
            aVarArr2[i] = aVarArr2[i].a(jArr[i]);
        }
        return new a(this.c, aVarArr2, this.e, this.f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.b == aVar.b && this.e == aVar.e && this.f == aVar.f && Arrays.equals(this.c, aVar.c) && Arrays.equals(this.d, aVar.d);
    }

    public int hashCode() {
        return (((((((this.b * 31) + ((int) this.e)) * 31) + ((int) this.f)) * 31) + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d);
    }

    private boolean a(long j, int i) {
        long j2 = this.c[i];
        if (j2 != Long.MIN_VALUE) {
            return j < j2;
        }
        long j3 = this.f;
        return j3 == -9223372036854775807L || j < j3;
    }
}
