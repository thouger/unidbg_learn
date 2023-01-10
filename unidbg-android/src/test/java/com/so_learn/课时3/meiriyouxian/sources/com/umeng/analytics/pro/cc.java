package com.umeng.analytics.pro;

/* compiled from: TMemoryInputTransport */
public final class cc extends cd {
    private byte[] a;
    private int b;
    private int c;

    @Override // com.umeng.analytics.pro.cd
    public boolean a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.cd
    public void b() throws ce {
    }

    @Override // com.umeng.analytics.pro.cd
    public void c() {
    }

    public cc() {
    }

    public cc(byte[] bArr) {
        a(bArr);
    }

    public cc(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.a = null;
    }

    @Override // com.umeng.analytics.pro.cd
    public int a(byte[] bArr, int i, int i2) throws ce {
        int h = h();
        if (i2 > h) {
            i2 = h;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    @Override // com.umeng.analytics.pro.cd
    public void b(byte[] bArr, int i, int i2) throws ce {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.analytics.pro.cd
    public byte[] f() {
        return this.a;
    }

    @Override // com.umeng.analytics.pro.cd
    public int g() {
        return this.b;
    }

    @Override // com.umeng.analytics.pro.cd
    public int h() {
        return this.c - this.b;
    }

    @Override // com.umeng.analytics.pro.cd
    public void a(int i) {
        this.b += i;
    }
}
