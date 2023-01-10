package com.hmt.analytics.android;

import java.util.Arrays;

/* compiled from: BaseNCodec */
public abstract class d {
    private final int a;
    @Deprecated
    protected final byte b;
    protected final byte c;
    protected final int d;
    private final int e;
    private final int f;

    /* access modifiers changed from: protected */
    public int a() {
        return 8192;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2, a aVar);

    /* access modifiers changed from: protected */
    public abstract boolean a(byte b);

    /* compiled from: BaseNCodec */
    /* access modifiers changed from: package-private */
    public static class a {
        int a;
        long b;
        byte[] c;
        int d;
        int e;
        boolean f;
        int g;
        int h;

        a() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.a), Long.valueOf(this.b), Integer.valueOf(this.h), Integer.valueOf(this.d), Integer.valueOf(this.e));
        }
    }

    protected d(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (byte) 61);
    }

    protected d(int i, int i2, int i3, int i4, byte b) {
        this.b = 61;
        this.a = i;
        this.e = i2;
        this.d = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.f = i4;
        this.c = b;
    }

    /* access modifiers changed from: package-private */
    public int a(a aVar) {
        if (aVar.c != null) {
            return aVar.d - aVar.e;
        }
        return 0;
    }

    private byte[] b(a aVar) {
        if (aVar.c == null) {
            aVar.c = new byte[a()];
            aVar.d = 0;
            aVar.e = 0;
        } else {
            byte[] bArr = new byte[(aVar.c.length * 2)];
            System.arraycopy(aVar.c, 0, bArr, 0, aVar.c.length);
            aVar.c = bArr;
        }
        return aVar.c;
    }

    /* access modifiers changed from: protected */
    public byte[] a(int i, a aVar) {
        if (aVar.c == null || aVar.c.length < aVar.d + i) {
            return b(aVar);
        }
        return aVar.c;
    }

    /* access modifiers changed from: package-private */
    public int b(byte[] bArr, int i, int i2, a aVar) {
        if (aVar.c == null) {
            return aVar.f ? -1 : 0;
        }
        int min = Math.min(a(aVar), i2);
        System.arraycopy(aVar.c, aVar.e, bArr, i, min);
        aVar.e += min;
        if (aVar.e >= aVar.d) {
            aVar.c = null;
        }
        return min;
    }

    public byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        a(bArr, 0, bArr.length, aVar);
        a(bArr, 0, -1, aVar);
        byte[] bArr2 = new byte[(aVar.d - aVar.e)];
        b(bArr2, 0, bArr2.length, aVar);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    public boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.c == b || a(b)) {
                return true;
            }
        }
        return false;
    }

    public long d(byte[] bArr) {
        int length = bArr.length;
        int i = this.a;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.e);
        int i2 = this.d;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.f)) : j;
    }
}
