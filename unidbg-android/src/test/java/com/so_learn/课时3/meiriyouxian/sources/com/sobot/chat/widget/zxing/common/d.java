package com.sobot.chat.widget.zxing.common;

import com.sobot.chat.widget.zxing.NotFoundException;

/* compiled from: DefaultGridSampler */
public final class d extends g {
    @Override // com.sobot.chat.widget.zxing.common.g
    public a a(a aVar, int i, int i2, i iVar) throws NotFoundException {
        if (i <= 0 || i2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        a aVar2 = new a(i, i2);
        float[] fArr = new float[(i * 2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int length = fArr.length;
            float f = ((float) i3) + 0.5f;
            for (int i4 = 0; i4 < length; i4 += 2) {
                fArr[i4] = ((float) (i4 / 2)) + 0.5f;
                fArr[i4 + 1] = f;
            }
            iVar.a(fArr);
            a(aVar, fArr);
            for (int i5 = 0; i5 < length; i5 += 2) {
                try {
                    if (aVar.a((int) fArr[i5], (int) fArr[i5 + 1])) {
                        aVar2.b(i5 / 2, i3);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return aVar2;
    }
}
