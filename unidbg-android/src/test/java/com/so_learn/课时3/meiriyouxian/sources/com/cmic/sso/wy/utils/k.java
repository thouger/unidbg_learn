package com.cmic.sso.wy.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* compiled from: PackageUtils */
public class k {
    private static PackageManager e(Context context) {
        return context.getPackageManager();
    }

    public static PackageInfo a(Context context) {
        try {
            return e(context).getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(Context context) {
        PackageInfo a = a(context);
        if (a != null) {
            return a.packageName;
        }
        return null;
    }

    public static String c(Context context) {
        String string;
        try {
            PackageManager e = e(context);
            String str = (String) e.getApplicationLabel(e.getApplicationInfo(b(context), 0));
            if (str != null) {
                return str;
            }
            PackageInfo a = a(context);
            if (a == null || (string = context.getResources().getString(a.applicationInfo.labelRes)) == null) {
                return null;
            }
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String d(Context context) {
        PackageInfo a = a(context);
        if (a == null) {
            return null;
        }
        return b(context) + "&" + a.versionName;
    }
}
