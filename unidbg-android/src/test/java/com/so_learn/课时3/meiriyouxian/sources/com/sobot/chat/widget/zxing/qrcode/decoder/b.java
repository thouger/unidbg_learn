package com.sobot.chat.widget.zxing.qrcode.decoder;

import com.sobot.chat.widget.zxing.qrcode.decoder.g;

/* compiled from: DataBlock */
/* access modifiers changed from: package-private */
public final class b {
    private final int a;
    private final byte[] b;

    private b(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    static b[] a(byte[] bArr, g gVar, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length == gVar.c()) {
            g.b a = gVar.a(errorCorrectionLevel);
            g.a[] b = a.b();
            int i = 0;
            for (g.a aVar : b) {
                i += aVar.a();
            }
            b[] bVarArr = new b[i];
            int length = b.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                g.a aVar2 = b[i2];
                int i4 = i3;
                int i5 = 0;
                while (i5 < aVar2.a()) {
                    int b2 = aVar2.b();
                    bVarArr[i4] = new b(b2, new byte[(a.a() + b2)]);
                    i5++;
                    i4++;
                }
                i2++;
                i3 = i4;
            }
            int length2 = bVarArr[0].b.length;
            int length3 = bVarArr.length - 1;
            while (length3 >= 0 && bVarArr[length3].b.length != length2) {
                length3--;
            }
            int i6 = length3 + 1;
            int a2 = length2 - a.a();
            int i7 = 0;
            int i8 = 0;
            while (i7 < a2) {
                int i9 = i8;
                int i10 = 0;
                while (i10 < i3) {
                    bVarArr[i10].b[i7] = bArr[i9];
                    i10++;
                    i9++;
                }
                i7++;
                i8 = i9;
            }
            int i11 = i6;
            while (i11 < i3) {
                bVarArr[i11].b[a2] = bArr[i8];
                i11++;
                i8++;
            }
            int length4 = bVarArr[0].b.length;
            while (a2 < length4) {
                int i12 = i8;
                int i13 = 0;
                while (i13 < i3) {
                    bVarArr[i13].b[i13 < i6 ? a2 : a2 + 1] = bArr[i12];
                    i13++;
                    i12++;
                }
                a2++;
                i8 = i12;
            }
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public byte[] b() {
        return this.b;
    }
}
