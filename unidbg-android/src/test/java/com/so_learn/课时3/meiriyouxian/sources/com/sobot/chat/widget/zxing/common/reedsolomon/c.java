package com.sobot.chat.widget.zxing.common.reedsolomon;

/* compiled from: ReedSolomonDecoder */
public final class c {
    private final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public void a(int[] iArr, int i) throws ReedSolomonException {
        b bVar = new b(this.a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            a aVar = this.a;
            int b = bVar.b(aVar.a(aVar.d() + i2));
            iArr2[(iArr2.length - 1) - i2] = b;
            if (b != 0) {
                z = false;
            }
        }
        if (!z) {
            b[] a = a(this.a.a(i, 1), new b(this.a, iArr2), i);
            b bVar2 = a[0];
            b bVar3 = a[1];
            int[] a2 = a(bVar2);
            int[] a3 = a(bVar3, a2);
            for (int i3 = 0; i3 < a2.length; i3++) {
                int length = (iArr.length - 1) - this.a.b(a2[i3]);
                if (length >= 0) {
                    iArr[length] = a.b(iArr[length], a3[i3]);
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private b[] a(b bVar, b bVar2, int i) throws ReedSolomonException {
        if (bVar.a() < bVar2.a()) {
            bVar2 = bVar;
            bVar = bVar2;
        }
        b a = this.a.a();
        b b = this.a.b();
        do {
            bVar2 = bVar;
            bVar = bVar2;
            a = b;
            if (bVar.a() < i / 2) {
                int a2 = a.a(0);
                if (a2 != 0) {
                    int c = this.a.c(a2);
                    return new b[]{a.c(c), bVar.c(c)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else if (!bVar.b()) {
                b a3 = this.a.a();
                int c2 = this.a.c(bVar.a(bVar.a()));
                while (bVar2.a() >= bVar.a() && !bVar2.b()) {
                    int a4 = bVar2.a() - bVar.a();
                    int c3 = this.a.c(bVar2.a(bVar2.a()), c2);
                    a3 = a3.a(this.a.a(a4, c3));
                    bVar2 = bVar2.a(bVar.a(a4, c3));
                }
                b = a3.b(a).a(a);
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        } while (bVar2.a() < bVar.a());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    private int[] a(b bVar) throws ReedSolomonException {
        int a = bVar.a();
        int i = 0;
        if (a == 1) {
            return new int[]{bVar.a(1)};
        }
        int[] iArr = new int[a];
        for (int i2 = 1; i2 < this.a.c() && i < a; i2++) {
            if (bVar.b(i2) == 0) {
                iArr[i] = this.a.c(i2);
                i++;
            }
        }
        if (i == a) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] a(b bVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int c = this.a.c(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int c2 = this.a.c(iArr[i3], c);
                    i2 = this.a.c(i2, (c2 & 1) == 0 ? c2 | 1 : c2 & -2);
                }
            }
            iArr2[i] = this.a.c(bVar.b(c), this.a.c(i2));
            if (this.a.d() != 0) {
                iArr2[i] = this.a.c(iArr2[i], c);
            }
        }
        return iArr2;
    }
}
