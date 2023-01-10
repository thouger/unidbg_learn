package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.a;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: Sonic */
/* access modifiers changed from: package-private */
public final class p {
    private final int a;
    private final int b;
    private final float c;
    private final float d;
    private final float e;
    private final int f;
    private final int g;
    private final int h = (this.g * 2);
    private final short[] i;
    private short[] j;
    private int k;
    private short[] l;
    private int m;
    private short[] n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;

    public p(int i, int i2, float f, float f2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = f;
        this.d = f2;
        this.e = ((float) i) / ((float) i3);
        this.f = i / 400;
        this.g = i / 65;
        int i4 = this.h;
        this.i = new short[i4];
        this.j = new short[(i4 * i2)];
        this.l = new short[(i4 * i2)];
        this.n = new short[(i4 * i2)];
    }

    public void a(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i = this.b;
        int i2 = remaining / i;
        this.j = a(this.j, this.k, i2);
        shortBuffer.get(this.j, this.k * this.b, ((i * i2) * 2) / 2);
        this.k += i2;
        d();
    }

    public void b(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.b, this.m);
        shortBuffer.put(this.l, 0, this.b * min);
        this.m -= min;
        short[] sArr = this.l;
        int i = this.b;
        System.arraycopy(sArr, min * i, sArr, 0, this.m * i);
    }

    public void a() {
        int i;
        int i2 = this.k;
        float f = this.c;
        float f2 = this.d;
        int i3 = this.m + ((int) ((((((float) i2) / (f / f2)) + ((float) this.o)) / (this.e * f2)) + 0.5f));
        this.j = a(this.j, i2, (this.h * 2) + i2);
        int i4 = 0;
        while (true) {
            i = this.h;
            int i5 = this.b;
            if (i4 >= i * 2 * i5) {
                break;
            }
            this.j[(i5 * i2) + i4] = 0;
            i4++;
        }
        this.k += i * 2;
        d();
        if (this.m > i3) {
            this.m = i3;
        }
        this.k = 0;
        this.r = 0;
        this.o = 0;
    }

    public void b() {
        this.k = 0;
        this.m = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
    }

    public int c() {
        return this.m;
    }

    private short[] a(short[] sArr, int i, int i2) {
        int length = sArr.length;
        int i3 = this.b;
        int i4 = length / i3;
        if (i + i2 <= i4) {
            return sArr;
        }
        return Arrays.copyOf(sArr, (((i4 * 3) / 2) + i2) * i3);
    }

    private void a(int i) {
        int i2 = this.k - i;
        short[] sArr = this.j;
        int i3 = this.b;
        System.arraycopy(sArr, i * i3, sArr, 0, i3 * i2);
        this.k = i2;
    }

    private void b(short[] sArr, int i, int i2) {
        this.l = a(this.l, this.m, i2);
        int i3 = this.b;
        System.arraycopy(sArr, i * i3, this.l, this.m * i3, i3 * i2);
        this.m += i2;
    }

    private int b(int i) {
        int min = Math.min(this.h, this.r);
        b(this.j, i, min);
        this.r -= min;
        return min;
    }

    private void c(short[] sArr, int i, int i2) {
        int i3 = this.h / i2;
        int i4 = this.b;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.i[i7] = (short) (i8 / i5);
        }
    }

    private int a(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.b;
        int i5 = 1;
        int i6 = 0;
        int i7 = 255;
        int i8 = 0;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                i9 += Math.abs(sArr[i4 + i10] - sArr[(i4 + i2) + i10]);
            }
            if (i9 * i8 < i5 * i2) {
                i8 = i2;
                i5 = i9;
            }
            if (i9 * i7 > i6 * i2) {
                i7 = i2;
                i6 = i9;
            }
            i2++;
        }
        this.u = i5 / i8;
        this.v = i6 / i7;
        return i8;
    }

    private boolean a(int i, int i2) {
        if (i == 0 || this.s == 0 || i2 > i * 3 || i * 2 <= this.t * 3) {
            return false;
        }
        return true;
    }

    private int a(short[] sArr, int i) {
        int i2;
        int i3 = this.a;
        int i4 = i3 > 4000 ? i3 / 4000 : 1;
        if (this.b == 1 && i4 == 1) {
            i2 = a(sArr, i, this.f, this.g);
        } else {
            c(sArr, i, i4);
            int a = a(this.i, 0, this.f / i4, this.g / i4);
            if (i4 != 1) {
                int i5 = a * i4;
                int i6 = i4 * 4;
                int i7 = i5 - i6;
                int i8 = i5 + i6;
                int i9 = this.f;
                if (i7 >= i9) {
                    i9 = i7;
                }
                int i10 = this.g;
                if (i8 > i10) {
                    i8 = i10;
                }
                if (this.b == 1) {
                    i2 = a(sArr, i, i9, i8);
                } else {
                    c(sArr, i, 1);
                    i2 = a(this.i, 0, i9, i8);
                }
            } else {
                i2 = a;
            }
        }
        int i11 = a(this.u, this.v) ? this.s : i2;
        this.t = this.u;
        this.s = i2;
        return i11;
    }

    private void c(int i) {
        int i2 = this.m - i;
        this.n = a(this.n, this.o, i2);
        short[] sArr = this.l;
        int i3 = this.b;
        System.arraycopy(sArr, i * i3, this.n, this.o * i3, i3 * i2);
        this.m = i;
        this.o += i2;
    }

    private void d(int i) {
        if (i != 0) {
            short[] sArr = this.n;
            int i2 = this.b;
            System.arraycopy(sArr, i * i2, sArr, 0, (this.o - i) * i2);
            this.o -= i;
        }
    }

    private short b(short[] sArr, int i, int i2, int i3) {
        short s = sArr[i];
        short s2 = sArr[i + this.b];
        int i4 = this.q * i2;
        int i5 = this.p;
        int i6 = i5 * i3;
        int i7 = (i5 + 1) * i3;
        int i8 = i7 - i4;
        int i9 = i7 - i6;
        return (short) (((s * i8) + ((i9 - i8) * s2)) / i9);
    }

    private void a(float f, int i) {
        int i2;
        int i3;
        if (this.m != i) {
            int i4 = this.a;
            int i5 = (int) (((float) i4) / f);
            while (true) {
                if (i5 <= 16384 && i4 <= 16384) {
                    break;
                }
                i5 /= 2;
                i4 /= 2;
            }
            c(i);
            int i6 = 0;
            while (true) {
                int i7 = this.o;
                boolean z = true;
                if (i6 < i7 - 1) {
                    while (true) {
                        i2 = this.p;
                        int i8 = (i2 + 1) * i5;
                        i3 = this.q;
                        if (i8 <= i3 * i4) {
                            break;
                        }
                        this.l = a(this.l, this.m, 1);
                        int i9 = 0;
                        while (true) {
                            int i10 = this.b;
                            if (i9 >= i10) {
                                break;
                            }
                            this.l[(this.m * i10) + i9] = b(this.n, (i10 * i6) + i9, i4, i5);
                            i9++;
                        }
                        this.q++;
                        this.m++;
                    }
                    this.p = i2 + 1;
                    if (this.p == i4) {
                        this.p = 0;
                        if (i3 != i5) {
                            z = false;
                        }
                        a.b(z);
                        this.q = 0;
                    }
                    i6++;
                } else {
                    d(i7 - 1);
                    return;
                }
            }
        }
    }

    private int a(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f >= 2.0f) {
            i3 = (int) (((float) i2) / (f - 1.0f));
        } else {
            this.r = (int) ((((float) i2) * (2.0f - f)) / (f - 1.0f));
            i3 = i2;
        }
        this.l = a(this.l, this.m, i3);
        a(i3, this.b, this.l, this.m, sArr, i, sArr, i + i2);
        this.m += i3;
        return i3;
    }

    private int b(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f < 0.5f) {
            i3 = (int) ((((float) i2) * f) / (1.0f - f));
        } else {
            this.r = (int) ((((float) i2) * ((2.0f * f) - 1.0f)) / (1.0f - f));
            i3 = i2;
        }
        int i4 = i2 + i3;
        this.l = a(this.l, this.m, i4);
        int i5 = this.b;
        System.arraycopy(sArr, i * i5, this.l, this.m * i5, i5 * i2);
        a(i3, this.b, this.l, this.m + i2, sArr, i + i2, sArr, i);
        this.m += i4;
        return i3;
    }

    private void a(float f) {
        int b;
        int i = this.k;
        if (i >= this.h) {
            int i2 = 0;
            do {
                if (this.r > 0) {
                    b = b(i2);
                } else {
                    int a = a(this.j, i2);
                    if (((double) f) > 1.0d) {
                        b = a + a(this.j, i2, f, a);
                    } else {
                        b = b(this.j, i2, f, a);
                    }
                }
                i2 += b;
            } while (this.h + i2 <= i);
            a(i2);
        }
    }

    private void d() {
        int i = this.m;
        float f = this.c;
        float f2 = this.d;
        float f3 = f / f2;
        float f4 = this.e * f2;
        double d = (double) f3;
        if (d > 1.00001d || d < 0.99999d) {
            a(f3);
        } else {
            b(this.j, 0, this.k);
            this.k = 0;
        }
        if (f4 != 1.0f) {
            a(f4, i);
        }
    }

    private static void a(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i4 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i3 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i9] = (short) (((sArr2[i7] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i9 += i2;
                i7 += i2;
                i8 += i2;
            }
        }
    }
}
