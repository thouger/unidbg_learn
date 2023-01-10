package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* compiled from: UMProbe */
public class k {
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e = new Object();

    public static String a(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
            if (sharedPreferences == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            synchronized (e) {
                jSONObject.put(b, sharedPreferences.getString(b, ""));
                jSONObject.put(c, sharedPreferences.getString(c, ""));
                jSONObject.put(d, sharedPreferences.getString(d, ""));
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static void b(Context context) {
        if (!c(context)) {
            new AnonymousClass1(new String[]{"unknown", "unknown", "unknown"}, context).start();
        }
    }

    /* compiled from: UMProbe */
    /* renamed from: com.umeng.commonsdk.internal.utils.k$1  reason: invalid class name */
    static class AnonymousClass1 extends Thread {
        final /* synthetic */ String[] a;
        final /* synthetic */ Context b;

        AnonymousClass1(String[] strArr, Context context) {
            this.a = strArr;
            this.b = context;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            try {
                this.a[0] = k.c();
                this.a[1] = k.a();
                this.a[2] = k.b();
                ULog.i("diskType = " + this.a[0] + "; ThremalZone = " + this.a[1] + "; GoldFishRc = " + this.a[2]);
                k.b(this.b, this.a);
            } catch (Throwable th) {
                UMCrashManager.reportCrash(this.b, th);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context != null && (sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0)) != null) {
            synchronized (e) {
                sharedPreferences.edit().putString(b, strArr[0]).putString(c, strArr[1]).putString(d, strArr[2]).commit();
            }
        }
    }

    public static boolean c(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(b, ""))) {
            return false;
        }
        return true;
    }

    public static int a(String str, String str2) throws IOException {
        int i;
        if (Build.VERSION.SDK_INT > 28) {
            return -1;
        }
        Process exec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (readLine.contains(str2)) {
                    i = 1;
                    break;
                }
            } else {
                i = -1;
                break;
            }
        }
        try {
            if (exec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException unused) {
            return -1;
        }
    }

    public static String a() {
        int i;
        try {
            i = a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable unused) {
            i = -1;
        }
        if (i > 0) {
            return "thermal_zone";
        }
        return i < 0 ? "noper" : "unknown";
    }

    public static String b() {
        int i;
        try {
            i = a("ls /", "goldfish");
        } catch (Throwable unused) {
            i = -1;
        }
        if (i > 0) {
            return "goldfish";
        }
        return i < 0 ? "noper" : "unknown";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A[SYNTHETIC, Splitter:B:21:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0038 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "/proc/diskstats"
            r2.<init>(r3)     // Catch:{ all -> 0x0038 }
            r1.<init>(r2)     // Catch:{ all -> 0x0038 }
        L_0x000e:
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "mtd"
            java.lang.String r3 = "sda"
            java.lang.String r4 = "mmcblk"
            if (r0 == 0) goto L_0x0034
            boolean r5 = r0.contains(r4)
            if (r5 == 0) goto L_0x0025
            r2 = r4
            goto L_0x003c
        L_0x0025:
            boolean r4 = r0.contains(r3)
            if (r4 == 0) goto L_0x002d
            r2 = r3
            goto L_0x003c
        L_0x002d:
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x000e
            goto L_0x003c
        L_0x0034:
            java.lang.String r2 = "unknown"
            goto L_0x003c
        L_0x0038:
            r1 = r0
        L_0x0039:
            java.lang.String r2 = "noper"
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ all -> 0x0041 }
        L_0x0041:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.k.c():java.lang.String");
    }
}
