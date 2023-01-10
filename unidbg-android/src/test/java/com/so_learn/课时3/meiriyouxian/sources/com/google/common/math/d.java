package com.google.common.math;

import com.google.common.base.m;

/* compiled from: LinearTransformation */
public abstract class d {
    public static a a(double d, double d2) {
        m.a(b.b(d) && b.b(d2));
        return new a(d, d2);
    }

    /* compiled from: LinearTransformation */
    public static final class a {
        private final double a;
        private final double b;

        private a(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public d a(double d) {
            m.a(!Double.isNaN(d));
            if (b.b(d)) {
                return new c(d, this.b - (this.a * d));
            }
            return new C0109d(this.a);
        }
    }

    public static d a(double d) {
        m.a(b.b(d));
        return new C0109d(d);
    }

    public static d b(double d) {
        m.a(b.b(d));
        return new c(0.0d, d);
    }

    public static d a() {
        return b.a;
    }

    /* compiled from: LinearTransformation */
    private static final class c extends d {
        final double a;
        final double b;
        d c = null;

        c(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.a), Double.valueOf(this.b));
        }
    }

    /* compiled from: LinearTransformation */
    /* renamed from: com.google.common.math.d$d  reason: collision with other inner class name */
    private static final class C0109d extends d {
        final double a;
        d b = null;

        C0109d(double d) {
            this.a = d;
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.a));
        }
    }

    /* compiled from: LinearTransformation */
    private static final class b extends d {
        static final b a = new b();

        public String toString() {
            return "NaN";
        }

        private b() {
        }
    }
}
