package com.b.a.a;

/* compiled from: Pinyin */
public final class a {
    public static String a(char c) {
        if (!b(c)) {
            return String.valueOf(c);
        }
        if (c == '\u3007') {
            return "LING";
        }
        return e.b[c(c)];
    }

    public static boolean b(char c) {
        return ('\u4e00' <= c && c <= '\u9fa5' && c(c) > 0) || '\u3007' == c;
    }

    private static int c(char c) {
        int i = c - '\u4e00';
        if (i >= 0 && i < 7000) {
            return a(b.a, b.b, i);
        }
        if (7000 > i || i >= 14000) {
            return a(d.a, d.b, i - 14000);
        }
        return a(c.a, c.b, i - 7000);
    }

    private static short a(byte[] bArr, byte[] bArr2, int i) {
        int i2 = i % 8;
        short s = (short) (bArr2[i] & 255);
        return (bArr[i / 8] & e.a[i2]) != 0 ? (short) (s | 256) : s;
    }
}
