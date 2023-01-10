package com.alipay.b.a.a.b;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaDrm;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import com.alipay.b.a.a.a.a;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.TelephonyProperties;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b {
    private static b a = new b();

    private b() {
    }

    public static b a() {
        return a;
    }

    public static String a(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    private static boolean a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r2) {
        /*
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = a(r2, r0)
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x000d
            return r1
        L_0x000d:
            if (r2 == 0) goto L_0x001f
            java.lang.String r0 = "phone"
            java.lang.Object r2 = r2.getSystemService(r0)     // Catch:{ all -> 0x001f }
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x001f
            java.lang.String r2 = r2.getSubscriberId()     // Catch:{ all -> 0x001f }
            goto L_0x0020
        L_0x001f:
            r2 = r1
        L_0x0020:
            if (r2 != 0) goto L_0x0023
            r2 = r1
        L_0x0023:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.b(android.content.Context):java.lang.String");
    }

    public static String c() {
        long j = 0;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(a.a().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    public static String c(Context context) {
        int i = 0;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
        }
        return i == 1 ? "1" : "0";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0062, code lost:
        if (r2 != null) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005a A[SYNTHETIC, Splitter:B:33:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A[SYNTHETIC, Splitter:B:37:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0067 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0056 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ all -> 0x0056 }
            r2.<init>(r3)     // Catch:{ all -> 0x0056 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0054 }
            r3.<init>(r2)     // Catch:{ all -> 0x0054 }
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch:{ all -> 0x0058 }
            r4.<init>(r3)     // Catch:{ all -> 0x0058 }
            r1 = 1
            r5 = r1
        L_0x001d:
            r6 = 100
            if (r5 >= r6) goto L_0x004a
            java.lang.String r6 = r4.readLine()     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x004a
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch:{ all -> 0x0048 }
            if (r7 < 0) goto L_0x0045
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch:{ all -> 0x0048 }
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch:{ all -> 0x0048 }
            java.lang.String r1 = r6.substring(r5, r1)     // Catch:{ all -> 0x0048 }
            java.lang.String r0 = r1.trim()     // Catch:{ all -> 0x0048 }
            goto L_0x004a
        L_0x0045:
            int r5 = r5 + 1
            goto L_0x001d
        L_0x0048:
            r1 = r4
            goto L_0x0058
        L_0x004a:
            r4.close()     // Catch:{ all -> 0x004d }
        L_0x004d:
            r3.close()     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r2.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0065
        L_0x0054:
            r3 = r1
            goto L_0x0058
        L_0x0056:
            r2 = r1
            r3 = r2
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ all -> 0x005d }
        L_0x005d:
            if (r3 == 0) goto L_0x0062
            r3.close()     // Catch:{ all -> 0x0062 }
        L_0x0062:
            if (r2 == 0) goto L_0x0065
            goto L_0x0050
        L_0x0065:
            if (r0 != 0) goto L_0x006a
            java.lang.String r0 = ""
        L_0x006a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.d():java.lang.String");
    }

    public static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put(StorageManager.UUID_SYSTEM, String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put("alarm", String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    public static String e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    public static String f() {
        String v = v();
        return !a.a(v) ? v : w();
    }

    public static String f(Context context) {
        List<Sensor> sensorList;
        String str = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    str = a.e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0035 A[SYNTHETIC, Splitter:B:29:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String g() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch:{ all -> 0x0032 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0033 }
            r2.<init>(r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = r2.readLine()     // Catch:{ all -> 0x0030 }
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x0029
            int r3 = r0.length     // Catch:{ all -> 0x0030 }
            r4 = 1
            if (r3 <= r4) goto L_0x0029
            r0 = r0[r4]     // Catch:{ all -> 0x0030 }
            r1.close()     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r2.close()     // Catch:{ all -> 0x0028 }
        L_0x0028:
            return r0
        L_0x0029:
            r1.close()     // Catch:{ all -> 0x002c }
        L_0x002c:
            r2.close()     // Catch:{ all -> 0x003d }
            goto L_0x003d
        L_0x0030:
            r0 = r2
            goto L_0x0033
        L_0x0032:
            r1 = r0
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ all -> 0x0038 }
        L_0x0038:
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.g():java.lang.String");
    }

    public static String g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put(MediaDrm.PROPERTY_VENDOR, sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034 A[SYNTHETIC, Splitter:B:21:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String h() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ all -> 0x0031 }
            r4.<init>(r0)     // Catch:{ all -> 0x0031 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x0032 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r0.readLine()     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x0028
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch:{ all -> 0x002f }
            r5 = 1
            r1 = r1[r5]     // Catch:{ all -> 0x002f }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x002f }
            long r1 = (long) r1
            r2 = r1
        L_0x0028:
            r4.close()     // Catch:{ all -> 0x002b }
        L_0x002b:
            r0.close()     // Catch:{ all -> 0x003c }
            goto L_0x003c
        L_0x002f:
            r1 = r0
            goto L_0x0032
        L_0x0031:
            r4 = r1
        L_0x0032:
            if (r4 == 0) goto L_0x0037
            r4.close()     // Catch:{ all -> 0x0037 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.h():java.lang.String");
    }

    public static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + PhoneConstants.APN_TYPE_ALL + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String i() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(wrap: int : 0x000d: IGET  (r1v4 int) = (r1v3 android.util.DisplayMetrics) android.util.DisplayMetrics.widthPixels int)] */
    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String j() {
        long j = 0;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(wrap: int : 0x000d: IGET  (r1v4 int) = (r1v3 android.util.DisplayMetrics) android.util.DisplayMetrics.heightPixels int)] */
    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String k() {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), TelephonyProperties.PROPERTY_BASEBAND_VERSION, "no message");
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String k(Context context) {
        String str = "";
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return str;
        }
        try {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (str == null || str.length() == 0 || "02:00:00:00:00:00".equals(str)) {
                return u();
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String l() {
        String str;
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String l(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable unused) {
                    return simSerialNumber;
                }
            }
        } catch (Throwable unused2) {
        }
        return "";
    }

    public static String m() {
        String str;
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String m(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(wrap: long : 0x0012: ARITH  (r0v3 long) = (r0v2 long) - (wrap: long : 0x0010: ARITH  (r3v1 long) = (r0v2 long) % (1000 long)))] */
    public static String n() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String n(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(wrap: long : 0x0005: INVOKE  (r1v0 long) =  type: STATIC call: android.os.SystemClock.elapsedRealtime():long)] */
    public static String o() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String o(android.content.Context r2) {
        /*
            java.lang.String r0 = "android.permission.ACCESS_WIFI_STATE"
            boolean r0 = a(r2, r0)
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x000d
            return r1
        L_0x000d:
            java.lang.String r0 = "wifi"
            java.lang.Object r2 = r2.getSystemService(r0)     // Catch:{ all -> 0x0025 }
            android.net.wifi.WifiManager r2 = (android.net.wifi.WifiManager) r2     // Catch:{ all -> 0x0025 }
            boolean r0 = r2.isWifiEnabled()     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0025
            android.net.wifi.WifiInfo r2 = r2.getConnectionInfo()     // Catch:{ all -> 0x0025 }
            java.lang.String r2 = r2.getBSSID()     // Catch:{ all -> 0x0025 }
            goto L_0x0026
        L_0x0025:
            r2 = r1
        L_0x0026:
            if (r2 != 0) goto L_0x0029
            r2 = r1
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.o(android.content.Context):java.lang.String");
    }

    public static String p() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String p(android.content.Context r3) {
        /*
            java.lang.String r0 = ""
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()
            int r3 = r3.targetSdkVersion
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0022 }
            r2 = 29
            if (r1 < r2) goto L_0x0010
            goto L_0x0022
        L_0x0010:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0022 }
            r2 = 26
            if (r1 < r2) goto L_0x001f
            r1 = 28
            if (r3 < r1) goto L_0x001f
            java.lang.String r3 = android.os.Build.getSerial()     // Catch:{ all -> 0x0022 }
            goto L_0x0023
        L_0x001f:
            java.lang.String r3 = android.os.Build.SERIAL     // Catch:{ all -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r3 = r0
        L_0x0023:
            if (r3 != 0) goto L_0x0026
            r3 = r0
        L_0x0026:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.p(android.content.Context):java.lang.String");
    }

    public static String q() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static String q(Context context) {
        try {
            String t = t(context);
            String x = x();
            if (!a.b(t) || !a.b(x)) {
                return "";
            }
            return t + ":" + x();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0042 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String r() {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.lang.String r2 = "/system/build.prop"
            java.lang.String r3 = "ro.product.name=sdk"
            r1.put(r2, r3)
            java.lang.String r2 = "goldfish"
            java.lang.String r3 = "/proc/tty/drivers"
            r1.put(r3, r2)
            java.lang.String r3 = "/proc/cpuinfo"
            r1.put(r3, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "00"
            r2.append(r3)
            java.lang.String r3 = ":"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.util.Set r2 = r1.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0042:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0089
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 48
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch:{ all -> 0x0080 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ all -> 0x0080 }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ all -> 0x0080 }
            r8.<init>(r3)     // Catch:{ all -> 0x0080 }
            r7.<init>(r8)     // Catch:{ all -> 0x0080 }
            r6.<init>(r7)     // Catch:{ all -> 0x0080 }
        L_0x0060:
            java.lang.String r4 = r6.readLine()     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0078
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ all -> 0x007f }
            java.lang.Object r7 = r1.get(r3)     // Catch:{ all -> 0x007f }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x007f }
            boolean r4 = r4.contains(r7)     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0060
            r5 = 49
        L_0x0078:
            r0.append(r5)
            r6.close()     // Catch:{ all -> 0x0042 }
            goto L_0x0042
        L_0x007f:
            r4 = r6
        L_0x0080:
            r0.append(r5)
            if (r4 == 0) goto L_0x0042
            r4.close()
            goto L_0x0042
        L_0x0089:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.r():java.lang.String");
    }

    public static String r(Context context) {
        try {
            long j = 0;
            if (!((KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE)).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            for (int i = 0; i < 5; i++) {
                long j2 = -1;
                try {
                    j2 = new File(strArr[i]).lastModified();
                } catch (Throwable unused) {
                }
                j = Math.max(j2, j);
            }
            return "1:" + j;
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String s() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put(SkipBean.Type.PRODUCT, "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = null;
                String str3 = (String) Build.class.getField(str).get(null);
                String str4 = (String) linkedHashMap.get(str);
                if (str3 != null) {
                    str2 = str3.toLowerCase();
                }
                if (str2 != null && str2.contains(str4)) {
                    c = '1';
                }
            } catch (Throwable unused) {
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d A[Catch:{ all -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[Catch:{ all -> 0x0045 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String s(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch:{ all -> 0x0045 }
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch:{ all -> 0x0045 }
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch:{ all -> 0x0045 }
            r1 = 2
            if (r3 == r1) goto L_0x0025
            r1 = 5
            if (r3 != r1) goto L_0x0023
            goto L_0x0025
        L_0x0023:
            r3 = 0
            goto L_0x0026
        L_0x0025:
            r3 = 1
        L_0x0026:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0045 }
            r1.<init>()     // Catch:{ all -> 0x0045 }
            if (r3 == 0) goto L_0x0031
            java.lang.String r3 = "1"
            goto L_0x0034
        L_0x0031:
            java.lang.String r3 = "0"
        L_0x0034:
            r1.append(r3)     // Catch:{ all -> 0x0045 }
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch:{ all -> 0x0045 }
            r1.append(r0)     // Catch:{ all -> 0x0045 }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x0045 }
            return r3
        L_0x0045:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.s(android.content.Context):java.lang.String");
    }

    public static String t() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String b = a.b(str, "");
            if (b != null && b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String t(Context context) {
        if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0) {
                return null;
            }
            int subtype = activeNetworkInfo.getSubtype();
            if (!(subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7)) {
                if (subtype != 11) {
                    if (!(subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14)) {
                        if (subtype != 15) {
                            return subtype == 13 ? "4G" : "UNKNOW";
                        }
                    }
                    return "3G";
                }
            }
            return "2G";
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String u() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list == null) {
                return "02:00:00:00:00:00";
            }
            for (NetworkInterface networkInterface : list) {
                if (!(networkInterface == null || networkInterface.getName() == null || !networkInterface.getName().equalsIgnoreCase("wlan0"))) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "02:00:00:00:00:00";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Integer.valueOf(hardwareAddress[i] & 255)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0031 A[SYNTHETIC, Splitter:B:27:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String v() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x002e }
            r2.<init>(r0)     // Catch:{ all -> 0x002e }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x002f }
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r3)     // Catch:{ all -> 0x002f }
            java.lang.String r1 = r0.readLine()     // Catch:{ all -> 0x002c }
            boolean r3 = com.alipay.b.a.a.a.a.a(r1)     // Catch:{ all -> 0x002c }
            if (r3 != 0) goto L_0x0025
            java.lang.String r1 = r1.trim()     // Catch:{ all -> 0x002c }
            r0.close()     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r2.close()     // Catch:{ all -> 0x0024 }
        L_0x0024:
            return r1
        L_0x0025:
            r0.close()     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r2.close()     // Catch:{ all -> 0x0037 }
            goto L_0x0037
        L_0x002c:
            r1 = r0
            goto L_0x002f
        L_0x002e:
            r2 = r1
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ all -> 0x0034 }
        L_0x0034:
            if (r2 == 0) goto L_0x0037
            goto L_0x0028
        L_0x0037:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.v():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A[SYNTHETIC, Splitter:B:28:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String w() {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ all -> 0x0047 }
            r3.<init>(r0)     // Catch:{ all -> 0x0047 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x0048 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch:{ all -> 0x0048 }
        L_0x0013:
            java.lang.String r2 = r0.readLine()     // Catch:{ all -> 0x0045 }
            if (r2 == 0) goto L_0x003e
            boolean r4 = com.alipay.b.a.a.a.a.a(r2)     // Catch:{ all -> 0x0045 }
            if (r4 != 0) goto L_0x0013
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ all -> 0x0045 }
            if (r2 == 0) goto L_0x0013
            int r4 = r2.length     // Catch:{ all -> 0x0045 }
            r5 = 1
            if (r4 <= r5) goto L_0x0013
            r4 = 0
            r4 = r2[r4]     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch:{ all -> 0x0045 }
            if (r4 == 0) goto L_0x0013
            r2 = r2[r5]     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = r2.trim()     // Catch:{ all -> 0x0045 }
        L_0x003e:
            r3.close()     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0.close()     // Catch:{ all -> 0x0052 }
            goto L_0x0052
        L_0x0045:
            r2 = r0
            goto L_0x0048
        L_0x0047:
            r3 = r2
        L_0x0048:
            if (r3 == 0) goto L_0x004d
            r3.close()     // Catch:{ all -> 0x004d }
        L_0x004d:
            if (r2 == 0) goto L_0x0052
            r2.close()
        L_0x0052:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.b.w():java.lang.String");
    }

    private static String x() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress().toString();
                        }
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}
