package com.google.common.cache;

import com.google.common.base.i;
import com.google.common.base.j;
import com.google.common.base.m;

/* compiled from: CacheStats */
public final class c {
    private final long a;
    private final long b;
    private final long c;
    private final long d;
    private final long e;
    private final long f;

    public c(long j, long j2, long j3, long j4, long j5, long j6) {
        boolean z = true;
        m.a(j >= 0);
        m.a(j2 >= 0);
        m.a(j3 >= 0);
        m.a(j4 >= 0);
        m.a(j5 >= 0);
        m.a(j6 < 0 ? false : z);
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = j4;
        this.e = j5;
        this.f = j6;
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public int hashCode() {
        return j.a(Long.valueOf(this.a), Long.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a == cVar.a && this.b == cVar.b && this.c == cVar.c && this.d == cVar.d && this.e == cVar.e && this.f == cVar.f) {
            return true;
        }
        return false;
    }

    public String toString() {
        return i.a(this).a("hitCount", this.a).a("missCount", this.b).a("loadSuccessCount", this.c).a("loadExceptionCount", this.d).a("totalLoadTime", this.e).a("evictionCount", this.f).toString();
    }
}
