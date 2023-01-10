package com.cmic.sso.wy.utils;

import android.util.Log;

/* compiled from: LogUtils */
public class g {
    public static boolean a = false;
    private static final g b = new g();

    public static final void a(String str, String str2) {
        if (a) {
            Log.e(str, "CMCC-SDK:" + str2);
        }
    }

    public static final void b(String str, String str2) {
        if (a) {
            Log.d(str, "CMCC-SDK:" + str2);
        }
    }

    public static final void c(String str, String str2) {
        if (a) {
            Log.i(str, "CMCC-SDK:" + str2);
        }
    }

    public static final void d(String str, String str2) {
        if (a) {
            Log.d("CMCC-SDK:", "[" + str + "] : " + str2);
        }
    }
}
