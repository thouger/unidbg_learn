package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

public class b {
    private static b b;
    private String a;

    public String a() {
        return "000000000000000";
    }

    public String b() {
        return "000000000000000";
    }

    public static b a(Context context) {
        if (b == null) {
            b = new b(context);
        }
        return b;
    }

    private b(Context context) {
        try {
            this.a = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (!TextUtils.isEmpty(this.a)) {
                return;
            }
        } catch (Exception e) {
            e.a(e);
            if (!TextUtils.isEmpty(this.a)) {
                return;
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.a)) {
                this.a = "00:00:00:00:00:00";
            }
            throw th;
        }
        this.a = "00:00:00:00:00:00";
    }

    public String c() {
        return this.a;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return d.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return d.NONE;
            }
            return d.WIFI;
        } catch (Exception unused) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
