package com.google.android.exoplayer2.util;

/* compiled from: ParsableNalUnitBitArray */
public final class p {
    private byte[] a;
    private int b;
    private int c;
    private int d;

    public p(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.c = i;
        this.b = i2;
        this.d = 0;
        g();
    }

    public void a() {
        int i = 1;
        int i2 = this.d + 1;
        this.d = i2;
        if (i2 == 8) {
            this.d = 0;
            int i3 = this.c;
            if (d(i3 + 1)) {
                i = 2;
            }
            this.c = i3 + i;
        }
        g();
    }

    public void a(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        this.c = i2 + i3;
        this.d += i - (i3 * 8);
        int i4 = this.d;
        if (i4 > 7) {
            this.c++;
            this.d = i4 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.c) {
                g();
                return;
            } else if (d(i2)) {
                this.c++;
                i2 += 2;
            }
        }
    }

    public boolean b(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        int i4 = i2 + i3;
        int i5 = (this.d + i) - (i3 * 8);
        if (i5 > 7) {
            i4++;
            i5 -= 8;
        }
        while (true) {
            i2++;
            if (i2 > i4 || i4 >= this.b) {
                break;
            } else if (d(i2)) {
                i4++;
                i2 += 2;
            }
        }
        int i6 = this.b;
        if (i4 < i6) {
            return true;
        }
        if (i4 == i6 && i5 == 0) {
            return true;
        }
        return false;
    }

    public boolean b() {
        boolean z = (this.a[this.c] & (128 >> this.d)) != 0;
        a();
        return z;
    }

    public int c(int i) {
        int i2;
        int i3;
        this.d += i;
        int i4 = 0;
        while (true) {
            i2 = this.d;
            i3 = 2;
            if (i2 <= 8) {
                break;
            }
            this.d = i2 - 8;
            byte[] bArr = this.a;
            int i5 = this.c;
            i4 |= (bArr[i5] & 255) << this.d;
            if (!d(i5 + 1)) {
                i3 = 1;
            }
            this.c = i5 + i3;
        }
        byte[] bArr2 = this.a;
        int i6 = this.c;
        int i7 = (-1 >>> (32 - i)) & (i4 | ((bArr2[i6] & 255) >> (8 - i2)));
        if (i2 == 8) {
            this.d = 0;
            if (!d(i6 + 1)) {
                i3 = 1;
            }
            this.c = i6 + i3;
        }
        g();
        return i7;
    }

    public boolean c() {
        int i = this.c;
        int i2 = this.d;
        int i3 = 0;
        while (this.c < this.b && !b()) {
            i3++;
        }
        boolean z = this.c == this.b;
        this.c = i;
        this.d = i2;
        if (z || !b((i3 * 2) + 1)) {
            return false;
        }
        return true;
    }

    public int d() {
        return f();
    }

    public int e() {
        int f = f();
        return (f % 2 == 0 ? -1 : 1) * ((f + 1) / 2);
    }

    private int f() {
        int i = 0;
        int i2 = 0;
        while (!b()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = c(i2);
        }
        return i3 + i;
    }

    private boolean d(int i) {
        if (2 <= i && i < this.b) {
            byte[] bArr = this.a;
            if (bArr[i] == 3 && bArr[i - 2] == 0 && bArr[i - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    private void g() {
        int i;
        int i2 = this.c;
        a.b(i2 >= 0 && (i2 < (i = this.b) || (i2 == i && this.d == 0)));
    }
}
