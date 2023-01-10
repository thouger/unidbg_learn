package com.google.android.exoplayer2.source.dash;

import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.extractor.q;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.a.d;
import com.google.android.exoplayer2.source.q;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: PlayerEmsgHandler */
public final class h implements Handler.Callback {
    private final com.google.android.exoplayer2.upstream.b a;
    private final b b;
    private final com.google.android.exoplayer2.metadata.emsg.a c = new com.google.android.exoplayer2.metadata.emsg.a();
    private final Handler d = z.a((Handler.Callback) this);
    private final TreeMap<Long, Long> e = new TreeMap<>();
    private com.google.android.exoplayer2.source.dash.a.b f;
    private long g;
    private long h = -9223372036854775807L;
    private long i = -9223372036854775807L;
    private boolean j;
    private boolean k;

    /* compiled from: PlayerEmsgHandler */
    public interface b {
        void a();

        void a(long j);
    }

    public h(com.google.android.exoplayer2.source.dash.a.b bVar, b bVar2, com.google.android.exoplayer2.upstream.b bVar3) {
        this.f = bVar;
        this.b = bVar2;
        this.a = bVar3;
    }

    public void a(com.google.android.exoplayer2.source.dash.a.b bVar) {
        this.j = false;
        this.g = -9223372036854775807L;
        this.f = bVar;
        c();
    }

    /* access modifiers changed from: package-private */
    public boolean a(long j) {
        boolean z = false;
        if (!this.f.d) {
            return false;
        }
        if (this.j) {
            return true;
        }
        Map.Entry<Long, Long> b2 = b(this.f.h);
        if (b2 != null && b2.getValue().longValue() < j) {
            this.g = b2.getKey().longValue();
            d();
            z = true;
        }
        if (z) {
            e();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean a(d dVar) {
        if (!this.f.d) {
            return false;
        }
        if (this.j) {
            return true;
        }
        long j = this.h;
        if (!(j != -9223372036854775807L && j < dVar.h)) {
            return false;
        }
        e();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(d dVar) {
        if (this.h != -9223372036854775807L || dVar.i > this.h) {
            this.h = dVar.i;
        }
    }

    public static boolean a(String str, String str2) {
        return "urn:mpeg:dash:event:2012".equals(str) && ("1".equals(str2) || "2".equals(str2) || "3".equals(str2));
    }

    public c a() {
        return new c(new q(this.a));
    }

    public void b() {
        this.k = true;
        this.d.removeCallbacksAndMessages(null);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (this.k) {
            return true;
        }
        if (message.what != 1) {
            return false;
        }
        a aVar = (a) message.obj;
        a(aVar.a, aVar.b);
        return true;
    }

    private void a(long j, long j2) {
        Long l = this.e.get(Long.valueOf(j2));
        if (l == null) {
            this.e.put(Long.valueOf(j2), Long.valueOf(j));
        } else if (l.longValue() > j) {
            this.e.put(Long.valueOf(j2), Long.valueOf(j));
        }
    }

    private Map.Entry<Long, Long> b(long j) {
        return this.e.ceilingEntry(Long.valueOf(j));
    }

    private void c() {
        Iterator<Map.Entry<Long, Long>> it2 = this.e.entrySet().iterator();
        while (it2.hasNext()) {
            if (it2.next().getKey().longValue() < this.f.h) {
                it2.remove();
            }
        }
    }

    private void d() {
        this.b.a(this.g);
    }

    private void e() {
        long j = this.i;
        if (j == -9223372036854775807L || j != this.h) {
            this.j = true;
            this.i = this.h;
            this.b.a();
        }
    }

    /* access modifiers changed from: private */
    public static long b(EventMessage eventMessage) {
        try {
            return z.g(z.a(eventMessage.e));
        } catch (ParserException unused) {
            return -9223372036854775807L;
        }
    }

    /* compiled from: PlayerEmsgHandler */
    public final class c implements com.google.android.exoplayer2.extractor.q {
        private final q b;
        private final k c = new k();
        private final com.google.android.exoplayer2.metadata.c d = new com.google.android.exoplayer2.metadata.c();

        c(q qVar) {
            this.b = qVar;
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(Format format) {
            this.b.a(format);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public int a(com.google.android.exoplayer2.extractor.h hVar, int i, boolean z) throws IOException, InterruptedException {
            return this.b.a(hVar, i, z);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(o oVar, int i) {
            this.b.a(oVar, i);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(long j, int i, int i2, int i3, q.a aVar) {
            this.b.a(j, i, i2, i3, aVar);
            b();
        }

        public boolean a(long j) {
            return h.this.a(j);
        }

        public void a(d dVar) {
            h.this.b(dVar);
        }

        public boolean b(d dVar) {
            return h.this.a(dVar);
        }

        public void a() {
            this.b.a();
        }

        private void b() {
            while (this.b.d()) {
                com.google.android.exoplayer2.metadata.c c = c();
                if (c != null) {
                    long j = c.c;
                    EventMessage eventMessage = (EventMessage) h.this.c.a(c).a(0);
                    if (h.a(eventMessage.a, eventMessage.b)) {
                        a(j, eventMessage);
                    }
                }
            }
            this.b.m();
        }

        private com.google.android.exoplayer2.metadata.c c() {
            this.d.a();
            if (this.b.a(this.c, (e) this.d, false, false, 0L) != -4) {
                return null;
            }
            this.d.h();
            return this.d;
        }

        private void a(long j, EventMessage eventMessage) {
            long b = h.b(eventMessage);
            if (b != -9223372036854775807L) {
                a(j, b);
            }
        }

        private void a(long j, long j2) {
            h.this.d.sendMessage(h.this.d.obtainMessage(1, new a(j, j2)));
        }
    }

    /* compiled from: PlayerEmsgHandler */
    /* access modifiers changed from: private */
    public static final class a {
        public final long a;
        public final long b;

        public a(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }
}
