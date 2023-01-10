package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.hls.e;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.source.hls.playlist.d;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.q;
import com.google.android.exoplayer2.util.y;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/* compiled from: DefaultHlsPlaylistTracker */
public final class b implements HlsPlaylistTracker, Loader.a<q<e>> {
    public static final HlsPlaylistTracker.a a = $$Lambda$I28oJP4eQLhOqYK9DueUaVnxjhM.INSTANCE;
    private final e b;
    private final g c;
    private final o d;
    private final IdentityHashMap<c.a, a> e = new IdentityHashMap<>();
    private final List<HlsPlaylistTracker.b> f = new ArrayList();
    private q.a<e> g;
    private n.a h;
    private Loader i;
    private Handler j;
    private HlsPlaylistTracker.c k;
    private c l;
    private c.a m;
    private d n;
    private boolean o;
    private long p = -9223372036854775807L;

    public b(e eVar, o oVar, g gVar) {
        this.b = eVar;
        this.c = gVar;
        this.d = oVar;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void a(Uri uri, n.a aVar, HlsPlaylistTracker.c cVar) {
        this.j = new Handler();
        this.h = aVar;
        this.k = cVar;
        q qVar = new q(this.b.a(4), uri, 4, this.c.a());
        com.google.android.exoplayer2.util.a.b(this.i == null);
        this.i = new Loader("DefaultHlsPlaylistTracker:MasterPlaylist");
        aVar.a(qVar.a, qVar.b, this.i.a(qVar, this, this.d.a(qVar.b)));
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void a() {
        this.m = null;
        this.n = null;
        this.l = null;
        this.p = -9223372036854775807L;
        this.i.d();
        this.i = null;
        for (a aVar : this.e.values()) {
            aVar.c();
        }
        this.j.removeCallbacksAndMessages(null);
        this.j = null;
        this.e.clear();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void a(HlsPlaylistTracker.b bVar) {
        this.f.add(bVar);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void b(HlsPlaylistTracker.b bVar) {
        this.f.remove(bVar);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public c b() {
        return this.l;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public d a(c.a aVar, boolean z) {
        d a2 = this.e.get(aVar).a();
        if (a2 != null && z) {
            d(aVar);
        }
        return a2;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public long c() {
        return this.p;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public boolean a(c.a aVar) {
        return this.e.get(aVar).b();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void d() throws IOException {
        Loader loader = this.i;
        if (loader != null) {
            loader.a();
        }
        c.a aVar = this.m;
        if (aVar != null) {
            b(aVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void b(c.a aVar) throws IOException {
        this.e.get(aVar).e();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public void c(c.a aVar) {
        this.e.get(aVar).d();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker
    public boolean e() {
        return this.o;
    }

    public void a(q<e> qVar, long j, long j2) {
        c cVar;
        e eVar = (e) qVar.c();
        boolean z = eVar instanceof d;
        if (z) {
            cVar = c.a(eVar.n);
        } else {
            cVar = (c) eVar;
        }
        this.l = cVar;
        this.g = this.c.a(cVar);
        this.m = cVar.b.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(cVar.b);
        arrayList.addAll(cVar.c);
        arrayList.addAll(cVar.d);
        a(arrayList);
        a aVar = this.e.get(this.m);
        if (z) {
            aVar.a((d) eVar, j2);
        } else {
            aVar.d();
        }
        this.h.a(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d());
    }

    public void a(q<e> qVar, long j, long j2, boolean z) {
        this.h.b(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d());
    }

    public Loader.b a(q<e> qVar, long j, long j2, IOException iOException, int i) {
        long b = this.d.b(qVar.b, j2, iOException, i);
        boolean z = b == -9223372036854775807L;
        this.h.a(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d(), iOException, z);
        if (z) {
            return Loader.d;
        }
        return Loader.a(false, b);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean f() {
        List<c.a> list = this.l.b;
        int size = list.size();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            a aVar = this.e.get(list.get(i));
            if (elapsedRealtime > aVar.i) {
                this.m = aVar.b;
                aVar.d();
                return true;
            }
        }
        return false;
    }

    private void d(c.a aVar) {
        if (aVar != this.m && this.l.b.contains(aVar)) {
            d dVar = this.n;
            if (dVar == null || !dVar.i) {
                this.m = aVar;
                this.e.get(this.m).d();
            }
        }
    }

    private void a(List<c.a> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            c.a aVar = list.get(i);
            this.e.put(aVar, new a(aVar));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(c.a aVar, d dVar) {
        if (aVar == this.m) {
            if (this.n == null) {
                this.o = !dVar.i;
                this.p = dVar.c;
            }
            this.n = dVar;
            this.k.a(dVar);
        }
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            this.f.get(i).h();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean a(c.a aVar, long j) {
        int size = this.f.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            z |= !this.f.get(i).a(aVar, j);
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private d a(d dVar, d dVar2) {
        if (!dVar2.a(dVar)) {
            return dVar2.i ? dVar.b() : dVar;
        }
        return dVar2.a(b(dVar, dVar2), c(dVar, dVar2));
    }

    private long b(d dVar, d dVar2) {
        if (dVar2.j) {
            return dVar2.c;
        }
        d dVar3 = this.n;
        long j = dVar3 != null ? dVar3.c : 0;
        if (dVar == null) {
            return j;
        }
        int size = dVar.l.size();
        d.a d = d(dVar, dVar2);
        if (d != null) {
            return dVar.c + d.f;
        }
        return ((long) size) == dVar2.f - dVar.f ? dVar.a() : j;
    }

    private int c(d dVar, d dVar2) {
        d.a d;
        if (dVar2.d) {
            return dVar2.e;
        }
        d dVar3 = this.n;
        int i = dVar3 != null ? dVar3.e : 0;
        return (dVar == null || (d = d(dVar, dVar2)) == null) ? i : (dVar.e + d.e) - dVar2.l.get(0).e;
    }

    private static d.a d(d dVar, d dVar2) {
        int i = (int) (dVar2.f - dVar.f);
        List<d.a> list = dVar.l;
        if (i < list.size()) {
            return list.get(i);
        }
        return null;
    }

    /* compiled from: DefaultHlsPlaylistTracker */
    /* access modifiers changed from: private */
    public final class a implements Loader.a<q<e>>, Runnable {
        private final c.a b;
        private final Loader c = new Loader("DefaultHlsPlaylistTracker:MediaPlaylist");
        private final q<e> d;
        private d e;
        private long f;
        private long g;
        private long h;
        private long i;
        private boolean j;
        private IOException k;

        public a(c.a aVar) {
            this.b = aVar;
            this.d = new q<>(b.this.b.a(4), y.a(b.this.l.n, aVar.a), 4, b.this.g);
        }

        public d a() {
            return this.e;
        }

        public boolean b() {
            if (this.e == null) {
                return false;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long max = Math.max(30000L, com.google.android.exoplayer2.c.a(this.e.m));
            if (this.e.i || this.e.a == 2 || this.e.a == 1 || this.f + max > elapsedRealtime) {
                return true;
            }
            return false;
        }

        public void c() {
            this.c.d();
        }

        public void d() {
            this.i = 0;
            if (!this.j && !this.c.b()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime < this.h) {
                    this.j = true;
                    b.this.j.postDelayed(this, this.h - elapsedRealtime);
                    return;
                }
                f();
            }
        }

        public void e() throws IOException {
            this.c.a();
            IOException iOException = this.k;
            if (iOException != null) {
                throw iOException;
            }
        }

        public void a(q<e> qVar, long j, long j2) {
            e eVar = (e) qVar.c();
            if (eVar instanceof d) {
                a((d) eVar, j2);
                b.this.h.a(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d());
                return;
            }
            this.k = new ParserException("Loaded playlist has unexpected type.");
        }

        public void a(q<e> qVar, long j, long j2, boolean z) {
            b.this.h.b(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d());
        }

        public Loader.b a(q<e> qVar, long j, long j2, IOException iOException, int i) {
            Loader.b bVar;
            long a = b.this.d.a(qVar.b, j2, iOException, i);
            boolean z = a != -9223372036854775807L;
            boolean z2 = b.this.a(this.b, a) || !z;
            if (z) {
                z2 |= a(a);
            }
            if (z2) {
                long b = b.this.d.b(qVar.b, j2, iOException, i);
                bVar = b != -9223372036854775807L ? Loader.a(false, b) : Loader.d;
            } else {
                bVar = Loader.c;
            }
            b.this.h.a(qVar.a, qVar.e(), qVar.f(), 4, j, j2, qVar.d(), iOException, !bVar.a());
            return bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.j = false;
            f();
        }

        private void f() {
            b.this.h.a(this.d.a, this.d.b, this.c.a(this.d, this, b.this.d.a(this.d.b)));
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(d dVar, long j) {
            d dVar2 = this.e;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f = elapsedRealtime;
            this.e = b.this.a(dVar2, dVar);
            d dVar3 = this.e;
            if (dVar3 != dVar2) {
                this.k = null;
                this.g = elapsedRealtime;
                b.this.a(this.b, dVar3);
            } else if (!dVar3.i) {
                if (dVar.f + ((long) dVar.l.size()) < this.e.f) {
                    this.k = new HlsPlaylistTracker.PlaylistResetException(this.b.a);
                    b.this.a(this.b, -9223372036854775807L);
                } else if (((double) (elapsedRealtime - this.g)) > ((double) com.google.android.exoplayer2.c.a(this.e.h)) * 3.5d) {
                    this.k = new HlsPlaylistTracker.PlaylistStuckException(this.b.a);
                    long a = b.this.d.a(4, j, this.k, 1);
                    b.this.a(this.b, a);
                    if (a != -9223372036854775807L) {
                        a(a);
                    }
                }
            }
            d dVar4 = this.e;
            this.h = elapsedRealtime + com.google.android.exoplayer2.c.a(dVar4 != dVar2 ? dVar4.h : dVar4.h / 2);
            if (this.b == b.this.m && !this.e.i) {
                d();
            }
        }

        private boolean a(long j) {
            this.i = SystemClock.elapsedRealtime() + j;
            return b.this.m == this.b && !b.this.f();
        }
    }
}
