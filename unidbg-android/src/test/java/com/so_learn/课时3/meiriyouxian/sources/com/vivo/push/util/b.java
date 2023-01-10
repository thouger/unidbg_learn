package com.vivo.push.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: BitmapUtil */
public final class b {
    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(1050, false);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i) / ((float) width);
        float f2 = ((float) i2) / ((float) height);
        try {
            Matrix matrix = new Matrix();
            matrix.postScale(f, f2);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            AppMethodBeat.o(1050);
            return createBitmap;
        } catch (Exception unused) {
            AppMethodBeat.o(1050);
            return bitmap;
        }
    }
}
