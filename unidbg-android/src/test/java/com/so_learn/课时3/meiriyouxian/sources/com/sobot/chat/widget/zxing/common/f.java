package com.sobot.chat.widget.zxing.common;

import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.a;
import com.sobot.chat.widget.zxing.c;

/* compiled from: GlobalHistogramBinarizer */
public class f extends a {
    private static final byte[] a = new byte[0];
    private byte[] b = a;
    private final int[] c = new int[32];

    public f(c cVar) {
        super(cVar);
    }

    @Override // com.sobot.chat.widget.zxing.a
    public a b() throws NotFoundException {
        c a2 = a();
        int b = a2.b();
        int c = a2.c();
        a aVar = new a(b, c);
        a(b);
        int[] iArr = this.c;
        for (int i = 1; i < 5; i++) {
            byte[] a3 = a2.a((c * i) / 5, this.b);
            int i2 = (b * 4) / 5;
            for (int i3 = b / 5; i3 < i2; i3++) {
                int i4 = (a3[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a4 = a(iArr);
        byte[] a5 = a2.a();
        for (int i5 = 0; i5 < c; i5++) {
            int i6 = i5 * b;
            for (int i7 = 0; i7 < b; i7++) {
                if ((a5[i6 + i7] & 255) < a4) {
                    aVar.b(i7, i5);
                }
            }
        }
        return aVar;
    }

    private void a(int i) {
        if (this.b.length < i) {
            this.b = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.c[i2] = 0;
        }
    }

    private static int a(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i6) {
                i5 = i7;
                i6 = i9;
            }
        }
        if (i3 > i5) {
            i3 = i5;
            i5 = i3;
        }
        if (i5 - i3 > length / 16) {
            int i10 = i5 - 1;
            int i11 = -1;
            int i12 = i10;
            while (i10 > i3) {
                int i13 = i10 - i3;
                int i14 = i13 * i13 * (i5 - i10) * (i2 - iArr[i10]);
                if (i14 > i11) {
                    i12 = i10;
                    i11 = i14;
                }
                i10--;
            }
            return i12 << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
