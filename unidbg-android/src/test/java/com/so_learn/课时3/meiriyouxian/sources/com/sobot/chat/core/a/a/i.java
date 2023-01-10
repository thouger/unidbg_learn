package com.sobot.chat.core.a.a;

import java.util.Arrays;

/* compiled from: SocketPacketHelper */
public class i {
    final i a = this;
    private i b;
    private byte[] c;
    private c d;
    private byte[] e;
    private int f;
    private boolean g;
    private long h;
    private boolean i;
    private a j = a.Manually;
    private byte[] k;
    private int l;
    private b m;
    private byte[] n;
    private int o;
    private boolean p;
    private long q;
    private boolean r;

    /* compiled from: SocketPacketHelper */
    public enum a {
        Manually,
        AutoReadToTrailer,
        AutoReadByLength
    }

    /* compiled from: SocketPacketHelper */
    public interface b {
        int a(i iVar, byte[] bArr);
    }

    /* compiled from: SocketPacketHelper */
    public interface c {
        byte[] a(i iVar, int i);
    }

    public i a() {
        i iVar = new i();
        iVar.a(this);
        iVar.b(d());
        iVar.a(e());
        iVar.c(f());
        iVar.b(g());
        iVar.a(h());
        iVar.a(i());
        iVar.b(j());
        iVar.a(k());
        iVar.d(l());
        iVar.c(m());
        iVar.a(n());
        iVar.e(o());
        iVar.d(p());
        iVar.c(q());
        iVar.b(r());
        iVar.d(s());
        return iVar;
    }

    /* compiled from: SocketPacketHelper */
    /* renamed from: com.sobot.chat.core.a.a.i$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.Manually.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.AutoReadToTrailer.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.AutoReadByLength.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void b() {
        int i = AnonymousClass1.a[k().ordinal()];
        if (i == 1) {
            return;
        }
        if (i != 2) {
            if (i != 3) {
                throw new IllegalArgumentException("we need a correct ReadStrategy");
            } else if (m() <= 0 || n() == null) {
                throw new IllegalArgumentException("we need ReceivePacketLengthDataLength and ReceivePacketDataLengthConvertor for AutoReadByLength");
            }
        } else if (o() == null || o().length <= 0) {
            throw new IllegalArgumentException("we need ReceiveTrailerData for AutoReadToTrailer");
        }
    }

    public byte[] a(int i) {
        if (e() != null) {
            return e().a(c(), i);
        }
        return null;
    }

    public int a(byte[] bArr) {
        if (k() != a.AutoReadByLength || n() == null) {
            return 0;
        }
        return n().a(c(), bArr);
    }

    /* access modifiers changed from: protected */
    public i a(i iVar) {
        this.b = iVar;
        return this;
    }

    public i c() {
        i iVar = this.b;
        return iVar == null ? this : iVar;
    }

    public i b(byte[] bArr) {
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

    public i a(c cVar) {
        this.d = cVar;
        return this;
    }

    public c e() {
        return this.d;
    }

    public i c(byte[] bArr) {
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

    public i b(int i) {
        this.f = i;
        return this;
    }

    public int g() {
        return this.f;
    }

    public i a(boolean z) {
        this.g = z;
        return this;
    }

    public boolean h() {
        if (g() <= 0) {
            return false;
        }
        return this.g;
    }

    public i a(long j) {
        this.h = j;
        return this;
    }

    public long i() {
        return this.h;
    }

    public i b(boolean z) {
        this.i = z;
        return this;
    }

    public boolean j() {
        return this.i;
    }

    public i a(a aVar) {
        this.j = aVar;
        return this;
    }

    public a k() {
        return this.j;
    }

    public i d(byte[] bArr) {
        if (bArr != null) {
            this.k = Arrays.copyOf(bArr, bArr.length);
        } else {
            this.k = null;
        }
        return this;
    }

    public byte[] l() {
        return this.k;
    }

    public i c(int i) {
        this.l = i;
        return this;
    }

    public int m() {
        return this.l;
    }

    public i a(b bVar) {
        this.m = bVar;
        return this;
    }

    public b n() {
        return this.m;
    }

    public i e(byte[] bArr) {
        if (bArr != null) {
            this.n = Arrays.copyOf(bArr, bArr.length);
        } else {
            this.n = null;
        }
        return this;
    }

    public byte[] o() {
        return this.n;
    }

    public i d(int i) {
        this.o = i;
        return this;
    }

    public int p() {
        return this.o;
    }

    public i c(boolean z) {
        this.p = z;
        return this;
    }

    public boolean q() {
        if (p() <= 0) {
            return false;
        }
        return this.p;
    }

    public i b(long j) {
        this.q = j;
        return this;
    }

    public long r() {
        return this.q;
    }

    public i d(boolean z) {
        this.r = z;
        return this;
    }

    public boolean s() {
        return this.r;
    }
}
