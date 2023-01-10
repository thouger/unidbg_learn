package com.google.common.math;

import com.umeng.message.proguard.l;
import java.math.RoundingMode;

/* compiled from: MathPreconditions */
/* access modifiers changed from: package-private */
public final class e {
    static int a(String str, int i) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " (" + i + ") must be > 0");
    }

    static void a(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static void a(boolean z, double d, RoundingMode roundingMode) {
        if (!z) {
            throw new ArithmeticException("rounded value is out of range for input " + d + " and rounding mode " + roundingMode);
        }
    }

    static void a(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new ArithmeticException("overflow: " + str + l.s + i + ", " + i2 + l.t);
        }
    }
}
