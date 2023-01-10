package com.sina.weibo.sdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.Logging.Session;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/* compiled from: NetworkHelper */
public class f {
    public static boolean a(Context context) {
        return context == null || context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    public static boolean b(Context context) {
        NetworkInfo d;
        if (context == null || (d = d(context)) == null || !d.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean c(Context context) {
        NetworkInfo d;
        if (context == null || (d = d(context)) == null || 1 != d.getType() || !d.isConnected()) {
            return false;
        }
        return true;
    }

    public static NetworkInfo d(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    public static void e(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static String f(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("Android");
        sb.append("__");
        sb.append("weibo");
        sb.append("__");
        sb.append("sdk");
        sb.append("__");
        try {
            sb.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName.replaceAll("\\s+", Session.SESSION_SEPARATION_CHAR_CHILD));
        } catch (Exception unused) {
            sb.append("unknown");
        }
        return sb.toString();
    }
}
