package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.IOException;

/* compiled from: ClippingMediaPeriod */
public final class b implements l, l.a {
    public final l a;
    long b;
    long c;
    private l.a d;
    private a[] e = new a[0];
    private long f;

    public b(l lVar, boolean z, long j, long j2) {
        this.a = lVar;
        this.f = z ? j : -9223372036854775807L;
        this.b = j;
        this.c = j2;
    }

    public void a(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.d = aVar;
        this.a.a(this, j);
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        this.a.ae_();
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.a.b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        if (r2 > r4) goto L_0x0065;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    @Override // com.google.android.exoplayer2.source.l
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(com.google.android.exoplayer2.trackselection.e[] r13, boolean[] r14, com.google.android.exoplayer2.source.r[] r15, boolean[] r16, long r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r15
            int r2 = r1.length
            com.google.android.exoplayer2.source.b$a[] r2 = new com.google.android.exoplayer2.source.b.a[r2]
            r0.e = r2
            int r2 = r1.length
            com.google.android.exoplayer2.source.r[] r9 = new com.google.android.exoplayer2.source.r[r2]
            r10 = 0
            r2 = r10
        L_0x000c:
            int r3 = r1.length
            r11 = 0
            if (r2 >= r3) goto L_0x0025
            com.google.android.exoplayer2.source.b$a[] r3 = r0.e
            r4 = r1[r2]
            com.google.android.exoplayer2.source.b$a r4 = (com.google.android.exoplayer2.source.b.a) r4
            r3[r2] = r4
            r4 = r3[r2]
            if (r4 == 0) goto L_0x0020
            r3 = r3[r2]
            com.google.android.exoplayer2.source.r r11 = r3.a
        L_0x0020:
            r9[r2] = r11
            int r2 = r2 + 1
            goto L_0x000c
        L_0x0025:
            com.google.android.exoplayer2.source.l r2 = r0.a
            r3 = r13
            r4 = r14
            r5 = r9
            r6 = r16
            r7 = r17
            long r2 = r2.a(r3, r4, r5, r6, r7)
            boolean r4 = r12.f()
            if (r4 == 0) goto L_0x0047
            long r4 = r0.b
            int r6 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0047
            r6 = r13
            boolean r4 = a(r4, r13)
            if (r4 == 0) goto L_0x0047
            r4 = r2
            goto L_0x004c
        L_0x0047:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x004c:
            r0.f = r4
            int r4 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r4 == 0) goto L_0x0067
            long r4 = r0.b
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0065
            long r4 = r0.c
            r6 = -9223372036854775808
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0067
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r4 = r10
            goto L_0x0068
        L_0x0067:
            r4 = 1
        L_0x0068:
            com.google.android.exoplayer2.util.a.b(r4)
        L_0x006b:
            int r4 = r1.length
            if (r10 >= r4) goto L_0x0099
            r4 = r9[r10]
            if (r4 != 0) goto L_0x0077
            com.google.android.exoplayer2.source.b$a[] r4 = r0.e
            r4[r10] = r11
            goto L_0x0090
        L_0x0077:
            r4 = r1[r10]
            if (r4 == 0) goto L_0x0085
            com.google.android.exoplayer2.source.b$a[] r4 = r0.e
            r4 = r4[r10]
            com.google.android.exoplayer2.source.r r4 = r4.a
            r5 = r9[r10]
            if (r4 == r5) goto L_0x0090
        L_0x0085:
            com.google.android.exoplayer2.source.b$a[] r4 = r0.e
            com.google.android.exoplayer2.source.b$a r5 = new com.google.android.exoplayer2.source.b$a
            r6 = r9[r10]
            r5.<init>(r6)
            r4[r10] = r5
        L_0x0090:
            com.google.android.exoplayer2.source.b$a[] r4 = r0.e
            r4 = r4[r10]
            r1[r10] = r4
            int r10 = r10 + 1
            goto L_0x006b
        L_0x0099:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.b.a(com.google.android.exoplayer2.trackselection.e[], boolean[], com.google.android.exoplayer2.source.r[], boolean[], long):long");
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        this.a.a(j, z);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.a.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        if (f()) {
            long j = this.f;
            this.f = -9223372036854775807L;
            long c = c();
            return c != -9223372036854775807L ? c : j;
        }
        long c2 = this.a.c();
        if (c2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        boolean z = true;
        com.google.android.exoplayer2.util.a.b(c2 >= this.b);
        long j2 = this.c;
        if (j2 != Long.MIN_VALUE && c2 > j2) {
            z = false;
        }
        com.google.android.exoplayer2.util.a.b(z);
        return c2;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        long d = this.a.d();
        if (d != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || d < j) {
                return d;
            }
        }
        return Long.MIN_VALUE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r0 > r6) goto L_0x0035;
     */
    @Override // com.google.android.exoplayer2.source.l
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long b(long r6) {
        /*
            r5 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5.f = r0
            com.google.android.exoplayer2.source.b$a[] r0 = r5.e
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x000c:
            if (r3 >= r1) goto L_0x0018
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0015
            r4.a()
        L_0x0015:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0018:
            com.google.android.exoplayer2.source.l r0 = r5.a
            long r0 = r0.b(r6)
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0034
            long r6 = r5.b
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0035
            long r6 = r5.c
            r3 = -9223372036854775808
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0034
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x0035
        L_0x0034:
            r2 = 1
        L_0x0035:
            com.google.android.exoplayer2.util.a.b(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.b.b(long):long");
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        long j2 = this.b;
        if (j == j2) {
            return j2;
        }
        return this.a.a(j, b(j, xVar));
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        long e = this.a.e();
        if (e != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || e < j) {
                return e;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        return this.a.c(j);
    }

    @Override // com.google.android.exoplayer2.source.l.a
    public void a(l lVar) {
        this.d.a((l) this);
    }

    /* renamed from: b */
    public void a(l lVar) {
        this.d.a((l.a) this);
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.f != -9223372036854775807L;
    }

    private x b(long j, x xVar) {
        long a2 = z.a(xVar.f, 0, j - this.b);
        long j2 = xVar.g;
        long j3 = this.c;
        long a3 = z.a(j2, 0, j3 == Long.MIN_VALUE ? Long.MAX_VALUE : j3 - j);
        if (a2 == xVar.f && a3 == xVar.g) {
            return xVar;
        }
        return new x(a2, a3);
    }

    private static boolean a(long j, e[] eVarArr) {
        if (j != 0) {
            for (e eVar : eVarArr) {
                if (!(eVar == null || com.google.android.exoplayer2.util.l.a(eVar.h().g))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* compiled from: ClippingMediaPeriod */
    private final class a implements r {
        public final r a;
        private boolean c;

        public a(r rVar) {
            this.a = rVar;
        }

        public void a() {
            this.c = false;
        }

        @Override // com.google.android.exoplayer2.source.r
        public boolean b() {
            return !b.this.f() && this.a.b();
        }

        @Override // com.google.android.exoplayer2.source.r
        public void c() throws IOException {
            this.a.c();
        }

        @Override // com.google.android.exoplayer2.source.r
        public int a(k kVar, com.google.android.exoplayer2.b.e eVar, boolean z) {
            if (b.this.f()) {
                return -3;
            }
            if (this.c) {
                eVar.b_(4);
                return -4;
            }
            int a = this.a.a(kVar, eVar, z);
            if (a == -5) {
                Format format = kVar.a;
                if (!(format.w == 0 && format.x == 0)) {
                    int i = 0;
                    int i2 = b.this.b != 0 ? 0 : format.w;
                    if (b.this.c == Long.MIN_VALUE) {
                        i = format.x;
                    }
                    kVar.a = format.a(i2, i);
                }
                return -5;
            } else if (b.this.c == Long.MIN_VALUE || ((a != -4 || eVar.c < b.this.c) && (a != -3 || b.this.d() != Long.MIN_VALUE))) {
                return a;
            } else {
                eVar.a();
                eVar.b_(4);
                this.c = true;
                return -4;
            }
        }

        @Override // com.google.android.exoplayer2.source.r
        public int b_(long j) {
            if (b.this.f()) {
                return -3;
            }
            return this.a.b_(j);
        }
    }
}
