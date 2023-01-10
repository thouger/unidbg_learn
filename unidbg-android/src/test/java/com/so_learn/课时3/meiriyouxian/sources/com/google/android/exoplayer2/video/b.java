package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.o;
import java.util.Collections;
import java.util.List;

/* compiled from: HevcConfig */
public final class b {
    public final List<byte[]> a;
    public final int b;

    public static b a(o oVar) throws ParserException {
        List list;
        try {
            oVar.d(21);
            int h = oVar.h() & 3;
            int h2 = oVar.h();
            int d = oVar.d();
            int i = 0;
            int i2 = 0;
            while (i < h2) {
                oVar.d(1);
                int i3 = oVar.i();
                int i4 = i2;
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = oVar.i();
                    i4 += i6 + 4;
                    oVar.d(i6);
                }
                i++;
                i2 = i4;
            }
            oVar.c(d);
            byte[] bArr = new byte[i2];
            int i7 = 0;
            int i8 = 0;
            while (i7 < h2) {
                oVar.d(1);
                int i9 = oVar.i();
                int i10 = i8;
                for (int i11 = 0; i11 < i9; i11++) {
                    int i12 = oVar.i();
                    System.arraycopy(m.a, 0, bArr, i10, m.a.length);
                    int length = i10 + m.a.length;
                    System.arraycopy(oVar.a, oVar.d(), bArr, length, i12);
                    i10 = length + i12;
                    oVar.d(i12);
                }
                i7++;
                i8 = i10;
            }
            if (i2 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new b(list, h + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing HEVC config", e);
        }
    }

    private b(List<byte[]> list, int i) {
        this.a = list;
        this.b = i;
    }
}
