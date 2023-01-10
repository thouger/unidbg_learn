package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.trackselection.g;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.z;

/* compiled from: MediaPeriodQueue */
/* access modifiers changed from: package-private */
public final class o {
    private final z.a a = new z.a();
    private final z.b b = new z.b();
    private long c;
    private z d = z.a;
    private int e;
    private boolean f;
    private m g;
    private m h;
    private m i;
    private int j;
    private Object k;
    private long l;

    public void a(z zVar) {
        this.d = zVar;
    }

    public boolean a(int i) {
        this.e = i;
        return i();
    }

    public boolean a(boolean z) {
        this.f = z;
        return i();
    }

    public boolean a(l lVar) {
        m mVar = this.i;
        return mVar != null && mVar.a == lVar;
    }

    public void a(long j) {
        m mVar = this.i;
        if (mVar != null) {
            mVar.c(j);
        }
    }

    public boolean a() {
        m mVar = this.i;
        return mVar == null || (!mVar.g.f && this.i.c() && this.i.g.d != -9223372036854775807L && this.j < 100);
    }

    public n a(long j, p pVar) {
        m mVar = this.i;
        if (mVar == null) {
            return a(pVar);
        }
        return a(mVar, j);
    }

    public l a(u[] uVarArr, g gVar, b bVar, m mVar, n nVar) {
        long j;
        m mVar2 = this.i;
        if (mVar2 == null) {
            j = nVar.b;
        } else {
            j = mVar2.a() + this.i.g.d;
        }
        m mVar3 = new m(uVarArr, j, gVar, bVar, mVar, nVar);
        if (this.i != null) {
            a.b(f());
            this.i.h = mVar3;
        }
        this.k = null;
        this.i = mVar3;
        this.j++;
        return mVar3.a;
    }

    public m b() {
        return this.i;
    }

    public m c() {
        return this.g;
    }

    public m d() {
        return this.h;
    }

    public m e() {
        return f() ? this.g : this.i;
    }

    public boolean f() {
        return this.g != null;
    }

    public m g() {
        m mVar = this.h;
        a.b((mVar == null || mVar.h == null) ? false : true);
        this.h = this.h.h;
        return this.h;
    }

    public m h() {
        m mVar = this.g;
        if (mVar != null) {
            if (mVar == this.h) {
                this.h = mVar.h;
            }
            this.g.f();
            this.j--;
            if (this.j == 0) {
                this.i = null;
                this.k = this.g.b;
                this.l = this.g.g.a.d;
            }
            this.g = this.g.h;
        } else {
            m mVar2 = this.i;
            this.g = mVar2;
            this.h = mVar2;
        }
        return this.g;
    }

    public boolean a(m mVar) {
        boolean z = false;
        a.b(mVar != null);
        this.i = mVar;
        while (mVar.h != null) {
            mVar = mVar.h;
            if (mVar == this.h) {
                this.h = this.g;
                z = true;
            }
            mVar.f();
            this.j--;
        }
        this.i.h = null;
        return z;
    }

    public void b(boolean z) {
        m e = e();
        if (e != null) {
            this.k = z ? e.b : null;
            this.l = e.g.a.d;
            e.f();
            a(e);
        } else if (!z) {
            this.k = null;
        }
        this.g = null;
        this.i = null;
        this.h = null;
        this.j = 0;
    }

    public boolean a(m.a aVar, long j) {
        int a = this.d.a(aVar.a);
        m mVar = null;
        m e = e();
        while (e != null) {
            if (mVar == null) {
                e.g = a(e.g);
            } else if (a == -1 || !e.b.equals(this.d.a(a))) {
                return !a(mVar);
            } else {
                n a2 = a(mVar, j);
                if (a2 == null) {
                    return !a(mVar);
                }
                e.g = a(e.g);
                if (!a(e, a2)) {
                    return !a(mVar);
                }
            }
            if (e.g.e) {
                a = this.d.a(a, this.a, this.b, this.e, this.f);
            }
            e = e.h;
            mVar = e;
        }
        return true;
    }

    public n a(n nVar) {
        long a;
        boolean a2 = a(nVar.a);
        boolean a3 = a(nVar.a, a2);
        this.d.a(nVar.a.a, this.a);
        if (nVar.a.a()) {
            a = this.a.c(nVar.a.b, nVar.a.c);
        } else {
            a = nVar.a.e == Long.MIN_VALUE ? this.a.a() : nVar.a.e;
        }
        return new n(nVar.a, nVar.b, nVar.c, a, a2, a3);
    }

    public m.a a(Object obj, long j) {
        return a(obj, j, a(obj));
    }

    private m.a a(Object obj, long j, long j2) {
        long j3;
        this.d.a(obj, this.a);
        int a = this.a.a(j);
        if (a != -1) {
            return new m.a(obj, a, this.a.b(a), j2);
        }
        int b = this.a.b(j);
        if (b == -1) {
            j3 = Long.MIN_VALUE;
        } else {
            j3 = this.a.a(b);
        }
        return new m.a(obj, j2, j3);
    }

    private long a(Object obj) {
        int a;
        int i = this.d.a(obj, this.a).c;
        Object obj2 = this.k;
        if (!(obj2 == null || (a = this.d.a(obj2)) == -1 || this.d.a(a, this.a).c != i)) {
            return this.l;
        }
        for (m e = e(); e != null; e = e.h) {
            if (e.b.equals(obj)) {
                return e.g.a.d;
            }
        }
        for (m e2 = e(); e2 != null; e2 = e2.h) {
            int a2 = this.d.a(e2.b);
            if (a2 != -1 && this.d.a(a2, this.a).c == i) {
                return e2.g.a.d;
            }
        }
        long j = this.c;
        this.c = 1 + j;
        return j;
    }

    private boolean a(m mVar, n nVar) {
        n nVar2 = mVar.g;
        return nVar2.b == nVar.b && nVar2.a.equals(nVar.a);
    }

    private boolean i() {
        m e = e();
        if (e == null) {
            return true;
        }
        int a = this.d.a(e.b);
        while (true) {
            a = this.d.a(a, this.a, this.b, this.e, this.f);
            while (e.h != null && !e.g.e) {
                e = e.h;
            }
            if (a == -1 || e.h == null || this.d.a(e.h.b) != a) {
                break;
            }
            e = e.h;
        }
        boolean a2 = a(e);
        e.g = a(e.g);
        if (!a2 || !f()) {
            return true;
        }
        return false;
    }

    private n a(p pVar) {
        return a(pVar.c, pVar.e, pVar.d);
    }

    private n a(m mVar, long j) {
        long j2;
        long j3;
        Object obj;
        long j4;
        n nVar = mVar.g;
        long a = (mVar.a() + nVar.d) - j;
        long j5 = 0;
        if (nVar.e) {
            int a2 = this.d.a(this.d.a(nVar.a.a), this.a, this.b, this.e, this.f);
            if (a2 == -1) {
                return null;
            }
            int i = this.d.a(a2, this.a, true).c;
            Object obj2 = this.a.b;
            long j6 = nVar.a.d;
            if (this.d.a(i, this.b).f == a2) {
                Pair<Object, Long> a3 = this.d.a(this.b, this.a, i, -9223372036854775807L, Math.max(0L, a));
                if (a3 == null) {
                    return null;
                }
                Object obj3 = a3.first;
                long longValue = a3.second.longValue();
                if (mVar.h == null || !mVar.h.b.equals(obj3)) {
                    j4 = this.c;
                    this.c = 1 + j4;
                } else {
                    j4 = mVar.h.g.a.d;
                }
                j5 = longValue;
                j3 = j4;
                obj = obj3;
            } else {
                obj = obj2;
                j3 = j6;
            }
            return a(a(obj, j5, j3), j5, j5);
        }
        m.a aVar = nVar.a;
        this.d.a(aVar.a, this.a);
        if (aVar.a()) {
            int i2 = aVar.b;
            int d = this.a.d(i2);
            if (d == -1) {
                return null;
            }
            int a4 = this.a.a(i2, aVar.c);
            if (a4 >= d) {
                long j7 = nVar.c;
                if (this.a.d() == 1 && this.a.a(0) == 0) {
                    z zVar = this.d;
                    z.b bVar = this.b;
                    z.a aVar2 = this.a;
                    Pair<Object, Long> a5 = zVar.a(bVar, aVar2, aVar2.c, -9223372036854775807L, Math.max(0L, a));
                    if (a5 == null) {
                        return null;
                    }
                    j2 = a5.second.longValue();
                } else {
                    j2 = j7;
                }
                return b(aVar.a, j2, aVar.d);
            } else if (!this.a.b(i2, a4)) {
                return null;
            } else {
                return a(aVar.a, i2, a4, nVar.c, aVar.d);
            }
        } else if (nVar.a.e != Long.MIN_VALUE) {
            int a6 = this.a.a(nVar.a.e);
            if (a6 == -1) {
                return b(aVar.a, nVar.a.e, aVar.d);
            }
            int b = this.a.b(a6);
            if (!this.a.b(a6, b)) {
                return null;
            }
            return a(aVar.a, a6, b, nVar.a.e, aVar.d);
        } else {
            int d2 = this.a.d();
            if (d2 == 0) {
                return null;
            }
            int i3 = d2 - 1;
            if (this.a.a(i3) != Long.MIN_VALUE || this.a.c(i3)) {
                return null;
            }
            int b2 = this.a.b(i3);
            if (!this.a.b(i3, b2)) {
                return null;
            }
            return a(aVar.a, i3, b2, this.a.a(), aVar.d);
        }
    }

    private n a(m.a aVar, long j, long j2) {
        this.d.a(aVar.a, this.a);
        if (!aVar.a()) {
            return b(aVar.a, j2, aVar.d);
        }
        if (!this.a.b(aVar.b, aVar.c)) {
            return null;
        }
        return a(aVar.a, aVar.b, aVar.c, j, aVar.d);
    }

    private n a(Object obj, int i, int i2, long j, long j2) {
        m.a aVar = new m.a(obj, i, i2, j2);
        boolean a = a(aVar);
        boolean a2 = a(aVar, a);
        return new n(aVar, i2 == this.a.b(i) ? this.a.e() : 0, j, this.d.a(aVar.a, this.a).c(aVar.b, aVar.c), a, a2);
    }

    private n b(Object obj, long j, long j2) {
        long j3;
        int b = this.a.b(j);
        if (b == -1) {
            j3 = Long.MIN_VALUE;
        } else {
            j3 = this.a.a(b);
        }
        m.a aVar = new m.a(obj, j2, j3);
        this.d.a(aVar.a, this.a);
        boolean a = a(aVar);
        return new n(aVar, j, -9223372036854775807L, j3 == Long.MIN_VALUE ? this.a.a() : j3, a, a(aVar, a));
    }

    private boolean a(m.a aVar) {
        int d = this.d.a(aVar.a, this.a).d();
        if (d == 0) {
            return true;
        }
        int i = d - 1;
        boolean a = aVar.a();
        if (this.a.a(i) == Long.MIN_VALUE) {
            int d2 = this.a.d(i);
            if (d2 == -1) {
                return false;
            }
            if (a && aVar.b == i && aVar.c == d2 + -1) {
                return true;
            }
            if (a || this.a.b(i) != d2) {
                return false;
            }
            return true;
        } else if (a || aVar.e != Long.MIN_VALUE) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(m.a aVar, boolean z) {
        int a = this.d.a(aVar.a);
        return !this.d.a(this.d.a(a, this.a).c, this.b).e && this.d.b(a, this.a, this.b, this.e, this.f) && z;
    }
}
