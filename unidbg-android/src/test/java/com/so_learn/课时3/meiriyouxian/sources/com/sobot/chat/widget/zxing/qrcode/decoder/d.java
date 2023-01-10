package com.sobot.chat.widget.zxing.qrcode.decoder;

import com.sobot.chat.widget.zxing.ChecksumException;
import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.FormatException;
import com.sobot.chat.widget.zxing.common.reedsolomon.ReedSolomonException;
import com.sobot.chat.widget.zxing.common.reedsolomon.a;
import com.sobot.chat.widget.zxing.common.reedsolomon.c;
import java.util.Map;

/* compiled from: Decoder */
public final class d {
    private final c a = new c(a.e);

    public com.sobot.chat.widget.zxing.common.c a(com.sobot.chat.widget.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        ChecksumException e;
        a aVar2 = new a(aVar);
        FormatException formatException = null;
        try {
            return a(aVar2, map);
        } catch (FormatException e2) {
            e = null;
            formatException = e2;
            try {
                aVar2.d();
                aVar2.a(true);
                aVar2.b();
                aVar2.a();
                aVar2.e();
                com.sobot.chat.widget.zxing.common.c a = a(aVar2, map);
                a.a(new f(true));
                return a;
            } catch (ChecksumException | FormatException unused) {
                if (formatException != null) {
                    throw formatException;
                }
                throw e;
            }
        } catch (ChecksumException e3) {
            e = e3;
            aVar2.d();
            aVar2.a(true);
            aVar2.b();
            aVar2.a();
            aVar2.e();
            com.sobot.chat.widget.zxing.common.c a = a(aVar2, map);
            a.a(new f(true));
            return a;
        }
    }

    private com.sobot.chat.widget.zxing.common.c a(a aVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        g b = aVar.b();
        ErrorCorrectionLevel a = aVar.a().a();
        b[] a2 = b.a(aVar.c(), b, a);
        int i = 0;
        for (b bVar : a2) {
            i += bVar.a();
        }
        byte[] bArr = new byte[i];
        int length = a2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            b bVar2 = a2[i2];
            byte[] b2 = bVar2.b();
            int a3 = bVar2.a();
            a(b2, a3);
            int i4 = i3;
            int i5 = 0;
            while (i5 < a3) {
                bArr[i4] = b2[i5];
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return c.a(bArr, b, a, map);
    }

    private void a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.a.a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
