package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.c;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.o;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AvcConfig */
public final class a {
    public final List<byte[]> a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public static a a(o oVar) throws ParserException {
        float f;
        int i;
        int i2;
        try {
            oVar.d(4);
            int h = (oVar.h() & 3) + 1;
            if (h != 3) {
                ArrayList arrayList = new ArrayList();
                int h2 = oVar.h() & 31;
                for (int i3 = 0; i3 < h2; i3++) {
                    arrayList.add(b(oVar));
                }
                int h3 = oVar.h();
                for (int i4 = 0; i4 < h3; i4++) {
                    arrayList.add(b(oVar));
                }
                if (h2 > 0) {
                    m.b a = m.a((byte[]) arrayList.get(0), h, ((byte[]) arrayList.get(0)).length);
                    int i5 = a.e;
                    int i6 = a.f;
                    f = a.g;
                    i2 = i5;
                    i = i6;
                } else {
                    f = 1.0f;
                    i2 = -1;
                    i = -1;
                }
                return new a(arrayList, h, i2, i, f);
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing AVC config", e);
        }
    }

    private a(List<byte[]> list, int i, int i2, int i3, float f) {
        this.a = list;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f;
    }

    private static byte[] b(o oVar) {
        int i = oVar.i();
        int d = oVar.d();
        oVar.d(i);
        return c.a(oVar.a, d, i);
    }
}
