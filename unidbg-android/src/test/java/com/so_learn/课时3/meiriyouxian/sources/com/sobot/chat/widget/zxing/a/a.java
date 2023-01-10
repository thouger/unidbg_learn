package com.sobot.chat.widget.zxing.a;

import android.graphics.Bitmap;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.sobot.chat.widget.zxing.DecodeHintType;
import com.sobot.chat.widget.zxing.NotFoundException;
import com.sobot.chat.widget.zxing.b;
import com.sobot.chat.widget.zxing.common.f;
import com.sobot.chat.widget.zxing.common.h;
import com.sobot.chat.widget.zxing.d;
import com.sobot.chat.widget.zxing.e;
import com.sobot.chat.widget.zxing.multi.qrcode.QRCodeMultiReader;
import java.util.HashMap;
import java.util.Map;

/* compiled from: CodeUtils */
public class a {
    public static e[] a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(DecodeHintType.CHARACTER_SET, FileOpt.ENCODE_STR);
        return a(str, hashMap);
    }

    public static e[] a(String str, Map<DecodeHintType, ?> map) {
        e[] eVarArr;
        try {
            QRCodeMultiReader qRCodeMultiReader = new QRCodeMultiReader();
            d a = a(b(str));
            if (a == null) {
                return null;
            }
            try {
                eVarArr = qRCodeMultiReader.a(new b(new h(a)), map);
            } catch (Exception unused) {
                try {
                    eVarArr = qRCodeMultiReader.a(new b(new f(a)));
                } catch (NotFoundException unused2) {
                    eVarArr = null;
                }
            } catch (Throwable th) {
                qRCodeMultiReader.b();
                throw th;
            }
            qRCodeMultiReader.b();
            return eVarArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap b(java.lang.String r6) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            android.graphics.BitmapFactory.decodeFile(r6, r0)
            int r2 = r0.outWidth
            int r3 = r0.outHeight
            if (r2 <= r3) goto L_0x001e
            float r4 = (float) r2
            r5 = 1145569280(0x44480000, float:800.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x001e
            int r2 = r0.outWidth
            float r2 = (float) r2
            float r2 = r2 / r5
        L_0x001c:
            int r2 = (int) r2
            goto L_0x002d
        L_0x001e:
            if (r2 >= r3) goto L_0x002c
            float r2 = (float) r3
            r3 = 1139802112(0x43f00000, float:480.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x002c
            int r2 = r0.outHeight
            float r2 = (float) r2
            float r2 = r2 / r3
            goto L_0x001c
        L_0x002c:
            r2 = r1
        L_0x002d:
            if (r2 > 0) goto L_0x0030
            goto L_0x0031
        L_0x0030:
            r1 = r2
        L_0x0031:
            r0.inSampleSize = r1
            r1 = 0
            r0.inJustDecodeBounds = r1
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.zxing.a.a.b(java.lang.String):android.graphics.Bitmap");
    }

    private static d a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return new d(width, height, iArr);
    }
}
