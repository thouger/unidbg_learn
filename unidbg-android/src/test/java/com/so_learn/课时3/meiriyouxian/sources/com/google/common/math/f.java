package com.google.common.math;

import com.google.common.primitives.Doubles;
import java.util.Iterator;

/* compiled from: StatsAccumulator */
public final class f {
    private long a = 0;
    private double b = 0.0d;
    private double c = 0.0d;
    private double d = Double.NaN;
    private double e = Double.NaN;

    public void a(double d) {
        long j = this.a;
        if (j == 0) {
            this.a = 1;
            this.b = d;
            this.d = d;
            this.e = d;
            if (!Doubles.b(d)) {
                this.c = Double.NaN;
                return;
            }
            return;
        }
        this.a = j + 1;
        if (!Doubles.b(d) || !Doubles.b(this.b)) {
            this.b = a(this.b, d);
            this.c = Double.NaN;
        } else {
            double d2 = this.b;
            double d3 = d - d2;
            this.b = d2 + (d3 / ((double) this.a));
            this.c += d3 * (d - this.b);
        }
        this.d = Math.min(this.d, d);
        this.e = Math.max(this.e, d);
    }

    public void a(Iterable<? extends Number> iterable) {
        for (Number number : iterable) {
            a(number.doubleValue());
        }
    }

    public void a(Iterator<? extends Number> it2) {
        while (it2.hasNext()) {
            a(((Number) it2.next()).doubleValue());
        }
    }

    public void a(double... dArr) {
        for (double d : dArr) {
            a(d);
        }
    }

    public void a(int... iArr) {
        for (int i : iArr) {
            a((double) i);
        }
    }

    public void a(long... jArr) {
        for (long j : jArr) {
            a((double) j);
        }
    }

    public Stats a() {
        return new Stats(this.a, this.b, this.c, this.d, this.e);
    }

    static double a(double d, double d2) {
        if (Doubles.b(d)) {
            return d2;
        }
        if (Doubles.b(d2) || d == d2) {
            return d;
        }
        return Double.NaN;
    }
}
