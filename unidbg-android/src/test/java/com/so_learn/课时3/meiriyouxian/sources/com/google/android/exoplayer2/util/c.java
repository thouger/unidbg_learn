package com.google.android.exoplayer2.util;

import android.util.Pair;
import com.google.android.exoplayer2.ParserException;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import java.util.ArrayList;

/* compiled from: CodecSpecificDataUtil */
public final class c {
    private static final byte[] a = {0, 0, 0, 1};
    private static final int[] b = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, JosStatusCodes.RTN_CODE_COMMON_ERROR, 7350};
    private static final int[] c = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static byte[] a(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    public static Pair<Integer, Integer> a(byte[] bArr) throws ParserException {
        return a(new n(bArr), false);
    }

    public static Pair<Integer, Integer> a(n nVar, boolean z) throws ParserException {
        int a2 = a(nVar);
        int b2 = b(nVar);
        int c2 = nVar.c(4);
        if (a2 == 5 || a2 == 29) {
            b2 = b(nVar);
            a2 = a(nVar);
            if (a2 == 22) {
                c2 = nVar.c(4);
            }
        }
        boolean z2 = true;
        if (z) {
            if (!(a2 == 1 || a2 == 2 || a2 == 3 || a2 == 4 || a2 == 6 || a2 == 7 || a2 == 17)) {
                switch (a2) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        throw new ParserException("Unsupported audio object type: " + a2);
                }
            }
            a(nVar, a2, c2);
            switch (a2) {
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int c3 = nVar.c(2);
                    if (c3 == 2 || c3 == 3) {
                        throw new ParserException("Unsupported epConfig: " + c3);
                    }
            }
        }
        int i = c[c2];
        if (i == -1) {
            z2 = false;
        }
        a.a(z2);
        return Pair.create(Integer.valueOf(b2), Integer.valueOf(i));
    }

    public static byte[] a(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (true) {
            int[] iArr = b;
            if (i4 >= iArr.length) {
                break;
            }
            if (i == iArr[i4]) {
                i5 = i4;
            }
            i4++;
        }
        int i6 = -1;
        while (true) {
            int[] iArr2 = c;
            if (i3 >= iArr2.length) {
                break;
            }
            if (i2 == iArr2[i3]) {
                i6 = i3;
            }
            i3++;
        }
        if (i != -1 && i6 != -1) {
            return a(2, i5, i6);
        }
        throw new IllegalArgumentException("Invalid sample rate or number of channels: " + i + ", " + i2);
    }

    public static String b(int i, int i2, int i3) {
        return String.format("avc1.%02X%02X%02X", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = a;
        byte[] bArr3 = new byte[(bArr2.length + i2)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, a.length, i2);
        return bArr3;
    }

    public static byte[][] b(byte[] bArr) {
        if (!b(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        do {
            arrayList.add(Integer.valueOf(i));
            i = a(bArr, i + a.length);
        } while (i != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int intValue = ((Integer) arrayList.get(i2)).intValue();
            byte[] bArr3 = new byte[((i2 < arrayList.size() + -1 ? ((Integer) arrayList.get(i2 + 1)).intValue() : bArr.length) - intValue)];
            System.arraycopy(bArr, intValue, bArr3, 0, bArr3.length);
            bArr2[i2] = bArr3;
            i2++;
        }
        return bArr2;
    }

    private static int a(byte[] bArr, int i) {
        int length = bArr.length - a.length;
        while (i <= length) {
            if (b(bArr, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean b(byte[] bArr, int i) {
        if (bArr.length - i <= a.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = a;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private static int a(n nVar) {
        int c2 = nVar.c(5);
        return c2 == 31 ? nVar.c(6) + 32 : c2;
    }

    private static int b(n nVar) {
        int c2 = nVar.c(4);
        if (c2 == 15) {
            return nVar.c(24);
        }
        a.a(c2 < 13);
        return b[c2];
    }

    private static void a(n nVar, int i, int i2) {
        nVar.b(1);
        if (nVar.e()) {
            nVar.b(14);
        }
        boolean e = nVar.e();
        if (i2 != 0) {
            if (i == 6 || i == 20) {
                nVar.b(3);
            }
            if (e) {
                if (i == 22) {
                    nVar.b(16);
                }
                if (i == 17 || i == 19 || i == 20 || i == 23) {
                    nVar.b(3);
                }
                nVar.b(1);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException();
    }
}
