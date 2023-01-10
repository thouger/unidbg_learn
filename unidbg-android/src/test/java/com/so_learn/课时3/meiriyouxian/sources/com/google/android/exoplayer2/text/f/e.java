package com.google.android.exoplayer2.text.f;

import android.text.Layout;
import com.google.android.exoplayer2.util.a;

/* compiled from: TtmlStyle */
/* access modifiers changed from: package-private */
public final class e {
    private String a;
    private int b;
    private boolean c;
    private int d;
    private boolean e;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private float k;
    private String l;
    private e m;
    private Layout.Alignment n;

    public int a() {
        if (this.h == -1 && this.i == -1) {
            return -1;
        }
        int i = 0;
        int i2 = this.h == 1 ? 1 : 0;
        if (this.i == 1) {
            i = 2;
        }
        return i2 | i;
    }

    public boolean b() {
        return this.f == 1;
    }

    public e a(boolean z) {
        a.b(this.m == null);
        this.f = z ? 1 : 0;
        return this;
    }

    public boolean c() {
        return this.g == 1;
    }

    public e b(boolean z) {
        a.b(this.m == null);
        this.g = z ? 1 : 0;
        return this;
    }

    public e c(boolean z) {
        a.b(this.m == null);
        this.h = z ? 1 : 0;
        return this;
    }

    public e d(boolean z) {
        a.b(this.m == null);
        this.i = z ? 1 : 0;
        return this;
    }

    public String d() {
        return this.a;
    }

    public e a(String str) {
        a.b(this.m == null);
        this.a = str;
        return this;
    }

    public int e() {
        if (this.c) {
            return this.b;
        }
        throw new IllegalStateException("Font color has not been defined.");
    }

    public e a(int i) {
        a.b(this.m == null);
        this.b = i;
        this.c = true;
        return this;
    }

    public boolean f() {
        return this.c;
    }

    public int g() {
        if (this.e) {
            return this.d;
        }
        throw new IllegalStateException("Background color has not been defined.");
    }

    public e b(int i) {
        this.d = i;
        this.e = true;
        return this;
    }

    public boolean h() {
        return this.e;
    }

    public e a(e eVar) {
        return a(eVar, true);
    }

    private e a(e eVar, boolean z) {
        if (eVar != null) {
            if (!this.c && eVar.c) {
                a(eVar.b);
            }
            if (this.h == -1) {
                this.h = eVar.h;
            }
            if (this.i == -1) {
                this.i = eVar.i;
            }
            if (this.a == null) {
                this.a = eVar.a;
            }
            if (this.f == -1) {
                this.f = eVar.f;
            }
            if (this.g == -1) {
                this.g = eVar.g;
            }
            if (this.n == null) {
                this.n = eVar.n;
            }
            if (this.j == -1) {
                this.j = eVar.j;
                this.k = eVar.k;
            }
            if (z && !this.e && eVar.e) {
                b(eVar.d);
            }
        }
        return this;
    }

    public e b(String str) {
        this.l = str;
        return this;
    }

    public String i() {
        return this.l;
    }

    public Layout.Alignment j() {
        return this.n;
    }

    public e a(Layout.Alignment alignment) {
        this.n = alignment;
        return this;
    }

    public e a(float f) {
        this.k = f;
        return this;
    }

    public e c(int i) {
        this.j = i;
        return this;
    }

    public int k() {
        return this.j;
    }

    public float l() {
        return this.k;
    }
}
