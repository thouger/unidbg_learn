package com.sina.weibo.sdk.a;

/* compiled from: Base64 */
public final class c {
    private static char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] b = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) ((i3 + 26) - 97);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) ((i4 + 52) - 48);
        }
        byte[] bArr = b;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    public static byte[] a(byte[] bArr) {
        int length = ((bArr.length + 3) / 4) * 3;
        if (bArr.length > 0 && bArr[bArr.length - 1] == 61) {
            length--;
        }
        if (bArr.length > 1 && bArr[bArr.length - 2] == 61) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (byte b2 : bArr) {
            byte b3 = b[b2 & 255];
            if (b3 >= 0) {
                i3 += 6;
                i2 = (i2 << 6) | b3;
                if (i3 >= 8) {
                    i3 -= 8;
                    bArr2[i] = (byte) ((i2 >> i3) & 255);
                    i++;
                }
            }
        }
        if (i == bArr2.length) {
            return bArr2;
        }
        throw new RuntimeException("miscalculated data length!");
    }

    public static byte[] b(byte[] bArr) {
        boolean z;
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
            } else {
                z2 = false;
            }
            int i7 = 64;
            bArr2[i2 + 3] = (byte) a[z2 ? i5 & 63 : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            char[] cArr = a;
            if (z) {
                i7 = i8 & 63;
            }
            bArr2[i9] = (byte) cArr[i7];
            int i10 = i8 >> 6;
            char[] cArr2 = a;
            bArr2[i2 + 1] = (byte) cArr2[i10 & 63];
            bArr2[i2 + 0] = (byte) cArr2[(i10 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return bArr2;
    }
}
