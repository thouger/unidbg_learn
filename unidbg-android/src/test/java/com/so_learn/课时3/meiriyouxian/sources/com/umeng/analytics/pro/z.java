package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Looper;

/* compiled from: OpenDeviceId */
public class z {
    private static y a;
    private static boolean b;

    public static synchronized String a(Context context) {
        synchronized (z.class) {
            if (context == null) {
                throw new RuntimeException("Context is null");
            } else if (Looper.myLooper() != Looper.getMainLooper()) {
                b(context);
                if (a != null) {
                    try {
                        return a.a(context);
                    } catch (Exception unused) {
                    }
                }
                return null;
            } else {
                throw new IllegalStateException("Cannot be called from the main thread");
            }
        }
    }

    private static void b(Context context) {
        if (a == null && !b) {
            synchronized (z.class) {
                if (a == null && !b) {
                    a = aa.a(context);
                    b = true;
                }
            }
        }
    }
}
