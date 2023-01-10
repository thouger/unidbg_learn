package com.sobot.chat.b.b;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: ScreenUtil */
public class b {
    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0043: APUT  (r0v1 int[]), (0 ??[int, short, byte, char]), (r2v1 int) */
    public static int[] b(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                i = point.x;
                i2 = point.y;
            } catch (Throwable unused) {
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public static Rect a(Context context, int i, int i2) {
        int i3;
        int i4;
        int[] b = b(context);
        int i5 = 0;
        int i6 = b[0];
        int i7 = b[1];
        if (a(context)) {
            i4 = (i6 - i) / 2;
            i3 = i + i4;
        } else {
            int i8 = (i7 - i) / 2;
            int i9 = i + i8;
            i5 = i8;
            i4 = 0;
            i2 = i9;
            i3 = i2;
        }
        return new Rect(i4, i5, i3, i2);
    }
}
