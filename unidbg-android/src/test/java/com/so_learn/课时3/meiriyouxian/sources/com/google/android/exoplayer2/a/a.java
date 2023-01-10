package com.google.android.exoplayer2.a;

import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.a.b;
import com.google.android.exoplayer2.audio.e;
import com.google.android.exoplayer2.audio.f;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.d;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.c;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: AnalyticsCollector */
public class a implements e, f, com.google.android.exoplayer2.drm.a, d, r.a, n, c.a, com.google.android.exoplayer2.video.e, com.google.android.exoplayer2.video.f {
    private final CopyOnWriteArraySet<b> a;
    private final com.google.android.exoplayer2.util.b b;
    private final z.b c;
    private final c d;
    private r e;

    @Override // com.google.android.exoplayer2.video.e
    public final void d() {
    }

    /* compiled from: AnalyticsCollector */
    /* renamed from: com.google.android.exoplayer2.a.a$a  reason: collision with other inner class name */
    public static class C0078a {
        public a a(r rVar, com.google.android.exoplayer2.util.b bVar) {
            return new a(rVar, bVar);
        }
    }

    protected a(r rVar, com.google.android.exoplayer2.util.b bVar) {
        if (rVar != null) {
            this.e = rVar;
        }
        this.b = (com.google.android.exoplayer2.util.b) com.google.android.exoplayer2.util.a.a(bVar);
        this.a = new CopyOnWriteArraySet<>();
        this.d = new c();
        this.c = new z.b();
    }

    public final void b() {
        if (!this.d.e()) {
            b.a j = j();
            this.d.f();
            Iterator<b> it2 = this.a.iterator();
            while (it2.hasNext()) {
                it2.next().a(j);
            }
        }
    }

    public final void c() {
        for (b bVar : new ArrayList(this.d.a)) {
            b(bVar.c, bVar.a);
        }
    }

    @Override // com.google.android.exoplayer2.metadata.d
    public final void a(Metadata metadata) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, metadata);
        }
    }

    @Override // com.google.android.exoplayer2.audio.f
    public final void c(com.google.android.exoplayer2.b.d dVar) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, 1, dVar);
        }
    }

    @Override // com.google.android.exoplayer2.audio.f
    public final void b(String str, long j, long j2) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, 1, str, j2);
        }
    }

    @Override // com.google.android.exoplayer2.audio.f
    public final void b(Format format) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, 1, format);
        }
    }

    @Override // com.google.android.exoplayer2.audio.f
    public final void a(int i, long j, long j2) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(k, i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.audio.f
    public final void d(com.google.android.exoplayer2.b.d dVar) {
        b.a i = i();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(i, 1, dVar);
        }
    }

    @Override // com.google.android.exoplayer2.audio.e, com.google.android.exoplayer2.audio.f
    public final void a(int i) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().d(k, i);
        }
    }

    @Override // com.google.android.exoplayer2.audio.e
    public void a(com.google.android.exoplayer2.audio.b bVar) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, bVar);
        }
    }

    @Override // com.google.android.exoplayer2.audio.e
    public void a(float f) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, f);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void a(com.google.android.exoplayer2.b.d dVar) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, 2, dVar);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void a(String str, long j, long j2) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, 2, str, j2);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void a(Format format) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, 2, format);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void a(int i, long j) {
        b.a i2 = i();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(i2, i, j);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void b(com.google.android.exoplayer2.b.d dVar) {
        b.a i = i();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(i, 2, dVar);
        }
    }

    @Override // com.google.android.exoplayer2.video.f
    public final void a(Surface surface) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, surface);
        }
    }

    @Override // com.google.android.exoplayer2.video.e, com.google.android.exoplayer2.video.f
    public final void a(int i, int i2, int i3, float f) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, i, i2, i3, f);
        }
    }

    @Override // com.google.android.exoplayer2.video.e
    public void a(int i, int i2) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, i, i2);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void a(int i, m.a aVar) {
        this.d.a(i, aVar);
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().c(d);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void b(int i, m.a aVar) {
        b.a d = d(i, aVar);
        if (this.d.b(aVar)) {
            Iterator<b> it2 = this.a.iterator();
            while (it2.hasNext()) {
                it2.next().d(d);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void a(int i, m.a aVar, n.b bVar, n.c cVar) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(d, bVar, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void b(int i, m.a aVar, n.b bVar, n.c cVar) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(d, bVar, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void c(int i, m.a aVar, n.b bVar, n.c cVar) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().c(d, bVar, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void a(int i, m.a aVar, n.b bVar, n.c cVar, IOException iOException, boolean z) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(d, bVar, cVar, iOException, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void c(int i, m.a aVar) {
        this.d.c(aVar);
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().e(d);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void a(int i, m.a aVar, n.c cVar) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(d, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.source.n
    public final void b(int i, m.a aVar, n.c cVar) {
        b.a d = d(i, aVar);
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(d, cVar);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a(z zVar, Object obj, int i) {
        this.d.a(zVar);
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, i);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a(TrackGroupArray trackGroupArray, com.google.android.exoplayer2.trackselection.f fVar) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, trackGroupArray, fVar);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a(boolean z) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void onPlayerStateChanged(boolean z, int i) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, z, i);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a_(int i) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().c(j, i);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
        b.a aVar;
        if (exoPlaybackException.type == 0) {
            aVar = l();
        } else {
            aVar = j();
        }
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(aVar, exoPlaybackException);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void b(int i) {
        this.d.b(i);
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().b(j, i);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a(q qVar) {
        b.a j = j();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(j, qVar);
        }
    }

    @Override // com.google.android.exoplayer2.r.a
    public final void a() {
        if (this.d.e()) {
            this.d.g();
            b.a j = j();
            Iterator<b> it2 = this.a.iterator();
            while (it2.hasNext()) {
                it2.next().b(j);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.c.a
    public final void b(int i, long j, long j2) {
        b.a l = l();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(l, i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.drm.a
    public final void e() {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().f(k);
        }
    }

    @Override // com.google.android.exoplayer2.drm.a
    public final void f() {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().g(k);
        }
    }

    @Override // com.google.android.exoplayer2.drm.a
    public final void a(Exception exc) {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(k, exc);
        }
    }

    @Override // com.google.android.exoplayer2.drm.a
    public final void g() {
        b.a k = k();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().h(k);
        }
    }

    @Override // com.google.android.exoplayer2.drm.a
    public final void h() {
        b.a i = i();
        Iterator<b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().i(i);
        }
    }

    /* access modifiers changed from: protected */
    @RequiresNonNull({"player"})
    public b.a a(z zVar, int i, m.a aVar) {
        if (zVar.a()) {
            aVar = null;
        }
        long a = this.b.a();
        boolean z = true;
        boolean z2 = zVar == this.e.r() && i == this.e.h();
        long j = 0;
        if (aVar != null && aVar.a()) {
            if (!(z2 && this.e.n() == aVar.b && this.e.o() == aVar.c)) {
                z = false;
            }
            if (z) {
                j = this.e.j();
            }
        } else if (z2) {
            j = this.e.p();
        } else if (!zVar.a()) {
            j = zVar.a(i, this.c).a();
        }
        return new b.a(a, zVar, i, aVar, j, this.e.j(), this.e.l());
    }

    private b.a a(b bVar) {
        com.google.android.exoplayer2.util.a.a(this.e);
        if (bVar == null) {
            int h = this.e.h();
            b a = this.d.a(h);
            if (a == null) {
                z r = this.e.r();
                if (!(h < r.b())) {
                    r = z.a;
                }
                return a(r, h, (m.a) null);
            }
            bVar = a;
        }
        return a(bVar.b, bVar.c, bVar.a);
    }

    private b.a i() {
        return a(this.d.b());
    }

    private b.a j() {
        return a(this.d.a());
    }

    private b.a k() {
        return a(this.d.c());
    }

    private b.a l() {
        return a(this.d.d());
    }

    private b.a d(int i, m.a aVar) {
        com.google.android.exoplayer2.util.a.a(this.e);
        if (aVar != null) {
            b a = this.d.a(aVar);
            if (a != null) {
                return a(a);
            }
            return a(z.a, i, aVar);
        }
        z r = this.e.r();
        if (!(i < r.b())) {
            r = z.a;
        }
        return a(r, i, (m.a) null);
    }

    /* compiled from: AnalyticsCollector */
    /* access modifiers changed from: private */
    public static final class c {
        private final ArrayList<b> a = new ArrayList<>();
        private final HashMap<m.a, b> b = new HashMap<>();
        private final z.a c = new z.a();
        private b d;
        private b e;
        private z f = z.a;
        private boolean g;

        public b a() {
            if (this.a.isEmpty() || this.f.a() || this.g) {
                return null;
            }
            return this.a.get(0);
        }

        public b b() {
            return this.d;
        }

        public b c() {
            return this.e;
        }

        public b d() {
            if (this.a.isEmpty()) {
                return null;
            }
            ArrayList<b> arrayList = this.a;
            return arrayList.get(arrayList.size() - 1);
        }

        public b a(m.a aVar) {
            return this.b.get(aVar);
        }

        public boolean e() {
            return this.g;
        }

        public b a(int i) {
            b bVar = null;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                b bVar2 = this.a.get(i2);
                int a = this.f.a(bVar2.a.a);
                if (a != -1 && this.f.a(a, this.c).c == i) {
                    if (bVar != null) {
                        return null;
                    }
                    bVar = bVar2;
                }
            }
            return bVar;
        }

        public void b(int i) {
            h();
        }

        public void a(z zVar) {
            for (int i = 0; i < this.a.size(); i++) {
                b a = a(this.a.get(i), zVar);
                this.a.set(i, a);
                this.b.put(a.a, a);
            }
            b bVar = this.e;
            if (bVar != null) {
                this.e = a(bVar, zVar);
            }
            this.f = zVar;
            h();
        }

        public void f() {
            this.g = true;
        }

        public void g() {
            this.g = false;
            h();
        }

        public void a(int i, m.a aVar) {
            b bVar = new b(aVar, this.f.a(aVar.a) != -1 ? this.f : z.a, i);
            this.a.add(bVar);
            this.b.put(aVar, bVar);
            if (this.a.size() == 1 && !this.f.a()) {
                h();
            }
        }

        public boolean b(m.a aVar) {
            b remove = this.b.remove(aVar);
            if (remove == null) {
                return false;
            }
            this.a.remove(remove);
            b bVar = this.e;
            if (bVar == null || !aVar.equals(bVar.a)) {
                return true;
            }
            this.e = this.a.isEmpty() ? null : this.a.get(0);
            return true;
        }

        public void c(m.a aVar) {
            this.e = this.b.get(aVar);
        }

        private void h() {
            if (!this.a.isEmpty()) {
                this.d = this.a.get(0);
            }
        }

        private b a(b bVar, z zVar) {
            int a = zVar.a(bVar.a.a);
            if (a == -1) {
                return bVar;
            }
            return new b(bVar.a, zVar, zVar.a(a, this.c).c);
        }
    }

    /* compiled from: AnalyticsCollector */
    /* access modifiers changed from: private */
    public static final class b {
        public final m.a a;
        public final z b;
        public final int c;

        public b(m.a aVar, z zVar, int i) {
            this.a = aVar;
            this.b = zVar;
            this.c = i;
        }
    }
}
