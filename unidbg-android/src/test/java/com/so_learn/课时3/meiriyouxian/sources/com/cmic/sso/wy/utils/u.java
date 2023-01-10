package com.cmic.sso.wy.utils;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.Method;

/* compiled from: TelephonyUtils */
public class u {
    public static int a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    int type = activeNetworkInfo.getType();
                    if (type == 1) {
                        g.b("TelephonyUtils", "WIFI");
                        boolean a = l.a(context, Manifest.permission.CHANGE_NETWORK_STATE);
                        g.a("TelephonyUtils", "CHANGE_NETWORK_STATE=" + a);
                        if (!a || !a(context, connectivityManager)) {
                            return 2;
                        }
                        g.b("TelephonyUtils", "\u6d41\u91cf\u6570\u636e WIFI \u540c\u5f00");
                        return 3;
                    } else if (type == 0) {
                        g.b("TelephonyUtils", "\u6d41\u91cf");
                        return 1;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    private static boolean a(Context context, ConnectivityManager connectivityManager) {
        try {
            if (TextUtils.isEmpty(p.a(context).a(false))) {
                return false;
            }
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            boolean booleanValue = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            g.b("TelephonyUtils", "data is on ---------" + booleanValue);
            return booleanValue;
        } catch (Exception e) {
            g.a("TelephonyUtils", "data is on ----\u53cd\u5c04\u51fa\u9519-----");
            e.printStackTrace();
            return false;
        }
    }

    public static String a() {
        return Build.BRAND;
    }

    public static String b() {
        return Build.MODEL;
    }

    public static String c() {
        return "android" + Build.VERSION.RELEASE;
    }

    public static boolean b(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null || 1 != telephonyManager.getSimState()) {
            return true;
        }
        return false;
    }

    public static boolean d() {
        String str = Build.MANUFACTURER;
        g.a("brand", str);
        return "HUAWEI".equalsIgnoreCase(str);
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT <= 28;
    }
}
