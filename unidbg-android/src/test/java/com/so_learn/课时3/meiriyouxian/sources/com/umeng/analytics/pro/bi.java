package com.umeng.analytics.pro;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol */
public class bi extends bp {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final bu h = new bu();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    @Override // com.umeng.analytics.pro.bp
    public void a() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bu buVar) {
    }

    @Override // com.umeng.analytics.pro.bp
    public void b() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void c() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void e() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void f() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void g() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void i() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void k() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void m() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void o() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void q() {
    }

    @Override // com.umeng.analytics.pro.bp
    public void s() {
    }

    /* compiled from: TBinaryProtocol */
    public static class a implements br {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        @Override // com.umeng.analytics.pro.br
        public bp a(cd cdVar) {
            bi biVar = new bi(cdVar, this.a, this.b);
            int i = this.c;
            if (i != 0) {
                biVar.c(i);
            }
            return biVar;
        }
    }

    public bi(cd cdVar) {
        this(cdVar, false, true);
    }

    public bi(cd cdVar, boolean z, boolean z2) {
        super(cdVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bn bnVar) throws aw {
        if (this.d) {
            a(-2147418112 | bnVar.b);
            a(bnVar.a);
            a(bnVar.c);
            return;
        }
        a(bnVar.a);
        a(bnVar.b);
        a(bnVar.c);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bk bkVar) throws aw {
        a(bkVar.b);
        a(bkVar.c);
    }

    @Override // com.umeng.analytics.pro.bp
    public void d() throws aw {
        a((byte) 0);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bm bmVar) throws aw {
        a(bmVar.a);
        a(bmVar.b);
        a(bmVar.c);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bl blVar) throws aw {
        a(blVar.a);
        a(blVar.b);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(bt btVar) throws aw {
        a(btVar.a);
        a(btVar.b);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(boolean z) throws aw {
        a(z ? (byte) 1 : 0);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(byte b2) throws aw {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(short s) throws aw {
        byte[] bArr = this.j;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(int i) throws aw {
        byte[] bArr = this.k;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(long j) throws aw {
        byte[] bArr = this.l;
        bArr[0] = (byte) ((int) ((j >> 56) & 255));
        bArr[1] = (byte) ((int) ((j >> 48) & 255));
        bArr[2] = (byte) ((int) ((j >> 40) & 255));
        bArr[3] = (byte) ((int) ((j >> 32) & 255));
        bArr[4] = (byte) ((int) ((j >> 24) & 255));
        bArr[5] = (byte) ((int) ((j >> 16) & 255));
        bArr[6] = (byte) ((int) ((j >> 8) & 255));
        bArr[7] = (byte) ((int) (j & 255));
        this.g.b(this.l, 0, 8);
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(double d) throws aw {
        a(Double.doubleToLongBits(d));
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(String str) throws aw {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new aw("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.bp
    public void a(ByteBuffer byteBuffer) throws aw {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // com.umeng.analytics.pro.bp
    public bn h() throws aw {
        int w = w();
        if (w < 0) {
            if ((-65536 & w) == -2147418112) {
                return new bn(z(), (byte) (w & 255), w());
            }
            throw new bq(4, "Bad version in readMessageBegin");
        } else if (!this.c) {
            return new bn(b(w), u(), w());
        } else {
            throw new bq(4, "Missing version in readMessageBegin, old client?");
        }
    }

    @Override // com.umeng.analytics.pro.bp
    public bu j() {
        return h;
    }

    @Override // com.umeng.analytics.pro.bp
    public bk l() throws aw {
        short s;
        byte u = u();
        if (u == 0) {
            s = 0;
        } else {
            s = v();
        }
        return new bk("", u, s);
    }

    @Override // com.umeng.analytics.pro.bp
    public bm n() throws aw {
        return new bm(u(), u(), w());
    }

    @Override // com.umeng.analytics.pro.bp
    public bl p() throws aw {
        return new bl(u(), w());
    }

    @Override // com.umeng.analytics.pro.bp
    public bt r() throws aw {
        return new bt(u(), w());
    }

    @Override // com.umeng.analytics.pro.bp
    public boolean t() throws aw {
        return u() == 1;
    }

    @Override // com.umeng.analytics.pro.bp
    public byte u() throws aw {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    @Override // com.umeng.analytics.pro.bp
    public short v() throws aw {
        byte[] bArr = this.n;
        int i = 0;
        if (this.g.h() >= 2) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // com.umeng.analytics.pro.bp
    public int w() throws aw {
        byte[] bArr = this.o;
        int i = 0;
        if (this.g.h() >= 4) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // com.umeng.analytics.pro.bp
    public long x() throws aw {
        byte[] bArr = this.p;
        int i = 0;
        if (this.g.h() >= 8) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48) | (((long) (bArr[i + 2] & 255)) << 40) | (((long) (bArr[i + 3] & 255)) << 32) | (((long) (bArr[i + 4] & 255)) << 24) | (((long) (bArr[i + 5] & 255)) << 16) | (((long) (bArr[i + 6] & 255)) << 8);
    }

    @Override // com.umeng.analytics.pro.bp
    public double y() throws aw {
        return Double.longBitsToDouble(x());
    }

    @Override // com.umeng.analytics.pro.bp
    public String z() throws aw {
        int w = w();
        if (this.g.h() < w) {
            return b(w);
        }
        try {
            String str = new String(this.g.f(), this.g.g(), w, "UTF-8");
            this.g.a(w);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new aw("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws aw {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new aw("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.bp
    public ByteBuffer A() throws aw {
        int w = w();
        d(w);
        if (this.g.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws aw {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public void d(int i) throws aw {
        if (i < 0) {
            throw new bq("Negative length: " + i);
        } else if (this.f) {
            this.e -= i;
            if (this.e < 0) {
                throw new bq("Message length exceeded: " + i);
            }
        }
    }
}
