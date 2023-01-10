package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.z;

/* compiled from: SinglePeriodTimeline */
public final class t extends z {
    private static final Object b = new Object();
    private final long c;
    private final long d;
    private final long e;
    private final long f;
    private final long g;
    private final long h;
    private final boolean i;
    private final boolean j;
    private final Object k;

    @Override // com.google.android.exoplayer2.z
    public int b() {
        return 1;
    }

    @Override // com.google.android.exoplayer2.z
    public int c() {
        return 1;
    }

    public t(long j, boolean z, boolean z2, Object obj) {
        this(j, j, 0, 0, z, z2, obj);
    }

    public t(long j, long j2, long j3, long j4, boolean z, boolean z2, Object obj) {
        this(-9223372036854775807L, -9223372036854775807L, j, j2, j3, j4, z, z2, obj);
    }

    public t(long j, long j2, long j3, long j4, long j5, long j6, boolean z, boolean z2, Object obj) {
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = j4;
        this.g = j5;
        this.h = j6;
        this.i = z;
        this.j = z2;
        this.k = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r1 > r7) goto L_0x0027;
     */
    @Override // com.google.android.exoplayer2.z
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.z.b a(int r19, com.google.android.exoplayer2.z.b r20, boolean r21, long r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r2 = 1
            r3 = r19
            com.google.android.exoplayer2.util.a.a(r3, r1, r2)
            if (r21 == 0) goto L_0x000e
            java.lang.Object r1 = r0.k
            goto L_0x000f
        L_0x000e:
            r1 = 0
        L_0x000f:
            r3 = r1
            long r1 = r0.h
            boolean r4 = r0.j
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x0030
            r7 = 0
            int r4 = (r22 > r7 ? 1 : (r22 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0030
            long r7 = r0.f
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0029
        L_0x0027:
            r10 = r5
            goto L_0x0031
        L_0x0029:
            long r1 = r1 + r22
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0030
            goto L_0x0027
        L_0x0030:
            r10 = r1
        L_0x0031:
            long r4 = r0.c
            long r6 = r0.d
            boolean r8 = r0.i
            boolean r9 = r0.j
            long r12 = r0.f
            r14 = 0
            r15 = 0
            long r1 = r0.g
            r16 = r1
            r2 = r20
            com.google.android.exoplayer2.z$b r1 = r2.a(r3, r4, r6, r8, r9, r10, r12, r14, r15, r16)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.t.a(int, com.google.android.exoplayer2.z$b, boolean, long):com.google.android.exoplayer2.z$b");
    }

    @Override // com.google.android.exoplayer2.z
    public z.a a(int i, z.a aVar, boolean z) {
        a.a(i, 0, 1);
        return aVar.a(null, z ? b : null, 0, this.e, -this.g);
    }

    @Override // com.google.android.exoplayer2.z
    public int a(Object obj) {
        return b.equals(obj) ? 0 : -1;
    }

    @Override // com.google.android.exoplayer2.z
    public Object a(int i) {
        a.a(i, 0, 1);
        return b;
    }
}
