package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.e;
import com.google.android.exoplayer2.s;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.trackselection.g;
import com.google.android.exoplayer2.trackselection.h;
import com.google.android.exoplayer2.util.x;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: ExoPlayerImplInternal */
final class i implements Handler.Callback, e.a, s.a, l.a, m.b, g.a {
    private boolean A;
    private int B;
    private d C;
    private long D;
    private int E;
    private final t[] a;
    private final u[] b;
    private final g c;
    private final h d;
    private final l e;
    private final com.google.android.exoplayer2.upstream.c f;
    private final com.google.android.exoplayer2.util.h g;
    private final HandlerThread h;
    private final Handler i;
    private final z.b j;
    private final z.a k;
    private final long l;
    private final boolean m;
    private final e n;
    private final c o;
    private final ArrayList<b> p;
    private final com.google.android.exoplayer2.util.b q;
    private final o r = new o();
    private x s;
    private p t;
    private m u;
    private t[] v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;

    public i(t[] tVarArr, g gVar, h hVar, l lVar, com.google.android.exoplayer2.upstream.c cVar, boolean z, int i, boolean z2, Handler handler, com.google.android.exoplayer2.util.b bVar) {
        this.a = tVarArr;
        this.c = gVar;
        this.d = hVar;
        this.e = lVar;
        this.f = cVar;
        this.x = z;
        this.z = i;
        this.A = z2;
        this.i = handler;
        this.q = bVar;
        this.l = lVar.e();
        this.m = lVar.f();
        this.s = x.e;
        this.t = p.a(-9223372036854775807L, hVar);
        this.o = new c();
        this.b = new u[tVarArr.length];
        for (int i2 = 0; i2 < tVarArr.length; i2++) {
            tVarArr[i2].a(i2);
            this.b[i2] = tVarArr[i2].b();
        }
        this.n = new e(this, bVar);
        this.p = new ArrayList<>();
        this.v = new t[0];
        this.j = new z.b();
        this.k = new z.a();
        gVar.a(this, cVar);
        this.h = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.h.start();
        this.g = bVar.a(this.h.getLooper(), this);
    }

    public void a(m mVar, boolean z, boolean z2) {
        this.g.a(0, z ? 1 : 0, z2 ? 1 : 0, mVar).sendToTarget();
    }

    public void a(boolean z) {
        this.g.a(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void a(int i) {
        this.g.a(12, i, 0).sendToTarget();
    }

    public void a(z zVar, int i, long j) {
        this.g.a(3, new d(zVar, i, j)).sendToTarget();
    }

    public void b(boolean z) {
        this.g.a(6, z ? 1 : 0, 0).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.s.a
    public synchronized void a(s sVar) {
        if (this.w) {
            com.google.android.exoplayer2.util.i.c("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            sVar.a(false);
            return;
        }
        this.g.a(14, sVar).sendToTarget();
    }

    public synchronized void a() {
        if (!this.w) {
            this.g.a(7);
            boolean z = false;
            while (!this.w) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Looper b() {
        return this.h.getLooper();
    }

    @Override // com.google.android.exoplayer2.source.m.b
    public void onSourceInfoRefreshed(m mVar, z zVar, Object obj) {
        this.g.a(8, new a(mVar, zVar, obj)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.l.a
    public void a(l lVar) {
        this.g.a(9, lVar).sendToTarget();
    }

    /* renamed from: b */
    public void a(l lVar) {
        this.g.a(10, lVar).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.e.a
    public void a(q qVar) {
        this.g.a(16, qVar).sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    b((m) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    d(message.arg1 != 0);
                    break;
                case 2:
                    g();
                    break;
                case 3:
                    a((d) message.obj);
                    break;
                case 4:
                    b((q) message.obj);
                    break;
                case 5:
                    a((x) message.obj);
                    break;
                case 6:
                    a(message.arg1 != 0, true);
                    break;
                case 7:
                    h();
                    return true;
                case 8:
                    a((a) message.obj);
                    break;
                case 9:
                    c((l) message.obj);
                    break;
                case 10:
                    d((l) message.obj);
                    break;
                case 11:
                    j();
                    break;
                case 12:
                    c(message.arg1);
                    break;
                case 13:
                    e(message.arg1 != 0);
                    break;
                case 14:
                    b((s) message.obj);
                    break;
                case 15:
                    d((s) message.obj);
                    break;
                case 16:
                    c((q) message.obj);
                    break;
                default:
                    return false;
            }
            c();
        } catch (ExoPlaybackException e) {
            com.google.android.exoplayer2.util.i.b("ExoPlayerImplInternal", "Playback error.", e);
            a(false, false);
            this.i.obtainMessage(2, e).sendToTarget();
            c();
        } catch (IOException e2) {
            com.google.android.exoplayer2.util.i.b("ExoPlayerImplInternal", "Source error.", e2);
            a(false, false);
            this.i.obtainMessage(2, ExoPlaybackException.createForSource(e2)).sendToTarget();
            c();
        } catch (RuntimeException e3) {
            com.google.android.exoplayer2.util.i.b("ExoPlayerImplInternal", "Internal runtime error.", e3);
            a(false, false);
            this.i.obtainMessage(2, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            c();
        }
        return true;
    }

    private void b(int i) {
        if (this.t.f != i) {
            this.t = this.t.a(i);
        }
    }

    private void c(boolean z) {
        if (this.t.g != z) {
            this.t = this.t.a(z);
        }
    }

    private void c() {
        if (this.o.a(this.t)) {
            this.i.obtainMessage(0, this.o.b, this.o.c ? this.o.d : -1, this.t).sendToTarget();
            this.o.b(this.t);
        }
    }

    private void b(m mVar, boolean z, boolean z2) {
        this.B++;
        a(true, z, z2);
        this.e.a();
        this.u = mVar;
        b(2);
        mVar.a(this, this.f.b());
        this.g.a(2);
    }

    private void d(boolean z) throws ExoPlaybackException {
        this.y = false;
        this.x = z;
        if (!z) {
            e();
            f();
        } else if (this.t.f == 3) {
            d();
            this.g.a(2);
        } else if (this.t.f == 2) {
            this.g.a(2);
        }
    }

    private void c(int i) throws ExoPlaybackException {
        this.z = i;
        if (!this.r.a(i)) {
            f(true);
        }
        h(false);
    }

    private void e(boolean z) throws ExoPlaybackException {
        this.A = z;
        if (!this.r.a(z)) {
            f(true);
        }
        h(false);
    }

    private void f(boolean z) throws ExoPlaybackException {
        m.a aVar = this.r.c().g.a;
        long a2 = a(aVar, this.t.m, true);
        if (a2 != this.t.m) {
            p pVar = this.t;
            this.t = pVar.a(aVar, a2, pVar.e, r());
            if (z) {
                this.o.b(4);
            }
        }
    }

    private void d() throws ExoPlaybackException {
        this.y = false;
        this.n.a();
        for (t tVar : this.v) {
            tVar.ad_();
        }
    }

    private void e() throws ExoPlaybackException {
        this.n.b();
        for (t tVar : this.v) {
            a(tVar);
        }
    }

    private void f() throws ExoPlaybackException {
        if (this.r.f()) {
            m c2 = this.r.c();
            long c3 = c2.a.c();
            if (c3 != -9223372036854775807L) {
                a(c3);
                if (c3 != this.t.m) {
                    p pVar = this.t;
                    this.t = pVar.a(pVar.c, c3, this.t.e, r());
                    this.o.b(4);
                }
            } else {
                this.D = this.n.c();
                long b2 = c2.b(this.D);
                b(this.t.m, b2);
                this.t.m = b2;
            }
            m b3 = this.r.b();
            this.t.k = b3.d();
            this.t.l = r();
        }
    }

    private void g() throws ExoPlaybackException, IOException {
        long b2 = this.q.b();
        o();
        if (!this.r.f()) {
            m();
            a(b2, 10);
            return;
        }
        m c2 = this.r.c();
        x.a("doSomeWork");
        f();
        long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        c2.a.a(this.t.m - this.l, this.m);
        t[] tVarArr = this.v;
        boolean z = true;
        boolean z2 = true;
        for (t tVar : tVarArr) {
            tVar.a(this.D, elapsedRealtime);
            z2 = z2 && tVar.v();
            boolean z3 = tVar.u() || tVar.v() || c(tVar);
            if (!z3) {
                tVar.j();
            }
            z = z && z3;
        }
        if (!z) {
            m();
        }
        long j = c2.g.d;
        if (z2 && ((j == -9223372036854775807L || j <= this.t.m) && c2.g.f)) {
            b(4);
            e();
        } else if (this.t.f == 2 && g(z)) {
            b(3);
            if (this.x) {
                d();
            }
        } else if (this.t.f == 3 && (this.v.length != 0 ? !z : !k())) {
            this.y = this.x;
            b(2);
            e();
        }
        if (this.t.f == 2) {
            for (t tVar2 : this.v) {
                tVar2.j();
            }
        }
        if ((this.x && this.t.f == 3) || this.t.f == 2) {
            a(b2, 10);
        } else if (this.v.length == 0 || this.t.f == 4) {
            this.g.b(2);
        } else {
            a(b2, 1000);
        }
        x.a();
    }

    private void a(long j, long j2) {
        this.g.b(2);
        this.g.a(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d7 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.google.android.exoplayer2.i.d r23) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
        // Method dump skipped, instructions count: 244
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.i.a(com.google.android.exoplayer2.i$d):void");
    }

    private long a(m.a aVar, long j) throws ExoPlaybackException {
        return a(aVar, j, this.r.c() != this.r.d());
    }

    private long a(m.a aVar, long j, boolean z) throws ExoPlaybackException {
        e();
        this.y = false;
        b(2);
        m c2 = this.r.c();
        m mVar = c2;
        while (true) {
            if (mVar == null) {
                break;
            }
            if (aVar.equals(mVar.g.a) && mVar.e) {
                this.r.a(mVar);
                break;
            }
            mVar = this.r.h();
        }
        if (c2 != mVar || z) {
            for (t tVar : this.v) {
                b(tVar);
            }
            this.v = new t[0];
            c2 = null;
        }
        if (mVar != null) {
            a(c2);
            if (mVar.f) {
                long b2 = mVar.a.b(j);
                mVar.a.a(b2 - this.l, this.m);
                j = b2;
            }
            a(j);
            q();
        } else {
            this.r.b(true);
            this.t = this.t.a(TrackGroupArray.a, this.d);
            a(j);
        }
        h(false);
        this.g.a(2);
        return j;
    }

    private void a(long j) throws ExoPlaybackException {
        if (this.r.f()) {
            j = this.r.c().a(j);
        }
        this.D = j;
        this.n.a(this.D);
        for (t tVar : this.v) {
            tVar.a(this.D);
        }
    }

    private void b(q qVar) {
        this.n.a(qVar);
    }

    private void a(x xVar) {
        this.s = xVar;
    }

    private void a(boolean z, boolean z2) {
        a(true, z, z);
        this.o.a(this.B + (z2 ? 1 : 0));
        this.B = 0;
        this.e.b();
        b(1);
    }

    private void h() {
        a(true, true, true);
        this.e.c();
        b(1);
        this.h.quit();
        synchronized (this) {
            this.w = true;
            notifyAll();
        }
    }

    private void a(boolean z, boolean z2, boolean z3) {
        m mVar;
        this.g.b(2);
        this.y = false;
        this.n.b();
        this.D = 0;
        for (t tVar : this.v) {
            try {
                b(tVar);
            } catch (ExoPlaybackException | RuntimeException e) {
                com.google.android.exoplayer2.util.i.b("ExoPlayerImplInternal", "Stop failed.", e);
            }
        }
        this.v = new t[0];
        this.r.b(!z2);
        c(false);
        if (z2) {
            this.C = null;
        }
        if (z3) {
            this.r.a(z.a);
            Iterator<b> it2 = this.p.iterator();
            while (it2.hasNext()) {
                it2.next().a.a(false);
            }
            this.p.clear();
            this.E = 0;
        }
        m.a a2 = z2 ? this.t.a(this.A, this.j) : this.t.c;
        long j = -9223372036854775807L;
        long j2 = z2 ? -9223372036854775807L : this.t.m;
        if (!z2) {
            j = this.t.e;
        }
        this.t = new p(z3 ? z.a : this.t.a, z3 ? null : this.t.b, a2, j2, j, this.t.f, false, z3 ? TrackGroupArray.a : this.t.h, z3 ? this.d : this.t.i, a2, j2, 0, j2);
        if (z && (mVar = this.u) != null) {
            mVar.a(this);
            this.u = null;
        }
    }

    private void b(s sVar) throws ExoPlaybackException {
        if (sVar.f() == -9223372036854775807L) {
            c(sVar);
        } else if (this.u == null || this.B > 0) {
            this.p.add(new b(sVar));
        } else {
            b bVar = new b(sVar);
            if (a(bVar)) {
                this.p.add(bVar);
                Collections.sort(this.p);
                return;
            }
            sVar.a(false);
        }
    }

    private void c(s sVar) throws ExoPlaybackException {
        if (sVar.e().getLooper() == this.g.a()) {
            e(sVar);
            if (this.t.f == 3 || this.t.f == 2) {
                this.g.a(2);
                return;
            }
            return;
        }
        this.g.a(15, sVar).sendToTarget();
    }

    private void d(s sVar) {
        sVar.e().post(new $$Lambda$i$tN1vIseDTT0DNQBGuiGLmSaZS8(this, sVar));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(s sVar) {
        try {
            e(sVar);
        } catch (ExoPlaybackException e) {
            com.google.android.exoplayer2.util.i.b("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void e(s sVar) throws ExoPlaybackException {
        if (!sVar.j()) {
            boolean z = true;
            try {
                sVar.b().a(sVar.c(), sVar.d());
            } finally {
                sVar.a(z);
            }
        }
    }

    private void i() {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            if (!a(this.p.get(size))) {
                this.p.get(size).a.a(false);
                this.p.remove(size);
            }
        }
        Collections.sort(this.p);
    }

    private boolean a(b bVar) {
        if (bVar.d == null) {
            Pair<Object, Long> a2 = a(new d(bVar.a.a(), bVar.a.g(), c.b(bVar.a.f())), false);
            if (a2 == null) {
                return false;
            }
            bVar.a(this.t.a.a(a2.first), a2.second.longValue(), a2.first);
            return true;
        }
        int a3 = this.t.a.a(bVar.d);
        if (a3 == -1) {
            return false;
        }
        bVar.b = a3;
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a8 A[ADDED_TO_REGION, EDGE_INSN: B:62:0x00a8->B:77:0x00a8 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ec A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(long r6, long r8) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
        // Method dump skipped, instructions count: 250
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.i.b(long, long):void");
    }

    private void a(t tVar) throws ExoPlaybackException {
        if (tVar.ac_() == 2) {
            tVar.k();
        }
    }

    private void b(t tVar) throws ExoPlaybackException {
        this.n.b(tVar);
        a(tVar);
        tVar.l();
    }

    private void j() throws ExoPlaybackException {
        if (this.r.f()) {
            float f = this.n.e().b;
            m c2 = this.r.c();
            m d2 = this.r.d();
            boolean z = true;
            while (c2 != null && c2.e) {
                if (c2.b(f)) {
                    if (z) {
                        m c3 = this.r.c();
                        boolean a2 = this.r.a(c3);
                        boolean[] zArr = new boolean[this.a.length];
                        long a3 = c3.a(this.t.m, a2, zArr);
                        if (!(this.t.f == 4 || a3 == this.t.m)) {
                            p pVar = this.t;
                            this.t = pVar.a(pVar.c, a3, this.t.e, r());
                            this.o.b(4);
                            a(a3);
                        }
                        boolean[] zArr2 = new boolean[this.a.length];
                        int i = 0;
                        int i2 = 0;
                        while (true) {
                            t[] tVarArr = this.a;
                            if (i >= tVarArr.length) {
                                break;
                            }
                            t tVar = tVarArr[i];
                            zArr2[i] = tVar.ac_() != 0;
                            r rVar = c3.c[i];
                            if (rVar != null) {
                                i2++;
                            }
                            if (zArr2[i]) {
                                if (rVar != tVar.f()) {
                                    b(tVar);
                                } else if (zArr[i]) {
                                    tVar.a(this.D);
                                }
                            }
                            i++;
                        }
                        this.t = this.t.a(c3.i, c3.j);
                        a(zArr2, i2);
                    } else {
                        this.r.a(c2);
                        if (c2.e) {
                            c2.a(Math.max(c2.g.b, c2.b(this.D)), false);
                        }
                    }
                    h(true);
                    if (this.t.f != 4) {
                        q();
                        f();
                        this.g.a(2);
                        return;
                    }
                    return;
                }
                if (c2 == d2) {
                    z = false;
                }
                c2 = c2.h;
            }
        }
    }

    private void a(float f) {
        for (m e = this.r.e(); e != null; e = e.h) {
            if (e.j != null) {
                com.google.android.exoplayer2.trackselection.e[] a2 = e.j.c.a();
                for (com.google.android.exoplayer2.trackselection.e eVar : a2) {
                    if (eVar != null) {
                        eVar.a(f);
                    }
                }
            }
        }
    }

    private boolean g(boolean z) {
        if (this.v.length == 0) {
            return k();
        }
        if (!z) {
            return false;
        }
        if (!this.t.g) {
            return true;
        }
        m b2 = this.r.b();
        if ((b2.c() && b2.g.f) || this.e.a(r(), this.n.e().b, this.y)) {
            return true;
        }
        return false;
    }

    private boolean k() {
        m c2 = this.r.c();
        long j = c2.g.d;
        return j == -9223372036854775807L || this.t.m < j || (c2.h != null && (c2.h.e || c2.h.g.a.a()));
    }

    private void l() throws IOException {
        if (this.r.b() != null) {
            for (t tVar : this.v) {
                if (!tVar.g()) {
                    return;
                }
            }
        }
        this.u.b();
    }

    private void m() throws IOException {
        m b2 = this.r.b();
        m d2 = this.r.d();
        if (!(b2 == null || b2.e)) {
            if (d2 == null || d2.h == b2) {
                for (t tVar : this.v) {
                    if (!tVar.g()) {
                        return;
                    }
                }
                b2.a.ae_();
            }
        }
    }

    private void a(a aVar) throws ExoPlaybackException {
        if (aVar.a == this.u) {
            z zVar = this.t.a;
            z zVar2 = aVar.b;
            Object obj = aVar.c;
            this.r.a(zVar2);
            this.t = this.t.a(zVar2, obj);
            i();
            int i = this.B;
            long j = 0;
            if (i > 0) {
                this.o.a(i);
                this.B = 0;
                d dVar = this.C;
                if (dVar != null) {
                    try {
                        Pair<Object, Long> a2 = a(dVar, true);
                        this.C = null;
                        if (a2 == null) {
                            n();
                            return;
                        }
                        Object obj2 = a2.first;
                        long longValue = a2.second.longValue();
                        m.a a3 = this.r.a(obj2, longValue);
                        this.t = this.t.a(a3, a3.a() ? 0 : longValue, longValue);
                    } catch (IllegalSeekPositionException e) {
                        this.t = this.t.a(this.t.a(this.A, this.j), -9223372036854775807L, -9223372036854775807L);
                        throw e;
                    }
                } else if (this.t.d != -9223372036854775807L) {
                } else {
                    if (zVar2.a()) {
                        n();
                        return;
                    }
                    Pair<Object, Long> b2 = b(zVar2, zVar2.b(this.A), -9223372036854775807L);
                    Object obj3 = b2.first;
                    long longValue2 = b2.second.longValue();
                    m.a a4 = this.r.a(obj3, longValue2);
                    this.t = this.t.a(a4, a4.a() ? 0 : longValue2, longValue2);
                }
            } else if (!zVar.a()) {
                m e2 = this.r.e();
                long j2 = this.t.e;
                Object obj4 = e2 == null ? this.t.c.a : e2.b;
                if (zVar2.a(obj4) == -1) {
                    Object a5 = a(obj4, zVar, zVar2);
                    if (a5 == null) {
                        n();
                        return;
                    }
                    Pair<Object, Long> b3 = b(zVar2, zVar2.a(a5, this.k).c, -9223372036854775807L);
                    Object obj5 = b3.first;
                    long longValue3 = b3.second.longValue();
                    m.a a6 = this.r.a(obj5, longValue3);
                    if (e2 != null) {
                        while (e2.h != null) {
                            e2 = e2.h;
                            if (e2.g.a.equals(a6)) {
                                e2.g = this.r.a(e2.g);
                            }
                        }
                    }
                    if (!a6.a()) {
                        j = longValue3;
                    }
                    this.t = this.t.a(a6, a(a6, j), longValue3, r());
                    return;
                }
                m.a aVar2 = this.t.c;
                if (aVar2.a()) {
                    m.a a7 = this.r.a(obj4, j2);
                    if (!a7.equals(aVar2)) {
                        if (!a7.a()) {
                            j = j2;
                        }
                        this.t = this.t.a(a7, a(a7, j), j2, r());
                        return;
                    }
                }
                if (!this.r.a(aVar2, this.D)) {
                    f(false);
                }
                h(false);
            } else if (!zVar2.a()) {
                Pair<Object, Long> b4 = b(zVar2, zVar2.b(this.A), -9223372036854775807L);
                Object obj6 = b4.first;
                long longValue4 = b4.second.longValue();
                m.a a8 = this.r.a(obj6, longValue4);
                this.t = this.t.a(a8, a8.a() ? 0 : longValue4, longValue4);
            }
        }
    }

    private void n() {
        b(4);
        a(false, true, false);
    }

    private Object a(Object obj, z zVar, z zVar2) {
        int a2 = zVar.a(obj);
        int c2 = zVar.c();
        int i = a2;
        int i2 = -1;
        for (int i3 = 0; i3 < c2 && i2 == -1; i3++) {
            i = zVar.a(i, this.k, this.j, this.z, this.A);
            if (i == -1) {
                break;
            }
            i2 = zVar2.a(zVar.a(i));
        }
        if (i2 == -1) {
            return null;
        }
        return zVar2.a(i2);
    }

    private Pair<Object, Long> a(d dVar, boolean z) {
        int a2;
        z zVar = this.t.a;
        z zVar2 = dVar.a;
        if (zVar.a()) {
            return null;
        }
        if (zVar2.a()) {
            zVar2 = zVar;
        }
        try {
            Pair<Object, Long> a3 = zVar2.a(this.j, this.k, dVar.b, dVar.c);
            if (zVar == zVar2 || (a2 = zVar.a(a3.first)) != -1) {
                return a3;
            }
            if (!z || a(a3.first, zVar2, zVar) == null) {
                return null;
            }
            return b(zVar, zVar.a(a2, this.k).c, -9223372036854775807L);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(zVar, dVar.b, dVar.c);
        }
    }

    private Pair<Object, Long> b(z zVar, int i, long j) {
        return zVar.a(this.j, this.k, i, j);
    }

    private void o() throws ExoPlaybackException, IOException {
        m mVar = this.u;
        if (mVar != null) {
            if (this.B > 0) {
                mVar.b();
                return;
            }
            p();
            m b2 = this.r.b();
            int i = 0;
            if (b2 == null || b2.c()) {
                c(false);
            } else if (!this.t.g) {
                q();
            }
            if (this.r.f()) {
                m c2 = this.r.c();
                m d2 = this.r.d();
                boolean z = false;
                while (this.x && c2 != d2 && this.D >= c2.h.b()) {
                    if (z) {
                        c();
                    }
                    int i2 = c2.g.e ? 0 : 3;
                    m h = this.r.h();
                    a(c2);
                    this.t = this.t.a(h.g.a, h.g.b, h.g.c, r());
                    this.o.b(i2);
                    f();
                    z = true;
                    c2 = h;
                }
                if (d2.g.f) {
                    while (true) {
                        t[] tVarArr = this.a;
                        if (i < tVarArr.length) {
                            t tVar = tVarArr[i];
                            r rVar = d2.c[i];
                            if (rVar != null && tVar.f() == rVar && tVar.g()) {
                                tVar.h();
                            }
                            i++;
                        } else {
                            return;
                        }
                    }
                } else if (d2.h != null) {
                    int i3 = 0;
                    while (true) {
                        t[] tVarArr2 = this.a;
                        if (i3 < tVarArr2.length) {
                            t tVar2 = tVarArr2[i3];
                            r rVar2 = d2.c[i3];
                            if (tVar2.f() != rVar2) {
                                return;
                            }
                            if (rVar2 == null || tVar2.g()) {
                                i3++;
                            } else {
                                return;
                            }
                        } else if (!d2.h.e) {
                            m();
                            return;
                        } else {
                            h hVar = d2.j;
                            m g = this.r.g();
                            h hVar2 = g.j;
                            boolean z2 = g.a.c() != -9223372036854775807L;
                            int i4 = 0;
                            while (true) {
                                t[] tVarArr3 = this.a;
                                if (i4 < tVarArr3.length) {
                                    t tVar3 = tVarArr3[i4];
                                    if (hVar.a(i4)) {
                                        if (z2) {
                                            tVar3.h();
                                        } else if (!tVar3.i()) {
                                            com.google.android.exoplayer2.trackselection.e a2 = hVar2.c.a(i4);
                                            boolean a3 = hVar2.a(i4);
                                            boolean z3 = this.b[i4].a() == 6;
                                            v vVar = hVar.b[i4];
                                            v vVar2 = hVar2.b[i4];
                                            if (!a3 || !vVar2.equals(vVar) || z3) {
                                                tVar3.h();
                                            } else {
                                                tVar3.a(a(a2), g.c[i4], g.a());
                                            }
                                        }
                                    }
                                    i4++;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void p() throws IOException {
        this.r.a(this.D);
        if (this.r.a()) {
            n a2 = this.r.a(this.D, this.t);
            if (a2 == null) {
                l();
                return;
            }
            this.r.a(this.b, this.c, this.e.d(), this.u, a2).a(this, a2.b);
            c(true);
            h(false);
        }
    }

    private void c(l lVar) throws ExoPlaybackException {
        if (this.r.a(lVar)) {
            m b2 = this.r.b();
            b2.a(this.n.e().b);
            a(b2.i, b2.j);
            if (!this.r.f()) {
                a(this.r.h().g.b);
                a((m) null);
            }
            q();
        }
    }

    private void d(l lVar) {
        if (this.r.a(lVar)) {
            this.r.a(this.D);
            q();
        }
    }

    private void c(q qVar) throws ExoPlaybackException {
        this.i.obtainMessage(1, qVar).sendToTarget();
        a(qVar.b);
        t[] tVarArr = this.a;
        for (t tVar : tVarArr) {
            if (tVar != null) {
                tVar.a(qVar.b);
            }
        }
    }

    private void q() {
        m b2 = this.r.b();
        long e = b2.e();
        if (e == Long.MIN_VALUE) {
            c(false);
            return;
        }
        boolean a2 = this.e.a(b(e), this.n.e().b);
        c(a2);
        if (a2) {
            b2.d(this.D);
        }
    }

    private void a(m mVar) throws ExoPlaybackException {
        m c2 = this.r.c();
        if (c2 != null && mVar != c2) {
            boolean[] zArr = new boolean[this.a.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                t[] tVarArr = this.a;
                if (i < tVarArr.length) {
                    t tVar = tVarArr[i];
                    zArr[i] = tVar.ac_() != 0;
                    if (c2.j.a(i)) {
                        i2++;
                    }
                    if (zArr[i] && (!c2.j.a(i) || (tVar.i() && tVar.f() == mVar.c[i]))) {
                        b(tVar);
                    }
                    i++;
                } else {
                    this.t = this.t.a(c2.i, c2.j);
                    a(zArr, i2);
                    return;
                }
            }
        }
    }

    private void a(boolean[] zArr, int i) throws ExoPlaybackException {
        this.v = new t[i];
        m c2 = this.r.c();
        int i2 = 0;
        for (int i3 = 0; i3 < this.a.length; i3++) {
            if (c2.j.a(i3)) {
                a(i3, zArr[i3], i2);
                i2++;
            }
        }
    }

    private void a(int i, boolean z, int i2) throws ExoPlaybackException {
        m c2 = this.r.c();
        t tVar = this.a[i];
        this.v[i2] = tVar;
        if (tVar.ac_() == 0) {
            v vVar = c2.j.b[i];
            Format[] a2 = a(c2.j.c.a(i));
            boolean z2 = this.x && this.t.f == 3;
            tVar.a(vVar, a2, c2.c[i], this.D, !z && z2, c2.a());
            this.n.a(tVar);
            if (z2) {
                tVar.ad_();
            }
        }
    }

    private boolean c(t tVar) {
        m d2 = this.r.d();
        return d2.h != null && d2.h.e && tVar.g();
    }

    private void h(boolean z) {
        long j;
        m b2 = this.r.b();
        m.a aVar = b2 == null ? this.t.c : b2.g.a;
        boolean z2 = !this.t.j.equals(aVar);
        if (z2) {
            this.t = this.t.a(aVar);
        }
        p pVar = this.t;
        if (b2 == null) {
            j = pVar.m;
        } else {
            j = b2.d();
        }
        pVar.k = j;
        this.t.l = r();
        if ((z2 || z) && b2 != null && b2.e) {
            a(b2.i, b2.j);
        }
    }

    private long r() {
        return b(this.t.k);
    }

    private long b(long j) {
        m b2 = this.r.b();
        if (b2 == null) {
            return 0;
        }
        return j - b2.b(this.D);
    }

    private void a(TrackGroupArray trackGroupArray, h hVar) {
        this.e.a(this.a, trackGroupArray, hVar.c);
    }

    private static Format[] a(com.google.android.exoplayer2.trackselection.e eVar) {
        int g = eVar != null ? eVar.g() : 0;
        Format[] formatArr = new Format[g];
        for (int i = 0; i < g; i++) {
            formatArr[i] = eVar.a(i);
        }
        return formatArr;
    }

    /* compiled from: ExoPlayerImplInternal */
    /* access modifiers changed from: private */
    public static final class d {
        public final z a;
        public final int b;
        public final long c;

        public d(z zVar, int i, long j) {
            this.a = zVar;
            this.b = i;
            this.c = j;
        }
    }

    /* compiled from: ExoPlayerImplInternal */
    /* access modifiers changed from: private */
    public static final class b implements Comparable<b> {
        public final s a;
        public int b;
        public long c;
        public Object d;

        public b(s sVar) {
            this.a = sVar;
        }

        public void a(int i, long j, Object obj) {
            this.b = i;
            this.c = j;
            this.d = obj;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            if ((this.d == null) != (bVar.d == null)) {
                if (this.d != null) {
                    return -1;
                }
                return 1;
            } else if (this.d == null) {
                return 0;
            } else {
                int i = this.b - bVar.b;
                if (i != 0) {
                    return i;
                }
                return com.google.android.exoplayer2.util.z.b(this.c, bVar.c);
            }
        }
    }

    /* compiled from: ExoPlayerImplInternal */
    /* access modifiers changed from: private */
    public static final class a {
        public final m a;
        public final z b;
        public final Object c;

        public a(m mVar, z zVar, Object obj) {
            this.a = mVar;
            this.b = zVar;
            this.c = obj;
        }
    }

    /* compiled from: ExoPlayerImplInternal */
    /* access modifiers changed from: private */
    public static final class c {
        private p a;
        private int b;
        private boolean c;
        private int d;

        private c() {
        }

        public boolean a(p pVar) {
            return pVar != this.a || this.b > 0 || this.c;
        }

        public void b(p pVar) {
            this.a = pVar;
            this.b = 0;
            this.c = false;
        }

        public void a(int i) {
            this.b += i;
        }

        public void b(int i) {
            boolean z = true;
            if (!this.c || this.d == 4) {
                this.c = true;
                this.d = i;
                return;
            }
            if (i != 4) {
                z = false;
            }
            com.google.android.exoplayer2.util.a.a(z);
        }
    }
}
