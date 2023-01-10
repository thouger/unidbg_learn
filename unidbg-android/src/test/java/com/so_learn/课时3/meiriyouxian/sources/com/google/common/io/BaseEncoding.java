package com.google.common.io;

import com.google.common.base.j;
import com.google.common.base.m;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;

public abstract class BaseEncoding {
    private static final BaseEncoding a = new c("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding b = new c("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final BaseEncoding c = new d("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding d = new d("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding e = new b("base16()", "0123456789ABCDEF");

    BaseEncoding() {
    }

    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }

        DecodingException(Throwable th) {
            super(th);
        }
    }

    private static final class a {
        final int a;
        final int b;
        final int c;
        final int d;
        private final String e;
        private final char[] f;
        private final byte[] g;
        private final boolean[] h;

        a(String str, char[] cArr) {
            this.e = (String) m.a(str);
            this.f = (char[]) m.a(cArr);
            try {
                this.b = com.google.common.math.c.a(cArr.length, RoundingMode.UNNECESSARY);
                int min = Math.min(8, Integer.lowestOneBit(this.b));
                try {
                    this.c = 8 / min;
                    this.d = this.b / min;
                    this.a = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int i = 0; i < cArr.length; i++) {
                        char c = cArr[i];
                        m.a(c < bArr.length, "Non-ASCII character: %s", c);
                        m.a(bArr[c] == -1, "Duplicate character: %s", c);
                        bArr[c] = (byte) i;
                    }
                    this.g = bArr;
                    boolean[] zArr = new boolean[this.c];
                    for (int i2 = 0; i2 < this.d; i2++) {
                        zArr[com.google.common.math.c.a(i2 * 8, this.b, RoundingMode.CEILING)] = true;
                    }
                    this.h = zArr;
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("Illegal alphabet " + new String(cArr), e);
                }
            } catch (ArithmeticException e2) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e2);
            }
        }

        /* access modifiers changed from: package-private */
        public char a(int i) {
            return this.f[i];
        }

        public boolean a(char c) {
            byte[] bArr = this.g;
            return c < bArr.length && bArr[c] != -1;
        }

        public String toString() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return Arrays.equals(this.f, ((a) obj).f);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.f);
        }
    }

    static class d extends BaseEncoding {
        final a b;
        final Character c;

        d(String str, String str2, Character ch) {
            this(new a(str, str2.toCharArray()), ch);
        }

        d(a aVar, Character ch) {
            this.b = (a) m.a(aVar);
            m.a(ch == null || !aVar.a(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.c = ch;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.b.toString());
            if (8 % this.b.b != 0) {
                if (this.c == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.c);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            if (!this.b.equals(dVar.b) || !j.a(this.c, dVar.c)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.b.hashCode() ^ j.a(this.c);
        }
    }

    static final class b extends d {
        final char[] a;

        b(String str, String str2) {
            this(new a(str, str2.toCharArray()));
        }

        private b(a aVar) {
            super(aVar, null);
            this.a = new char[512];
            m.a(aVar.f.length == 16);
            for (int i = 0; i < 256; i++) {
                this.a[i] = aVar.a(i >>> 4);
                this.a[i | 256] = aVar.a(i & 15);
            }
        }
    }

    static final class c extends d {
        c(String str, String str2, Character ch) {
            this(new a(str, str2.toCharArray()), ch);
        }

        private c(a aVar, Character ch) {
            super(aVar, ch);
            m.a(aVar.f.length == 64);
        }
    }
}
