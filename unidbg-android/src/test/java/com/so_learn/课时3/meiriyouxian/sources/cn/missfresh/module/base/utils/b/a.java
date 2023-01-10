package cn.missfresh.module.base.utils.b;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import cn.missfresh.lib.image.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

/* compiled from: CropUtils */
public class a {
    public static Bitmap a(View view) {
        AppMethodBeat.i(23590, false);
        Bitmap a = a(view, 1.0f, 1.0f);
        AppMethodBeat.o(23590);
        return a;
    }

    public static Bitmap a(View view, float f, float f2) {
        AppMethodBeat.i(23592, false);
        if (view == null || view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            AppMethodBeat.o(23592);
            return null;
        }
        try {
            d.c("CropUtils=>width_pre:", view.getMeasuredWidth() + "");
            d.c("CropUtils=>height_pre:", view.getMeasuredHeight() + "");
            int measuredWidth = (int) (((float) view.getMeasuredWidth()) * f);
            int measuredHeight = (int) (((float) view.getMeasuredHeight()) * f2);
            d.c("CropUtils=>width:", measuredWidth + "");
            d.c("CropUtils=>height:", measuredHeight + "");
            Bitmap a = c.a(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(a);
            canvas.scale(f, f2);
            canvas.concat(new Matrix());
            canvas.translate((float) (-view.getScrollX()), (float) (-view.getScrollY()));
            Drawable background = view.getBackground();
            if (background != null) {
                background.draw(canvas);
            } else {
                canvas.drawColor(-1);
            }
            view.draw(canvas);
            AppMethodBeat.o(23592);
            return a;
        } catch (OutOfMemoryError unused) {
            AppMethodBeat.o(23592);
            return null;
        }
    }
}
