package com.hmt.analytics.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* compiled from: CommonUtillNetwork */
public class f {
    private static final String a = f.class.getSimpleName();

    private static int a(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 2;
                    case 13:
                        return 3;
                    default:
                        return 0;
                }
            }
        }
        return i2;
    }

    public static String a(Context context) {
        int b = b(context);
        if (b == -101) {
            return "WIFI";
        }
        if (b == -1 || b == 0) {
            return "";
        }
        if (b == 1) {
            return "2G";
        }
        if (b != 2) {
            return b != 3 ? "\u672a\u77e5" : "4G";
        }
        return "3G";
    }

    private static int b(Context context) {
        int i = 0;
        try {
            if (a.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                    i = -1;
                } else {
                    int type = activeNetworkInfo.getType();
                    if (type == 1) {
                        i = -101;
                    } else if (type == 0) {
                        i = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                    }
                }
            } else {
                a.a("getNetworkClass lost  permission", "lost----> android.permission.ACCESS_NETWORK_STATE");
            }
        } catch (Exception e) {
            a.a(a, "Collected:" + e.getMessage());
        }
        return a(i);
    }
}
