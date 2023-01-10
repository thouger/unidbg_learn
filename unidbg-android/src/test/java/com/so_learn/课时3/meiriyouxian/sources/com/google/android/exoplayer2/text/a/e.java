package com.google.android.exoplayer2.text.a;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* compiled from: CeaDecoder */
abstract class e implements com.google.android.exoplayer2.text.e {
    private final ArrayDeque<a> a = new ArrayDeque<>();
    private final ArrayDeque<h> b;
    private final PriorityQueue<a> c;
    private a d;
    private long e;
    private long f;

    /* access modifiers changed from: protected */
    public abstract void a(g gVar);

    @Override // com.google.android.exoplayer2.b.c
    public void d() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean e();

    /* access modifiers changed from: protected */
    public abstract d f();

    public e() {
        for (int i = 0; i < 10; i++) {
            this.a.add(new a());
        }
        this.b = new ArrayDeque<>();
        for (int i2 = 0; i2 < 2; i2++) {
            this.b.add(new b());
        }
        this.c = new PriorityQueue<>();
    }

    @Override // com.google.android.exoplayer2.text.e
    public void a(long j) {
        this.e = j;
    }

    /* renamed from: h */
    public g a() throws SubtitleDecoderException {
        com.google.android.exoplayer2.util.a.b(this.d == null);
        if (this.a.isEmpty()) {
            return null;
        }
        this.d = this.a.pollFirst();
        return this.d;
    }

    /* renamed from: b */
    public void a(g gVar) throws SubtitleDecoderException {
        com.google.android.exoplayer2.util.a.a(gVar == this.d);
        if (gVar.af_()) {
            a(this.d);
        } else {
            a aVar = this.d;
            long j = this.f;
            this.f = 1 + j;
            aVar.e = j;
            this.c.add(this.d);
        }
        this.d = null;
    }

    /* renamed from: g */
    public h b() throws SubtitleDecoderException {
        if (this.b.isEmpty()) {
            return null;
        }
        while (!this.c.isEmpty() && this.c.peek().c <= this.e) {
            a poll = this.c.poll();
            if (poll.c()) {
                h pollFirst = this.b.pollFirst();
                pollFirst.b(4);
                a(poll);
                return pollFirst;
            }
            a((g) poll);
            if (e()) {
                d f = f();
                if (!poll.af_()) {
                    h pollFirst2 = this.b.pollFirst();
                    pollFirst2.a(poll.c, f, Long.MAX_VALUE);
                    a(poll);
                    return pollFirst2;
                }
            }
            a(poll);
        }
        return null;
    }

    private void a(a aVar) {
        aVar.a();
        this.a.add(aVar);
    }

    /* access modifiers changed from: protected */
    public void a(h hVar) {
        hVar.a();
        this.b.add(hVar);
    }

    @Override // com.google.android.exoplayer2.b.c
    public void c() {
        this.f = 0;
        this.e = 0;
        while (!this.c.isEmpty()) {
            a(this.c.poll());
        }
        a aVar = this.d;
        if (aVar != null) {
            a(aVar);
            this.d = null;
        }
    }

    /* compiled from: CeaDecoder */
    /* access modifiers changed from: private */
    public static final class a extends g implements Comparable<a> {
        private long e;

        private a() {
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            if (c() != aVar.c()) {
                return c() ? 1 : -1;
            }
            long j = this.c - aVar.c;
            if (j == 0) {
                j = this.e - aVar.e;
                if (j == 0) {
                    return 0;
                }
            }
            return j > 0 ? 1 : -1;
        }
    }

    /* compiled from: CeaDecoder */
    private final class b extends h {
        private b() {
        }

        @Override // com.google.android.exoplayer2.text.h, com.google.android.exoplayer2.b.f
        public final void e() {
            e.this.a((h) this);
        }
    }
}
