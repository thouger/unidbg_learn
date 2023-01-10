package com.google.common.primitives;

import com.google.common.base.m;
import java.util.Comparator;

public final class UnsignedInts {
    static int a(int i) {
        return i ^ Integer.MIN_VALUE;
    }

    public static long b(int i) {
        return ((long) i) & 4294967295L;
    }

    public static int a(int i, int i2) {
        return Ints.a(a(i), a(i2));
    }

    enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }

        public int compare(int[] iArr, int[] iArr2) {
            int min = Math.min(iArr.length, iArr2.length);
            for (int i = 0; i < min; i++) {
                if (iArr[i] != iArr2[i]) {
                    return UnsignedInts.a(iArr[i], iArr2[i]);
                }
            }
            return iArr.length - iArr2.length;
        }
    }

    public static int b(int i, int i2) {
        return (int) (b(i) / b(i2));
    }

    public static int c(int i, int i2) {
        return (int) (b(i) % b(i2));
    }

    public static int a(String str, int i) {
        m.a(str);
        long parseLong = Long.parseLong(str, i);
        if ((4294967295L & parseLong) == parseLong) {
            return (int) parseLong;
        }
        throw new NumberFormatException("Input " + str + " in base " + i + " is not in the range of an unsigned integer");
    }

    public static String d(int i, int i2) {
        return Long.toString(((long) i) & 4294967295L, i2);
    }
}
