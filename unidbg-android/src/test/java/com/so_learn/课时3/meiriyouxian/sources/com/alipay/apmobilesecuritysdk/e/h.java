package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.b.a.a.a.a.b;
import com.alipay.b.a.a.c.a;
import com.alipay.b.a.a.c.e;
import java.util.UUID;

public class h {
    private static String a = "";

    public static long a(Context context) {
        String a2 = a.a(context, "vkeyid_settings", "update_time_interval");
        if (!com.alipay.b.a.a.a.a.b(a2)) {
            return 86400000;
        }
        try {
            return Long.parseLong(a2);
        } catch (Exception unused) {
            return 86400000;
        }
    }

    public static void a(Context context, String str) {
        a(context, "update_time_interval", str);
    }

    public static void a(Context context, String str, long j) {
        a.a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    private static void a(Context context, String str, String str2) {
        a.a(context, "vkeyid_settings", str, str2);
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : "0");
    }

    public static String b(Context context) {
        return a.a(context, "vkeyid_settings", "last_apdid_env");
    }

    public static void b(Context context, String str) {
        a(context, "last_machine_boot_time", str);
    }

    public static void c(Context context, String str) {
        a(context, "last_apdid_env", str);
    }

    public static boolean c(Context context) {
        String a2 = a.a(context, "vkeyid_settings", "log_switch");
        return a2 != null && "1".equals(a2);
    }

    public static String d(Context context) {
        return a.a(context, "vkeyid_settings", "dynamic_key");
    }

    public static void d(Context context, String str) {
        a(context, "agent_switch", str);
    }

    public static String e(Context context) {
        return a.a(context, "vkeyid_settings", "apse_degrade");
    }

    public static void e(Context context, String str) {
        a(context, "dynamic_key", str);
    }

    public static String f(Context context) {
        String str;
        SharedPreferences.Editor edit;
        synchronized (h.class) {
            if (com.alipay.b.a.a.a.a.a(a)) {
                String a2 = e.a(context, "alipay_vkey_random", "random", "");
                a = a2;
                if (com.alipay.b.a.a.a.a.a(a2)) {
                    a = b.a(UUID.randomUUID().toString());
                    String str2 = a;
                    if (!(str2 == null || (edit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) == null)) {
                        edit.putString("random", str2);
                        edit.commit();
                    }
                }
            }
            str = a;
        }
        return str;
    }

    public static void f(Context context, String str) {
        a(context, "webrtc_url", str);
    }

    public static void g(Context context, String str) {
        a(context, "apse_degrade", str);
    }

    public static long h(Context context, String str) {
        try {
            String a2 = a.a(context, "vkeyid_settings", "vkey_valid" + str);
            if (com.alipay.b.a.a.a.a.a(a2)) {
                return 0;
            }
            return Long.parseLong(a2);
        } catch (Throwable unused) {
            return 0;
        }
    }
}
