package com.umeng.analytics.pro;

/* compiled from: TTransport */
public abstract class cd {
    public abstract int a(byte[] bArr, int i, int i2) throws ce;

    public void a(int i) {
    }

    public abstract boolean a();

    public abstract void b() throws ce;

    public abstract void b(byte[] bArr, int i, int i2) throws ce;

    public abstract void c();

    public void d() throws ce {
    }

    public byte[] f() {
        return null;
    }

    public int g() {
        return 0;
    }

    public int h() {
        return -1;
    }

    public boolean i() {
        return a();
    }

    public int d(byte[] bArr, int i, int i2) throws ce {
        int i3 = 0;
        while (i3 < i2) {
            int a = a(bArr, i + i3, i2 - i3);
            if (a > 0) {
                i3 += a;
            } else {
                throw new ce("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
        }
        return i3;
    }

    public void b(byte[] bArr) throws ce {
        b(bArr, 0, bArr.length);
    }
}
