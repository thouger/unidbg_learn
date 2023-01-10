package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.a;

/* compiled from: SeekParameters */
public final class x {
    public static final x a = new x(0, 0);
    public static final x b = new x(Long.MAX_VALUE, Long.MAX_VALUE);
    public static final x c = new x(Long.MAX_VALUE, 0);
    public static final x d = new x(0, Long.MAX_VALUE);
    public static final x e = a;
    public final long f;
    public final long g;

    public x(long j, long j2) {
        boolean z = true;
        a.a(j >= 0);
        a.a(j2 < 0 ? false : z);
        this.f = j;
        this.g = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        x xVar = (x) obj;
        return this.f == xVar.f && this.g == xVar.g;
    }

    public int hashCode() {
        return (((int) this.f) * 31) + ((int) this.g);
    }
}
