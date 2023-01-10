package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.lang.reflect.Method;

public class c {
    public static String a(Context context) {
        SharedPreferences a;
        if (context == null || (a = a.a(context)) == null) {
            return null;
        }
        return a.getString("aaid", null);
    }

    public static void a(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString(Constant.KEY_MAC, str).commit();
        }
    }

    public static String b(Context context) {
        SharedPreferences a;
        return (context == null || (a = a.a(context)) == null) ? "" : a.getString("zdata", null);
    }

    public static void b(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("oaid", str).commit();
        }
    }

    public static String c(Context context) {
        Method declaredMethod;
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.statistics.common.DeviceConfig");
            if (!(cls == null || (declaredMethod = cls.getDeclaredMethod("getMac", Context.class)) == null)) {
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(cls, context);
                if (invoke != null && (invoke instanceof String)) {
                    return (String) invoke;
                }
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static void c(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("resetToken", str).commit();
        }
    }

    public static String d(Context context) {
        Method declaredMethod;
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.statistics.common.DeviceConfig");
            if (!(cls == null || (declaredMethod = cls.getDeclaredMethod("getOaid", Context.class)) == null)) {
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(cls, context);
                if (invoke != null && (invoke instanceof String)) {
                    return (String) invoke;
                }
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static void d(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("uabc", str).commit();
        }
    }

    public static void e(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("aaid", str).commit();
        }
    }

    public static void f(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = a.a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("zdata", str).commit();
        }
    }
}
