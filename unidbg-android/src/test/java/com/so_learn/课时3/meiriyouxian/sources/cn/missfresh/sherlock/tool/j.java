package cn.missfresh.sherlock.tool;

import android.text.TextUtils;
import android.util.Log;

/* compiled from: SLog */
public class j {
    private static String a = "[Sherlock]-";
    private static boolean b = true;

    public static void a(String str, String str2) {
    }

    public static void a(String str, String str2, Object... objArr) {
    }

    public static void a(boolean z) {
        a((String) null, z);
    }

    public static void b(String str, String str2, Object... objArr) {
    }

    public static void c(String str, String str2) {
    }

    public static void c(String str, String str2, Object... objArr) {
    }

    public static void d(String str, String str2) {
    }

    public static void e(String str, String str2) {
        if (b) {
            Log.e(a + str, str2 + "");
        }
    }

    public static void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            a += "[" + str + "]";
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (b) {
            Log.e(a + str, str2 + "");
        }
    }

    public static void b(String str, String str2) {
        if (b) {
            Log.d(a + str, str2 + "");
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (b) {
            Log.d(a + str, str2 + "", th);
        }
    }
}
