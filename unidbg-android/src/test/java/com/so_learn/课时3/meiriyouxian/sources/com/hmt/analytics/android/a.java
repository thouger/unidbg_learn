package com.hmt.analytics.android;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.hmt.analytics.util.b;
import com.hmt.analytics.util.g;
import com.hmt.analytics.util.h;
import com.hmt.analytics.util.i;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

/* compiled from: CommonUtil */
public class a {
    public static final String a = a.class.getSimpleName();

    public static String g() {
        return "";
    }

    public static String h() {
        return "";
    }

    public static String s(Context context) {
        return "";
    }

    public static boolean a(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String str2 = a;
            a(str2, "Collected:" + e.getMessage());
            return false;
        }
    }

    public static String a(Context context) {
        return (String) i.b(context, "hmt_agent_online_setting", "muid", "");
    }

    public static boolean b(Context context) {
        NetworkInfo[] allNetworkInfo;
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
                for (int i = 0; i < allNetworkInfo.length; i++) {
                    if (allNetworkInfo[i].getTypeName().equals("WIFI") && allNetworkInfo[i].isConnected()) {
                        return true;
                    }
                }
            }
            return false;
        }
        a(a, "isWiFiActive : lost permission:android.permission.ACCESS_WIFI_STATE");
        return false;
    }

    public static boolean c(Context context) {
        if (!a(context, "android.permission.INTERNET")) {
            a(a, "isNetworkAvailable : lost permission:android.permission.INTERNET");
            return false;
        } else if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo networkInfo = null;
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (Exception e) {
                String str = a;
                a(str, "Collected:" + e.getMessage());
            }
            if (networkInfo != null && networkInfo.isAvailable()) {
                return true;
            }
            a(a, "isNetworkAvailable : Network error");
            return false;
        } else {
            a(a, "isNetworkAvailable : lost permission: android.permission.ACCESS_NETWORK_STATE");
            return false;
        }
    }

    public static String a() {
        return Long.valueOf(System.currentTimeMillis()).toString();
    }

    public static String d(Context context) {
        Exception e;
        if (context == null) {
            return "";
        }
        String str = (String) i.b(context, "manual_setting_appkey", "");
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("HMT_APPKEY");
                if (!TextUtils.isEmpty(string)) {
                    try {
                        return string.toString().trim();
                    } catch (Exception e2) {
                        str = string;
                        e = e2;
                        a(a, "getAppKey exception : Could not read HMT_APPKEY meta-data from AndroidManifest.xml");
                        String str2 = a;
                        a(str2, "Collected:" + e.getMessage());
                        return str;
                    }
                } else {
                    a(a, "getAppKey : Could not read HMT_APPKEY meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Exception e3) {
            e = e3;
            a(a, "getAppKey exception : Could not read HMT_APPKEY meta-data from AndroidManifest.xml");
            String str2 = a;
            a(str2, "Collected:" + e.getMessage());
            return str;
        }
        return str;
    }

    public static String e(Context context) {
        Exception e;
        String string;
        String str;
        if (context == null) {
            return "";
        }
        String str2 = (String) i.b(context, "manual_setting_channel_id", "");
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String str3 = "null";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || (string = applicationInfo.metaData.getString("HMT_CHANNEL")) == null)) {
                try {
                    return string.toString().trim();
                } catch (Exception e2) {
                    str3 = str;
                    e = e2;
                    a(a, "getChannel : Could not read HMT_CHANNEL meta-data from AndroidManifest.xml.");
                    String str4 = a;
                    a(str4, "Collected:" + e.getMessage());
                    return str3;
                }
            }
        } catch (Exception e3) {
            e = e3;
            a(a, "getChannel : Could not read HMT_CHANNEL meta-data from AndroidManifest.xml.");
            String str4 = a;
            a(str4, "Collected:" + e.getMessage());
            return str3;
        }
        return str3;
    }

    public static String a(Context context, int i) {
        if (context == null) {
            return "";
        }
        try {
            String obj = context.toString();
            String substring = obj.substring(0, obj.indexOf("@"));
            String packageName = context.getPackageName();
            return (packageName == null || packageName.equals("") || i != 1) ? substring : substring.replaceFirst(packageName, "");
        } catch (Exception e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            if (a(context, "android.permission.GET_TASKS")) {
                List<ActivityManager.RunningTaskInfo> list = null;
                try {
                    list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
                } catch (Exception e2) {
                    String str2 = a;
                    a(str2, "Collected:" + e2.getMessage());
                }
                if (list != null && list.size() > 0) {
                    ComponentName componentName = list.get(0).topActivity;
                    String str3 = a;
                    a(str3, "getActivityName : " + componentName.getClassName());
                    String className = componentName.getClassName();
                    String packageName2 = context.getPackageName();
                    return (packageName2 == null || packageName2.equals("") || i != 1) ? className : className.replaceFirst(packageName2, "");
                }
            } else {
                a(a, "getActivityName : lost permission:android.permission.GET_TASKS");
            }
            return "";
        }
    }

    public static String f(Context context) {
        String str = Build.VERSION.RELEASE;
        String str2 = a;
        a(str2, "getOsVersion : " + str);
        return str == null ? "" : str;
    }

    public static String g(Context context) {
        String str;
        String str2 = "";
        if (context == null) {
            return str2;
        }
        String packageName = context.getPackageName();
        String str3 = (String) i.b(context, g.q + packageName, g.s, str2);
        if (c(str3)) {
            return str3;
        }
        String str4 = (String) i.b(context, g.r, g.s, str2);
        if (c(str4)) {
            return str4;
        }
        String w = w(context);
        if (!b(w)) {
            w = str2;
        }
        String y = y(context);
        if (a(y)) {
            str2 = y;
        }
        String str5 = w + str2;
        if (TextUtils.isEmpty(str5)) {
            str = o(context);
            if (TextUtils.isEmpty(str) || str.equals("9774d56d682e549c") || str.length() < 15) {
                str = new BigInteger(64, new SecureRandom()).toString(16);
            }
        } else {
            str = g.a(str5);
        }
        if (!TextUtils.isEmpty(str)) {
            i.a(context, g.q + packageName, g.s, str);
            i.a(context, g.r, g.s, str);
        }
        return str;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("02:00:00:00:00:00") && !str.equals("00:00:00:00:00:00") && !str.equals("ff:ff:ff:ff:ff:ff")) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("000000000000000") && !str.equals("00000000")) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("0f607264fc6318a92b9e13c65db7cd3c") && !str.equals("3f0fe74b555ff95d563a2cfe3cb9c834") && !str.equals("5284047f4ffb4e04824a2fd1d1f0cd62") && !str.equals("528c8e6cd4a3c6598999a0e9df15ad32") && !str.equals("b21929f60cb26fe36e48926c33f1903c") && !str.equals("dd4b21e9ef71e1291183a46b913ae6f2") && !str.equals("feef34bbe6f4a1f343ad614c1b25f9b9")) {
            return true;
        }
        return false;
    }

    public static boolean h(Context context) {
        return a(context, "android.permission.READ_PHONE_STATE");
    }

    public static int i(Context context) {
        int intValue = ((Integer) i.b(context, "hmt_agent_online_setting", "hmtlocal_report_policy_server", 10000)).intValue();
        String str = a;
        a(str, "Policy mode from server is " + intValue);
        if (intValue == 10000) {
            intValue = ((Integer) i.b(context, "hmt_agent_online_setting", "hmtlocal_report_policy_client", 10000)).intValue();
        }
        String str2 = a;
        a(str2, "Plicy mode from client is " + intValue);
        if (intValue == 10000) {
            return 0;
        }
        return intValue;
    }

    public static Boolean a(String[] strArr, String str) {
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] j(Context context) {
        String str = (String) i.b(context, "hmt_agent_online_setting", "hmtlocal_untracked_server", "");
        if (TextUtils.isEmpty(str)) {
            str = (String) i.b(context, "hmt_agent_online_setting", "hmtlocal_untracked_client", "");
        }
        if (str == null || str == "") {
            return null;
        }
        return str.split("#");
    }

    public static String k(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String simOperator = telephonyManager.getSimOperator();
        if (simOperator == null) {
            return "";
        }
        return simOperator;
    }

    public static boolean l(Context context) {
        SensorManager sensorManager;
        if (!b()) {
            return false;
        }
        try {
            sensorManager = (SensorManager) context.getSystemService("sensor");
        } catch (NoSuchFieldError e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            sensorManager = null;
        }
        if (sensorManager == null) {
            return false;
        }
        return true;
    }

    public static boolean b() {
        try {
            return !"sdk".equals(Build.MODEL) && !"sdk".equals(Build.PRODUCT) && !"generic".equals(Build.DEVICE);
        } catch (NoSuchFieldError e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            return !"sdk".equals(Build.MODEL) && !"sdk".equals(Build.PRODUCT);
        }
    }

    public static String m(Context context) {
        String str;
        Exception e;
        if (context == null) {
            return "";
        }
        try {
            String str2 = (String) i.b(context, "manual_app_version", "");
            try {
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                if (str != null) {
                    try {
                        if (str.length() > 0) {
                            return str;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        String str3 = a;
                        a(str3, "Collected:" + e.getMessage());
                        return str;
                    }
                }
                return "";
            } catch (Exception e3) {
                e = e3;
                str = str2;
                String str3 = a;
                a(str3, "Collected:" + e.getMessage());
                return str;
            }
        } catch (Exception e4) {
            e = e4;
            str = "";
            String str3 = a;
            a(str3, "Collected:" + e.getMessage());
            return str;
        }
    }

    public static void a(String str, String str2) {
        str2.startsWith("Collected:");
        if (g.b && !TextUtils.isEmpty(str2)) {
            Log.e(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (g.b) {
            Log.e(str, str2, th);
        }
    }

    public static String n(Context context) {
        return f.a(context);
    }

    public static String c() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return d(str2);
        }
        return d(str) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + str2;
    }

    private static String d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    public static boolean d() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < strArr.length; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception e) {
                a(a, "Collected:" + e.getMessage());
            }
        }
        return false;
    }

    public static String o(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "";
            }
            return string;
        } catch (NullPointerException e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            return "";
        }
    }

    public static String p(Context context) {
        if (!j.b()) {
            j.a(context);
        }
        String a2 = j.a();
        return a2 == null ? "" : a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[SYNTHETIC, Splitter:B:10:0x0034] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String q(android.content.Context r6) {
        /*
            java.lang.String r0 = "Collected:"
            r1 = 0
            android.content.Context r2 = r6.getApplicationContext()     // Catch:{ Exception -> 0x0018 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x0018 }
            java.lang.String r3 = r6.getPackageName()     // Catch:{ Exception -> 0x0016 }
            r4 = 0
            android.content.pm.ApplicationInfo r1 = r2.getApplicationInfo(r3, r4)     // Catch:{ Exception -> 0x0016 }
            goto L_0x0032
        L_0x0016:
            r3 = move-exception
            goto L_0x001a
        L_0x0018:
            r3 = move-exception
            r2 = r1
        L_0x001a:
            java.lang.String r4 = com.hmt.analytics.android.a.a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            a(r4, r3)
        L_0x0032:
            if (r2 == 0) goto L_0x006d
            java.lang.CharSequence r1 = r2.getApplicationLabel(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0054 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0054 }
            r2.<init>()     // Catch:{ Exception -> 0x0054 }
            r2.append(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r1 = "_"
            r2.append(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r6 = m(r6)     // Catch:{ Exception -> 0x0054 }
            r2.append(r6)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r6 = r2.toString()     // Catch:{ Exception -> 0x0054 }
            goto L_0x0070
        L_0x0054:
            r6 = move-exception
            java.lang.String r1 = com.hmt.analytics.android.a.a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r6 = r6.getMessage()
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            a(r1, r6)
        L_0x006d:
            java.lang.String r6 = ""
        L_0x0070:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hmt.analytics.android.a.q(android.content.Context):java.lang.String");
    }

    public static String e() {
        return g.l;
    }

    public static String f() {
        return g.m;
    }

    public static String r(Context context) {
        int i = 0;
        if (context == null) {
            return "";
        }
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
        }
        return String.valueOf(i);
    }

    public static String t(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            return "";
        }
    }

    public static String u(Context context) {
        return (String) i.b(context, "manual_setting_imei", "");
    }

    public static String v(Context context) {
        String b = h.k().b(context);
        return (!TextUtils.isEmpty(b) ? b.a("mobileanalytics", b) : "").toLowerCase();
    }

    public static String w(Context context) {
        String u = u(context);
        if (!TextUtils.isEmpty(u)) {
            return u;
        }
        if (h(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            try {
                u = telephonyManager.getDeviceId();
            } catch (Exception e) {
                String str = a;
                a(str, "Collected:" + e.getMessage());
            }
        } else {
            a(a, "get_imei : lost permission = android.permission.READ_PHONE_STATE");
        }
        if (u == null) {
            return "";
        }
        return u;
    }

    public static String x(Context context) {
        String y = y(context);
        return (!TextUtils.isEmpty(y) ? b.a("mobileanalytics", y) : "").toLowerCase();
    }

    public static String y(Context context) {
        String str;
        try {
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (Build.VERSION.SDK_INT == 17) {
                    str = "02:00:00:00:00:00";
                } else {
                    WifiInfo wifiInfo = null;
                    try {
                        wifiInfo = wifiManager.getConnectionInfo();
                    } catch (Exception e) {
                        String str2 = a;
                        a(str2, "Collected:" + e.getMessage());
                    } catch (NoSuchFieldError e2) {
                        String str3 = a;
                        a(str3, "Collected:" + e2.getMessage());
                    }
                    if (wifiInfo == null) {
                        return "";
                    }
                    str = wifiInfo.getMacAddress();
                }
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                if (!str.equals("02:00:00:00:00:00")) {
                    return str.toLowerCase();
                }
                String H = H(context);
                if (!TextUtils.isEmpty(H)) {
                    return H.toLowerCase();
                }
                String I = I(context);
                if (!TextUtils.isEmpty(I)) {
                    return I.toLowerCase();
                }
                return "02:00:00:00:00:00";
            }
            a(a, "get_mac : lost permission = android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e3) {
            String str4 = a;
            a(str4, "Collected:" + e3.getMessage());
        }
    }

    private static String H(Context context) {
        if (a(context, "android.permission.INTERNET")) {
            try {
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "";
                        }
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                }
            } catch (Exception e) {
                a(a, "Collected:" + e.getMessage());
            }
        } else {
            a(a, "getAdressMacByInterface : need permission = android.permission.INTERNET");
        }
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0099 A[SYNTHETIC, Splitter:B:24:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A[SYNTHETIC, Splitter:B:30:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String I(android.content.Context r10) {
        /*
        // Method dump skipped, instructions count: 314
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hmt.analytics.android.a.I(android.content.Context):java.lang.String");
    }

    private static String i() {
        if (Build.VERSION.SDK_INT > 27) {
            return "";
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls.newInstance(), "wifi.interface", "wlan0");
        } catch (Exception e) {
            String str = a;
            a(str, "Collected:" + e.getMessage());
            return "";
        }
    }

    public static int z(Context context) {
        if (h(context)) {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
        }
        a(a, "getPhoneType : lost permission = android.permission.READ_PHONE_STATE");
        return 0;
    }

    public static String A(Context context) {
        String str = null;
        try {
            if (h(context)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return "";
                }
                str = telephonyManager.getSubscriberId();
            } else {
                a(a, "getImsi : lost permission = android.permission.READ_PHONE_STATE");
            }
        } catch (Exception e) {
            String str2 = a;
            a(str2, "Collected:" + e.getMessage());
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String B(Context context) {
        String w = w(context);
        if (w == null || w.equals("")) {
            return "";
        }
        return g.a(w);
    }

    public static String C(Context context) {
        String o = o(context);
        if (!o.equals("")) {
            return g.a(o);
        }
        return "";
    }

    public static String D(Context context) {
        String y = y(context);
        if (y == null || y.equals("")) {
            return "";
        }
        return g.a(y.replace(":", "").toUpperCase());
    }

    public static String E(Context context) {
        String y = y(context);
        if (y == null || y.equals("")) {
            return "";
        }
        return g.a(y.toUpperCase());
    }

    public static boolean F(Context context) {
        return context instanceof Application;
    }

    public static int G(Context context) {
        WindowManager windowManager;
        Display defaultDisplay;
        if (context == null || (windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE)) == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return -1;
        }
        return defaultDisplay.getRotation();
    }

    public static String b(Context context, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (h(context)) {
                return telephonyManager.getImei(i);
            }
            return "";
        } else if (Build.VERSION.SDK_INT >= 21) {
            try {
                Method method = telephonyManager.getClass().getMethod("getImei", Integer.TYPE);
                if (method != null) {
                    return (String) method.invoke(telephonyManager, Integer.valueOf(i));
                }
                return "";
            } catch (Throwable th) {
                String str = a;
                a(str, "Collected:" + th.getMessage());
                return "";
            }
        } else {
            a(a, "Collected:Can't call getImei method under Android L!");
            return "";
        }
    }
}
