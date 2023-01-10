package com.google.common.math;

import java.math.RoundingMode;

/* compiled from: DoubleMath */
public final class a {
    static final double[] a = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};
    private static final double b = Math.log(2.0d);

    static double a(double d, RoundingMode roundingMode) {
        if (b.b(d)) {
            switch (AnonymousClass1.a[roundingMode.ordinal()]) {
                case 1:
                    e.a(a(d));
                    return d;
                case 2:
                    return (d >= 0.0d || a(d)) ? d : (double) (((long) d) - 1);
                case 3:
                    return (d <= 0.0d || a(d)) ? d : (double) (((long) d) + 1);
                case 4:
                    return d;
                case 5:
                    if (a(d)) {
                        return d;
                    }
                    return (double) (((long) d) + ((long) (d > 0.0d ? 1 : -1)));
                case 6:
                    return Math.rint(d);
                case 7:
                    double rint = Math.rint(d);
                    return Math.abs(d - rint) == 0.5d ? d + Math.copySign(0.5d, d) : rint;
                case 8:
                    double rint2 = Math.rint(d);
                    return Math.abs(d - rint2) == 0.5d ? d : rint2;
                default:
                    throw new AssertionError();
            }
        } else {
            throw new ArithmeticException("input is infinite or NaN");
        }
    }

    /* compiled from: DoubleMath */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.a$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[RoundingMode.values().length];

        static {
            try {
                a[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static long b(double d, RoundingMode roundingMode) {
        double a2 = a(d, roundingMode);
        boolean z = true;
        boolean z2 = -9.223372036854776E18d - a2 < 1.0d;
        if (a2 >= 9.223372036854776E18d) {
            z = false;
        }
        e.a(z2 & z, d, roundingMode);
        return (long) a2;
    }

    public static boolean a(double d) {
        return b.b(d) && (d == 0.0d || 52 - Long.numberOfTrailingZeros(b.a(d)) <= Math.getExponent(d));
    }
}
