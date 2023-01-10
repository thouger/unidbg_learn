package com.sobot.chat.b.b;

import android.os.Build;
import android.os.Environment;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* compiled from: RomUtils */
public final class a {
    private static C0140a a;

    public static boolean a() {
        return "huawei".equals(e().a);
    }

    public static boolean b() {
        return "vivo".equals(e().a);
    }

    public static boolean c() {
        return MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI.equals(e().a);
    }

    public static boolean d() {
        return "oppo".equals(e().a);
    }

    public static C0140a e() {
        C0140a aVar = a;
        if (aVar != null) {
            return aVar;
        }
        a = new C0140a();
        String g = g();
        String f = f();
        if (a(g, f, "huawei")) {
            a.a = "huawei";
            String a2 = a("ro.build.version.emui");
            String[] split = a2.split(Session.SESSION_SEPARATION_CHAR_CHILD);
            if (split.length > 1) {
                a.b = split[1];
            } else {
                a.b = a2;
            }
            return a;
        } else if (a(g, f, "vivo")) {
            a.a = "vivo";
            a.b = a("ro.vivo.os.build.display.id");
            return a;
        } else if (a(g, f, MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI)) {
            a.a = MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI;
            a.b = a("ro.build.version.incremental");
            return a;
        } else if (a(g, f, "oppo")) {
            a.a = "oppo";
            a.b = a("ro.build.version.opporom");
            return a;
        } else {
            a.a = f;
            a.b = a("");
            return a;
        }
    }

    private static boolean a(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    private static String f() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String g() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String a(String str) {
        String b = !TextUtils.isEmpty(str) ? b(str) : "";
        if (TextUtils.isEmpty(b) || b.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    b = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        if (TextUtils.isEmpty(b)) {
            return "unknown";
        }
        return b;
    }

    private static String b(String str) {
        String c = c(str);
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String d = d(str);
        return (TextUtils.isEmpty(d) && Build.VERSION.SDK_INT < 28) ? e(str) : d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A[SYNTHETIC, Splitter:B:18:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            r2.<init>()     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            r2.append(r4)     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.lang.Process r4 = r1.exec(r4)     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch:{ IOException -> 0x0045, all -> 0x003e }
            java.lang.String r4 = r1.readLine()     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            if (r4 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r4
        L_0x0035:
            r1.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004a
        L_0x0039:
            r4 = move-exception
            r0 = r1
            goto L_0x003f
        L_0x003c:
            r0 = r1
            goto L_0x0045
        L_0x003e:
            r4 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            throw r4
        L_0x0045:
            if (r0 == 0) goto L_0x004a
            r0.close()
        L_0x004a:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.b.b.a.c(java.lang.String):java.lang.String");
    }

    private static String d(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    /* compiled from: RomUtils */
    /* renamed from: com.sobot.chat.b.b.a$a  reason: collision with other inner class name */
    public static class C0140a {
        private String a;
        private String b;

        public String toString() {
            return "RomInfo{name=" + this.a + ", version=" + this.b + "}";
        }
    }
}
