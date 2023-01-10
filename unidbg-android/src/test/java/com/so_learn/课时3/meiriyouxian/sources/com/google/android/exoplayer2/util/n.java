package com.google.android.exoplayer2.util;

/* compiled from: ParsableBitArray */
public final class n {
    public byte[] a;
    private int b;
    private int c;
    private int d;

    public n() {
        this.a = z.f;
    }

    public n(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public n(byte[] bArr, int i) {
        this.a = bArr;
        this.d = i;
    }

    public void a(byte[] bArr) {
        a(bArr, bArr.length);
    }

    public void a(o oVar) {
        a(oVar.a, oVar.c());
        a(oVar.d() * 8);
    }

    public void a(byte[] bArr, int i) {
        this.a = bArr;
        this.b = 0;
        this.c = 0;
        this.d = i;
    }

    public int a() {
        return ((this.d - this.b) * 8) - this.c;
    }

    public int b() {
        return (this.b * 8) + this.c;
    }

    public int c() {
        a.b(this.c == 0);
        return this.b;
    }

    public void a(int i) {
        this.b = i / 8;
        this.c = i - (this.b * 8);
        g();
    }

    public void d() {
        int i = this.c + 1;
        this.c = i;
        if (i == 8) {
            this.c = 0;
            this.b++;
        }
        g();
    }

    public void b(int i) {
        int i2 = i / 8;
        this.b += i2;
        this.c += i - (i2 * 8);
        int i3 = this.c;
        if (i3 > 7) {
            this.b++;
            this.c = i3 - 8;
        }
        g();
    }

    public boolean e() {
        boolean z = (this.a[this.b] & (128 >> this.c)) != 0;
        d();
        return z;
    }

    public int c(int i) {
        int i2;
        if (i == 0) {
            return 0;
        }
        this.c += i;
        int i3 = 0;
        while (true) {
            i2 = this.c;
            if (i2 <= 8) {
                break;
            }
            this.c = i2 - 8;
            byte[] bArr = this.a;
            int i4 = this.b;
            this.b = i4 + 1;
            i3 |= (bArr[i4] & 255) << this.c;
        }
        byte[] bArr2 = this.a;
        int i5 = this.b;
        int i6 = (-1 >>> (32 - i)) & (i3 | ((bArr2[i5] & 255) >> (8 - i2)));
        if (i2 == 8) {
            this.c = 0;
            this.b = i5 + 1;
        }
        g();
        return i6;
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = (i2 >> 3) + i;
        while (i < i3) {
            byte[] bArr2 = this.a;
            int i4 = this.b;
            this.b = i4 + 1;
            byte b = bArr2[i4];
            int i5 = this.c;
            bArr[i] = (byte) (b << i5);
            bArr[i] = (byte) (((255 & bArr2[this.b]) >> (8 - i5)) | bArr[i]);
            i++;
        }
        int i6 = i2 & 7;
        if (i6 != 0) {
            bArr[i3] = (byte) (bArr[i3] & (255 >> i6));
            int i7 = this.c;
            if (i7 + i6 > 8) {
                byte b2 = bArr[i3];
                byte[] bArr3 = this.a;
                int i8 = this.b;
                this.b = i8 + 1;
                bArr[i3] = (byte) (b2 | ((bArr3[i8] & 255) << i7));
                this.c = i7 - 8;
            }
            this.c += i6;
            byte[] bArr4 = this.a;
            int i9 = this.b;
            int i10 = this.c;
            bArr[i3] = (byte) (((byte) (((bArr4[i9] & 255) >> (8 - i10)) << (8 - i6))) | bArr[i3]);
            if (i10 == 8) {
                this.c = 0;
                this.b = i9 + 1;
            }
            g();
        }
    }

    public void f() {
        if (this.c != 0) {
            this.c = 0;
            this.b++;
            g();
        }
    }

    public void b(byte[] bArr, int i, int i2) {
        a.b(this.c == 0);
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
        g();
    }

    public void d(int i) {
        a.b(this.c == 0);
        this.b += i;
        g();
    }

    public void a(int i, int i2) {
        if (i2 < 32) {
            i &= (1 << i2) - 1;
        }
        int min = Math.min(8 - this.c, i2);
        int i3 = this.c;
        int i4 = (8 - i3) - min;
        byte[] bArr = this.a;
        int i5 = this.b;
        bArr[i5] = (byte) (((65280 >> i3) | ((1 << i4) - 1)) & bArr[i5]);
        int i6 = i2 - min;
        bArr[i5] = (byte) (((i >>> i6) << i4) | bArr[i5]);
        int i7 = i5 + 1;
        while (i6 > 8) {
            this.a[i7] = (byte) (i >>> (i6 - 8));
            i6 -= 8;
            i7++;
        }
        int i8 = 8 - i6;
        byte[] bArr2 = this.a;
        bArr2[i7] = (byte) (bArr2[i7] & ((1 << i8) - 1));
        bArr2[i7] = (byte) (((i & ((1 << i6) - 1)) << i8) | bArr2[i7]);
        b(i2);
        g();
    }

    private void g() {
        int i;
        int i2 = this.b;
        a.b(i2 >= 0 && (i2 < (i = this.d) || (i2 == i && this.c == 0)));
    }
}
