package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.b;
import com.google.android.exoplayer2.source.hls.playlist.d;
import com.google.android.exoplayer2.source.hls.playlist.g;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.t;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.n;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.s;
import java.io.IOException;
import java.util.List;

/* compiled from: HlsMediaSource */
public final class j extends com.google.android.exoplayer2.source.a implements HlsPlaylistTracker.c {
    private final f a;
    private final Uri b;
    private final e c;
    private final e d;
    private final o e;
    private final boolean f;
    private final HlsPlaylistTracker g;
    private final Object h;
    private s i;

    static {
        com.google.android.exoplayer2.j.a("goog.exo.hls");
    }

    /* compiled from: HlsMediaSource */
    public static final class a implements AdsMediaSource.c {
        private final e a;
        private f b;
        private g c;
        private HlsPlaylistTracker.a d;
        private e e;
        private o f;
        private boolean g;
        private boolean h;
        private Object i;

        public a(f.a aVar) {
            this(new b(aVar));
        }

        public a(e eVar) {
            this.a = (e) com.google.android.exoplayer2.util.a.a(eVar);
            this.c = new com.google.android.exoplayer2.source.hls.playlist.a();
            this.d = b.a;
            this.b = f.a;
            this.f = new n();
            this.e = new com.google.android.exoplayer2.source.f();
        }

        /* renamed from: a */
        public j b(Uri uri) {
            this.h = true;
            e eVar = this.a;
            f fVar = this.b;
            e eVar2 = this.e;
            o oVar = this.f;
            return new j(uri, eVar, fVar, eVar2, oVar, this.d.createTracker(eVar, oVar, this.c), this.g, this.i);
        }
    }

    private j(Uri uri, e eVar, f fVar, e eVar2, o oVar, HlsPlaylistTracker hlsPlaylistTracker, boolean z, Object obj) {
        this.b = uri;
        this.c = eVar;
        this.a = fVar;
        this.d = eVar2;
        this.e = oVar;
        this.g = hlsPlaylistTracker;
        this.f = z;
        this.h = obj;
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        this.i = sVar;
        this.g.a(this.b, a((m.a) null), this);
    }

    @Override // com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        this.g.d();
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, com.google.android.exoplayer2.upstream.b bVar, long j) {
        return new i(this.a, this.g, this.c, this.i, this.e, a(aVar), bVar, this.d, this.f);
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        ((i) lVar).f();
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a() {
        this.g.a();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.c
    public void a(d dVar) {
        t tVar;
        long j;
        long j2;
        long a2 = dVar.j ? c.a(dVar.c) : -9223372036854775807L;
        long j3 = (dVar.a == 2 || dVar.a == 1) ? a2 : -9223372036854775807L;
        long j4 = dVar.b;
        if (this.g.e()) {
            long c = dVar.c - this.g.c();
            long j5 = dVar.i ? c + dVar.m : -9223372036854775807L;
            List<d.a> list = dVar.l;
            if (j4 == -9223372036854775807L) {
                if (list.isEmpty()) {
                    j2 = 0;
                } else {
                    j2 = list.get(Math.max(0, list.size() - 3)).f;
                }
                j = j2;
            } else {
                j = j4;
            }
            tVar = new t(j3, a2, j5, dVar.m, c, j, true, !dVar.i, this.h);
        } else {
            tVar = new t(j3, a2, dVar.m, dVar.m, 0, j4 == -9223372036854775807L ? 0 : j4, true, false, this.h);
        }
        a(tVar, new g(this.g.b(), dVar));
    }
}
