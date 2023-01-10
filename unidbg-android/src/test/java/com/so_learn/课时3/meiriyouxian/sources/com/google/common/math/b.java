package com.google.common.math;

import com.google.common.base.m;

/* compiled from: DoubleUtils */
/* access modifiers changed from: package-private */
public final class b {
    static long a(double d) {
        m.a(b(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & 4503599627370495L;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | 4503599627370496L;
    }

    static boolean b(double d) {
        return Math.getExponent(d) <= 1023;
    }

    static double c(double d) {
        m.a(!Double.isNaN(d));
        if (d > 0.0d) {
            return d;
        }
        return 0.0d;
    }
}
