package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.s;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.trackselection.g;
import com.google.android.exoplayer2.upstream.c;
import com.google.android.exoplayer2.util.b;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.z;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: ExoPlayerImpl */
/* access modifiers changed from: package-private */
public final class h extends a implements f {
    final com.google.android.exoplayer2.trackselection.h b;
    private final t[] c;
    private final g d;
    private final Handler e;
    private final i f;
    private final Handler g;
    private final CopyOnWriteArraySet<r.a> h;
    private final z.a i;
    private final ArrayDeque<a> j;
    private m k;
    private boolean l;
    private boolean m;
    private int n;
    private boolean o;
    private int p;
    private boolean q;
    private boolean r;
    private q s;
    private x t;
    private ExoPlaybackException u;
    private p v;
    private int w;
    private int x;
    private long y;

    public h(t[] tVarArr, g gVar, l lVar, c cVar, b bVar, Looper looper) {
        i.b("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [ExoPlayerLib/2.9.6] [" + com.google.android.exoplayer2.util.z.e + "]");
        com.google.android.exoplayer2.util.a.b(tVarArr.length > 0);
        this.c = (t[]) com.google.android.exoplayer2.util.a.a(tVarArr);
        this.d = (g) com.google.android.exoplayer2.util.a.a(gVar);
        this.l = false;
        this.n = 0;
        this.o = false;
        this.h = new CopyOnWriteArraySet<>();
        this.b = new com.google.android.exoplayer2.trackselection.h(new v[tVarArr.length], new e[tVarArr.length], null);
        this.i = new z.a();
        this.s = q.a;
        this.t = x.e;
        this.e = new AnonymousClass1(looper);
        this.v = p.a(0, this.b);
        this.j = new ArrayDeque<>();
        this.f = new i(tVarArr, gVar, this.b, lVar, cVar, this.l, this.n, this.o, this.e, bVar);
        this.g = new Handler(this.f.b());
    }

    /* compiled from: ExoPlayerImpl */
    /* renamed from: com.google.android.exoplayer2.h$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            h.this.a(message);
        }
    }

    public Looper c() {
        return this.e.getLooper();
    }

    public void a(r.a aVar) {
        this.h.add(aVar);
    }

    public int d() {
        return this.v.f;
    }

    public void a(m mVar, boolean z, boolean z2) {
        this.u = null;
        this.k = mVar;
        p a2 = a(z, z2, 2);
        this.q = true;
        this.p++;
        this.f.a(mVar, z, z2);
        a(a2, false, 4, 1, false, false);
    }

    public void a(boolean z, boolean z2) {
        boolean z3 = z && !z2;
        if (this.m != z3) {
            this.m = z3;
            this.f.a(z3);
        }
        if (this.l != z) {
            this.l = z;
            a(this.v, false, 4, 1, false, true);
        }
    }

    public boolean e() {
        return this.l;
    }

    public void a(int i) {
        if (this.n != i) {
            this.n = i;
            this.f.a(i);
            Iterator<r.a> it2 = this.h.iterator();
            while (it2.hasNext()) {
                it2.next().a_(i);
            }
        }
    }

    @Override // com.google.android.exoplayer2.r
    public void a(int i, long j) {
        z zVar = this.v.a;
        if (i < 0 || (!zVar.a() && i >= zVar.b())) {
            throw new IllegalSeekPositionException(zVar, i, j);
        }
        this.r = true;
        this.p++;
        if (m()) {
            i.c("ExoPlayerImpl", "seekTo ignored because an ad is playing");
            this.e.obtainMessage(0, 1, -1, this.v).sendToTarget();
            return;
        }
        this.w = i;
        if (zVar.a()) {
            this.y = j == -9223372036854775807L ? 0 : j;
            this.x = 0;
        } else {
            long b = j == -9223372036854775807L ? zVar.a(i, this.a).b() : c.b(j);
            Pair<Object, Long> a2 = zVar.a(this.a, this.i, i, b);
            this.y = c.a(b);
            this.x = zVar.a(a2.first);
        }
        this.f.a(zVar, i, c.b(j));
        Iterator<r.a> it2 = this.h.iterator();
        while (it2.hasNext()) {
            it2.next().b(1);
        }
    }

    @Override // com.google.android.exoplayer2.r
    public void a(boolean z) {
        if (z) {
            this.u = null;
            this.k = null;
        }
        p a2 = a(z, z, 1);
        this.p++;
        this.f.b(z);
        a(a2, false, 4, 1, false, false);
    }

    public void f() {
        i.b("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [ExoPlayerLib/2.9.6] [" + com.google.android.exoplayer2.util.z.e + "] [" + j.a() + "]");
        this.k = null;
        this.f.a();
        this.e.removeCallbacksAndMessages(null);
    }

    public s a(s.b bVar) {
        return new s(this.f, bVar, this.v.a, h(), this.g);
    }

    public int g() {
        if (s()) {
            return this.x;
        }
        return this.v.a.a(this.v.c.a);
    }

    @Override // com.google.android.exoplayer2.r
    public int h() {
        if (s()) {
            return this.w;
        }
        return this.v.a.a(this.v.c.a, this.i).c;
    }

    public long i() {
        if (!m()) {
            return b();
        }
        m.a aVar = this.v.c;
        this.v.a.a(aVar.a, this.i);
        return c.a(this.i.c(aVar.b, aVar.c));
    }

    @Override // com.google.android.exoplayer2.r
    public long j() {
        if (s()) {
            return this.y;
        }
        if (this.v.c.a()) {
            return c.a(this.v.m);
        }
        return a(this.v.c, this.v.m);
    }

    public long k() {
        if (!m()) {
            return q();
        }
        if (this.v.j.equals(this.v.c)) {
            return c.a(this.v.k);
        }
        return i();
    }

    @Override // com.google.android.exoplayer2.r
    public long l() {
        return Math.max(0L, c.a(this.v.l));
    }

    public boolean m() {
        return !s() && this.v.c.a();
    }

    @Override // com.google.android.exoplayer2.r
    public int n() {
        if (m()) {
            return this.v.c.b;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.r
    public int o() {
        if (m()) {
            return this.v.c.c;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.r
    public long p() {
        if (!m()) {
            return j();
        }
        this.v.a.a(this.v.c.a, this.i);
        return this.i.b() + c.a(this.v.e);
    }

    public long q() {
        if (s()) {
            return this.y;
        }
        if (this.v.j.d != this.v.c.d) {
            return this.v.a.a(h(), this.a).c();
        }
        long j = this.v.k;
        if (this.v.j.a()) {
            z.a a2 = this.v.a.a(this.v.j.a, this.i);
            long a3 = a2.a(this.v.j.b);
            j = a3 == Long.MIN_VALUE ? a2.d : a3;
        }
        return a(this.v.j, j);
    }

    @Override // com.google.android.exoplayer2.r
    public z r() {
        return this.v.a;
    }

    /* access modifiers changed from: package-private */
    public void a(Message message) {
        int i = message.what;
        boolean z = true;
        if (i == 0) {
            p pVar = (p) message.obj;
            int i2 = message.arg1;
            if (message.arg2 == -1) {
                z = false;
            }
            a(pVar, i2, z, message.arg2);
        } else if (i == 1) {
            q qVar = (q) message.obj;
            if (!this.s.equals(qVar)) {
                this.s = qVar;
                Iterator<r.a> it2 = this.h.iterator();
                while (it2.hasNext()) {
                    it2.next().a(qVar);
                }
            }
        } else if (i == 2) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) message.obj;
            this.u = exoPlaybackException;
            Iterator<r.a> it3 = this.h.iterator();
            while (it3.hasNext()) {
                it3.next().onPlayerError(exoPlaybackException);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private void a(p pVar, int i, boolean z, int i2) {
        this.p -= i;
        if (this.p == 0) {
            if (pVar.d == -9223372036854775807L) {
                pVar = pVar.a(pVar.c, 0, pVar.e);
            }
            if ((!this.v.a.a() || this.q) && pVar.a.a()) {
                this.x = 0;
                this.w = 0;
                this.y = 0;
            }
            int i3 = this.q ? 0 : 2;
            boolean z2 = this.r;
            this.q = false;
            this.r = false;
            a(pVar, z, i2, i3, z2, false);
        }
    }

    private p a(boolean z, boolean z2, int i) {
        long j;
        long j2 = 0;
        if (z) {
            this.w = 0;
            this.x = 0;
            this.y = 0;
        } else {
            this.w = h();
            this.x = g();
            this.y = j();
        }
        m.a a2 = z ? this.v.a(this.o, this.a) : this.v.c;
        if (!z) {
            j2 = this.v.m;
        }
        if (z) {
            j = -9223372036854775807L;
        } else {
            j = this.v.e;
        }
        return new p(z2 ? z.a : this.v.a, z2 ? null : this.v.b, a2, j2, j, i, false, z2 ? TrackGroupArray.a : this.v.h, z2 ? this.b : this.v.i, a2, j2, 0, j2);
    }

    private void a(p pVar, boolean z, int i, int i2, boolean z2, boolean z3) {
        boolean z4 = !this.j.isEmpty();
        this.j.addLast(new a(pVar, this.v, this.h, this.d, z, i, i2, z2, this.l, z3));
        this.v = pVar;
        if (!z4) {
            while (!this.j.isEmpty()) {
                this.j.peekFirst().a();
                this.j.removeFirst();
            }
        }
    }

    private long a(m.a aVar, long j) {
        long a2 = c.a(j);
        this.v.a.a(aVar.a, this.i);
        return a2 + this.i.b();
    }

    private boolean s() {
        return this.v.a.a() || this.p > 0;
    }

    /* compiled from: ExoPlayerImpl */
    /* access modifiers changed from: private */
    public static final class a {
        private final p a;
        private final Set<r.a> b;
        private final g c;
        private final boolean d;
        private final int e;
        private final int f;
        private final boolean g;
        private final boolean h;
        private final boolean i;
        private final boolean j;
        private final boolean k;
        private final boolean l;

        public a(p pVar, p pVar2, Set<r.a> set, g gVar, boolean z, int i, int i2, boolean z2, boolean z3, boolean z4) {
            this.a = pVar;
            this.b = set;
            this.c = gVar;
            this.d = z;
            this.e = i;
            this.f = i2;
            this.g = z2;
            this.h = z3;
            boolean z5 = false;
            this.i = z4 || pVar2.f != pVar.f;
            this.j = (pVar2.a == pVar.a && pVar2.b == pVar.b) ? false : true;
            this.k = pVar2.g != pVar.g;
            this.l = pVar2.i != pVar.i ? true : z5;
        }

        public void a() {
            if (this.j || this.f == 0) {
                for (r.a aVar : this.b) {
                    aVar.a(this.a.a, this.a.b, this.f);
                }
            }
            if (this.d) {
                for (r.a aVar2 : this.b) {
                    aVar2.b(this.e);
                }
            }
            if (this.l) {
                this.c.a(this.a.i.d);
                for (r.a aVar3 : this.b) {
                    aVar3.a(this.a.h, this.a.i.c);
                }
            }
            if (this.k) {
                for (r.a aVar4 : this.b) {
                    aVar4.a(this.a.g);
                }
            }
            if (this.i) {
                for (r.a aVar5 : this.b) {
                    aVar5.onPlayerStateChanged(this.h, this.a.f);
                }
            }
            if (this.g) {
                for (r.a aVar6 : this.b) {
                    aVar6.a();
                }
            }
        }
    }
}
