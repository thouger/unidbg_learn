package com.sobot.chat.widget.zxing.common;

/* compiled from: BitSource */
public final class b {
    private final byte[] a;
    private int b;
    private int c;

    public b(byte[] bArr) {
        this.a = bArr;
    }

    public int a(int i) {
        int i2;
        if (i < 1 || i > 32 || i > a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i3 = this.c;
        if (i3 > 0) {
            int i4 = 8 - i3;
            int min = Math.min(i, i4);
            int i5 = i4 - min;
            byte[] bArr = this.a;
            int i6 = this.b;
            i2 = (((255 >> (8 - min)) << i5) & bArr[i6]) >> i5;
            i -= min;
            this.c += min;
            if (this.c == 8) {
                this.c = 0;
                this.b = i6 + 1;
            }
        } else {
            i2 = 0;
        }
        if (i <= 0) {
            return i2;
        }
        while (i >= 8) {
            byte[] bArr2 = this.a;
            int i7 = this.b;
            i2 = (i2 << 8) | (bArr2[i7] & 255);
            this.b = i7 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return i2;
        }
        int i8 = 8 - i;
        int i9 = (i2 << i) | ((((255 >> i8) << i8) & this.a[this.b]) >> i8);
        this.c += i;
        return i9;
    }

    public int a() {
        return ((this.a.length - this.b) * 8) - this.c;
    }
}
