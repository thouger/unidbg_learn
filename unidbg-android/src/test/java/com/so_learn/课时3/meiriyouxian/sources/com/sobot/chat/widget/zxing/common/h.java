package com.sobot.chat.widget.zxing.common;

import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.c;
import java.lang.reflect.Array;

/* compiled from: HybridBinarizer */
public final class h extends f {
    private a a;

    public h(c cVar) {
        super(cVar);
    }

    @Override // com.sobot.chat.widget.zxing.common.f, com.sobot.chat.widget.zxing.a
    public a b() throws NotFoundException {
        a aVar = this.a;
        if (aVar != null) {
            return aVar;
        }
        c a = a();
        int b = a.b();
        int c = a.c();
        if (b < 40 || c < 40) {
            this.a = super.b();
        } else {
            byte[] a2 = a.a();
            int i = b >> 3;
            if ((b & 7) != 0) {
                i++;
            }
            int i2 = c >> 3;
            if ((c & 7) != 0) {
                i2++;
            }
            int[][] a3 = a(a2, i, i2, b, c);
            a aVar2 = new a(b, c);
            a(a2, i, i2, b, c, a3, aVar2);
            this.a = aVar2;
        }
        return this.a;
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, a aVar) {
        int i5 = i4 - 8;
        int i6 = i3 - 8;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i7 << 3;
            int i9 = i8 > i5 ? i5 : i8;
            int a = a(i7, i2 - 3);
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i10 << 3;
                int i12 = i11 > i6 ? i6 : i11;
                int a2 = a(i10, i - 3);
                int i13 = 0;
                for (int i14 = -2; i14 <= 2; i14++) {
                    int[] iArr2 = iArr[a + i14];
                    i13 += iArr2[a2 - 2] + iArr2[a2 - 1] + iArr2[a2] + iArr2[a2 + 1] + iArr2[2 + a2];
                }
                a(bArr, i12, i9, i13 / 25, i3, aVar);
            }
        }
    }

    private static int a(int i, int i2) {
        if (i < 2) {
            return 2;
        }
        return Math.min(i, i2);
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, a aVar) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    aVar.b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = 8;
        int i6 = i4 - 8;
        int i7 = i3 - 8;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i);
        for (int i8 = 0; i8 < i2; i8++) {
            int i9 = i8 << 3;
            if (i9 > i6) {
                i9 = i6;
            }
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i10 << 3;
                if (i11 > i7) {
                    i11 = i7;
                }
                int i12 = 255;
                int i13 = (i9 * i3) + i11;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                while (i14 < i5) {
                    int i17 = i15;
                    int i18 = 0;
                    while (i18 < i5) {
                        int i19 = bArr[i13 + i18] & 255;
                        i17 += i19;
                        if (i19 < i12) {
                            i12 = i19;
                        }
                        if (i19 > i16) {
                            i16 = i19;
                        }
                        i18++;
                        i5 = 8;
                    }
                    if (i16 - i12 > 24) {
                        i14++;
                        i13 += i3;
                        i5 = 8;
                        while (i14 < 8) {
                            for (int i20 = 0; i20 < 8; i20++) {
                                i17 += bArr[i13 + i20] & 255;
                            }
                            i14++;
                            i13 += i3;
                        }
                    } else {
                        i5 = 8;
                    }
                    i15 = i17;
                    i14++;
                    i13 += i3;
                }
                int i21 = i15 >> 6;
                if (i16 - i12 <= 24) {
                    i21 = i12 / 2;
                    if (i8 > 0 && i10 > 0) {
                        int i22 = i8 - 1;
                        int i23 = i10 - 1;
                        int i24 = ((iArr[i22][i10] + (iArr[i8][i23] * 2)) + iArr[i22][i23]) / 4;
                        if (i12 < i24) {
                            i21 = i24;
                        }
                    }
                }
                iArr[i8][i10] = i21;
            }
        }
        return iArr;
    }
}
