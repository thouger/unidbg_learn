package com.sobot.chat.widget.zxing.common.reedsolomon;

import android.text.format.DateFormat;
import com.android.internal.transition.EpicenterTranslateClipReveal;

/* compiled from: GenericGFPoly */
/* access modifiers changed from: package-private */
public final class b {
    private final a a;
    private final int[] b;

    b(a aVar, int[] iArr) {
        if (iArr.length != 0) {
            this.a = aVar;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.b = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.b = new int[]{0};
                return;
            }
            this.b = new int[(length - i)];
            int[] iArr2 = this.b;
            System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b.length - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.b[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        int[] iArr = this.b;
        return iArr[(iArr.length - 1) - i];
    }

    /* access modifiers changed from: package-private */
    public int b(int i) {
        if (i == 0) {
            return a(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int i3 : this.b) {
                i2 = a.b(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.b;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            i4 = a.b(this.a.c(i, i4), this.b[i5]);
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    public b a(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b()) {
            return bVar;
        } else {
            if (bVar.b()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = bVar.b;
            if (iArr.length > iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr3[i] = a.b(iArr[i - length], iArr2[i]);
            }
            return new b(this.a, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public b b(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b() || bVar.b()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = bVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = a.b(iArr3[i4], this.a.c(i2, iArr2[i3]));
                }
            }
            return new b(this.a, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public b c(int i) {
        if (i == 0) {
            return this.a.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.a.c(this.b[i2], i);
        }
        return new b(this.a, iArr);
    }

    /* access modifiers changed from: package-private */
    public b a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.c(this.b[i3], i2);
            }
            return new b(this.a, iArr);
        }
    }

    public String toString() {
        if (b()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(a() * 8);
        for (int a = a(); a >= 0; a--) {
            int a2 = a(a);
            if (a2 != 0) {
                if (a2 < 0) {
                    if (a == a()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    a2 = -a2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (a == 0 || a2 != 1) {
                    int b = this.a.b(a2);
                    if (b == 0) {
                        sb.append('1');
                    } else if (b == 1) {
                        sb.append(DateFormat.AM_PM);
                    } else {
                        sb.append("a^");
                        sb.append(b);
                    }
                }
                if (a != 0) {
                    if (a == 1) {
                        sb.append(EpicenterTranslateClipReveal.StateProperty.TARGET_X);
                    } else {
                        sb.append("x^");
                        sb.append(a);
                    }
                }
            }
        }
        return sb.toString();
    }
}
