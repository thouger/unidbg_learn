package com.google.android.exoplayer2.source;

/* compiled from: CompositeSequenceableLoader */
public class d implements s {
    protected final s[] a;

    public d(s[] sVarArr) {
        this.a = sVarArr;
    }

    @Override // com.google.android.exoplayer2.source.s
    public final long d() {
        long j = Long.MAX_VALUE;
        for (s sVar : this.a) {
            long d = sVar.d();
            if (d != Long.MIN_VALUE) {
                j = Math.min(j, d);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.s
    public final long e() {
        long j = Long.MAX_VALUE;
        for (s sVar : this.a) {
            long e = sVar.e();
            if (e != Long.MIN_VALUE) {
                j = Math.min(j, e);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.s
    public final void a(long j) {
        for (s sVar : this.a) {
            sVar.a(j);
        }
    }

    @Override // com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        boolean z = false;
        while (true) {
            long e = e();
            if (e == Long.MIN_VALUE) {
                break;
            }
            s[] sVarArr = this.a;
            boolean z2 = false;
            for (s sVar : sVarArr) {
                long e2 = sVar.e();
                boolean z3 = e2 != Long.MIN_VALUE && e2 <= j;
                if (e2 == e || z3) {
                    z2 |= sVar.c(j);
                }
            }
            z |= z2;
            if (!z2) {
                break;
            }
        }
        return z;
    }
}
