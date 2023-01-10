package com.google.android.exoplayer2.util;

import java.nio.charset.Charset;

/* compiled from: ParsableByteArray */
public final class o {
    public byte[] a;
    private int b;
    private int c;

    public o() {
        this.a = z.f;
    }

    public o(int i) {
        this.a = new byte[i];
        this.c = i;
    }

    public o(byte[] bArr) {
        this.a = bArr;
        this.c = bArr.length;
    }

    public o(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
    }

    public void a() {
        this.b = 0;
        this.c = 0;
    }

    public void a(int i) {
        a(e() < i ? new byte[i] : this.a, i);
    }

    public void a(byte[] bArr) {
        a(bArr, bArr.length);
    }

    public void a(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
        this.b = 0;
    }

    public int b() {
        return this.c - this.b;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        a.a(i >= 0 && i <= this.a.length);
        this.c = i;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.a.length;
    }

    public void c(int i) {
        a.a(i >= 0 && i <= this.c);
        this.b = i;
    }

    public void d(int i) {
        c(this.b + i);
    }

    public void a(n nVar, int i) {
        a(nVar.a, 0, i);
        nVar.a(0);
    }

    public void a(byte[] bArr, int i, int i2) {
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
    }

    public int f() {
        return this.a[this.b] & 255;
    }

    public char g() {
        byte[] bArr = this.a;
        int i = this.b;
        return (char) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int h() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    public int i() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        return (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
    }

    public int j() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        return ((bArr[i2] & 255) << 8) | (bArr[i] & 255);
    }

    public short k() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        return (short) ((bArr[i2] & 255) | ((bArr[i] & 255) << 8));
    }

    public int l() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        int i3 = ((bArr[i] & 255) << 16) | ((bArr[i2] & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        return (bArr[i4] & 255) | i3;
    }

    public int m() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        int i3 = (((bArr[i] & 255) << 24) >> 8) | ((bArr[i2] & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        return (bArr[i4] & 255) | i3;
    }

    public long n() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        long j = ((((long) bArr[i]) & 255) << 24) | ((((long) bArr[i2]) & 255) << 16);
        int i3 = this.b;
        this.b = i3 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        return j2 | (255 & ((long) bArr[i4]));
    }

    public long o() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        long j = (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8);
        int i3 = this.b;
        this.b = i3 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 16);
        int i4 = this.b;
        this.b = i4 + 1;
        return j2 | ((255 & ((long) bArr[i4])) << 24);
    }

    public int p() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        int i3 = ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16);
        int i4 = this.b;
        this.b = i4 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 8);
        int i6 = this.b;
        this.b = i6 + 1;
        return (bArr[i6] & 255) | i5;
    }

    public int q() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        int i3 = (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 16);
        int i6 = this.b;
        this.b = i6 + 1;
        return ((bArr[i6] & 255) << 24) | i5;
    }

    public long r() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        long j = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i2]) & 255) << 48);
        int i3 = this.b;
        this.b = i3 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 40);
        int i4 = this.b;
        this.b = i4 + 1;
        long j3 = j2 | ((((long) bArr[i4]) & 255) << 32);
        int i5 = this.b;
        this.b = i5 + 1;
        long j4 = j3 | ((((long) bArr[i5]) & 255) << 24);
        int i6 = this.b;
        this.b = i6 + 1;
        long j5 = j4 | ((((long) bArr[i6]) & 255) << 16);
        int i7 = this.b;
        this.b = i7 + 1;
        long j6 = j5 | ((((long) bArr[i7]) & 255) << 8);
        int i8 = this.b;
        this.b = i8 + 1;
        return j6 | (255 & ((long) bArr[i8]));
    }

    public long s() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        long j = (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8);
        int i3 = this.b;
        this.b = i3 + 1;
        long j2 = j | ((((long) bArr[i3]) & 255) << 16);
        int i4 = this.b;
        this.b = i4 + 1;
        long j3 = j2 | ((((long) bArr[i4]) & 255) << 24);
        int i5 = this.b;
        this.b = i5 + 1;
        long j4 = j3 | ((((long) bArr[i5]) & 255) << 32);
        int i6 = this.b;
        this.b = i6 + 1;
        long j5 = j4 | ((((long) bArr[i6]) & 255) << 40);
        int i7 = this.b;
        this.b = i7 + 1;
        long j6 = j5 | ((((long) bArr[i7]) & 255) << 48);
        int i8 = this.b;
        this.b = i8 + 1;
        return j6 | ((255 & ((long) bArr[i8])) << 56);
    }

    public int t() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = this.b;
        this.b = i2 + 1;
        int i3 = (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
        this.b += 2;
        return i3;
    }

    public int u() {
        return (h() << 21) | (h() << 14) | (h() << 7) | h();
    }

    public int v() {
        int p = p();
        if (p >= 0) {
            return p;
        }
        throw new IllegalStateException("Top bit not zero: " + p);
    }

    public int w() {
        int q = q();
        if (q >= 0) {
            return q;
        }
        throw new IllegalStateException("Top bit not zero: " + q);
    }

    public long x() {
        long r = r();
        if (r >= 0) {
            return r;
        }
        throw new IllegalStateException("Top bit not zero: " + r);
    }

    public double y() {
        return Double.longBitsToDouble(r());
    }

    public String e(int i) {
        return a(i, Charset.forName("UTF-8"));
    }

    public String a(int i, Charset charset) {
        String str = new String(this.a, this.b, i, charset);
        this.b += i;
        return str;
    }

    public String f(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = (this.b + i) - 1;
        String a = z.a(this.a, this.b, (i2 >= this.c || this.a[i2] != 0) ? i : i - 1);
        this.b += i;
        return a;
    }

    public String z() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && this.a[i] != 0) {
            i++;
        }
        byte[] bArr = this.a;
        int i2 = this.b;
        String a = z.a(bArr, i2, i - i2);
        this.b = i;
        int i3 = this.b;
        if (i3 < this.c) {
            this.b = i3 + 1;
        }
        return a;
    }

    public String A() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && !z.a(this.a[i])) {
            i++;
        }
        int i2 = this.b;
        if (i - i2 >= 3) {
            byte[] bArr = this.a;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.b = i2 + 3;
            }
        }
        byte[] bArr2 = this.a;
        int i3 = this.b;
        String a = z.a(bArr2, i3, i - i3);
        this.b = i;
        int i4 = this.b;
        int i5 = this.c;
        if (i4 == i5) {
            return a;
        }
        if (this.a[i4] == 13) {
            this.b = i4 + 1;
            if (this.b == i5) {
                return a;
            }
        }
        byte[] bArr3 = this.a;
        int i6 = this.b;
        if (bArr3[i6] == 10) {
            this.b = i6 + 1;
        }
        return a;
    }

    public long B() {
        int i;
        int i2;
        long j = (long) this.a[this.b];
        int i3 = 7;
        while (true) {
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((((long) i4) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= (long) (i4 - 1);
                i2 = 7 - i3;
            } else if (i3 == 7) {
                i2 = 1;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            for (i = 1; i < i2; i++) {
                byte b = this.a[this.b + i];
                if ((b & 192) == 128) {
                    j = (j << 6) | ((long) (b & 63));
                } else {
                    throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
                }
            }
            this.b += i2;
            return j;
        }
        throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
    }
}
