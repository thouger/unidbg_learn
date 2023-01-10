package com.sobot.chat.widget.zxing.qrcode.detector;

import com.sobot.chat.widget.zxing.FormatException;
import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.common.a;
import com.sobot.chat.widget.zxing.common.e;
import com.sobot.chat.widget.zxing.common.i;
import com.sobot.chat.widget.zxing.f;
import com.sobot.chat.widget.zxing.g;

/* compiled from: Detector */
public class c {
    private final a a;
    private g b;

    public c(a aVar) {
        this.a = aVar;
    }

    /* access modifiers changed from: protected */
    public final a a() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final e a(e eVar) throws NotFoundException, FormatException {
        d b = eVar.b();
        d c = eVar.c();
        d a = eVar.a();
        float a2 = a(b, c, a);
        if (a2 >= 1.0f) {
            int a3 = a(b, c, a, a2);
            com.sobot.chat.widget.zxing.qrcode.decoder.g a4 = com.sobot.chat.widget.zxing.qrcode.decoder.g.a(a3);
            int d = a4.d() - 7;
            a aVar = null;
            if (a4.b().length > 0) {
                float a5 = (c.a() - b.a()) + a.a();
                float b2 = (c.b() - b.b()) + a.b();
                float f = 1.0f - (3.0f / ((float) d));
                int a6 = (int) (b.a() + ((a5 - b.a()) * f));
                int b3 = (int) (b.b() + (f * (b2 - b.b())));
                for (int i = 4; i <= 16; i <<= 1) {
                    try {
                        aVar = a(a2, a6, b3, (float) i);
                        break;
                    } catch (NotFoundException unused) {
                    }
                }
            }
            return new e(a(this.a, a(b, c, a, aVar, a3), a3), aVar == null ? new f[]{a, b, c} : new f[]{a, b, c, aVar});
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static i a(f fVar, f fVar2, f fVar3, f fVar4, int i) {
        float f;
        float f2;
        float f3;
        float f4 = ((float) i) - 3.5f;
        if (fVar4 != null) {
            f2 = fVar4.a();
            f = fVar4.b();
            f3 = f4 - 3.0f;
        } else {
            f2 = (fVar2.a() - fVar.a()) + fVar3.a();
            f = (fVar2.b() - fVar.b()) + fVar3.b();
            f3 = f4;
        }
        return i.a(3.5f, 3.5f, f4, 3.5f, f3, f3, 3.5f, f4, fVar.a(), fVar.b(), fVar2.a(), fVar2.b(), f2, f, fVar3.a(), fVar3.b());
    }

    private static a a(a aVar, i iVar, int i) throws NotFoundException {
        return com.sobot.chat.widget.zxing.common.g.a().a(aVar, i, i, iVar);
    }

    private static int a(f fVar, f fVar2, f fVar3, float f) throws NotFoundException {
        int a = ((com.sobot.chat.widget.zxing.common.a.a.a(f.a(fVar, fVar2) / f) + com.sobot.chat.widget.zxing.common.a.a.a(f.a(fVar, fVar3) / f)) / 2) + 7;
        int i = a & 3;
        if (i == 0) {
            return a + 1;
        }
        if (i == 2) {
            return a - 1;
        }
        if (i != 3) {
            return a;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: protected */
    public final float a(f fVar, f fVar2, f fVar3) {
        return (a(fVar, fVar2) + a(fVar, fVar3)) / 2.0f;
    }

    private float a(f fVar, f fVar2) {
        float a = a((int) fVar.a(), (int) fVar.b(), (int) fVar2.a(), (int) fVar2.b());
        float a2 = a((int) fVar2.a(), (int) fVar2.b(), (int) fVar.a(), (int) fVar.b());
        if (Float.isNaN(a)) {
            return a2 / 7.0f;
        }
        return Float.isNaN(a2) ? a / 7.0f : (a + a2) / 14.0f;
    }

    private float a(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        float f2;
        float b = b(i, i2, i3, i4);
        int i6 = i - (i3 - i);
        int i7 = 0;
        if (i6 < 0) {
            f = ((float) i) / ((float) (i - i6));
            i5 = 0;
        } else if (i6 >= this.a.a()) {
            f = ((float) ((this.a.a() - 1) - i)) / ((float) (i6 - i));
            i5 = this.a.a() - 1;
        } else {
            i5 = i6;
            f = 1.0f;
        }
        float f3 = (float) i2;
        int i8 = (int) (f3 - (((float) (i4 - i2)) * f));
        if (i8 < 0) {
            f2 = f3 / ((float) (i2 - i8));
        } else if (i8 >= this.a.b()) {
            f2 = ((float) ((this.a.b() - 1) - i2)) / ((float) (i8 - i2));
            i7 = this.a.b() - 1;
        } else {
            i7 = i8;
            f2 = 1.0f;
        }
        return (b + b(i, i2, (int) (((float) i) + (((float) (i5 - i)) * f2)), i7)) - 1.0f;
    }

    private float b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z;
        c cVar;
        boolean z2;
        boolean z3 = true;
        boolean z4 = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z4) {
            i5 = i;
            i7 = i2;
            i6 = i3;
            i8 = i4;
        } else {
            i7 = i;
            i5 = i2;
            i8 = i3;
            i6 = i4;
        }
        int abs = Math.abs(i8 - i7);
        int abs2 = Math.abs(i6 - i5);
        int i11 = 2;
        int i12 = (-abs) / 2;
        int i13 = -1;
        int i14 = i7 < i8 ? 1 : -1;
        if (i5 < i6) {
            i13 = 1;
        }
        int i15 = i8 + i14;
        int i16 = i5;
        int i17 = i12;
        int i18 = 0;
        int i19 = i7;
        while (true) {
            if (i19 == i15) {
                i9 = i15;
                i10 = i11;
                break;
            }
            int i20 = z4 ? i16 : i19;
            int i21 = z4 ? i19 : i16;
            if (i18 == z3) {
                z = z4;
                i9 = i15;
                z2 = z3;
                cVar = this;
            } else {
                cVar = this;
                z = z4;
                i9 = i15;
                z2 = false;
            }
            if (z2 == cVar.a.a(i20, i21)) {
                if (i18 == 2) {
                    return com.sobot.chat.widget.zxing.common.a.a.a(i19, i16, i7, i5);
                }
                i18++;
            }
            i17 += abs2;
            if (i17 > 0) {
                if (i16 == i6) {
                    i10 = 2;
                    break;
                }
                i16 += i13;
                i17 -= abs;
            }
            i19 += i14;
            i15 = i9;
            z4 = z;
            z3 = true;
            i11 = 2;
        }
        if (i18 == i10) {
            return com.sobot.chat.widget.zxing.common.a.a.a(i9, i6, i7, i5);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final a a(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.a.a() - 1, i + i3) - max;
        float f3 = 3.0f * f;
        if (((float) min) >= f3) {
            int max2 = Math.max(0, i2 - i3);
            int min2 = Math.min(this.a.b() - 1, i2 + i3) - max2;
            if (((float) min2) >= f3) {
                return new b(this.a, max, max2, min, min2, f, this.b).a();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
