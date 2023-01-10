package com.hmt.analytics.android;

import com.android.internal.telephony.GsmAlphabet;
import com.hmt.analytics.android.d;

/* compiled from: Base64 */
public class b extends d {
    static final byte[] a = {13, 10};
    private static final byte[] e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] f = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] g = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, GsmAlphabet.GSM_EXTENDED_ESCAPE, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private final byte[] h;
    private final byte[] i;
    private final byte[] j;
    private final int k;
    private final int l;

    public b() {
        this(0);
    }

    public b(boolean z) {
        this(76, a, z);
    }

    public b(int i) {
        this(i, a);
    }

    public b(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.i = g;
        if (bArr == null) {
            this.l = 4;
            this.j = null;
        } else if (c(bArr)) {
            String a2 = e.a(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + a2 + "]");
        } else if (i > 0) {
            this.l = bArr.length + 4;
            this.j = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.j, 0, bArr.length);
        } else {
            this.l = 4;
            this.j = null;
        }
        this.k = this.l - 1;
        this.h = z ? f : e;
    }

    /* access modifiers changed from: package-private */
    @Override // com.hmt.analytics.android.d
    public void a(byte[] bArr, int i, int i2, d.a aVar) {
        if (!aVar.f) {
            if (i2 < 0) {
                aVar.f = true;
                if (aVar.h != 0 || this.d != 0) {
                    byte[] a2 = a(this.l, aVar);
                    int i3 = aVar.d;
                    int i4 = aVar.h;
                    if (i4 != 0) {
                        if (i4 == 1) {
                            int i5 = aVar.d;
                            aVar.d = i5 + 1;
                            a2[i5] = this.h[(aVar.a >> 2) & 63];
                            int i6 = aVar.d;
                            aVar.d = i6 + 1;
                            a2[i6] = this.h[(aVar.a << 4) & 63];
                            if (this.h == e) {
                                int i7 = aVar.d;
                                aVar.d = i7 + 1;
                                a2[i7] = this.c;
                                int i8 = aVar.d;
                                aVar.d = i8 + 1;
                                a2[i8] = this.c;
                            }
                        } else if (i4 == 2) {
                            int i9 = aVar.d;
                            aVar.d = i9 + 1;
                            a2[i9] = this.h[(aVar.a >> 10) & 63];
                            int i10 = aVar.d;
                            aVar.d = i10 + 1;
                            a2[i10] = this.h[(aVar.a >> 4) & 63];
                            int i11 = aVar.d;
                            aVar.d = i11 + 1;
                            a2[i11] = this.h[(aVar.a << 2) & 63];
                            if (this.h == e) {
                                int i12 = aVar.d;
                                aVar.d = i12 + 1;
                                a2[i12] = this.c;
                            }
                        } else {
                            throw new IllegalStateException("Impossible modulus " + aVar.h);
                        }
                    }
                    aVar.g += aVar.d - i3;
                    if (this.d > 0 && aVar.g > 0) {
                        System.arraycopy(this.j, 0, a2, aVar.d, this.j.length);
                        aVar.d += this.j.length;
                        return;
                    }
                    return;
                }
                return;
            }
            int i13 = i;
            int i14 = 0;
            while (i14 < i2) {
                byte[] a3 = a(this.l, aVar);
                aVar.h = (aVar.h + 1) % 3;
                int i15 = i13 + 1;
                byte b = bArr[i13];
                int i16 = b;
                if (b < 0) {
                    i16 = b + 256;
                }
                aVar.a = (aVar.a << 8) + (i16 == 1 ? 1 : 0);
                if (aVar.h == 0) {
                    int i17 = aVar.d;
                    aVar.d = i17 + 1;
                    a3[i17] = this.h[(aVar.a >> 18) & 63];
                    int i18 = aVar.d;
                    aVar.d = i18 + 1;
                    a3[i18] = this.h[(aVar.a >> 12) & 63];
                    int i19 = aVar.d;
                    aVar.d = i19 + 1;
                    a3[i19] = this.h[(aVar.a >> 6) & 63];
                    int i20 = aVar.d;
                    aVar.d = i20 + 1;
                    a3[i20] = this.h[aVar.a & 63];
                    aVar.g += 4;
                    if (this.d > 0 && this.d <= aVar.g) {
                        System.arraycopy(this.j, 0, a3, aVar.d, this.j.length);
                        aVar.d += this.j.length;
                        aVar.g = 0;
                    }
                }
                i14++;
                i13 = i15;
            }
        }
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, false, true);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        b bVar = z ? new b(z2) : new b(0, a, z2);
        long d = bVar.d(bArr);
        if (d <= ((long) i)) {
            return bVar.b(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + d + ") than the specified maximum size of " + i);
    }

    /* access modifiers changed from: protected */
    @Override // com.hmt.analytics.android.d
    public boolean a(byte b) {
        if (b >= 0) {
            byte[] bArr = this.i;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
