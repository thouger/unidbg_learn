package com.a.a;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Spring */
public class d {
    private static int a;
    private e b;
    private boolean c;
    private final String d;
    private final a e = new a();
    private final a f = new a();
    private final a g = new a();
    private double h;
    private double i;
    private boolean j = true;
    private double k = 0.005d;
    private double l = 0.005d;
    private CopyOnWriteArraySet<f> m = new CopyOnWriteArraySet<>();
    private double n = 0.0d;
    private final b o;

    /* compiled from: Spring */
    /* access modifiers changed from: private */
    public static class a {
        double a;
        double b;

        private a() {
        }
    }

    d(b bVar) {
        if (bVar != null) {
            this.o = bVar;
            StringBuilder sb = new StringBuilder();
            sb.append("spring:");
            int i = a;
            a = i + 1;
            sb.append(i);
            this.d = sb.toString();
            a(e.c);
            return;
        }
        throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
    }

    public String a() {
        return this.d;
    }

    public d a(e eVar) {
        if (eVar != null) {
            this.b = eVar;
            return this;
        }
        throw new IllegalArgumentException("springConfig is required");
    }

    public double b() {
        return this.e.a;
    }

    private double a(a aVar) {
        return Math.abs(this.i - aVar.a);
    }

    public d a(double d) {
        if (this.i == d && g()) {
            return this;
        }
        this.h = b();
        this.i = d;
        this.o.a(a());
        Iterator<f> it2 = this.m.iterator();
        while (it2.hasNext()) {
            it2.next().d(this);
        }
        return this;
    }

    public double c() {
        return this.i;
    }

    public d b(double d) {
        if (d == this.e.b) {
            return this;
        }
        this.e.b = d;
        this.o.a(a());
        return this;
    }

    public boolean d() {
        return this.b.b > 0.0d && ((this.h < this.i && b() > this.i) || (this.h > this.i && b() < this.i));
    }

    /* access modifiers changed from: package-private */
    public void c(double d) {
        double d2;
        boolean z;
        boolean g = g();
        if (!g || !this.j) {
            double d3 = 0.064d;
            if (d <= 0.064d) {
                d3 = d;
            }
            this.n += d3;
            double d4 = this.b.b;
            double d5 = this.b.a;
            double d6 = this.e.a;
            double d7 = this.e.b;
            double d8 = this.g.a;
            double d9 = this.g.b;
            while (true) {
                d2 = this.n;
                if (d2 < 0.001d) {
                    break;
                }
                this.n = d2 - 0.001d;
                if (this.n < 0.001d) {
                    a aVar = this.f;
                    aVar.a = d6;
                    aVar.b = d7;
                }
                double d10 = this.i;
                double d11 = ((d10 - d8) * d4) - (d5 * d7);
                double d12 = d7 + (d11 * 0.001d * 0.5d);
                double d13 = ((d10 - (((d7 * 0.001d) * 0.5d) + d6)) * d4) - (d5 * d12);
                double d14 = d7 + (d13 * 0.001d * 0.5d);
                double d15 = ((d10 - (d6 + ((d12 * 0.001d) * 0.5d))) * d4) - (d5 * d14);
                double d16 = d6 + (d14 * 0.001d);
                double d17 = d7 + (d15 * 0.001d);
                d6 += (d7 + ((d12 + d14) * 2.0d) + d17) * 0.16666666666666666d * 0.001d;
                d7 += (d11 + ((d13 + d15) * 2.0d) + (((d10 - d16) * d4) - (d5 * d17))) * 0.16666666666666666d * 0.001d;
                d8 = d16;
                d9 = d17;
            }
            a aVar2 = this.g;
            aVar2.a = d8;
            aVar2.b = d9;
            a aVar3 = this.e;
            aVar3.a = d6;
            aVar3.b = d7;
            if (d2 > 0.0d) {
                d(d2 / 0.001d);
            }
            if (g() || (this.c && d())) {
                if (d4 > 0.0d) {
                    double d18 = this.i;
                    this.h = d18;
                    this.e.a = d18;
                } else {
                    this.i = this.e.a;
                    this.h = this.i;
                }
                b(0.0d);
                g = true;
            }
            boolean z2 = false;
            if (this.j) {
                this.j = false;
                z = true;
            } else {
                z = false;
            }
            if (g) {
                this.j = true;
                z2 = true;
            }
            Iterator<f> it2 = this.m.iterator();
            while (it2.hasNext()) {
                f next = it2.next();
                if (z) {
                    next.c(this);
                }
                next.a(this);
                if (z2) {
                    next.b(this);
                }
            }
        }
    }

    public boolean e() {
        return !g() || !f();
    }

    public boolean f() {
        return this.j;
    }

    public boolean g() {
        return Math.abs(this.e.b) <= this.k && (a(this.e) <= this.l || this.b.b == 0.0d);
    }

    private void d(double d) {
        a aVar = this.e;
        double d2 = 1.0d - d;
        aVar.a = (aVar.a * d) + (this.f.a * d2);
        a aVar2 = this.e;
        aVar2.b = (aVar2.b * d) + (this.f.b * d2);
    }

    public d a(f fVar) {
        if (fVar != null) {
            this.m.add(fVar);
            return this;
        }
        throw new IllegalArgumentException("newListener is required");
    }
}
