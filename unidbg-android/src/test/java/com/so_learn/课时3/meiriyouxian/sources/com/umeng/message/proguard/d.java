package com.umeng.message.proguard;

import com.android.internal.telephony.GsmAlphabet;
import java.math.BigInteger;

/* compiled from: Base64 */
public class d extends e {
    static final byte[] a = {13, 10};
    private static final int m = 6;
    private static final int n = 3;
    private static final int o = 4;
    private static final byte[] p = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] q = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] r = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, GsmAlphabet.GSM_EXTENDED_ESCAPE, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private static final int s = 63;
    private final byte[] t;
    private final byte[] u;
    private final byte[] v;
    private final int w;
    private final int x;
    private int y;

    public d() {
        this(0);
    }

    public d(boolean z) {
        this(76, a, z);
    }

    public d(int i) {
        this(i, a);
    }

    public d(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.u = r;
        if (bArr == null) {
            this.x = 4;
            this.v = null;
        } else if (n(bArr)) {
            String f = b.f(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + f + "]");
        } else if (i > 0) {
            this.x = bArr.length + 4;
            this.v = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.v, 0, bArr.length);
        } else {
            this.x = 4;
            this.v = null;
        }
        this.w = this.x - 1;
        this.t = z ? q : p;
    }

    public boolean a() {
        return this.t == q;
    }

    /* access modifiers changed from: package-private */
    @Override // com.umeng.message.proguard.e
    public void a(byte[] bArr, int i, int i2) {
        if (!this.j) {
            if (i2 < 0) {
                this.j = true;
                if (this.l != 0 || this.g != 0) {
                    a(this.x);
                    int i3 = this.i;
                    int i4 = this.l;
                    if (i4 == 1) {
                        byte[] bArr2 = this.h;
                        int i5 = this.i;
                        this.i = i5 + 1;
                        bArr2[i5] = this.t[(this.y >> 2) & 63];
                        byte[] bArr3 = this.h;
                        int i6 = this.i;
                        this.i = i6 + 1;
                        byte[] bArr4 = this.t;
                        bArr3[i6] = bArr4[(this.y << 4) & 63];
                        if (bArr4 == p) {
                            byte[] bArr5 = this.h;
                            int i7 = this.i;
                            this.i = i7 + 1;
                            bArr5[i7] = 61;
                            byte[] bArr6 = this.h;
                            int i8 = this.i;
                            this.i = i8 + 1;
                            bArr6[i8] = 61;
                        }
                    } else if (i4 == 2) {
                        byte[] bArr7 = this.h;
                        int i9 = this.i;
                        this.i = i9 + 1;
                        bArr7[i9] = this.t[(this.y >> 10) & 63];
                        byte[] bArr8 = this.h;
                        int i10 = this.i;
                        this.i = i10 + 1;
                        bArr8[i10] = this.t[(this.y >> 4) & 63];
                        byte[] bArr9 = this.h;
                        int i11 = this.i;
                        this.i = i11 + 1;
                        byte[] bArr10 = this.t;
                        bArr9[i11] = bArr10[(this.y << 2) & 63];
                        if (bArr10 == p) {
                            byte[] bArr11 = this.h;
                            int i12 = this.i;
                            this.i = i12 + 1;
                            bArr11[i12] = 61;
                        }
                    }
                    this.k += this.i - i3;
                    if (this.g > 0 && this.k > 0) {
                        System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
                        this.i += this.v.length;
                        return;
                    }
                    return;
                }
                return;
            }
            int i13 = i;
            int i14 = 0;
            while (i14 < i2) {
                a(this.x);
                this.l = (this.l + 1) % 3;
                int i15 = i13 + 1;
                byte b = bArr[i13];
                int i16 = b;
                if (b < 0) {
                    i16 = b + 256;
                }
                this.y = (this.y << 8) + (i16 == 1 ? 1 : 0);
                if (this.l == 0) {
                    byte[] bArr12 = this.h;
                    int i17 = this.i;
                    this.i = i17 + 1;
                    bArr12[i17] = this.t[(this.y >> 18) & 63];
                    byte[] bArr13 = this.h;
                    int i18 = this.i;
                    this.i = i18 + 1;
                    bArr13[i18] = this.t[(this.y >> 12) & 63];
                    byte[] bArr14 = this.h;
                    int i19 = this.i;
                    this.i = i19 + 1;
                    bArr14[i19] = this.t[(this.y >> 6) & 63];
                    byte[] bArr15 = this.h;
                    int i20 = this.i;
                    this.i = i20 + 1;
                    bArr15[i20] = this.t[this.y & 63];
                    this.k += 4;
                    if (this.g > 0 && this.g <= this.k) {
                        System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
                        this.i += this.v.length;
                        this.k = 0;
                    }
                }
                i14++;
                i13 = i15;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.umeng.message.proguard.e
    public void b(byte[] bArr, int i, int i2) {
        byte b;
        if (!this.j) {
            if (i2 < 0) {
                this.j = true;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                a(this.w);
                int i4 = i + 1;
                byte b2 = bArr[i];
                if (b2 == 61) {
                    this.j = true;
                    break;
                }
                if (b2 >= 0) {
                    byte[] bArr2 = r;
                    if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                        this.l = (this.l + 1) % 4;
                        this.y = (this.y << 6) + b;
                        if (this.l == 0) {
                            byte[] bArr3 = this.h;
                            int i5 = this.i;
                            this.i = i5 + 1;
                            bArr3[i5] = (byte) ((this.y >> 16) & 255);
                            byte[] bArr4 = this.h;
                            int i6 = this.i;
                            this.i = i6 + 1;
                            bArr4[i6] = (byte) ((this.y >> 8) & 255);
                            byte[] bArr5 = this.h;
                            int i7 = this.i;
                            this.i = i7 + 1;
                            bArr5[i7] = (byte) (this.y & 255);
                        }
                    }
                }
                i3++;
                i = i4;
            }
            if (this.j && this.l != 0) {
                a(this.w);
                int i8 = this.l;
                if (i8 == 2) {
                    this.y >>= 4;
                    byte[] bArr6 = this.h;
                    int i9 = this.i;
                    this.i = i9 + 1;
                    bArr6[i9] = (byte) (this.y & 255);
                } else if (i8 == 3) {
                    this.y >>= 2;
                    byte[] bArr7 = this.h;
                    int i10 = this.i;
                    this.i = i10 + 1;
                    bArr7[i10] = (byte) ((this.y >> 8) & 255);
                    byte[] bArr8 = this.h;
                    int i11 = this.i;
                    this.i = i11 + 1;
                    bArr8[i11] = (byte) (this.y & 255);
                }
            }
        }
    }

    public static boolean a(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = r;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean a(String str) {
        return b(b.f(str));
    }

    public static boolean a(byte[] bArr) {
        return b(bArr);
    }

    public static boolean b(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!(a(bArr[i]) || c(bArr[i]))) {
                return false;
            }
        }
        return true;
    }

    public static byte[] c(byte[] bArr) {
        return a(bArr, false);
    }

    public static String d(byte[] bArr) {
        return b.f(a(bArr, false));
    }

    public static byte[] e(byte[] bArr) {
        return a(bArr, false, true);
    }

    public static String f(byte[] bArr) {
        return b.f(a(bArr, false, true));
    }

    public static byte[] g(byte[] bArr) {
        return a(bArr, true);
    }

    public static byte[] a(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        d dVar = z ? new d(z2) : new d(0, a, z2);
        long o2 = dVar.o(bArr);
        if (o2 <= ((long) i)) {
            return dVar.l(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + o2 + ") than the specified maximum size of " + i);
    }

    public static byte[] b(String str) {
        return new d().c(str);
    }

    public static byte[] h(byte[] bArr) {
        return new d().k(bArr);
    }

    public static BigInteger i(byte[] bArr) {
        return new BigInteger(1, h(bArr));
    }

    public static byte[] a(BigInteger bigInteger) {
        if (bigInteger != null) {
            return a(b(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    static byte[] b(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == bitLength / 8) {
            return byteArray;
        }
        int i = 0;
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
            i = 1;
        }
        int i2 = bitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.proguard.e
    public boolean b(byte b) {
        if (b >= 0) {
            byte[] bArr = this.u;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
