package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TimedRemoteCaller;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.j;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.smoothstreaming.b;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.a;
import com.google.android.exoplayer2.source.t;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.upstream.s;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: SsMediaSource */
public final class d extends com.google.android.exoplayer2.source.a implements Loader.a<q<com.google.android.exoplayer2.source.smoothstreaming.manifest.a>> {
    private final boolean a;
    private final Uri b;
    private final f.a c;
    private final b.a d;
    private final e e;
    private final o f;
    private final long g;
    private final n.a h;
    private final q.a<? extends com.google.android.exoplayer2.source.smoothstreaming.manifest.a> i;
    private final ArrayList<c> j;
    private final Object k;
    private f l;
    private Loader m;
    private p n;
    private s o;
    private long p;
    private com.google.android.exoplayer2.source.smoothstreaming.manifest.a q;
    private Handler r;

    static {
        j.a("goog.exo.smoothstreaming");
    }

    /* compiled from: SsMediaSource */
    public static final class a implements AdsMediaSource.c {
        private final b.a a;
        private final f.a b;
        private q.a<? extends com.google.android.exoplayer2.source.smoothstreaming.manifest.a> c;
        private e d = new com.google.android.exoplayer2.source.f();
        private o e = new com.google.android.exoplayer2.upstream.n();
        private long f = 30000;
        private boolean g;
        private Object h;

        public a(b.a aVar, f.a aVar2) {
            this.a = (b.a) com.google.android.exoplayer2.util.a.a(aVar);
            this.b = aVar2;
        }

        /* renamed from: a */
        public d b(Uri uri) {
            this.g = true;
            if (this.c == null) {
                this.c = new SsManifestParser();
            }
            return new d(null, (Uri) com.google.android.exoplayer2.util.a.a(uri), this.b, this.c, this.a, this.d, this.e, this.f, this.h);
        }
    }

    private d(com.google.android.exoplayer2.source.smoothstreaming.manifest.a aVar, Uri uri, f.a aVar2, q.a<? extends com.google.android.exoplayer2.source.smoothstreaming.manifest.a> aVar3, b.a aVar4, e eVar, o oVar, long j, Object obj) {
        Uri uri2;
        boolean z = false;
        com.google.android.exoplayer2.util.a.b(aVar == null || !aVar.d);
        this.q = aVar;
        if (uri == null) {
            uri2 = null;
        } else {
            uri2 = com.google.android.exoplayer2.source.smoothstreaming.manifest.b.a(uri);
        }
        this.b = uri2;
        this.c = aVar2;
        this.i = aVar3;
        this.d = aVar4;
        this.e = eVar;
        this.f = oVar;
        this.g = j;
        this.h = a((m.a) null);
        this.k = obj;
        this.a = aVar != null ? true : z;
        this.j = new ArrayList<>();
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        this.o = sVar;
        if (this.a) {
            this.n = new p.a();
            c();
            return;
        }
        this.l = this.c.a();
        this.m = new Loader("Loader:Manifest");
        this.n = this.m;
        this.r = new Handler();
        e();
    }

    @Override // com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        this.n.a();
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, com.google.android.exoplayer2.upstream.b bVar, long j) {
        c cVar = new c(this.q, this.d, this.o, this.e, this.f, a(aVar), this.n, bVar);
        this.j.add(cVar);
        return cVar;
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        ((c) lVar).f();
        this.j.remove(lVar);
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a() {
        this.q = this.a ? this.q : null;
        this.l = null;
        this.p = 0;
        Loader loader = this.m;
        if (loader != null) {
            loader.d();
            this.m = null;
        }
        Handler handler = this.r;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.r = null;
        }
    }

    public void a(q<com.google.android.exoplayer2.source.smoothstreaming.manifest.a> qVar, long j, long j2) {
        this.h.a(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d());
        this.q = (com.google.android.exoplayer2.source.smoothstreaming.manifest.a) qVar.c();
        this.p = j - j2;
        c();
        d();
    }

    public void a(q<com.google.android.exoplayer2.source.smoothstreaming.manifest.a> qVar, long j, long j2, boolean z) {
        this.h.b(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d());
    }

    public Loader.b a(q<com.google.android.exoplayer2.source.smoothstreaming.manifest.a> qVar, long j, long j2, IOException iOException, int i) {
        boolean z = iOException instanceof ParserException;
        this.h.a(qVar.a, qVar.e(), qVar.f(), qVar.b, j, j2, qVar.d(), iOException, z);
        return z ? Loader.d : Loader.a;
    }

    private void c() {
        t tVar;
        for (int i = 0; i < this.j.size(); i++) {
            this.j.get(i).a(this.q);
        }
        a.b[] bVarArr = this.q.f;
        long j = Long.MIN_VALUE;
        long j2 = Long.MAX_VALUE;
        for (a.b bVar : bVarArr) {
            if (bVar.k > 0) {
                long min = Math.min(j2, bVar.a(0));
                j = Math.max(j, bVar.a(bVar.k - 1) + bVar.b(bVar.k - 1));
                j2 = min;
            }
        }
        if (j2 == Long.MAX_VALUE) {
            tVar = new t(this.q.d ? -9223372036854775807L : 0, 0, 0, 0, true, this.q.d, this.k);
        } else if (this.q.d) {
            if (this.q.h != -9223372036854775807L && this.q.h > 0) {
                j2 = Math.max(j2, j - this.q.h);
            }
            long j3 = j - j2;
            long b = j3 - c.b(this.g);
            if (b < 5000000) {
                b = Math.min(5000000L, j3 / 2);
            }
            tVar = new t(-9223372036854775807L, j3, j2, b, true, true, this.k);
        } else {
            long j4 = this.q.g != -9223372036854775807L ? this.q.g : j - j2;
            tVar = new t(j2 + j4, j4, j2, 0, true, false, this.k);
        }
        a(tVar, this.q);
    }

    private void d() {
        if (this.q.d) {
            this.r.postDelayed(new $$Lambda$d$FrTh0L2trxk3XG3TuBdjbDUMFrQ(this), Math.max(0L, (this.p + TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) - SystemClock.elapsedRealtime()));
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        q qVar = new q(this.l, this.b, 4, this.i);
        this.h.a(qVar.a, qVar.b, this.m.a(qVar, this, this.f.a(qVar.b)));
    }
}
