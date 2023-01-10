package com.google.common.primitives;

import com.google.common.base.m;
import java.math.BigInteger;
import java.util.Comparator;

public final class UnsignedLongs {
    private static long b(long j) {
        return j ^ Long.MIN_VALUE;
    }

    public static int a(long j, long j2) {
        return Longs.a(b(j), b(j2));
    }

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i = 0; i < min; i++) {
                if (jArr[i] != jArr2[i]) {
                    return UnsignedLongs.a(jArr[i], jArr2[i]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static long b(long j, long j2) {
        if (j2 < 0) {
            return a(j, j2) < 0 ? 0 : 1;
        }
        if (j >= 0) {
            return j / j2;
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (a(j - (j3 * j2), j2) < 0) {
            i = 0;
        }
        return j3 + ((long) i);
    }

    public static long c(long j, long j2) {
        if (j2 < 0) {
            return a(j, j2) < 0 ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (a(j3, j2) < 0) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static long a(String str, int i) {
        m.a(str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (i < 2 || i > 36) {
            throw new NumberFormatException("illegal radix: " + i);
        } else {
            int i2 = a.c[i] - 1;
            long j = 0;
            for (int i3 = 0; i3 < str.length(); i3++) {
                int digit = Character.digit(str.charAt(i3), i);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i3 <= i2 || !a.a(j, digit, i)) {
                    j = (j * ((long) i)) + ((long) digit);
                } else {
                    throw new NumberFormatException("Too large for unsigned long: " + str);
                }
            }
            return j;
        }
    }

    private static final class a {
        static final long[] a = new long[37];
        static final int[] b = new int[37];
        static final int[] c = new int[37];

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i = 2; i <= 36; i++) {
                long j = (long) i;
                a[i] = UnsignedLongs.b(-1, j);
                b[i] = (int) UnsignedLongs.c(-1, j);
                c[i] = bigInteger.toString(i).length() - 1;
            }
        }

        static boolean a(long j, int i, int i2) {
            if (j < 0) {
                return true;
            }
            long[] jArr = a;
            if (j < jArr[i2]) {
                return false;
            }
            return j > jArr[i2] || i > b[i2];
        }
    }

    public static String a(long j) {
        return a(j, 10);
    }

    public static String a(long j, int i) {
        long j2;
        m.a(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i);
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i2 == 0) {
            return "0";
        }
        if (i2 > 0) {
            return Long.toString(j, i);
        }
        char[] cArr = new char[64];
        int length = cArr.length;
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            do {
                length--;
                cArr[length] = Character.forDigit(((int) j) & i3, i);
                j >>>= numberOfTrailingZeros;
            } while (j != 0);
        } else {
            if ((i & 1) == 0) {
                j2 = (j >>> 1) / ((long) (i >>> 1));
            } else {
                j2 = b(j, (long) i);
            }
            long j3 = (long) i;
            length--;
            cArr[length] = Character.forDigit((int) (j - (j2 * j3)), i);
            while (j2 > 0) {
                length--;
                cArr[length] = Character.forDigit((int) (j2 % j3), i);
                j2 /= j3;
            }
        }
        return new String(cArr, length, cArr.length - length);
    }
}
