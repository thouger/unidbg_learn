package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource extends c<Void> {
    private final m a;
    private final long b;
    private final long c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final ArrayList<b> g;
    private final z.b h;
    private Object i;
    private a j;
    private IllegalClippingException k;
    private long l;
    private long m;

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        private static String getReasonDescription(int i) {
            return i != 0 ? i != 1 ? i != 2 ? "unknown" : "start exceeds end" : "not seekable to start" : "invalid period count";
        }

        public IllegalClippingException(int i) {
            super("Illegal clipping: " + getReasonDescription(i));
            this.reason = i;
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        super.a(sVar);
        a((ClippingMediaSource) null, this.a);
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        IllegalClippingException illegalClippingException = this.k;
        if (illegalClippingException == null) {
            super.b();
            return;
        }
        throw illegalClippingException;
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, b bVar, long j) {
        b bVar2 = new b(this.a.a(aVar, bVar, j), this.d, this.l, this.m);
        this.g.add(bVar2);
        return bVar2;
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        com.google.android.exoplayer2.util.a.b(this.g.remove(lVar));
        this.a.a(((b) lVar).a);
        if (this.g.isEmpty() && !this.e) {
            a(this.j.b);
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a() {
        super.a();
        this.k = null;
        this.j = null;
    }

    /* access modifiers changed from: protected */
    public void a(Void r1, m mVar, z zVar, Object obj) {
        if (this.k == null) {
            this.i = obj;
            a(zVar);
        }
    }

    private void a(z zVar) {
        long j;
        zVar.a(0, this.h);
        long d = this.h.d();
        long j2 = Long.MIN_VALUE;
        if (this.j == null || this.g.isEmpty() || this.e) {
            long j3 = this.b;
            long j4 = this.c;
            if (this.f) {
                long b = this.h.b();
                j3 += b;
                j4 += b;
            }
            this.l = d + j3;
            if (this.c != Long.MIN_VALUE) {
                j2 = d + j4;
            }
            this.m = j2;
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                this.g.get(i).a(this.l, this.m);
            }
            j = j3;
            j2 = j4;
        } else {
            long j5 = this.l - d;
            if (this.c != Long.MIN_VALUE) {
                j2 = this.m - d;
            }
            j = j5;
        }
        try {
            this.j = new a(zVar, j, j2);
            a(this.j, this.i);
        } catch (IllegalClippingException e) {
            this.k = e;
        }
    }

    /* access modifiers changed from: protected */
    public long a(Void r7, long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long a2 = c.a(this.b);
        long max = Math.max(0L, j - a2);
        long j2 = this.c;
        return j2 != Long.MIN_VALUE ? Math.min(c.a(j2) - a2, max) : max;
    }

    /* access modifiers changed from: private */
    public static final class a extends k {
        private final long c;
        private final long d;
        private final long e;
        private final boolean f;

        public a(z zVar, long j, long j2) throws IllegalClippingException {
            super(zVar);
            boolean z = false;
            if (zVar.c() == 1) {
                z.b a = zVar.a(0, new z.b());
                long max = Math.max(0L, j);
                long max2 = j2 == Long.MIN_VALUE ? a.i : Math.max(0L, j2);
                if (a.i != -9223372036854775807L) {
                    max2 = max2 > a.i ? a.i : max2;
                    if (max != 0 && !a.d) {
                        throw new IllegalClippingException(1);
                    } else if (max > max2) {
                        throw new IllegalClippingException(2);
                    }
                }
                this.c = max;
                this.d = max2;
                int i = (max2 > -9223372036854775807L ? 1 : (max2 == -9223372036854775807L ? 0 : -1));
                this.e = i == 0 ? -9223372036854775807L : max2 - max;
                if (a.e && (i == 0 || (a.i != -9223372036854775807L && max2 == a.i))) {
                    z = true;
                }
                this.f = z;
                return;
            }
            throw new IllegalClippingException(0);
        }

        @Override // com.google.android.exoplayer2.source.k, com.google.android.exoplayer2.z
        public z.b a(int i, z.b bVar, boolean z, long j) {
            this.b.a(0, bVar, z, 0);
            bVar.j += this.c;
            bVar.i = this.e;
            bVar.e = this.f;
            if (bVar.h != -9223372036854775807L) {
                bVar.h = Math.max(bVar.h, this.c);
                int i2 = (this.d > -9223372036854775807L ? 1 : (this.d == -9223372036854775807L ? 0 : -1));
                long j2 = bVar.h;
                if (i2 != 0) {
                    j2 = Math.min(j2, this.d);
                }
                bVar.h = j2;
                bVar.h -= this.c;
            }
            long a = c.a(this.c);
            if (bVar.b != -9223372036854775807L) {
                bVar.b += a;
            }
            if (bVar.c != -9223372036854775807L) {
                bVar.c += a;
            }
            return bVar;
        }

        @Override // com.google.android.exoplayer2.source.k, com.google.android.exoplayer2.z
        public z.a a(int i, z.a aVar, boolean z) {
            this.b.a(0, aVar, z);
            long c = aVar.c() - this.c;
            long j = this.e;
            return aVar.a(aVar.a, aVar.b, 0, j == -9223372036854775807L ? -9223372036854775807L : j - c, c);
        }
    }
}
