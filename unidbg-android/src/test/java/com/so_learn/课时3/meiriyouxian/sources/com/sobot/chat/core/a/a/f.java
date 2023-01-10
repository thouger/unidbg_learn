package com.sobot.chat.core.a.a;

import java.util.Arrays;

/* compiled from: SocketHeartBeatHelper */
public class f {
    final f a = this;
    private f b;
    private byte[] c;
    private b d;
    private byte[] e;
    private a f;
    private long g;
    private boolean h;

    /* compiled from: SocketHeartBeatHelper */
    public interface a {
        boolean a(f fVar, j jVar);
    }

    /* compiled from: SocketHeartBeatHelper */
    public interface b {
        byte[] a(f fVar);
    }

    public f a() {
        f fVar = new f();
        fVar.a(this);
        fVar.a(d());
        fVar.a(e());
        fVar.b(f());
        fVar.a(g());
        fVar.a(h());
        fVar.a(i());
        return fVar;
    }

    public byte[] b() {
        if (e() != null) {
            return e().a(c());
        }
        return d();
    }

    public boolean a(j jVar) {
        if (g() != null) {
            return g().a(c(), jVar);
        }
        if (f() != null) {
            return jVar.a(f());
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public f a(f fVar) {
        this.b = fVar;
        return this;
    }

    public f c() {
        f fVar = this.b;
        return fVar == null ? this : fVar;
    }

    public f a(byte[] bArr) {
        if (bArr != null) {
            this.c = Arrays.copyOf(bArr, bArr.length);
        } else {
            this.c = null;
        }
        return this;
    }

    public byte[] d() {
        return this.c;
    }

    public f a(b bVar) {
        this.d = bVar;
        return this;
    }

    public b e() {
        return this.d;
    }

    public f b(byte[] bArr) {
        if (bArr != null) {
            this.e = Arrays.copyOf(bArr, bArr.length);
        } else {
            this.e = null;
        }
        return this;
    }

    public byte[] f() {
        return this.e;
    }

    public f a(a aVar) {
        this.f = aVar;
        return this;
    }

    public a g() {
        return this.f;
    }

    public f a(long j) {
        this.g = j;
        return this;
    }

    public long h() {
        return this.g;
    }

    public f a(boolean z) {
        this.h = z;
        return this;
    }

    public boolean i() {
        if ((d() != null || e() != null) && h() > 0) {
            return this.h;
        }
        return false;
    }
}
