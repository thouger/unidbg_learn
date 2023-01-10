package com.sobot.chat.widget.zxing.qrcode.decoder;

import com.sobot.chat.widget.zxing.FormatException;

/* compiled from: BitMatrixParser */
/* access modifiers changed from: package-private */
public final class a {
    private final com.sobot.chat.widget.zxing.common.a a;
    private g b;
    private e c;
    private boolean d;

    a(com.sobot.chat.widget.zxing.common.a aVar) throws FormatException {
        int b = aVar.b();
        if (b < 21 || (b & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.a = aVar;
    }

    /* access modifiers changed from: package-private */
    public e a() throws FormatException {
        e eVar = this.c;
        if (eVar != null) {
            return eVar;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 = a(i3, 8, i2);
        }
        int a = a(8, 7, a(8, 8, a(7, 8, i2)));
        for (int i4 = 5; i4 >= 0; i4--) {
            a = a(8, i4, a);
        }
        int b = this.a.b();
        int i5 = b - 7;
        for (int i6 = b - 1; i6 >= i5; i6--) {
            i = a(8, i6, i);
        }
        for (int i7 = b - 8; i7 < b; i7++) {
            i = a(i7, 8, i);
        }
        this.c = e.b(a, i);
        e eVar2 = this.c;
        if (eVar2 != null) {
            return eVar2;
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: package-private */
    public g b() throws FormatException {
        g gVar = this.b;
        if (gVar != null) {
            return gVar;
        }
        int b = this.a.b();
        int i = (b - 17) / 4;
        if (i <= 6) {
            return g.b(i);
        }
        int i2 = b - 11;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = b - 9; i6 >= i2; i6--) {
                i4 = a(i6, i5, i4);
            }
        }
        g c = g.c(i4);
        if (c == null || c.d() != b) {
            for (int i7 = 5; i7 >= 0; i7--) {
                for (int i8 = b - 9; i8 >= i2; i8--) {
                    i3 = a(i7, i8, i3);
                }
            }
            g c2 = g.c(i3);
            if (c2 == null || c2.d() != b) {
                throw FormatException.getFormatInstance();
            }
            this.b = c2;
            return c2;
        }
        this.b = c;
        return c;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.a.a(i2, i) : this.a.a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    /* access modifiers changed from: package-private */
    public byte[] c() throws FormatException {
        e a = a();
        g b = b();
        DataMask dataMask = DataMask.values()[a.b()];
        int b2 = this.a.b();
        dataMask.unmaskBitMatrix(this.a, b2);
        com.sobot.chat.widget.zxing.common.a e = b.e();
        byte[] bArr = new byte[b.c()];
        int i = b2 - 1;
        boolean z = true;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = i;
        while (i5 > 0) {
            if (i5 == 6) {
                i5--;
            }
            int i6 = i4;
            int i7 = i3;
            int i8 = i2;
            int i9 = 0;
            while (i9 < b2) {
                int i10 = z ? i - i9 : i9;
                int i11 = i6;
                int i12 = i7;
                int i13 = i8;
                for (int i14 = 0; i14 < 2; i14++) {
                    int i15 = i5 - i14;
                    if (!e.a(i15, i10)) {
                        i12++;
                        int i16 = i11 << 1;
                        int i17 = this.a.a(i15, i10) ? i16 | 1 : i16;
                        if (i12 == 8) {
                            bArr[i13] = (byte) i17;
                            i13++;
                            i12 = 0;
                            i11 = 0;
                        } else {
                            i11 = i17;
                        }
                    }
                }
                i9++;
                i8 = i13;
                i7 = i12;
                i6 = i11;
            }
            z = !z;
            i5 -= 2;
            i2 = i8;
            i3 = i7;
            i4 = i6;
        }
        if (i2 == b.c()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (this.c != null) {
            DataMask.values()[this.c.b()].unmaskBitMatrix(this.a, this.a.b());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        int i = 0;
        while (i < this.a.a()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.a.b(); i3++) {
                if (this.a.a(i, i3) != this.a.a(i3, i)) {
                    this.a.c(i3, i);
                    this.a.c(i, i3);
                }
            }
            i = i2;
        }
    }
}
