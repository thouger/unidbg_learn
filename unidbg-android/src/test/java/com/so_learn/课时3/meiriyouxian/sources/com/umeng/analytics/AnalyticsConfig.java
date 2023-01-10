package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.i;
import com.umeng.common.b;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;

public class AnalyticsConfig {
    public static boolean CATCH_EXCEPTION = false;
    public static boolean CHANGE_CATCH_EXCEPTION_NOTALLOW = true;
    public static boolean CLEAR_EKV_BL = false;
    public static boolean CLEAR_EKV_WL = false;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";
    static double[] a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    public static boolean enable = true;
    public static long kContinueSessionMillis = 30000;
    public static String mWrapperType;
    public static String mWrapperVersion;

    static void a(String str) {
        c = str;
    }

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return a;
    }

    static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            b.a(context).a(d);
            return;
        }
        UMLog.aq(i.A, 0, "\\|");
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = b.a(context).c();
        }
        return d;
    }

    static void a(Context context, int i) {
        e = i;
        b.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = b.a(context).d();
        }
        return e;
    }

    public static String getGameSdkVersion(Context context) {
        try {
            Class<?> cls = Class.forName("com.umeng.analytics.game.GameSdkVersion");
            if (cls != null) {
                return (String) cls.getDeclaredField("SDK_VERSION").get(cls);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
