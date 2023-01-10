package com.umeng.message.proguard;

/* compiled from: BaseNCodec */
public abstract class e {
    private static final int a = 2;
    public static final int b = 76;
    public static final int c = 64;
    protected static final int d = 255;
    protected static final byte e = 61;
    private static final int m = 8192;
    protected final byte f = e;
    protected final int g;
    protected byte[] h;
    protected int i;
    protected boolean j;
    protected int k;
    protected int l;
    private final int n;
    private final int o;
    private final int p;
    private int q;

    protected static boolean c(byte b2) {
        return b2 == 9 || b2 == 10 || b2 == 13 || b2 == 32;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void b(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract boolean b(byte b2);

    /* access modifiers changed from: protected */
    public int d() {
        return 8192;
    }

    protected e(int i, int i2, int i3, int i4) {
        this.n = i;
        this.o = i2;
        this.g = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.p = i4;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.h != null;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        if (this.h != null) {
            return this.i - this.q;
        }
        return 0;
    }

    private void a() {
        byte[] bArr = this.h;
        if (bArr == null) {
            this.h = new byte[d()];
            this.i = 0;
            this.q = 0;
            return;
        }
        byte[] bArr2 = new byte[(bArr.length * 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        this.h = bArr2;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        byte[] bArr = this.h;
        if (bArr == null || bArr.length < this.i + i) {
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public int c(byte[] bArr, int i, int i2) {
        if (this.h == null) {
            return this.j ? -1 : 0;
        }
        int min = Math.min(c(), i2);
        System.arraycopy(this.h, this.q, bArr, i, min);
        this.q += min;
        if (this.q >= this.i) {
            this.h = null;
        }
        return min;
    }

    private void e() {
        this.h = null;
        this.i = 0;
        this.q = 0;
        this.k = 0;
        this.l = 0;
        this.j = false;
    }

    public Object a(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return l((byte[]) obj);
        }
        throw new Exception("Parameter supplied to Base-N encode is not a byte[]");
    }

    public String j(byte[] bArr) {
        return b.f(l(bArr));
    }

    public Object b(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return k((byte[]) obj);
        }
        if (obj instanceof String) {
            return c((String) obj);
        }
        throw new Exception("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] c(String str) {
        return k(b.f(str));
    }

    public byte[] k(byte[] bArr) {
        e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        b(bArr, 0, bArr.length);
        b(bArr, 0, -1);
        byte[] bArr2 = new byte[this.i];
        c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public byte[] l(byte[] bArr) {
        e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a(bArr, 0, bArr.length);
        a(bArr, 0, -1);
        byte[] bArr2 = new byte[(this.i - this.q)];
        c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public String m(byte[] bArr) {
        return b.f(l(bArr));
    }

    public boolean b(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!(b(bArr[i]) || (z && (bArr[i] == 61 || c(bArr[i]))))) {
                return false;
            }
        }
        return true;
    }

    public boolean d(String str) {
        return b(b.f(str), true);
    }

    /* access modifiers changed from: protected */
    public boolean n(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (61 == bArr[i] || b(bArr[i])) {
                return true;
            }
        }
        return false;
    }

    public long o(byte[] bArr) {
        int length = bArr.length;
        int i = this.n;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.o);
        int i2 = this.g;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.p)) : j;
    }
}
