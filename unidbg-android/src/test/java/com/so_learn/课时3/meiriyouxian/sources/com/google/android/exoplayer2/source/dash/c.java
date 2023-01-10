package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.SparseArray;
import android.util.TimedRemoteCaller;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.j;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.dash.a;
import com.google.android.exoplayer2.source.dash.h;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.z;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: DashMediaSource */
public final class c extends com.google.android.exoplayer2.source.a {
    private long A;
    private long B;
    private long C;
    private int D;
    private long E;
    private int F;
    private final boolean a;
    private final f.a b;
    private final a.AbstractC0089a c;
    private final com.google.android.exoplayer2.source.e d;
    private final o e;
    private final long f;
    private final boolean g;
    private final n.a h;
    private final q.a<? extends com.google.android.exoplayer2.source.dash.a.b> i;
    private final e j;
    private final Object k;
    private final SparseArray<b> l;
    private final Runnable m;
    private final Runnable n;
    private final h.b o;
    private final p p;
    private final Object q;
    private com.google.android.exoplayer2.upstream.f r;
    private Loader s;
    private s t;
    private IOException u;
    private Handler v;
    private Uri w;
    private Uri x;
    private com.google.android.exoplayer2.source.dash.a.b y;
    private boolean z;

    static {
        j.a("goog.exo.dash");
    }

    /* compiled from: DashMediaSource */
    /* renamed from: com.google.android.exoplayer2.source.dash.c$c  reason: collision with other inner class name */
    public static final class C0090c implements AdsMediaSource.c {
        private final a.AbstractC0089a a;
        private final f.a b;
        private q.a<? extends com.google.android.exoplayer2.source.dash.a.b> c;
        private com.google.android.exoplayer2.source.e d = new com.google.android.exoplayer2.source.f();
        private o e = new com.google.android.exoplayer2.upstream.n();
        private long f = 30000;
        private boolean g;
        private boolean h;
        private Object i;

        public C0090c(a.AbstractC0089a aVar, f.a aVar2) {
            this.a = (a.AbstractC0089a) com.google.android.exoplayer2.util.a.a(aVar);
            this.b = aVar2;
        }

        /* renamed from: a */
        public c b(Uri uri) {
            this.h = true;
            if (this.c == null) {
                this.c = new com.google.android.exoplayer2.source.dash.a.c();
            }
            return new c(null, (Uri) com.google.android.exoplayer2.util.a.a(uri), this.b, this.c, this.a, this.d, this.e, this.f, this.g, this.i);
        }
    }

    private c(com.google.android.exoplayer2.source.dash.a.b bVar, Uri uri, f.a aVar, q.a<? extends com.google.android.exoplayer2.source.dash.a.b> aVar2, a.AbstractC0089a aVar3, com.google.android.exoplayer2.source.e eVar, o oVar, long j, boolean z, Object obj) {
        this.w = uri;
        this.y = bVar;
        this.x = uri;
        this.b = aVar;
        this.i = aVar2;
        this.c = aVar3;
        this.e = oVar;
        this.f = j;
        this.g = z;
        this.d = eVar;
        this.q = obj;
        this.a = bVar != null;
        this.h = a((m.a) null);
        this.k = new Object();
        this.l = new SparseArray<>();
        this.o = new b();
        this.E = -9223372036854775807L;
        if (this.a) {
            com.google.android.exoplayer2.util.a.b(!bVar.d);
            this.j = null;
            this.m = null;
            this.n = null;
            this.p = new p.a();
            return;
        }
        this.j = new e();
        this.p = new f();
        this.m = new $$Lambda$c$2xOvbA8J4D6UBPcOhwXOGmjSlg(this);
        this.n = new $$Lambda$c$zMOXeiJQR4mjBYGAHiEUcVjDF1s(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() {
        a(false);
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        this.t = sVar;
        if (this.a) {
            a(false);
            return;
        }
        this.r = this.b.a();
        this.s = new Loader("Loader:DashMediaSource");
        this.v = new Handler();
        d();
    }

    @Override // com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        this.p.a();
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, com.google.android.exoplayer2.upstream.b bVar, long j) {
        int intValue = ((Integer) aVar.a).intValue() - this.F;
        b bVar2 = new b(this.F + intValue, this.y, intValue, this.c, this.t, this.e, a(aVar, this.y.a(intValue).b), this.C, this.p, bVar, this.d, this.o);
        this.l.put(bVar2.a, bVar2);
        return bVar2;
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        b bVar = (b) lVar;
        bVar.f();
        this.l.remove(bVar.a);
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a() {
        this.z = false;
        this.r = null;
        Loader loader = this.s;
        if (loader != null) {
            loader.d();
            this.s = null;
        }
        this.A = 0;
        this.B = 0;
        this.y = this.a ? this.y : null;
        this.x = this.w;
        this.u = null;
        Handler handler = this.v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.v = null;
        }
        this.C = 0;
        this.D = 0;
        this.E = -9223372036854775807L;
        this.F = 0;
        this.l.clear();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.v.removeCallbacks(this.n);
        d();
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        long j2 = this.E;
        if (j2 == -9223372036854775807L || j2 < j) {
            this.E = j;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.google.android.exoplayer2.upstream.q<com.google.android.exoplayer2.source.dash.a.b> r16, long r17, long r19) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.c.a(com.google.android.exoplayer2.upstream.q, long, long):void");
    }

    /* access modifiers changed from: package-private */
    public Loader.b a(q<com.google.android.exoplayer2.source.dash.a.b> qVar, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof ParserException;
        this.h.a(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d(), iOException, z);
        return z ? Loader.d : Loader.a;
    }

    /* access modifiers changed from: package-private */
    public void b(q<Long> qVar, long j, long j2) {
        this.h.a(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d());
        b(((Long) qVar.c()).longValue() - j);
    }

    /* access modifiers changed from: package-private */
    public Loader.b b(q<Long> qVar, long j, long j2, IOException iOException) {
        this.h.a(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d(), iOException, true);
        a(iOException);
        return Loader.c;
    }

    /* access modifiers changed from: package-private */
    public void c(q<?> qVar, long j, long j2) {
        this.h.b(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d());
    }

    private void a(com.google.android.exoplayer2.source.dash.a.m mVar) {
        String str = mVar.a;
        if (z.a((Object) str, (Object) "urn:mpeg:dash:utc:direct:2014") || z.a((Object) str, (Object) "urn:mpeg:dash:utc:direct:2012")) {
            b(mVar);
        } else if (z.a((Object) str, (Object) "urn:mpeg:dash:utc:http-iso:2014") || z.a((Object) str, (Object) "urn:mpeg:dash:utc:http-iso:2012")) {
            a(mVar, new d());
        } else if (z.a((Object) str, (Object) "urn:mpeg:dash:utc:http-xsdate:2014") || z.a((Object) str, (Object) "urn:mpeg:dash:utc:http-xsdate:2012")) {
            a(mVar, new i());
        } else {
            a(new IOException("Unsupported UTC timing scheme"));
        }
    }

    private void b(com.google.android.exoplayer2.source.dash.a.m mVar) {
        try {
            b(z.g(mVar.b) - this.B);
        } catch (ParserException e2) {
            a(e2);
        }
    }

    private void a(com.google.android.exoplayer2.source.dash.a.m mVar, q.a<Long> aVar) {
        a(new q(this.r, Uri.parse(mVar.b), 5, aVar), new h(), 1);
    }

    private void b(long j) {
        this.C = j;
        a(true);
    }

    private void a(IOException iOException) {
        com.google.android.exoplayer2.util.i.b("DashMediaSource", "Failed to resolve UtcTiming element.", iOException);
        a(true);
    }

    private void a(boolean z) {
        boolean z2;
        long j;
        long j2;
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            int keyAt = this.l.keyAt(i2);
            if (keyAt >= this.F) {
                ((b) this.l.valueAt(i2)).a(this.y, keyAt - this.F);
            }
        }
        int a2 = this.y.a() - 1;
        g a3 = g.a(this.y.a(0), this.y.c(0));
        g a4 = g.a(this.y.a(a2), this.y.c(a2));
        long j3 = a3.b;
        long j4 = a4.c;
        if (!this.y.d || a4.a) {
            z2 = false;
        } else {
            j4 = Math.min((f() - com.google.android.exoplayer2.c.b(this.y.a)) - com.google.android.exoplayer2.c.b(this.y.a(a2).b), j4);
            if (this.y.f != -9223372036854775807L) {
                long b2 = j4 - com.google.android.exoplayer2.c.b(this.y.f);
                while (b2 < 0 && a2 > 0) {
                    a2--;
                    b2 += this.y.c(a2);
                }
                if (a2 == 0) {
                    j2 = Math.max(j3, b2);
                } else {
                    j2 = this.y.c(0);
                }
                j3 = j2;
            }
            z2 = true;
        }
        long j5 = j4 - j3;
        for (int i3 = 0; i3 < this.y.a() - 1; i3++) {
            j5 += this.y.c(i3);
        }
        if (this.y.d) {
            long j6 = this.f;
            if (!this.g && this.y.g != -9223372036854775807L) {
                j6 = this.y.g;
            }
            long b3 = j5 - com.google.android.exoplayer2.c.b(j6);
            if (b3 < 5000000) {
                b3 = Math.min(5000000L, j5 / 2);
            }
            j = b3;
        } else {
            j = 0;
        }
        a(new a(this.y.a, this.y.a + this.y.a(0).b + com.google.android.exoplayer2.c.a(j3), this.F, j3, j5, j, this.y, this.q), this.y);
        if (!this.a) {
            this.v.removeCallbacks(this.n);
            if (z2) {
                this.v.postDelayed(this.n, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
            }
            if (this.z) {
                d();
            } else if (z && this.y.d && this.y.e != -9223372036854775807L) {
                long j7 = this.y.e;
                if (j7 == 0) {
                    j7 = 5000;
                }
                c(Math.max(0L, (this.A + j7) - SystemClock.elapsedRealtime()));
            }
        }
    }

    private void c(long j) {
        this.v.postDelayed(this.m, j);
    }

    /* access modifiers changed from: private */
    public void d() {
        Uri uri;
        this.v.removeCallbacks(this.m);
        if (this.s.b()) {
            this.z = true;
            return;
        }
        synchronized (this.k) {
            uri = this.x;
        }
        this.z = false;
        a(new q(this.r, uri, 4, this.i), this.j, this.e.a(4));
    }

    private long e() {
        return (long) Math.min((this.D - 1) * 1000, 5000);
    }

    private <T> void a(q<T> qVar, Loader.a<q<T>> aVar, int i2) {
        this.h.a(qVar.a, qVar.b, this.s.a(qVar, aVar, i2));
    }

    private long f() {
        if (this.C != 0) {
            return com.google.android.exoplayer2.c.b(SystemClock.elapsedRealtime() + this.C);
        }
        return com.google.android.exoplayer2.c.b(System.currentTimeMillis());
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: private */
    public static final class g {
        public final boolean a;
        public final long b;
        public final long c;

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
            r3 = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.google.android.exoplayer2.source.dash.c.g a(com.google.android.exoplayer2.source.dash.a.f r18, long r19) {
            /*
                r0 = r18
                r4 = r19
                java.util.List<com.google.android.exoplayer2.source.dash.a.a> r1 = r0.c
                int r1 = r1.size()
                r2 = 0
                r3 = r2
            L_0x000c:
                r6 = 1
                if (r3 >= r1) goto L_0x0024
                java.util.List<com.google.android.exoplayer2.source.dash.a.a> r7 = r0.c
                java.lang.Object r7 = r7.get(r3)
                com.google.android.exoplayer2.source.dash.a.a r7 = (com.google.android.exoplayer2.source.dash.a.a) r7
                int r7 = r7.b
                if (r7 == r6) goto L_0x0022
                r8 = 2
                if (r7 != r8) goto L_0x001f
                goto L_0x0022
            L_0x001f:
                int r3 = r3 + 1
                goto L_0x000c
            L_0x0022:
                r3 = r6
                goto L_0x0025
            L_0x0024:
                r3 = r2
            L_0x0025:
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r11 = r2
                r14 = r9
                r12 = 0
                r9 = r11
                r10 = r9
            L_0x0030:
                if (r9 >= r1) goto L_0x00b4
                java.util.List<com.google.android.exoplayer2.source.dash.a.a> r6 = r0.c
                java.lang.Object r6 = r6.get(r9)
                com.google.android.exoplayer2.source.dash.a.a r6 = (com.google.android.exoplayer2.source.dash.a.a) r6
                if (r3 == 0) goto L_0x0047
                int r7 = r6.b
                r8 = 3
                if (r7 != r8) goto L_0x0047
                r17 = r1
                r16 = r3
                goto L_0x00a8
            L_0x0047:
                java.util.List<com.google.android.exoplayer2.source.dash.a.i> r6 = r6.c
                java.lang.Object r6 = r6.get(r2)
                com.google.android.exoplayer2.source.dash.a.i r6 = (com.google.android.exoplayer2.source.dash.a.i) r6
                com.google.android.exoplayer2.source.dash.d r6 = r6.e()
                if (r6 != 0) goto L_0x0061
                com.google.android.exoplayer2.source.dash.c$g r6 = new com.google.android.exoplayer2.source.dash.c$g
                r1 = 1
                r2 = 0
                r0 = r6
                r4 = r19
                r0.<init>(r1, r2, r4)
                return r6
            L_0x0061:
                boolean r7 = r6.b()
                r7 = r7 | r11
                int r8 = r6.c(r4)
                if (r8 != 0) goto L_0x0077
                r17 = r1
                r16 = r3
                r11 = r7
                r10 = 1
                r12 = 0
                r14 = 0
                goto L_0x00a8
            L_0x0077:
                if (r10 != 0) goto L_0x00a3
                r16 = r3
                long r2 = r6.a()
                r17 = r1
                long r0 = r6.a(r2)
                long r0 = java.lang.Math.max(r12, r0)
                r11 = -1
                if (r8 == r11) goto L_0x00a1
                long r11 = (long) r8
                long r2 = r2 + r11
                r11 = 1
                long r2 = r2 - r11
                long r11 = r6.a(r2)
                long r2 = r6.b(r2, r4)
                long r11 = r11 + r2
                long r2 = java.lang.Math.min(r14, r11)
                r12 = r0
                r14 = r2
                goto L_0x00a7
            L_0x00a1:
                r12 = r0
                goto L_0x00a7
            L_0x00a3:
                r17 = r1
                r16 = r3
            L_0x00a7:
                r11 = r7
            L_0x00a8:
                int r9 = r9 + 1
                r2 = 0
                r6 = 1
                r0 = r18
                r3 = r16
                r1 = r17
                goto L_0x0030
            L_0x00b4:
                com.google.android.exoplayer2.source.dash.c$g r0 = new com.google.android.exoplayer2.source.dash.c$g
                r10 = r0
                r10.<init>(r11, r12, r14)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.c.g.a(com.google.android.exoplayer2.source.dash.a.f, long):com.google.android.exoplayer2.source.dash.c$g");
        }

        private g(boolean z, long j, long j2) {
            this.a = z;
            this.b = j;
            this.c = j2;
        }
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: private */
    public static final class a extends com.google.android.exoplayer2.z {
        private final long b;
        private final long c;
        private final int d;
        private final long e;
        private final long f;
        private final long g;
        private final com.google.android.exoplayer2.source.dash.a.b h;
        private final Object i;

        @Override // com.google.android.exoplayer2.z
        public int b() {
            return 1;
        }

        public a(long j, long j2, int i, long j3, long j4, long j5, com.google.android.exoplayer2.source.dash.a.b bVar, Object obj) {
            this.b = j;
            this.c = j2;
            this.d = i;
            this.e = j3;
            this.f = j4;
            this.g = j5;
            this.h = bVar;
            this.i = obj;
        }

        @Override // com.google.android.exoplayer2.z
        public int c() {
            return this.h.a();
        }

        @Override // com.google.android.exoplayer2.z
        public z.a a(int i, z.a aVar, boolean z) {
            com.google.android.exoplayer2.util.a.a(i, 0, c());
            Integer num = null;
            String str = z ? this.h.a(i).a : null;
            if (z) {
                num = Integer.valueOf(this.d + i);
            }
            return aVar.a(str, num, 0, this.h.c(i), com.google.android.exoplayer2.c.b(this.h.a(i).b - this.h.a(0).b) - this.e);
        }

        @Override // com.google.android.exoplayer2.z
        public z.b a(int i, z.b bVar, boolean z, long j) {
            com.google.android.exoplayer2.util.a.a(i, 0, 1);
            return bVar.a(z ? this.i : null, this.b, this.c, true, this.h.d && this.h.e != -9223372036854775807L && this.h.b == -9223372036854775807L, a(j), this.f, 0, c() - 1, this.e);
        }

        @Override // com.google.android.exoplayer2.z
        public int a(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            int intValue = ((Integer) obj).intValue() - this.d;
            if (intValue < 0 || intValue >= c()) {
                return -1;
            }
            return intValue;
        }

        private long a(long j) {
            d e;
            long j2 = this.g;
            if (!this.h.d) {
                return j2;
            }
            if (j > 0) {
                j2 += j;
                if (j2 > this.f) {
                    return -9223372036854775807L;
                }
            }
            long c = this.h.c(0);
            long j3 = this.e + j2;
            int i = 0;
            while (i < this.h.a() - 1 && j3 >= c) {
                j3 -= c;
                i++;
                c = this.h.c(i);
            }
            com.google.android.exoplayer2.source.dash.a.f a = this.h.a(i);
            int a2 = a.a(2);
            return (a2 == -1 || (e = a.c.get(a2).c.get(0).e()) == null || e.c(c) == 0) ? j2 : (j2 + e.a(e.a(j3, c))) - j3;
        }

        @Override // com.google.android.exoplayer2.z
        public Object a(int i) {
            com.google.android.exoplayer2.util.a.a(i, 0, c());
            return Integer.valueOf(this.d + i);
        }
    }

    /* compiled from: DashMediaSource */
    private final class b implements h.b {
        private b() {
        }

        @Override // com.google.android.exoplayer2.source.dash.h.b
        public void a() {
            c.this.c();
        }

        @Override // com.google.android.exoplayer2.source.dash.h.b
        public void a(long j) {
            c.this.a(j);
        }
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: private */
    public final class e implements Loader.a<q<com.google.android.exoplayer2.source.dash.a.b>> {
        private e() {
        }

        public void a(q<com.google.android.exoplayer2.source.dash.a.b> qVar, long j, long j2) {
            c.this.a(qVar, j, j2);
        }

        public void a(q<com.google.android.exoplayer2.source.dash.a.b> qVar, long j, long j2, boolean z) {
            c.this.c(qVar, j, j2);
        }

        public Loader.b a(q<com.google.android.exoplayer2.source.dash.a.b> qVar, long j, long j2, IOException iOException, int i) {
            return c.this.a(qVar, j, j2, iOException);
        }
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: private */
    public final class h implements Loader.a<q<Long>> {
        private h() {
        }

        public void a(q<Long> qVar, long j, long j2) {
            c.this.b(qVar, j, j2);
        }

        public void a(q<Long> qVar, long j, long j2, boolean z) {
            c.this.c(qVar, j, j2);
        }

        public Loader.b a(q<Long> qVar, long j, long j2, IOException iOException, int i) {
            return c.this.b(qVar, j, j2, iOException);
        }
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: private */
    public static final class i implements q.a<Long> {
        private i() {
        }

        /* renamed from: a */
        public Long b(Uri uri, InputStream inputStream) throws IOException {
            return Long.valueOf(com.google.android.exoplayer2.util.z.g(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
        }
    }

    /* compiled from: DashMediaSource */
    /* access modifiers changed from: package-private */
    public static final class d implements q.a<Long> {
        private static final Pattern a = Pattern.compile("(.+?)(Z|((\\+|-|\u2212)(\\d\\d)(:?(\\d\\d))?))");

        d() {
        }

        /* renamed from: a */
        public Long b(Uri uri, InputStream inputStream) throws IOException {
            String readLine = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8"))).readLine();
            try {
                Matcher matcher = a.matcher(readLine);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Time.TIMEZONE_UTC));
                    long time = simpleDateFormat.parse(group).getTime();
                    if (!"Z".equals(matcher.group(2))) {
                        long j = "+".equals(matcher.group(4)) ? 1 : -1;
                        long parseLong = Long.parseLong(matcher.group(5));
                        String group2 = matcher.group(7);
                        time -= j * ((((parseLong * 60) + (TextUtils.isEmpty(group2) ? 0 : Long.parseLong(group2))) * 60) * 1000);
                    }
                    return Long.valueOf(time);
                }
                throw new ParserException("Couldn't parse timestamp: " + readLine);
            } catch (ParseException e) {
                throw new ParserException(e);
            }
        }
    }

    /* compiled from: DashMediaSource */
    final class f implements p {
        f() {
        }

        @Override // com.google.android.exoplayer2.upstream.p
        public void a() throws IOException {
            c.this.s.a();
            b();
        }

        private void b() throws IOException {
            if (c.this.u != null) {
                throw c.this.u;
            }
        }
    }
}
