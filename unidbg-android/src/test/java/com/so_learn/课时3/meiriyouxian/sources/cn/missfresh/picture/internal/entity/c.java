package cn.missfresh.picture.internal.entity;

import cn.missfresh.picture.MimeType;
import cn.missfresh.picture.R;
import cn.missfresh.picture.filter.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;
import java.util.Set;

/* compiled from: SelectionSpec */
public final class c {
    public Set<MimeType> a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public boolean f;
    public int g;
    public int h;
    public int i;
    public List<b> j;
    public boolean k;
    public a l;
    public int m;
    public int n;
    public float o;
    public cn.missfresh.picture.a.a p;
    public boolean q;
    public cn.missfresh.picture.b.c r;
    public boolean s;
    public boolean t;
    public int u;
    public cn.missfresh.picture.b.a v;
    public boolean w;

    private c() {
    }

    public static c a() {
        AppMethodBeat.i(18535, false);
        c cVar = a.a;
        AppMethodBeat.o(18535);
        return cVar;
    }

    public static c b() {
        AppMethodBeat.i(18537, false);
        c a2 = a();
        a2.h();
        AppMethodBeat.o(18537);
        return a2;
    }

    private void h() {
        AppMethodBeat.i(18538, false);
        this.a = null;
        this.b = true;
        this.c = false;
        this.d = R.style.Picture_MF;
        this.e = 0;
        this.f = false;
        this.g = 1;
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = 4;
        this.n = 0;
        this.o = 0.5f;
        this.p = new cn.missfresh.picture.a.a.a();
        this.q = true;
        this.s = false;
        this.t = false;
        this.u = Integer.MAX_VALUE;
        this.w = true;
        AppMethodBeat.o(18538);
    }

    public boolean c() {
        if (!this.f) {
            if (this.g == 1) {
                return true;
            }
            if (this.h == 1 && this.i == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean d() {
        return this.e != -1;
    }

    public boolean e() {
        boolean z = false;
        AppMethodBeat.i(18543, false);
        if (this.c && MimeType.ofImage().containsAll(this.a)) {
            z = true;
        }
        AppMethodBeat.o(18543);
        return z;
    }

    public boolean f() {
        boolean z = false;
        AppMethodBeat.i(18544, false);
        if (this.c && MimeType.ofVideo().containsAll(this.a)) {
            z = true;
        }
        AppMethodBeat.o(18544);
        return z;
    }

    public boolean g() {
        boolean z = false;
        AppMethodBeat.i(18545, false);
        if (this.c && MimeType.ofGif().equals(this.a)) {
            z = true;
        }
        AppMethodBeat.o(18545);
        return z;
    }

    /* compiled from: SelectionSpec */
    /* access modifiers changed from: private */
    public static final class a {
        private static final c a = new c();

        static {
            AppMethodBeat.i(18104, false);
            AppMethodBeat.o(18104);
        }
    }
}
