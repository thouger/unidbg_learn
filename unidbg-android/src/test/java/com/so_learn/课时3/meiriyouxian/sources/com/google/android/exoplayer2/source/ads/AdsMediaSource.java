package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.source.ads.b;
import com.google.android.exoplayer2.source.g;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class AdsMediaSource extends com.google.android.exoplayer2.source.c<m.a> {
    private static final m.a a = new m.a(new Object());
    private final m b;
    private final c c;
    private final b d;
    private final b.a e;
    private final Handler f;
    private final Map<m, List<g>> g;
    private final z.a h;
    private b i;
    private z j;
    private Object k;
    private a l;
    private m[][] m;
    private z[][] n;

    public interface c {
        m b(Uri uri);
    }

    public static final class AdLoadException extends IOException {
        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        public static AdLoadException createForAd(Exception exc) {
            return new AdLoadException(0, exc);
        }

        public static AdLoadException createForAdGroup(Exception exc, int i) {
            return new AdLoadException(1, new IOException("Failed to load ad group " + i, exc));
        }

        public static AdLoadException createForAllAds(Exception exc) {
            return new AdLoadException(2, exc);
        }

        public static AdLoadException createForUnexpected(RuntimeException runtimeException) {
            return new AdLoadException(3, runtimeException);
        }

        private AdLoadException(int i, Exception exc) {
            super(exc);
            this.type = i;
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            com.google.android.exoplayer2.util.a.b(this.type == 3);
            return (RuntimeException) getCause();
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        super.a(sVar);
        b bVar = new b();
        this.i = bVar;
        a((AdsMediaSource) a, this.b);
        this.f.post(new $$Lambda$AdsMediaSource$690BcMxdEwPrc9QZfVFQohCKSGA(this, bVar));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(b bVar) {
        this.d.a(bVar, this.e);
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, com.google.android.exoplayer2.upstream.b bVar, long j) {
        if (this.l.b <= 0 || !aVar.a()) {
            g gVar = new g(this.b, aVar, bVar, j);
            gVar.a(aVar);
            return gVar;
        }
        int i = aVar.b;
        int i2 = aVar.c;
        Uri uri = this.l.d[i].b[i2];
        if (this.m[i].length <= i2) {
            m b2 = this.c.b(uri);
            m[][] mVarArr = this.m;
            if (i2 >= mVarArr[i].length) {
                int i3 = i2 + 1;
                mVarArr[i] = (m[]) Arrays.copyOf(mVarArr[i], i3);
                z[][] zVarArr = this.n;
                zVarArr[i] = (z[]) Arrays.copyOf(zVarArr[i], i3);
            }
            this.m[i][i2] = b2;
            this.g.put(b2, new ArrayList());
            a((AdsMediaSource) aVar, b2);
        }
        m mVar = this.m[i][i2];
        g gVar2 = new g(mVar, aVar, bVar, j);
        gVar2.a(new a(uri, i, i2));
        List<g> list = this.g.get(mVar);
        if (list == null) {
            gVar2.a(new m.a(this.n[i][i2].a(0), aVar.d));
        } else {
            list.add(gVar2);
        }
        return gVar2;
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        g gVar = (g) lVar;
        List<g> list = this.g.get(gVar.a);
        if (list != null) {
            list.remove(gVar);
        }
        gVar.f();
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a() {
        super.a();
        this.i.a();
        this.i = null;
        this.g.clear();
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = new m[0][];
        this.n = new z[0][];
        Handler handler = this.f;
        b bVar = this.d;
        bVar.getClass();
        handler.post(new $$Lambda$Y1x11VWsqTUgUtbveOumhC5zbo(bVar));
    }

    /* access modifiers changed from: protected */
    public void a(m.a aVar, m mVar, z zVar, Object obj) {
        if (aVar.a()) {
            a(mVar, aVar.b, aVar.c, zVar);
        } else {
            b(zVar, obj);
        }
    }

    /* access modifiers changed from: protected */
    public m.a a(m.a aVar, m.a aVar2) {
        return aVar.a() ? aVar : aVar2;
    }

    private void b(z zVar, Object obj) {
        boolean z = true;
        if (zVar.c() != 1) {
            z = false;
        }
        com.google.android.exoplayer2.util.a.a(z);
        this.j = zVar;
        this.k = obj;
        c();
    }

    private void a(m mVar, int i, int i2, z zVar) {
        boolean z = true;
        if (zVar.c() != 1) {
            z = false;
        }
        com.google.android.exoplayer2.util.a.a(z);
        this.n[i][i2] = zVar;
        List<g> remove = this.g.remove(mVar);
        if (remove != null) {
            Object a2 = zVar.a(0);
            for (int i3 = 0; i3 < remove.size(); i3++) {
                g gVar = remove.get(i3);
                gVar.a(new m.a(a2, gVar.b.d));
            }
        }
        c();
    }

    private void c() {
        a aVar = this.l;
        if (aVar != null && this.j != null) {
            this.l = aVar.a(a(this.n, this.h));
            a(this.l.b == 0 ? this.j : new c(this.j, this.l), this.k);
        }
    }

    private static long[][] a(z[][] zVarArr, z.a aVar) {
        long j;
        long[][] jArr = new long[zVarArr.length][];
        for (int i = 0; i < zVarArr.length; i++) {
            jArr[i] = new long[zVarArr[i].length];
            for (int i2 = 0; i2 < zVarArr[i].length; i2++) {
                long[] jArr2 = jArr[i];
                if (zVarArr[i][i2] == null) {
                    j = -9223372036854775807L;
                } else {
                    j = zVarArr[i][i2].a(0, aVar).a();
                }
                jArr2[i2] = j;
            }
        }
        return jArr;
    }

    /* access modifiers changed from: private */
    public final class b implements b.AbstractC0088b {
        private final Handler b = new Handler();
        private volatile boolean c;

        public b() {
        }

        public void a() {
            this.c = true;
            this.b.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: private */
    public final class a implements g.a {
        private final Uri b;
        private final int c;
        private final int d;

        public a(Uri uri, int i, int i2) {
            this.b = uri;
            this.c = i;
            this.d = i2;
        }

        @Override // com.google.android.exoplayer2.source.g.a
        public void a(m.a aVar, IOException iOException) {
            AdsMediaSource.this.a(aVar).a(new h(this.b), this.b, Collections.emptyMap(), 6, -1L, 0L, 0L, (IOException) AdLoadException.createForAd(iOException), true);
            AdsMediaSource.this.f.post(new $$Lambda$AdsMediaSource$a$femxGvSuZlaVvweOobFVUsrp2qg(this, iOException));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(IOException iOException) {
            AdsMediaSource.this.d.a(this.c, this.d, iOException);
        }
    }
}
