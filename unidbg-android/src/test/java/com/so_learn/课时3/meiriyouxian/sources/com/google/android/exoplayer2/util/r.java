package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: SlidingPercentile */
public class r {
    private static final Comparator<a> a = $$Lambda$r$gXBAgz4e5jHK2EtRLQeN7A0KQ.INSTANCE;
    private static final Comparator<a> b = $$Lambda$r$KwDFIsDL6bldIrl8Rryvh8jABnc.INSTANCE;
    private final int c;
    private final ArrayList<a> d = new ArrayList<>();
    private final a[] e = new a[5];
    private int f = -1;
    private int g;
    private int h;
    private int i;

    /* access modifiers changed from: private */
    public static /* synthetic */ int b(a aVar, a aVar2) {
        return aVar.a - aVar2.a;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(a aVar, a aVar2) {
        return Float.compare(aVar.c, aVar2.c);
    }

    public r(int i) {
        this.c = i;
    }

    public void a(int i, float f) {
        a aVar;
        a();
        int i2 = this.i;
        if (i2 > 0) {
            a[] aVarArr = this.e;
            int i3 = i2 - 1;
            this.i = i3;
            aVar = aVarArr[i3];
        } else {
            aVar = new a();
        }
        int i4 = this.g;
        this.g = i4 + 1;
        aVar.a = i4;
        aVar.b = i;
        aVar.c = f;
        this.d.add(aVar);
        this.h += i;
        while (true) {
            int i5 = this.h;
            int i6 = this.c;
            if (i5 > i6) {
                int i7 = i5 - i6;
                a aVar2 = this.d.get(0);
                if (aVar2.b <= i7) {
                    this.h -= aVar2.b;
                    this.d.remove(0);
                    int i8 = this.i;
                    if (i8 < 5) {
                        a[] aVarArr2 = this.e;
                        this.i = i8 + 1;
                        aVarArr2[i8] = aVar2;
                    }
                } else {
                    aVar2.b -= i7;
                    this.h -= i7;
                }
            } else {
                return;
            }
        }
    }

    public float a(float f) {
        b();
        float f2 = f * ((float) this.h);
        int i = 0;
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            a aVar = this.d.get(i2);
            i += aVar.b;
            if (((float) i) >= f2) {
                return aVar.c;
            }
        }
        if (this.d.isEmpty()) {
            return Float.NaN;
        }
        ArrayList<a> arrayList = this.d;
        return arrayList.get(arrayList.size() - 1).c;
    }

    private void a() {
        if (this.f != 1) {
            Collections.sort(this.d, a);
            this.f = 1;
        }
    }

    private void b() {
        if (this.f != 0) {
            Collections.sort(this.d, b);
            this.f = 0;
        }
    }

    /* compiled from: SlidingPercentile */
    /* access modifiers changed from: private */
    public static class a {
        public int a;
        public int b;
        public float c;

        private a() {
        }
    }
}
