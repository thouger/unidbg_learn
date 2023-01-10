package com.sobot.chat.widget.zxing.qrcode.detector;

import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.common.a;
import com.sobot.chat.widget.zxing.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AlignmentPatternFinder */
/* access modifiers changed from: package-private */
public final class b {
    private final a a;
    private final List<a> b = new ArrayList(5);
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final float g;
    private final int[] h;
    private final g i;

    b(a aVar, int i, int i2, int i3, int i4, float f, g gVar) {
        this.a = aVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = f;
        this.h = new int[3];
        this.i = gVar;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0021: APUT  (r4v2 int[]), (0 ??[int, short, byte, char]), (0 int) */
    /* access modifiers changed from: package-private */
    public a a() throws NotFoundException {
        a a;
        a a2;
        int i = this.c;
        int i2 = this.f;
        int i3 = this.e + i;
        int i4 = this.d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.a.a(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.a.a(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i8 != 2) {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                } else if (a(iArr) && (a2 = a(iArr, i6, i7)) != null) {
                    return a2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                }
                i7++;
            }
            if (a(iArr) && (a = a(iArr, i6, i3)) != null) {
                return a;
            }
        }
        if (!this.b.isEmpty()) {
            return this.b.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static float a(int[] iArr, int i) {
        return ((float) (i - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    private boolean a(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - ((float) iArr[i])) >= f2) {
                return false;
            }
        }
        return true;
    }

    private float a(int i, int i2, int i3, int i4) {
        a aVar = this.a;
        int b = aVar.b();
        int[] iArr = this.h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && aVar.a(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        if (i5 < 0 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && !aVar.a(i2, i5) && iArr[0] <= i3) {
            iArr[0] = iArr[0] + 1;
            i5--;
        }
        if (iArr[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < b && aVar.a(i2, i6) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i6++;
        }
        if (i6 == b || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i6 < b && !aVar.a(i2, i6) && iArr[2] <= i3) {
            iArr[2] = iArr[2] + 1;
            i6++;
        }
        if (iArr[2] <= i3 && Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 < i4 * 2 && a(iArr)) {
            return a(iArr, i6);
        }
        return Float.NaN;
    }

    private a a(int[] iArr, int i, int i2) {
        int i3 = iArr[0] + iArr[1] + iArr[2];
        float a = a(iArr, i2);
        float a2 = a(i, (int) a, iArr[1] * 2, i3);
        if (Float.isNaN(a2)) {
            return null;
        }
        float f = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
        for (a aVar : this.b) {
            if (aVar.a(f, a2, a)) {
                return aVar.b(a2, a, f);
            }
        }
        a aVar2 = new a(a, a2, f);
        this.b.add(aVar2);
        g gVar = this.i;
        if (gVar == null) {
            return null;
        }
        gVar.a(aVar2);
        return null;
    }
}
