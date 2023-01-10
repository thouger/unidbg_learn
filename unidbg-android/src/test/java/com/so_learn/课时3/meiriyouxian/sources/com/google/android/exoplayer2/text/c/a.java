package com.google.android.exoplayer2.text.c;

import android.graphics.Bitmap;
import android.net.wifi.WifiNetworkScoreCache;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.Inflater;

/* compiled from: PgsDecoder */
public final class a extends b {
    private final o a = new o();
    private final o b = new o();
    private final C0098a c = new C0098a();
    private Inflater d;

    public a() {
        super("PgsDecoder");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.b
    public d a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.a.a(bArr, i);
        a(this.a);
        this.c.b();
        ArrayList arrayList = new ArrayList();
        while (this.a.b() >= 3) {
            com.google.android.exoplayer2.text.a a = a(this.a, this.c);
            if (a != null) {
                arrayList.add(a);
            }
        }
        return new b(Collections.unmodifiableList(arrayList));
    }

    private void a(o oVar) {
        if (oVar.b() > 0 && oVar.f() == 120) {
            if (this.d == null) {
                this.d = new Inflater();
            }
            if (z.a(oVar, this.b, this.d)) {
                oVar.a(this.b.a, this.b.c());
            }
        }
    }

    private static com.google.android.exoplayer2.text.a a(o oVar, C0098a aVar) {
        int c = oVar.c();
        int h = oVar.h();
        int i = oVar.i();
        int d = oVar.d() + i;
        com.google.android.exoplayer2.text.a aVar2 = null;
        if (d > c) {
            oVar.c(c);
            return null;
        }
        if (h != 128) {
            switch (h) {
                case 20:
                    aVar.a(oVar, i);
                    break;
                case 21:
                    aVar.b(oVar, i);
                    break;
                case 22:
                    aVar.c(oVar, i);
                    break;
            }
        } else {
            aVar2 = aVar.a();
            aVar.b();
        }
        oVar.c(d);
        return aVar2;
    }

    /* compiled from: PgsDecoder */
    /* access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.text.c.a$a  reason: collision with other inner class name */
    public static final class C0098a {
        private final o a = new o();
        private final int[] b = new int[256];
        private boolean c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(o oVar, int i) {
            if (i % 5 == 2) {
                oVar.d(2);
                Arrays.fill(this.b, 0);
                int i2 = i / 5;
                for (int i3 = 0; i3 < i2; i3++) {
                    int h = oVar.h();
                    int h2 = oVar.h();
                    int h3 = oVar.h();
                    int h4 = oVar.h();
                    int h5 = oVar.h();
                    double d = (double) h2;
                    double d2 = (double) (h3 + WifiNetworkScoreCache.INVALID_NETWORK_SCORE);
                    double d3 = (double) (h4 + WifiNetworkScoreCache.INVALID_NETWORK_SCORE);
                    this.b[h] = z.a((int) (d + (d3 * 1.772d)), 0, 255) | (z.a((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255) << 8) | (h5 << 24) | (z.a((int) ((1.402d * d2) + d), 0, 255) << 16);
                }
                this.c = true;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void b(o oVar, int i) {
            int l;
            if (i >= 4) {
                oVar.d(3);
                int i2 = i - 4;
                if ((oVar.h() & 128) != 0) {
                    if (i2 >= 7 && (l = oVar.l()) >= 4) {
                        this.h = oVar.i();
                        this.i = oVar.i();
                        this.a.a(l - 4);
                        i2 -= 7;
                    } else {
                        return;
                    }
                }
                int d = this.a.d();
                int c = this.a.c();
                if (d < c && i2 > 0) {
                    int min = Math.min(i2, c - d);
                    oVar.a(this.a.a, d, min);
                    this.a.c(d + min);
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void c(o oVar, int i) {
            if (i >= 19) {
                this.d = oVar.i();
                this.e = oVar.i();
                oVar.d(11);
                this.f = oVar.i();
                this.g = oVar.i();
            }
        }

        public com.google.android.exoplayer2.text.a a() {
            int i;
            int i2;
            int i3;
            if (this.d == 0 || this.e == 0 || this.h == 0 || this.i == 0 || this.a.c() == 0 || this.a.d() != this.a.c() || !this.c) {
                return null;
            }
            this.a.c(0);
            int[] iArr = new int[(this.h * this.i)];
            int i4 = 0;
            while (i4 < iArr.length) {
                int h = this.a.h();
                if (h != 0) {
                    i = i4 + 1;
                    iArr[i4] = this.b[h];
                } else {
                    int h2 = this.a.h();
                    if (h2 != 0) {
                        if ((h2 & 64) == 0) {
                            i2 = h2 & 63;
                        } else {
                            i2 = ((h2 & 63) << 8) | this.a.h();
                        }
                        if ((h2 & 128) == 0) {
                            i3 = 0;
                        } else {
                            i3 = this.b[this.a.h()];
                        }
                        i = i2 + i4;
                        Arrays.fill(iArr, i4, i, i3);
                    }
                }
                i4 = i;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr, this.h, this.i, Bitmap.Config.ARGB_8888);
            int i5 = this.d;
            float f = ((float) this.f) / ((float) i5);
            int i6 = this.e;
            return new com.google.android.exoplayer2.text.a(createBitmap, f, 0, ((float) this.g) / ((float) i6), 0, ((float) this.h) / ((float) i5), ((float) this.i) / ((float) i6));
        }

        public void b() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.a.a(0);
            this.c = false;
        }
    }
}
