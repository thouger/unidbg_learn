package com.alipay.b.a.a.b;

import android.os.Build;
import java.io.File;

public final class d {
    private static d a = new d();

    private d() {
    }

    public static d a() {
        return a;
    }

    private static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A[Catch:{ Exception -> 0x006b }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r7) {
        /*
            r0 = 0
            java.lang.String r1 = android.os.Build.HARDWARE     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "goldfish"
            boolean r1 = r1.contains(r2)     // Catch:{ Exception -> 0x006b }
            r2 = 1
            if (r1 != 0) goto L_0x006a
            java.lang.String r1 = android.os.Build.PRODUCT     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = "sdk"
            boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x006b }
            if (r1 != 0) goto L_0x006a
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = "generic"
            boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x006b }
            if (r1 == 0) goto L_0x0024
            goto L_0x006a
        L_0x0024:
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r7.getSystemService(r1)     // Catch:{ Exception -> 0x006b }
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch:{ Exception -> 0x006b }
            if (r1 == 0) goto L_0x005a
            java.lang.String r1 = r1.getDeviceId()     // Catch:{ Exception -> 0x006b }
            if (r1 == 0) goto L_0x0056
            int r3 = r1.length()     // Catch:{ Exception -> 0x006b }
            if (r3 != 0) goto L_0x003c
            goto L_0x0056
        L_0x003c:
            r4 = r0
        L_0x003d:
            if (r4 >= r3) goto L_0x0056
            char r5 = r1.charAt(r4)     // Catch:{ Exception -> 0x006b }
            boolean r5 = java.lang.Character.isWhitespace(r5)     // Catch:{ Exception -> 0x006b }
            if (r5 != 0) goto L_0x0053
            char r5 = r1.charAt(r4)     // Catch:{ Exception -> 0x006b }
            r6 = 48
            if (r5 == r6) goto L_0x0053
            r1 = r0
            goto L_0x0057
        L_0x0053:
            int r4 = r4 + 1
            goto L_0x003d
        L_0x0056:
            r1 = r2
        L_0x0057:
            if (r1 == 0) goto L_0x005a
            return r2
        L_0x005a:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ Exception -> 0x006b }
            java.lang.String r1 = "android_id"
            java.lang.String r7 = android.provider.Settings.Secure.getString(r7, r1)     // Catch:{ Exception -> 0x006b }
            boolean r7 = com.alipay.b.a.a.a.a.a(r7)     // Catch:{ Exception -> 0x006b }
            return r7
        L_0x006a:
            return r2
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.b.d.a(android.content.Context):boolean");
    }

    public static String b() {
        return "android";
    }

    public static boolean c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String d() {
        return Build.BOARD;
    }

    public static String e() {
        return Build.BRAND;
    }

    public static String f() {
        return Build.DEVICE;
    }

    public static String g() {
        return Build.DISPLAY;
    }

    public static String h() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String i() {
        return Build.MANUFACTURER;
    }

    public static String j() {
        return Build.MODEL;
    }

    public static String k() {
        return Build.PRODUCT;
    }

    public static String l() {
        return Build.VERSION.RELEASE;
    }

    public static String m() {
        return Build.VERSION.SDK;
    }

    public static String n() {
        return Build.TAGS;
    }

    public static String o() {
        return a("ro.kernel.qemu", "0");
    }
}
