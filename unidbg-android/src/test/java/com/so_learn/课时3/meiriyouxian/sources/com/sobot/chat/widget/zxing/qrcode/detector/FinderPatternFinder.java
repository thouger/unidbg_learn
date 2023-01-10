package com.sobot.chat.widget.zxing.qrcode.detector;

import com.sobot.chat.widget.zxing.common.a;
import com.sobot.chat.widget.zxing.g;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FinderPatternFinder {
    private static final EstimatedModuleComparator a = new EstimatedModuleComparator();
    private final a b;
    private final List<d> c = new ArrayList();
    private final int[] d = new int[5];
    private final g e;

    public FinderPatternFinder(a aVar, g gVar) {
        this.b = aVar;
        this.e = gVar;
    }

    /* access modifiers changed from: protected */
    public final a a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final List<d> b() {
        return this.c;
    }

    private static float a(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    protected static boolean a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = ((float) i) / 7.0f;
        float f2 = f / 2.0f;
        if (Math.abs(f - ((float) iArr[0])) >= f2 || Math.abs(f - ((float) iArr[1])) >= f2 || Math.abs((f * 3.0f) - ((float) iArr[2])) >= 3.0f * f2 || Math.abs(f - ((float) iArr[3])) >= f2 || Math.abs(f - ((float) iArr[4])) >= f2) {
            return false;
        }
        return true;
    }

    protected static boolean b(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = ((float) i) / 7.0f;
        float f2 = f / 1.333f;
        if (Math.abs(f - ((float) iArr[0])) >= f2 || Math.abs(f - ((float) iArr[1])) >= f2 || Math.abs((f * 3.0f) - ((float) iArr[2])) >= 3.0f * f2 || Math.abs(f - ((float) iArr[3])) >= f2 || Math.abs(f - ((float) iArr[4])) >= f2) {
            return false;
        }
        return true;
    }

    private int[] c() {
        c(this.d);
        return this.d;
    }

    /* access modifiers changed from: protected */
    public final void c(int[] iArr) {
        Arrays.fill(iArr, 0);
    }

    /* access modifiers changed from: protected */
    public final void d(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    private boolean a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int[] c = c();
        int i6 = 0;
        while (i >= i6 && i2 >= i6 && this.b.a(i2 - i6, i - i6)) {
            c[2] = c[2] + 1;
            i6++;
        }
        if (c[2] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && !this.b.a(i2 - i6, i - i6)) {
            c[1] = c[1] + 1;
            i6++;
        }
        if (c[1] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && this.b.a(i2 - i6, i - i6)) {
            c[0] = c[0] + 1;
            i6++;
        }
        if (c[0] == 0) {
            return false;
        }
        int b = this.b.b();
        int a2 = this.b.a();
        int i7 = 1;
        while (true) {
            int i8 = i + i7;
            if (i8 >= b || (i5 = i2 + i7) >= a2 || !this.b.a(i5, i8)) {
                break;
            }
            c[2] = c[2] + 1;
            i7++;
        }
        while (true) {
            int i9 = i + i7;
            if (i9 >= b || (i4 = i2 + i7) >= a2 || this.b.a(i4, i9)) {
                break;
            }
            c[3] = c[3] + 1;
            i7++;
        }
        if (c[3] == 0) {
            return false;
        }
        while (true) {
            int i10 = i + i7;
            if (i10 >= b || (i3 = i2 + i7) >= a2 || !this.b.a(i3, i10)) {
                break;
            }
            c[4] = c[4] + 1;
            i7++;
        }
        if (c[4] == 0) {
            return false;
        }
        return b(c);
    }

    private float a(int i, int i2, int i3, int i4) {
        a aVar = this.b;
        int b = aVar.b();
        int[] c = c();
        int i5 = i;
        while (i5 >= 0 && aVar.a(i2, i5)) {
            c[2] = c[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !aVar.a(i2, i5) && c[1] <= i3) {
            c[1] = c[1] + 1;
            i5--;
        }
        if (i5 < 0 || c[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && aVar.a(i2, i5) && c[0] <= i3) {
            c[0] = c[0] + 1;
            i5--;
        }
        if (c[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < b && aVar.a(i2, i6)) {
            c[2] = c[2] + 1;
            i6++;
        }
        if (i6 == b) {
            return Float.NaN;
        }
        while (i6 < b && !aVar.a(i2, i6) && c[3] < i3) {
            c[3] = c[3] + 1;
            i6++;
        }
        if (i6 == b || c[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < b && aVar.a(i2, i6) && c[4] < i3) {
            c[4] = c[4] + 1;
            i6++;
        }
        if (c[4] < i3 && Math.abs(((((c[0] + c[1]) + c[2]) + c[3]) + c[4]) - i4) * 5 < i4 * 2 && a(c)) {
            return a(c, i6);
        }
        return Float.NaN;
    }

    private float b(int i, int i2, int i3, int i4) {
        a aVar = this.b;
        int a2 = aVar.a();
        int[] c = c();
        int i5 = i;
        while (i5 >= 0 && aVar.a(i5, i2)) {
            c[2] = c[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !aVar.a(i5, i2) && c[1] <= i3) {
            c[1] = c[1] + 1;
            i5--;
        }
        if (i5 < 0 || c[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && aVar.a(i5, i2) && c[0] <= i3) {
            c[0] = c[0] + 1;
            i5--;
        }
        if (c[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < a2 && aVar.a(i6, i2)) {
            c[2] = c[2] + 1;
            i6++;
        }
        if (i6 == a2) {
            return Float.NaN;
        }
        while (i6 < a2 && !aVar.a(i6, i2) && c[3] < i3) {
            c[3] = c[3] + 1;
            i6++;
        }
        if (i6 == a2 || c[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < a2 && aVar.a(i6, i2) && c[4] < i3) {
            c[4] = c[4] + 1;
            i6++;
        }
        if (c[4] < i3 && Math.abs(((((c[0] + c[1]) + c[2]) + c[3]) + c[4]) - i4) * 5 < i4 && a(c)) {
            return a(c, i6);
        }
        return Float.NaN;
    }

    /* access modifiers changed from: protected */
    public final boolean a(int[] iArr, int i, int i2) {
        boolean z = false;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int a2 = (int) a(iArr, i2);
        float a3 = a(i, a2, iArr[2], i3);
        if (!Float.isNaN(a3)) {
            int i4 = (int) a3;
            float b = b(a2, i4, iArr[2], i3);
            if (!Float.isNaN(b) && a(i4, (int) b)) {
                float f = ((float) i3) / 7.0f;
                int i5 = 0;
                while (true) {
                    if (i5 >= this.c.size()) {
                        break;
                    }
                    d dVar = this.c.get(i5);
                    if (dVar.a(f, a3, b)) {
                        this.c.set(i5, dVar.b(a3, b, f));
                        z = true;
                        break;
                    }
                    i5++;
                }
                if (!z) {
                    d dVar2 = new d(b, a3, f);
                    this.c.add(dVar2);
                    g gVar = this.e;
                    if (gVar != null) {
                        gVar.a(dVar2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static final class EstimatedModuleComparator implements Serializable, Comparator<d> {
        private EstimatedModuleComparator() {
        }

        public int compare(d dVar, d dVar2) {
            return Float.compare(dVar.c(), dVar2.c());
        }
    }
}
