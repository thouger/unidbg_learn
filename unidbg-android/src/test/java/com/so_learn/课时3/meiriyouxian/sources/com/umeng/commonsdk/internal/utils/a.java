package com.umeng.commonsdk.internal.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ApplicationLayerUtil */
public class a {

    /* compiled from: ApplicationLayerUtil */
    /* renamed from: com.umeng.commonsdk.internal.utils.a$a  reason: collision with other inner class name */
    public static class C0182a {
        public String a;
        public String b;
    }

    /* compiled from: ApplicationLayerUtil */
    public static class b {
        public int a;
        public String b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public String h;
        public int i;
        public int j;
        public int k;
        public long l;
    }

    public static String m(Context context) {
        if (context == null) {
        }
        return null;
    }

    public static String n(Context context) {
        return null;
    }

    public static long a(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppFirstInstallTime" + e.getMessage());
            return 0;
        }
    }

    public static long b(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppLastUpdateTime:" + e.getMessage());
            return 0;
        }
    }

    public static String c(Context context, String str) {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppInstaller:" + e.getMessage());
            return null;
        }
    }

    public static int d(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            UMCrashManager.reportCrash(context, e);
            ULog.e("getAppUid:" + e.getMessage());
            return 0;
        }
    }

    public static boolean a() {
        return h.a();
    }

    public static String b() {
        return new SimpleDateFormat().format(new Date());
    }

    public static float a(Context context) {
        if (context == null) {
            return 0.0f;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            return configuration.fontScale;
        } catch (Exception e) {
            ULog.e("getFontSize:" + e.getMessage());
            return 0.0f;
        }
    }

    public static WifiInfo b(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3 A[Catch:{ Exception -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(android.content.Context r8) {
        /*
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            android.net.wifi.WifiInfo r0 = b(r8)
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            com.umeng.commonsdk.internal.utils.a$b r1 = new com.umeng.commonsdk.internal.utils.a$b
            r1.<init>()
            int r2 = r0.describeContents()
            r1.a = r2
            java.lang.String r2 = r0.getBSSID()
            r1.b = r2
            java.lang.String r2 = r0.getSSID()
            r1.c = r2
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r2 < r3) goto L_0x002e
            int r2 = r0.getFrequency()
            r1.d = r2
            goto L_0x0031
        L_0x002e:
            r2 = -1
            r1.d = r2
        L_0x0031:
            boolean r2 = r0.getHiddenSSID()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003c
            r1.e = r3
            goto L_0x003e
        L_0x003c:
            r1.e = r4
        L_0x003e:
            java.lang.String r2 = "header_local_ip"
            boolean r2 = com.umeng.commonsdk.config.FieldManager.allow(r2)
            if (r2 == 0) goto L_0x004d
            int r2 = r0.getIpAddress()
            r1.f = r2
        L_0x004d:
            int r2 = r0.getLinkSpeed()
            r1.g = r2
            java.lang.String r2 = com.umeng.commonsdk.statistics.common.DeviceConfig.getMac(r8)
            r1.h = r2
            int r2 = r0.getNetworkId()
            r1.i = r2
            int r2 = r0.getRssi()
            r1.j = r2
            int r2 = f(r8)
            r1.k = r2
            long r5 = java.lang.System.currentTimeMillis()
            r1.l = r5
            if (r0 == 0) goto L_0x00c1
            org.json.JSONArray r0 = com.umeng.commonsdk.internal.utils.f.a(r8)     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x00a0
            int r2 = r0.length()     // Catch:{ Exception -> 0x00a7 }
            if (r2 <= 0) goto L_0x00a0
            r2 = r4
        L_0x0080:
            int r5 = r0.length()     // Catch:{ Exception -> 0x00a7 }
            if (r2 >= r5) goto L_0x00a0
            org.json.JSONObject r5 = r0.optJSONObject(r2)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r6 = "ssid"
            r7 = 0
            java.lang.String r5 = r5.optString(r6, r7)     // Catch:{ Exception -> 0x00a7 }
            if (r5 == 0) goto L_0x009d
            java.lang.String r6 = r1.c     // Catch:{ Exception -> 0x00a7 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00a7 }
            if (r5 == 0) goto L_0x009d
            goto L_0x00a1
        L_0x009d:
            int r2 = r2 + 1
            goto L_0x0080
        L_0x00a0:
            r3 = r4
        L_0x00a1:
            if (r3 != 0) goto L_0x00c1
            com.umeng.commonsdk.internal.utils.f.a(r8, r1)     // Catch:{ Exception -> 0x00a7 }
            goto L_0x00c1
        L_0x00a7:
            r8 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "wifiChange:"
            r0.append(r1)
            java.lang.String r8 = r8.getMessage()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            com.umeng.commonsdk.statistics.common.ULog.e(r8)
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.a.c(android.content.Context):void");
    }

    public static JSONArray d(Context context) {
        if (context == null) {
            return null;
        }
        return f.a(context);
    }

    public static void e(Context context) {
        if (context != null) {
            f.b(context);
        }
    }

    public static int f(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return -1;
        }
        return wifiManager.getWifiState();
    }

    public static int g(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static DisplayMetrics i(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    public static String c() {
        return g.a("df");
    }

    public static List<InputMethodInfo> j(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)) == null) {
            return null;
        }
        return inputMethodManager.getInputMethodList();
    }

    public static List<C0182a> k(Context context) {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                for (String str : list) {
                    if (str != null && !str.startsWith(".")) {
                        C0182a aVar = new C0182a();
                        aVar.a = str;
                        aVar.b = e(context, str);
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception e) {
            ULog.e("getAppList:" + e.getMessage());
        }
        return arrayList;
    }

    private static String e(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
            return null;
        } catch (Exception e) {
            ULog.e("getLabel:" + e.getMessage());
            return null;
        }
    }

    public static ActivityManager.MemoryInfo l(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    public static long d() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static String e() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String obj = declaredMethod.invoke(null, "net.hostname").toString();
            if (obj == null || obj.equalsIgnoreCase("")) {
                return obj;
            }
            return HelperUtils.getUmengMD5(obj);
        } catch (Exception e) {
            ULog.e("getHostName:" + e.getMessage());
            return null;
        }
    }
}
