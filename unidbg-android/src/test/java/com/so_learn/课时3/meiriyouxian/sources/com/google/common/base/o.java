package com.google.common.base;

import android.net.wifi.WifiEnterpriseConfig;
import com.umeng.analytics.pro.ai;
import java.util.concurrent.TimeUnit;

/* compiled from: Stopwatch */
public final class o {
    private final s a = s.b();
    private boolean b;
    private long c;
    private long d;

    public static o a() {
        return new o();
    }

    public static o b() {
        return new o().c();
    }

    o() {
    }

    public o c() {
        m.b(!this.b, "This stopwatch is already running.");
        this.b = true;
        this.d = this.a.a();
        return this;
    }

    public o d() {
        long a = this.a.a();
        m.b(this.b, "This stopwatch is already stopped.");
        this.b = false;
        this.c += a - this.d;
        return this;
    }

    private long e() {
        return this.b ? (this.a.a() - this.d) + this.c : this.c;
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(e(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long e = e();
        TimeUnit a = a(e);
        double convert = ((double) e) / ((double) TimeUnit.NANOSECONDS.convert(1, a));
        return l.a(convert) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + b(a);
    }

    private static TimeUnit a(long j) {
        if (TimeUnit.DAYS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.DAYS;
        }
        if (TimeUnit.HOURS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.HOURS;
        }
        if (TimeUnit.MINUTES.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MINUTES;
        }
        if (TimeUnit.SECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.SECONDS;
        }
        if (TimeUnit.MILLISECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MILLISECONDS;
        }
        if (TimeUnit.MICROSECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MICROSECONDS;
        }
        return TimeUnit.NANOSECONDS;
    }

    /* compiled from: Stopwatch */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.base.o$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[TimeUnit.values().length];

        static {
            try {
                a[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static String b(TimeUnit timeUnit) {
        switch (AnonymousClass1.a[timeUnit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "\u03bcs";
            case 3:
                return "ms";
            case 4:
                return ai.az;
            case 5:
                return "min";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new AssertionError();
        }
    }
}
