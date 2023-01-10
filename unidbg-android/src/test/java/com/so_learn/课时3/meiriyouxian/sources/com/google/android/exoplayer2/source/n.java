package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.upstream.h;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MediaSourceEventListener */
public interface n {
    void a(int i, m.a aVar);

    void a(int i, m.a aVar, b bVar, c cVar);

    void a(int i, m.a aVar, b bVar, c cVar, IOException iOException, boolean z);

    void a(int i, m.a aVar, c cVar);

    void b(int i, m.a aVar);

    void b(int i, m.a aVar, b bVar, c cVar);

    void b(int i, m.a aVar, c cVar);

    void c(int i, m.a aVar);

    void c(int i, m.a aVar, b bVar, c cVar);

    /* compiled from: MediaSourceEventListener */
    public static final class b {
        public final h a;
        public final Uri b;
        public final Map<String, List<String>> c;
        public final long d;
        public final long e;
        public final long f;

        public b(h hVar, Uri uri, Map<String, List<String>> map, long j, long j2, long j3) {
            this.a = hVar;
            this.b = uri;
            this.c = map;
            this.d = j;
            this.e = j2;
            this.f = j3;
        }
    }

    /* compiled from: MediaSourceEventListener */
    public static final class c {
        public final int a;
        public final int b;
        public final Format c;
        public final int d;
        public final Object e;
        public final long f;
        public final long g;

        public c(int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            this.a = i;
            this.b = i2;
            this.c = format;
            this.d = i3;
            this.e = obj;
            this.f = j;
            this.g = j2;
        }
    }

    /* compiled from: MediaSourceEventListener */
    public static final class a {
        public final int a;
        public final m.a b;
        private final CopyOnWriteArrayList<C0092a> c;
        private final long d;

        public a() {
            this(new CopyOnWriteArrayList(), 0, null, 0);
        }

        private a(CopyOnWriteArrayList<C0092a> copyOnWriteArrayList, int i, m.a aVar, long j) {
            this.c = copyOnWriteArrayList;
            this.a = i;
            this.b = aVar;
            this.d = j;
        }

        public a a(int i, m.a aVar, long j) {
            return new a(this.c, i, aVar, j);
        }

        public void a(Handler handler, n nVar) {
            com.google.android.exoplayer2.util.a.a((handler == null || nVar == null) ? false : true);
            this.c.add(new C0092a(handler, nVar));
        }

        public void a(n nVar) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                if (next.b == nVar) {
                    this.c.remove(next);
                }
            }
        }

        public void a() {
            m.a aVar = (m.a) com.google.android.exoplayer2.util.a.a(this.b);
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$luQFFSAaxmBYvETUNmiql7zDndA(this, next.b, aVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c(n nVar, m.a aVar) {
            nVar.a(this.a, aVar);
        }

        public void b() {
            m.a aVar = (m.a) com.google.android.exoplayer2.util.a.a(this.b);
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$kiSa9BYdX2MTinBFnOLDfoc2aQ(this, next.b, aVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(n nVar, m.a aVar) {
            nVar.b(this.a, aVar);
        }

        public void a(h hVar, int i, long j) {
            a(hVar, i, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L, j);
        }

        public void a(h hVar, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
            a(new b(hVar, hVar.a, Collections.emptyMap(), j3, 0, 0), new c(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public void a(b bVar, c cVar) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$EhXPTEvQ1Vtx7I9UyRo88GOp_8(this, next.b, bVar, cVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c(n nVar, b bVar, c cVar) {
            nVar.a(this.a, this.b, bVar, cVar);
        }

        public void a(h hVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
            a(hVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
        }

        public void a(h hVar, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            b(new b(hVar, uri, map, j3, j4, j5), new c(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public void b(b bVar, c cVar) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$HlXFoTZZ6vk7NEi3AZrjgisgNrI(this, next.b, bVar, cVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(n nVar, b bVar, c cVar) {
            nVar.b(this.a, this.b, bVar, cVar);
        }

        public void b(h hVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3) {
            b(hVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
        }

        public void b(h hVar, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            c(new b(hVar, uri, map, j3, j4, j5), new c(i, i2, format, i3, obj, a(j), a(j2)));
        }

        public void c(b bVar, c cVar) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$F_3NR2C5W0qugA2xc4riGKa5a8(this, next.b, bVar, cVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(n nVar, b bVar, c cVar) {
            nVar.c(this.a, this.b, bVar, cVar);
        }

        public void a(h hVar, Uri uri, Map<String, List<String>> map, int i, long j, long j2, long j3, IOException iOException, boolean z) {
            a(hVar, uri, map, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3, iOException, z);
        }

        public void a(h hVar, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
            a(new b(hVar, uri, map, j3, j4, j5), new c(i, i2, format, i3, obj, a(j), a(j2)), iOException, z);
        }

        public void a(b bVar, c cVar, IOException iOException, boolean z) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$83lcLht6Mv_IBCLzNghsxEDNdLA(this, next.b, bVar, cVar, iOException, z));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(n nVar, b bVar, c cVar, IOException iOException, boolean z) {
            nVar.a(this.a, this.b, bVar, cVar, iOException, z);
        }

        public void c() {
            m.a aVar = (m.a) com.google.android.exoplayer2.util.a.a(this.b);
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$5uyOrem7bizMOUBQSTmmjl1V3lw(this, next.b, aVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(n nVar, m.a aVar) {
            nVar.c(this.a, aVar);
        }

        public void a(int i, long j, long j2) {
            a(new c(1, i, null, 3, null, a(j), a(j2)));
        }

        public void a(c cVar) {
            m.a aVar = (m.a) com.google.android.exoplayer2.util.a.a(this.b);
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$ZMfLRHIAc5IiIrghxBpIfy0jFU(this, next.b, aVar, cVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(n nVar, m.a aVar, c cVar) {
            nVar.a(this.a, aVar, cVar);
        }

        public void a(int i, Format format, int i2, Object obj, long j) {
            b(new c(1, i, format, i2, obj, a(j), -9223372036854775807L));
        }

        public void b(c cVar) {
            Iterator<C0092a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                C0092a next = it2.next();
                a(next.a, new $$Lambda$n$a$bccEhPLIwbNB_XLXJ2BIOens8nQ(this, next.b, cVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(n nVar, c cVar) {
            nVar.b(this.a, this.b, cVar);
        }

        private long a(long j) {
            long a = com.google.android.exoplayer2.c.a(j);
            if (a == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return this.d + a;
        }

        private void a(Handler handler, Runnable runnable) {
            if (handler.getLooper() == Looper.myLooper()) {
                runnable.run();
            } else {
                handler.post(runnable);
            }
        }

        /* compiled from: MediaSourceEventListener */
        /* access modifiers changed from: private */
        /* renamed from: com.google.android.exoplayer2.source.n$a$a  reason: collision with other inner class name */
        public static final class C0092a {
            public final Handler a;
            public final n b;

            public C0092a(Handler handler, n nVar) {
                this.a = handler;
                this.b = nVar;
            }
        }
    }
}
