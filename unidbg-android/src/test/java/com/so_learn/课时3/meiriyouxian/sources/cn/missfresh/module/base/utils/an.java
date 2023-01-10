package cn.missfresh.module.base.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;

/* compiled from: QRCodeUtil */
public class an {
    public static Bitmap a(String str, int i, int i2, Bitmap bitmap) {
        AppMethodBeat.i(23401, false);
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    if (i > 0) {
                        d.d("QRCodeUtil", "createQRImage....url:" + str);
                        HashMap hashMap = new HashMap();
                        hashMap.put(EncodeHintType.CHARACTER_SET, FileOpt.ENCODE_STR);
                        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
                        hashMap.put(EncodeHintType.MARGIN, 0);
                        b a = new com.google.zxing.qrcode.b().a(str, BarcodeFormat.QR_CODE, 1, 1, hashMap);
                        int[] iArr = new int[(a.f() * a.e())];
                        for (int i3 = 0; i3 < a.f(); i3++) {
                            for (int i4 = 0; i4 < a.e(); i4++) {
                                if (a.a(i4, i3)) {
                                    iArr[(a.e() * i3) + i4] = -16777216;
                                } else {
                                    iArr[(a.e() * i3) + i4] = -1;
                                }
                            }
                        }
                        Bitmap createBitmap = Bitmap.createBitmap(a.e(), a.f(), Bitmap.Config.ARGB_8888);
                        createBitmap.setPixels(iArr, 0, a.e(), 0, 0, a.e(), a.f());
                        if (bitmap != null) {
                            createBitmap = a(createBitmap, bitmap);
                        }
                        Bitmap a2 = a(createBitmap, i, i2);
                        AppMethodBeat.o(23401);
                        return a2;
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
                AppMethodBeat.o(23401);
                return null;
            }
        }
        AppMethodBeat.o(23401);
        return null;
    }

    private static Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        AppMethodBeat.i(23402, false);
        Bitmap bitmap3 = null;
        if (bitmap == null) {
            AppMethodBeat.o(23402);
            return null;
        } else if (bitmap2 == null) {
            AppMethodBeat.o(23402);
            return bitmap;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = bitmap2.getWidth();
            int height2 = bitmap2.getHeight();
            if (width == 0 || height == 0) {
                AppMethodBeat.o(23402);
                return null;
            } else if (width2 == 0 || height2 == 0) {
                AppMethodBeat.o(23402);
                return bitmap;
            } else {
                float f = ((((float) width) * 1.0f) / 5.0f) / ((float) width2);
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                try {
                    Canvas canvas = new Canvas(createBitmap);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                    canvas.scale(f, f, (float) (width / 2), (float) (height / 2));
                    canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) ((height - height2) / 2), (Paint) null);
                    canvas.save();
                    canvas.restore();
                    bitmap3 = createBitmap;
                } catch (Exception e) {
                    e.getStackTrace();
                }
                AppMethodBeat.o(23402);
                return bitmap3;
            }
        }
    }

    private static Bitmap a(Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(23403, false);
        if (i <= i2 * 2) {
            i2 = 0;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        int i3 = i - i2;
        Rect rect2 = new Rect(i2, i2, i3, i3);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        canvas.save();
        AppMethodBeat.o(23403);
        return createBitmap;
    }
}
