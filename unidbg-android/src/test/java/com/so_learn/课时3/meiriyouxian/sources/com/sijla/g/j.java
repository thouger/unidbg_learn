package com.sijla.g;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.sijla.g.a.a;
import com.sijla.g.a.d;
import java.io.File;

public class j {
    public static String a;

    public static String a(Context context) {
        a a2 = a.a(context);
        String a3 = a2.a("TruthUid");
        if (!TextUtils.isEmpty(a3)) {
            return a3;
        }
        String b = b(context);
        a2.a("TruthUid", b);
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r6) {
        /*
            java.lang.String r0 = f(r6)
            java.lang.String r1 = "arch"
            r2 = 0
            android.content.SharedPreferences r1 = r6.getSharedPreferences(r1, r2)     // Catch:{ Exception -> 0x006e }
            android.content.SharedPreferences$Editor r2 = r1.edit()     // Catch:{ Exception -> 0x006e }
            boolean r3 = com.sijla.g.b.a(r0)     // Catch:{ Exception -> 0x006e }
            java.lang.String r4 = "hbuid"
            if (r3 != 0) goto L_0x0029
            r3 = 32
            int r5 = r0.length()
            if (r3 != r5) goto L_0x0029
            android.content.SharedPreferences$Editor r1 = r2.putString(r4, r0)
            r1.apply()
            goto L_0x0072
        L_0x0029:
            java.lang.String r0 = ""
            java.lang.String r0 = r1.getString(r4, r0)
            boolean r1 = com.sijla.g.b.a(r0)
            if (r1 == 0) goto L_0x0041
            java.lang.String r0 = c(r6)
            android.content.SharedPreferences$Editor r1 = r2.putString(r4, r0)
            r1.apply()
        L_0x0041:
            java.lang.String r1 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r1 = com.sijla.g.a.a.j(r6, r1)
            if (r1 == 0) goto L_0x0072
            boolean r1 = com.sijla.g.a.a.b()
            if (r1 == 0) goto L_0x0072
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.add(r0)
            java.lang.String r2 = r6.getPackageName()
            r1.add(r2)
            long r2 = com.sijla.g.d.e()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r1.add(r2)
            com.sijla.g.b.a(r6, r1)
            goto L_0x0072
        L_0x006e:
            java.lang.String r0 = c(r6)
        L_0x0072:
            java.lang.String r6 = com.sijla.g.j.a
            if (r6 != 0) goto L_0x0078
            com.sijla.g.j.a = r0
        L_0x0078:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.g.j.b(android.content.Context):java.lang.String");
    }

    private static String f(Context context) {
        String str = "";
        if (a.j(context, Manifest.permission.READ_EXTERNAL_STORAGE) && a.b()) {
            for (String str2 : b.b()) {
                try {
                    str = b.b(b.a(new File(str2))).split("\t")[0];
                    if (!TextUtils.isEmpty(str)) {
                        break;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return str;
    }

    public static String c(Context context) {
        return d(context);
    }

    public static String d(Context context) {
        try {
            return d.a(a.u(context) + (Build.BRAND + Build.MODEL) + a.o(context)).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return d.a(a.o(context)).toUpperCase();
        }
    }

    public static String e(Context context) {
        try {
            return d.a(a.u(context) + (a.k() + a.h() + Build.BRAND + Build.CPU_ABI + Build.DEVICE + Build.MANUFACTURER + Build.MODEL + Build.PRODUCT) + a.o(context) + a.C(context)).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return d.a(a.o(context)).toUpperCase();
        }
    }
}
