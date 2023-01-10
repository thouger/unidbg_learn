package com.umeng.message.proguard;

import android.content.Context;

/* compiled from: SizeFactory */
public class j {
    private static float a = 1.0f;

    private static void a(Context context) {
        a = context.getResources().getDisplayMetrics().density;
    }

    public static int a(Context context, float f) {
        a(context);
        return (int) ((f * a) + 0.5f);
    }

    public static int b(Context context, float f) {
        a(context);
        return (int) ((f / a) + 0.5f);
    }

    public static int c(Context context, float f) {
        a(context);
        return (int) ((f * a) + 0.5f);
    }

    public static int d(Context context, float f) {
        a(context);
        return (int) ((f / a) + 0.5f);
    }
}
