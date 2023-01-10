package com.google.common.primitives;

import com.google.common.base.m;
import java.util.Comparator;

public final class SignedBytes {
    public static int a(byte b, byte b2) {
        return b - b2;
    }

    public static byte a(long j) {
        byte b = (byte) ((int) j);
        m.a(((long) b) == j, "Out of range: %s", j);
        return b;
    }

    private enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        public int compare(byte[] bArr, byte[] bArr2) {
            int min = Math.min(bArr.length, bArr2.length);
            for (int i = 0; i < min; i++) {
                int a = SignedBytes.a(bArr[i], bArr2[i]);
                if (a != 0) {
                    return a;
                }
            }
            return bArr.length - bArr2.length;
        }
    }
}
