package com.sobot.chat.widget.gif;

import android.graphics.Bitmap;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: GifDecoder */
public class b extends Thread {
    private Bitmap A;
    private c B;
    private boolean C;
    private byte[] D;
    private int E;
    private int F;
    private int G;
    private boolean H;
    private int I;
    private int J;
    private short[] K;
    private byte[] L;
    private byte[] M;
    private byte[] N;
    private c O;
    private int P;
    private a Q;
    private byte[] R;
    public int a;
    public int b;
    private InputStream c;
    private int d;
    private boolean e;
    private int f;
    private int g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private Bitmap z;

    public b(byte[] bArr, a aVar) {
        this.g = 1;
        this.B = null;
        this.C = false;
        this.D = new byte[256];
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = false;
        this.I = 0;
        this.Q = null;
        this.R = null;
        this.R = bArr;
        this.Q = aVar;
    }

    public b(InputStream inputStream, a aVar) {
        this.g = 1;
        this.B = null;
        this.C = false;
        this.D = new byte[256];
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = false;
        this.I = 0;
        this.Q = null;
        this.R = null;
        this.c = inputStream;
        this.Q = aVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.c != null) {
            g();
        } else if (this.R != null) {
            f();
        }
    }

    public void a() {
        c cVar = this.O;
        while (cVar != null) {
            cVar.a = null;
            this.O = this.O.c;
            cVar = this.O;
        }
        InputStream inputStream = this.c;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
            this.c = null;
        }
        this.R = null;
    }

    public int b() {
        return this.P;
    }

    public Bitmap c() {
        return a(0);
    }

    private void e() {
        int i;
        int[] iArr = new int[(this.a * this.b)];
        int i2 = this.G;
        int i3 = 0;
        if (i2 > 0) {
            if (i2 == 3) {
                int i4 = this.P - 2;
                if (i4 > 0) {
                    this.A = a(i4 - 1);
                } else {
                    this.A = null;
                }
            }
            Bitmap bitmap = this.A;
            if (bitmap != null) {
                int i5 = this.a;
                bitmap.getPixels(iArr, 0, i5, 0, 0, i5, this.b);
                if (this.G == 2) {
                    int i6 = !this.H ? this.m : 0;
                    for (int i7 = 0; i7 < this.y; i7++) {
                        int i8 = ((this.w + i7) * this.a) + this.v;
                        int i9 = this.x + i8;
                        while (i8 < i9) {
                            iArr[i8] = i6;
                            i8++;
                        }
                    }
                }
            }
        }
        int i10 = 8;
        int i11 = 0;
        int i12 = 1;
        while (true) {
            int i13 = this.u;
            if (i3 < i13) {
                if (this.p) {
                    if (i11 >= i13) {
                        i12++;
                        if (i12 == 2) {
                            i11 = 4;
                        } else if (i12 == 3) {
                            i10 = 4;
                            i11 = 2;
                        } else if (i12 == 4) {
                            i10 = 2;
                            i11 = 1;
                        }
                    }
                    i = i11 + i10;
                } else {
                    i = i11;
                    i11 = i3;
                }
                int i14 = i11 + this.s;
                if (i14 < this.b) {
                    int i15 = this.a;
                    int i16 = i14 * i15;
                    int i17 = this.r + i16;
                    int i18 = this.t + i17;
                    if (i16 + i15 < i18) {
                        i18 = i16 + i15;
                    }
                    int i19 = this.t * i3;
                    while (i17 < i18) {
                        int i20 = i19 + 1;
                        int i21 = this.j[this.N[i19] & 255];
                        if (i21 != 0) {
                            iArr[i17] = i21;
                        }
                        i17++;
                        i19 = i20;
                    }
                }
                i3++;
                i11 = i;
            } else {
                this.z = Bitmap.createBitmap(iArr, this.a, this.b, Bitmap.Config.ARGB_4444);
                return;
            }
        }
    }

    public Bitmap a(int i) {
        c b = b(i);
        if (b == null) {
            return null;
        }
        return b.a;
    }

    public c b(int i) {
        c cVar = this.O;
        int i2 = 0;
        while (cVar != null) {
            if (i2 == i) {
                return cVar;
            }
            cVar = cVar.c;
            i2++;
        }
        return null;
    }

    public c d() {
        if (!this.C) {
            this.C = true;
            return this.O;
        }
        if (this.d != 0) {
            this.B = this.B.c;
            if (this.B == null) {
                this.B = this.O;
            }
        } else if (this.B.c != null) {
            this.B = this.B.c;
        }
        return this.B;
    }

    private int f() {
        this.c = new ByteArrayInputStream(this.R);
        this.R = null;
        return g();
    }

    private int g() {
        j();
        if (this.c != null) {
            o();
            if (!i()) {
                m();
                if (this.P < 0) {
                    this.d = 1;
                    this.Q.a(false, -1);
                } else {
                    this.d = -1;
                    this.Q.a(true, -1);
                }
            }
            try {
                this.c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.d = 2;
            this.Q.a(false, -1);
        }
        return this.d;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:70:0x00bf */
    /* JADX DEBUG: Multi-variable search result rejected for r4v10, resolved type: short[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v22, resolved type: short */
    /* JADX WARN: Multi-variable type inference failed */
    private void h() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        short s;
        int i7 = this.t * this.u;
        byte[] bArr = this.N;
        if (bArr == null || bArr.length < i7) {
            this.N = new byte[i7];
        }
        if (this.K == null) {
            this.K = new short[4096];
        }
        if (this.L == null) {
            this.L = new byte[4096];
        }
        if (this.M == null) {
            this.M = new byte[4097];
        }
        int k = k();
        int i8 = 1 << k;
        int i9 = i8 + 1;
        int i10 = i8 + 2;
        int i11 = k + 1;
        int i12 = (1 << i11) - 1;
        for (int i13 = 0; i13 < i8; i13++) {
            this.K[i13] = 0;
            this.L[i13] = (byte) i13;
        }
        int i14 = i11;
        int i15 = i12;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = -1;
        int i25 = i10;
        while (i16 < i7) {
            if (i17 != 0) {
                i = i11;
                i2 = i9;
                i3 = i8;
                i4 = i22;
            } else if (i18 >= i14) {
                int i26 = i19 & i15;
                i19 >>= i14;
                i18 -= i14;
                if (i26 > i25 || i26 == i9) {
                    break;
                } else if (i26 == i8) {
                    i14 = i11;
                    i25 = i10;
                    i15 = i12;
                    i24 = -1;
                } else if (i24 == -1) {
                    this.M[i17] = this.L[i26];
                    i24 = i26;
                    i22 = i24;
                    i17++;
                    i11 = i11;
                } else {
                    i = i11;
                    if (i26 == i25) {
                        i6 = i17 + 1;
                        i5 = i26;
                        this.M[i17] = (byte) i22;
                        s = i24;
                    } else {
                        i5 = i26;
                        i6 = i17;
                        s = i5;
                    }
                    while (s > i8) {
                        this.M[i6] = this.L[s];
                        s = this.K[s];
                        i6++;
                        i8 = i8;
                    }
                    i3 = i8;
                    byte[] bArr2 = this.L;
                    i4 = bArr2[s] & 255;
                    if (i25 >= 4096) {
                        break;
                    }
                    i17 = i6 + 1;
                    i2 = i9;
                    byte b = (byte) i4;
                    this.M[i6] = b;
                    this.K[i25] = (short) i24;
                    bArr2[i25] = b;
                    i25++;
                    if ((i25 & i15) == 0 && i25 < 4096) {
                        i14++;
                        i15 += i25;
                    }
                    i24 = i5;
                }
            } else {
                if (i20 == 0) {
                    i20 = l();
                    if (i20 <= 0) {
                        break;
                    }
                    i21 = 0;
                }
                i19 += (this.D[i21] & 255) << i18;
                i18 += 8;
                i21++;
                i20--;
            }
            i17--;
            this.N[i23] = this.M[i17];
            i16++;
            i23++;
            i8 = i3;
            i9 = i2;
            i22 = i4;
            i11 = i;
        }
        for (int i27 = i23; i27 < i7; i27++) {
            this.N[i27] = 0;
        }
    }

    private boolean i() {
        return this.d != 0;
    }

    private void j() {
        this.d = 0;
        this.P = 0;
        this.O = null;
        this.h = null;
        this.i = null;
    }

    private int k() {
        try {
            return this.c.read();
        } catch (Exception unused) {
            this.d = 1;
            return 0;
        }
    }

    private int l() {
        this.E = k();
        int i = 0;
        if (this.E > 0) {
            while (i < this.E) {
                try {
                    int read = this.c.read(this.D, i, this.E - i);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i < this.E) {
                this.d = 1;
            }
        }
        return i;
    }

    private int[] c(int i) {
        int i2;
        int i3 = i * 3;
        byte[] bArr = new byte[i3];
        try {
            i2 = this.c.read(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            i2 = 0;
        }
        if (i2 < i3) {
            this.d = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            iArr[i5] = ((bArr[i4] & 255) << 16) | -16777216 | ((bArr[i6] & 255) << 8) | (bArr[i7] & 255);
            i4 = i7 + 1;
        }
        return iArr;
    }

    private void m() {
        boolean z = false;
        while (!z && !i()) {
            int k = k();
            if (k != 0) {
                if (k == 33) {
                    int k2 = k();
                    if (k2 == 249) {
                        n();
                    } else if (k2 != 255) {
                        u();
                    } else {
                        l();
                        String str = "";
                        for (int i = 0; i < 11; i++) {
                            str = str + ((char) this.D[i]);
                        }
                        if (str.equals("NETSCAPE2.0")) {
                            r();
                        } else {
                            u();
                        }
                    }
                } else if (k == 44) {
                    p();
                } else if (k != 59) {
                    this.d = 1;
                } else {
                    z = true;
                }
            }
        }
    }

    private void n() {
        k();
        int k = k();
        this.F = (k & 28) >> 2;
        boolean z = true;
        if (this.F == 0) {
            this.F = 1;
        }
        if ((k & 1) == 0) {
            z = false;
        }
        this.H = z;
        this.I = s() * 10;
        this.J = k();
        k();
    }

    private void o() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) k());
        }
        if (!str.startsWith("GIF")) {
            this.d = 1;
            return;
        }
        q();
        if (this.e && !i()) {
            this.h = c(this.f);
            this.l = this.h[this.k];
        }
    }

    private void p() {
        this.r = s();
        this.s = s();
        this.t = s();
        this.u = s();
        int k = k();
        int i = 0;
        this.o = (k & 128) != 0;
        this.p = (k & 64) != 0;
        this.q = 2 << (k & 7);
        if (this.o) {
            this.i = c(this.q);
            this.j = this.i;
        } else {
            this.j = this.h;
            if (this.k == this.J) {
                this.l = 0;
            }
        }
        if (this.H) {
            int[] iArr = this.j;
            int i2 = this.J;
            int i3 = iArr[i2];
            iArr[i2] = 0;
            i = i3;
        }
        if (this.j == null) {
            this.d = 1;
        }
        if (!i()) {
            h();
            u();
            if (!i()) {
                this.P++;
                this.z = Bitmap.createBitmap(this.a, this.b, Bitmap.Config.ARGB_4444);
                e();
                c cVar = this.O;
                if (cVar == null) {
                    this.O = new c(this.z, this.I);
                    this.B = this.O;
                } else {
                    while (cVar.c != null) {
                        cVar = cVar.c;
                    }
                    cVar.c = new c(this.z, this.I);
                }
                if (this.H) {
                    this.j[this.J] = i;
                }
                t();
                this.Q.a(true, this.P);
            }
        }
    }

    private void q() {
        this.a = s();
        this.b = s();
        int k = k();
        this.e = (k & 128) != 0;
        this.f = 2 << (k & 7);
        this.k = k();
        this.n = k();
    }

    private void r() {
        do {
            l();
            byte[] bArr = this.D;
            if (bArr[0] == 1) {
                this.g = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.E <= 0) {
                return;
            }
        } while (!i());
    }

    private int s() {
        return k() | (k() << 8);
    }

    private void t() {
        this.G = this.F;
        this.v = this.r;
        this.w = this.s;
        this.x = this.t;
        this.y = this.u;
        this.A = this.z;
        this.m = this.l;
        this.F = 0;
        this.H = false;
        this.I = 0;
        this.i = null;
    }

    private void u() {
        do {
            l();
            if (this.E <= 0) {
                return;
            }
        } while (!i());
    }
}
