package com.google.android.exoplayer2;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import com.google.android.exoplayer2.a.a;
import com.google.android.exoplayer2.audio.b;
import com.google.android.exoplayer2.audio.d;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.d;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.text.i;
import com.google.android.exoplayer2.trackselection.g;
import com.google.android.exoplayer2.upstream.c;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.video.e;
import com.google.android.exoplayer2.video.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: SimpleExoPlayer */
public class y extends a implements f {
    private b A;
    private float B;
    private m C;
    private List<com.google.android.exoplayer2.text.a> D;
    private boolean E;
    protected final t[] b;
    private final h c;
    private final Handler d;
    private final a e;
    private final CopyOnWriteArraySet<e> f;
    private final CopyOnWriteArraySet<com.google.android.exoplayer2.audio.e> g;
    private final CopyOnWriteArraySet<i> h;
    private final CopyOnWriteArraySet<d> i;
    private final CopyOnWriteArraySet<f> j;
    private final CopyOnWriteArraySet<com.google.android.exoplayer2.audio.f> k;
    private final c l;
    private final com.google.android.exoplayer2.a.a m;
    private final com.google.android.exoplayer2.audio.d n;
    private Format o;
    private Format p;
    private Surface q;
    private boolean r;
    private int s;
    private SurfaceHolder t;
    private TextureView u;
    private int v;
    private int w;
    private com.google.android.exoplayer2.b.d x;
    private com.google.android.exoplayer2.b.d y;
    private int z;

    protected y(Context context, w wVar, g gVar, l lVar, com.google.android.exoplayer2.drm.b<com.google.android.exoplayer2.drm.e> bVar, c cVar, a.C0078a aVar, Looper looper) {
        this(context, wVar, gVar, lVar, bVar, cVar, aVar, com.google.android.exoplayer2.util.b.a, looper);
    }

    protected y(Context context, w wVar, g gVar, l lVar, com.google.android.exoplayer2.drm.b<com.google.android.exoplayer2.drm.e> bVar, c cVar, a.C0078a aVar, com.google.android.exoplayer2.util.b bVar2, Looper looper) {
        this.l = cVar;
        this.e = new a();
        this.f = new CopyOnWriteArraySet<>();
        this.g = new CopyOnWriteArraySet<>();
        this.h = new CopyOnWriteArraySet<>();
        this.i = new CopyOnWriteArraySet<>();
        this.j = new CopyOnWriteArraySet<>();
        this.k = new CopyOnWriteArraySet<>();
        this.d = new Handler(looper);
        Handler handler = this.d;
        a aVar2 = this.e;
        this.b = wVar.a(handler, aVar2, aVar2, aVar2, aVar2, bVar);
        this.B = 1.0f;
        this.z = 0;
        this.A = b.a;
        this.s = 1;
        this.D = Collections.emptyList();
        this.c = new h(this.b, gVar, lVar, cVar, bVar2, looper);
        this.m = aVar.a(this.c, bVar2);
        a((r.a) this.m);
        this.j.add(this.m);
        this.f.add(this.m);
        this.k.add(this.m);
        this.g.add(this.m);
        a((d) this.m);
        cVar.a(this.d, this.m);
        if (bVar instanceof DefaultDrmSessionManager) {
            ((DefaultDrmSessionManager) bVar).a(this.d, this.m);
        }
        this.n = new com.google.android.exoplayer2.audio.d(context, this.e);
    }

    public void a(Surface surface) {
        s();
        m();
        int i = 0;
        a(surface, false);
        if (surface != null) {
            i = -1;
        }
        a(i, i);
    }

    public void a(b bVar) {
        a(bVar, false);
    }

    public void a(b bVar, boolean z) {
        s();
        if (!z.a(this.A, bVar)) {
            this.A = bVar;
            t[] tVarArr = this.b;
            for (t tVar : tVarArr) {
                if (tVar.a() == 1) {
                    this.c.a(tVar).a(3).a(bVar).i();
                }
            }
            Iterator<com.google.android.exoplayer2.audio.e> it2 = this.g.iterator();
            while (it2.hasNext()) {
                it2.next().a(bVar);
            }
        }
        com.google.android.exoplayer2.audio.d dVar = this.n;
        if (!z) {
            bVar = null;
        }
        a(f(), dVar.a(bVar, f(), e()));
    }

    public void a(float f) {
        s();
        float a2 = z.a(f, 0.0f, 1.0f);
        if (this.B != a2) {
            this.B = a2;
            q();
            Iterator<com.google.android.exoplayer2.audio.e> it2 = this.g.iterator();
            while (it2.hasNext()) {
                it2.next().a(a2);
            }
        }
    }

    @Deprecated
    public void a(int i) {
        int f = z.f(i);
        a(new b.a().b(f).a(z.g(i)).a());
    }

    public Format c() {
        return this.o;
    }

    public void a(d dVar) {
        this.i.add(dVar);
    }

    public Looper d() {
        return this.c.c();
    }

    public void a(r.a aVar) {
        s();
        this.c.a(aVar);
    }

    public int e() {
        s();
        return this.c.d();
    }

    public void a(m mVar) {
        a(mVar, true, true);
    }

    public void a(m mVar, boolean z, boolean z2) {
        s();
        m mVar2 = this.C;
        if (mVar2 != null) {
            mVar2.a(this.m);
            this.m.c();
        }
        this.C = mVar;
        mVar.a(this.d, this.m);
        a(f(), this.n.a(f()));
        this.c.a(mVar, z, z2);
    }

    public void b(boolean z) {
        s();
        a(z, this.n.a(z, e()));
    }

    public boolean f() {
        s();
        return this.c.e();
    }

    public void b(int i) {
        s();
        this.c.a(i);
    }

    @Override // com.google.android.exoplayer2.r
    public void a(int i, long j) {
        s();
        this.m.b();
        this.c.a(i, j);
    }

    @Override // com.google.android.exoplayer2.r
    public void a(boolean z) {
        s();
        this.c.a(z);
        m mVar = this.C;
        if (mVar != null) {
            mVar.a(this.m);
            this.m.c();
            if (z) {
                this.C = null;
            }
        }
        this.n.b();
        this.D = Collections.emptyList();
    }

    public void g() {
        this.n.b();
        this.c.f();
        m();
        Surface surface = this.q;
        if (surface != null) {
            if (this.r) {
                surface.release();
            }
            this.q = null;
        }
        m mVar = this.C;
        if (mVar != null) {
            mVar.a(this.m);
            this.C = null;
        }
        this.l.a(this.m);
        this.D = Collections.emptyList();
    }

    @Override // com.google.android.exoplayer2.r
    public z r() {
        s();
        return this.c.r();
    }

    @Override // com.google.android.exoplayer2.r
    public int h() {
        s();
        return this.c.h();
    }

    public long i() {
        s();
        return this.c.i();
    }

    @Override // com.google.android.exoplayer2.r
    public long j() {
        s();
        return this.c.j();
    }

    public long k() {
        s();
        return this.c.k();
    }

    @Override // com.google.android.exoplayer2.r
    public long l() {
        s();
        return this.c.l();
    }

    @Override // com.google.android.exoplayer2.r
    public int n() {
        s();
        return this.c.n();
    }

    @Override // com.google.android.exoplayer2.r
    public int o() {
        s();
        return this.c.o();
    }

    @Override // com.google.android.exoplayer2.r
    public long p() {
        s();
        return this.c.p();
    }

    private void m() {
        TextureView textureView = this.u;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != this.e) {
                com.google.android.exoplayer2.util.i.c("SimpleExoPlayer", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.u.setSurfaceTextureListener(null);
            }
            this.u = null;
        }
        SurfaceHolder surfaceHolder = this.t;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.e);
            this.t = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Surface surface, boolean z) {
        ArrayList<s> arrayList = new ArrayList();
        t[] tVarArr = this.b;
        for (t tVar : tVarArr) {
            if (tVar.a() == 2) {
                arrayList.add(this.c.a(tVar).a(1).a(surface).i());
            }
        }
        Surface surface2 = this.q;
        if (!(surface2 == null || surface2 == surface)) {
            try {
                for (s sVar : arrayList) {
                    sVar.k();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (this.r) {
                this.q.release();
            }
        }
        this.q = surface;
        this.r = z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, int i2) {
        if (i != this.v || i2 != this.w) {
            this.v = i;
            this.w = i2;
            Iterator<e> it2 = this.f.iterator();
            while (it2.hasNext()) {
                it2.next().a(i, i2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void q() {
        float a2 = this.B * this.n.a();
        t[] tVarArr = this.b;
        for (t tVar : tVarArr) {
            if (tVar.a() == 1) {
                this.c.a(tVar).a(2).a(Float.valueOf(a2)).i();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(boolean z, int i) {
        h hVar = this.c;
        boolean z2 = false;
        boolean z3 = z && i != -1;
        if (i != 1) {
            z2 = true;
        }
        hVar.a(z3, z2);
    }

    private void s() {
        if (Looper.myLooper() != d()) {
            com.google.android.exoplayer2.util.i.a("SimpleExoPlayer", "Player is accessed on the wrong thread. See https://google.github.io/ExoPlayer/faqs.html#what-do-player-is-accessed-on-the-wrong-thread-warnings-mean", this.E ? null : new IllegalStateException());
            this.E = true;
        }
    }

    /* compiled from: SimpleExoPlayer */
    /* access modifiers changed from: private */
    public final class a implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener, d.b, com.google.android.exoplayer2.audio.f, com.google.android.exoplayer2.metadata.d, i, f {
        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private a() {
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(com.google.android.exoplayer2.b.d dVar) {
            y.this.x = dVar;
            Iterator it2 = y.this.j.iterator();
            while (it2.hasNext()) {
                ((f) it2.next()).a(dVar);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(String str, long j, long j2) {
            Iterator it2 = y.this.j.iterator();
            while (it2.hasNext()) {
                ((f) it2.next()).a(str, j, j2);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(Format format) {
            y.this.o = format;
            Iterator it2 = y.this.j.iterator();
            while (it2.hasNext()) {
                ((f) it2.next()).a(format);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(int i, long j) {
            Iterator it2 = y.this.j.iterator();
            while (it2.hasNext()) {
                ((f) it2.next()).a(i, j);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(int i, int i2, int i3, float f) {
            Iterator it2 = y.this.f.iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                if (!y.this.j.contains(eVar)) {
                    eVar.a(i, i2, i3, f);
                }
            }
            Iterator it3 = y.this.j.iterator();
            while (it3.hasNext()) {
                ((f) it3.next()).a(i, i2, i3, f);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void a(Surface surface) {
            if (y.this.q == surface) {
                Iterator it2 = y.this.f.iterator();
                while (it2.hasNext()) {
                    ((e) it2.next()).d();
                }
            }
            Iterator it3 = y.this.j.iterator();
            while (it3.hasNext()) {
                ((f) it3.next()).a(surface);
            }
        }

        @Override // com.google.android.exoplayer2.video.f
        public void b(com.google.android.exoplayer2.b.d dVar) {
            Iterator it2 = y.this.j.iterator();
            while (it2.hasNext()) {
                ((f) it2.next()).b(dVar);
            }
            y.this.o = null;
            y.this.x = null;
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void c(com.google.android.exoplayer2.b.d dVar) {
            y.this.y = dVar;
            Iterator it2 = y.this.k.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.audio.f) it2.next()).c(dVar);
            }
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void a(int i) {
            if (y.this.z != i) {
                y.this.z = i;
                Iterator it2 = y.this.g.iterator();
                while (it2.hasNext()) {
                    com.google.android.exoplayer2.audio.e eVar = (com.google.android.exoplayer2.audio.e) it2.next();
                    if (!y.this.k.contains(eVar)) {
                        eVar.a(i);
                    }
                }
                Iterator it3 = y.this.k.iterator();
                while (it3.hasNext()) {
                    ((com.google.android.exoplayer2.audio.f) it3.next()).a(i);
                }
            }
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void b(String str, long j, long j2) {
            Iterator it2 = y.this.k.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.audio.f) it2.next()).b(str, j, j2);
            }
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void b(Format format) {
            y.this.p = format;
            Iterator it2 = y.this.k.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.audio.f) it2.next()).b(format);
            }
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void a(int i, long j, long j2) {
            Iterator it2 = y.this.k.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.audio.f) it2.next()).a(i, j, j2);
            }
        }

        @Override // com.google.android.exoplayer2.audio.f
        public void d(com.google.android.exoplayer2.b.d dVar) {
            Iterator it2 = y.this.k.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.audio.f) it2.next()).d(dVar);
            }
            y.this.p = null;
            y.this.y = null;
            y.this.z = 0;
        }

        @Override // com.google.android.exoplayer2.text.i
        public void a(List<com.google.android.exoplayer2.text.a> list) {
            y.this.D = list;
            Iterator it2 = y.this.h.iterator();
            while (it2.hasNext()) {
                ((i) it2.next()).a(list);
            }
        }

        @Override // com.google.android.exoplayer2.metadata.d
        public void a(Metadata metadata) {
            Iterator it2 = y.this.i.iterator();
            while (it2.hasNext()) {
                ((com.google.android.exoplayer2.metadata.d) it2.next()).a(metadata);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            y.this.a(surfaceHolder.getSurface(), false);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            y.this.a(i2, i3);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            y.this.a((Surface) null, false);
            y.this.a(0, 0);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            y.this.a(new Surface(surfaceTexture), true);
            y.this.a(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            y.this.a(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            y.this.a((Surface) null, true);
            y.this.a(0, 0);
            return true;
        }

        @Override // com.google.android.exoplayer2.audio.d.b
        public void a(float f) {
            y.this.q();
        }

        @Override // com.google.android.exoplayer2.audio.d.b
        public void b(int i) {
            y yVar = y.this;
            yVar.a(yVar.f(), i);
        }
    }
}
