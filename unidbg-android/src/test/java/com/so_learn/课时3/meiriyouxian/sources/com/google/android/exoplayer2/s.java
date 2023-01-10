package com.google.android.exoplayer2;

import android.os.Handler;

/* compiled from: PlayerMessage */
public final class s {
    private final b a;
    private final a b;
    private final z c;
    private int d;
    private Object e;
    private Handler f;
    private int g;
    private long h = -9223372036854775807L;
    private boolean i = true;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    /* compiled from: PlayerMessage */
    public interface a {
        void a(s sVar);
    }

    /* compiled from: PlayerMessage */
    public interface b {
        void a(int i, Object obj) throws ExoPlaybackException;
    }

    public s(a aVar, b bVar, z zVar, int i, Handler handler) {
        this.b = aVar;
        this.a = bVar;
        this.c = zVar;
        this.f = handler;
        this.g = i;
    }

    public z a() {
        return this.c;
    }

    public b b() {
        return this.a;
    }

    public s a(int i) {
        com.google.android.exoplayer2.util.a.b(!this.j);
        this.d = i;
        return this;
    }

    public int c() {
        return this.d;
    }

    public s a(Object obj) {
        com.google.android.exoplayer2.util.a.b(!this.j);
        this.e = obj;
        return this;
    }

    public Object d() {
        return this.e;
    }

    public Handler e() {
        return this.f;
    }

    public long f() {
        return this.h;
    }

    public int g() {
        return this.g;
    }

    public boolean h() {
        return this.i;
    }

    public s i() {
        com.google.android.exoplayer2.util.a.b(!this.j);
        if (this.h == -9223372036854775807L) {
            com.google.android.exoplayer2.util.a.a(this.i);
        }
        this.j = true;
        this.b.a(this);
        return this;
    }

    public synchronized boolean j() {
        return this.m;
    }

    public synchronized boolean k() throws InterruptedException {
        com.google.android.exoplayer2.util.a.b(this.j);
        com.google.android.exoplayer2.util.a.b(this.f.getLooper().getThread() != Thread.currentThread());
        while (!this.l) {
            wait();
        }
        return this.k;
    }

    public synchronized void a(boolean z) {
        this.k = z | this.k;
        this.l = true;
        notifyAll();
    }
}
