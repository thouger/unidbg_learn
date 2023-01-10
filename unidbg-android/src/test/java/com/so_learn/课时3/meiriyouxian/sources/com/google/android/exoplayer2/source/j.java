package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.e;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.i;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.n;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.s;
import java.io.IOException;

/* compiled from: ExtractorMediaSource */
public final class j extends a implements i.c {
    private final Uri a;
    private final f.a b;
    private final com.google.android.exoplayer2.extractor.j c;
    private final o d;
    private final String e;
    private final int f;
    private final Object g;
    private long h;
    private boolean i;
    private s j;

    @Override // com.google.android.exoplayer2.source.a
    public void a() {
    }

    @Override // com.google.android.exoplayer2.source.m
    public void b() throws IOException {
    }

    /* compiled from: ExtractorMediaSource */
    public static final class a implements AdsMediaSource.c {
        private final f.a a;
        private com.google.android.exoplayer2.extractor.j b;
        private String c;
        private Object d;
        private o e = new n();
        private int f = 1048576;
        private boolean g;

        public a(f.a aVar) {
            this.a = aVar;
        }

        public a a(com.google.android.exoplayer2.extractor.j jVar) {
            com.google.android.exoplayer2.util.a.b(!this.g);
            this.b = jVar;
            return this;
        }

        /* renamed from: a */
        public j b(Uri uri) {
            this.g = true;
            if (this.b == null) {
                this.b = new e();
            }
            return new j(uri, this.a, this.b, this.e, this.c, this.f, this.d);
        }
    }

    private j(Uri uri, f.a aVar, com.google.android.exoplayer2.extractor.j jVar, o oVar, String str, int i, Object obj) {
        this.a = uri;
        this.b = aVar;
        this.c = jVar;
        this.d = oVar;
        this.e = str;
        this.f = i;
        this.h = -9223372036854775807L;
        this.g = obj;
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        this.j = sVar;
        b(this.h, this.i);
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, b bVar, long j) {
        f a2 = this.b.a();
        s sVar = this.j;
        if (sVar != null) {
            a2.a(sVar);
        }
        return new i(this.a, a2, this.c.createExtractors(), this.d, a(aVar), this, bVar, this.e, this.f);
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        ((i) lVar).f();
    }

    @Override // com.google.android.exoplayer2.source.i.c
    public void a(long j, boolean z) {
        if (j == -9223372036854775807L) {
            j = this.h;
        }
        if (this.h != j || this.i != z) {
            b(j, z);
        }
    }

    private void b(long j, boolean z) {
        this.h = j;
        this.i = z;
        a(new t(this.h, this.i, false, this.g), (Object) null);
    }
}
