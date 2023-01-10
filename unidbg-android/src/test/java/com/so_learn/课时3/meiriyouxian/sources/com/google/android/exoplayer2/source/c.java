package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.HashMap;

/* compiled from: CompositeMediaSource */
public abstract class c<T> extends a {
    private final HashMap<T, b> a;
    private Handler b;
    private s c;

    /* access modifiers changed from: protected */
    public int a(T t, int i) {
        return i;
    }

    /* access modifiers changed from: protected */
    public long a(T t, long j) {
        return j;
    }

    /* access modifiers changed from: protected */
    public m.a a(T t, m.a aVar) {
        return aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void b(T t, m mVar, z zVar, Object obj);

    @Override // com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        this.c = sVar;
        this.b = new Handler();
    }

    @Override // com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        for (b bVar : this.a.values()) {
            bVar.a.b();
        }
    }

    @Override // com.google.android.exoplayer2.source.a
    public void a() {
        for (b bVar : this.a.values()) {
            bVar.a.a(bVar.b);
            bVar.a.a(bVar.c);
        }
        this.a.clear();
    }

    /* access modifiers changed from: protected */
    public final void a(T t, m mVar) {
        com.google.android.exoplayer2.util.a.a(!this.a.containsKey(t));
        $$Lambda$c$TtMWxuixaJEHobZSV3VGp3IOCo r0 = new $$Lambda$c$TtMWxuixaJEHobZSV3VGp3IOCo(this, t);
        a aVar = new a(t);
        this.a.put(t, new b(mVar, r0, aVar));
        mVar.a((Handler) com.google.android.exoplayer2.util.a.a(this.b), aVar);
        mVar.a(r0, this.c);
    }

    /* compiled from: CompositeMediaSource */
    private static final class b {
        public final m a;
        public final m.b b;
        public final n c;

        public b(m mVar, m.b bVar, n nVar) {
            this.a = mVar;
            this.b = bVar;
            this.c = nVar;
        }
    }

    /* compiled from: CompositeMediaSource */
    private final class a implements n {
        private final T b;
        private n.a c;

        public a(T t) {
            this.c = c.this.a((m.a) null);
            this.b = t;
        }

        @Override // com.google.android.exoplayer2.source.n
        public void a(int i, m.a aVar) {
            if (d(i, aVar)) {
                this.c.a();
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void b(int i, m.a aVar) {
            if (d(i, aVar)) {
                this.c.b();
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void a(int i, m.a aVar, n.b bVar, n.c cVar) {
            if (d(i, aVar)) {
                this.c.a(bVar, a(cVar));
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void b(int i, m.a aVar, n.b bVar, n.c cVar) {
            if (d(i, aVar)) {
                this.c.b(bVar, a(cVar));
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void c(int i, m.a aVar, n.b bVar, n.c cVar) {
            if (d(i, aVar)) {
                this.c.c(bVar, a(cVar));
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void a(int i, m.a aVar, n.b bVar, n.c cVar, IOException iOException, boolean z) {
            if (d(i, aVar)) {
                this.c.a(bVar, a(cVar), iOException, z);
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void c(int i, m.a aVar) {
            if (d(i, aVar)) {
                this.c.c();
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void a(int i, m.a aVar, n.c cVar) {
            if (d(i, aVar)) {
                this.c.a(a(cVar));
            }
        }

        @Override // com.google.android.exoplayer2.source.n
        public void b(int i, m.a aVar, n.c cVar) {
            if (d(i, aVar)) {
                this.c.b(a(cVar));
            }
        }

        private boolean d(int i, m.a aVar) {
            m.a aVar2;
            if (aVar != null) {
                aVar2 = c.this.a((c) this.b, aVar);
                if (aVar2 == null) {
                    return false;
                }
            } else {
                aVar2 = null;
            }
            int a = c.this.a((c) this.b, i);
            if (this.c.a == a && com.google.android.exoplayer2.util.z.a(this.c.b, aVar2)) {
                return true;
            }
            this.c = c.this.a(a, aVar2, 0);
            return true;
        }

        private n.c a(n.c cVar) {
            long a = c.this.a((c) this.b, cVar.f);
            long a2 = c.this.a((c) this.b, cVar.g);
            if (a == cVar.f && a2 == cVar.g) {
                return cVar;
            }
            return new n.c(cVar.a, cVar.b, cVar.c, cVar.d, cVar.e, a, a2);
        }
    }
}
