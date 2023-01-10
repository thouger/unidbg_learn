package com.alipay.sdk.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.alipay.sdk.c.a;
import java.io.ByteArrayInputStream;
import java.io.Closeable;

public class i {
    public static Drawable a(String str, Context context) {
        return a(str, context, 480);
    }

    public static Drawable a(String str, Context context, int i) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(a.a(str));
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                if (i <= 0) {
                    i = 240;
                }
                options.inDensity = i;
                options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(byteArrayInputStream, null, options));
                a(byteArrayInputStream);
                return bitmapDrawable;
            } catch (Throwable unused) {
                a(byteArrayInputStream);
                return null;
            }
        } catch (Throwable unused2) {
            byteArrayInputStream = null;
            a(byteArrayInputStream);
            return null;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
