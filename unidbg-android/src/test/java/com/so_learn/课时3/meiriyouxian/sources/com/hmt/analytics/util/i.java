package com.hmt.analytics.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* compiled from: SPUtils */
public class i {
    public static void a(Context context, String str, String str2, Object obj) {
        a(a(context, str), str2, obj);
    }

    public static void a(Context context, String str, Object obj) {
        a(context, "HMTAGENT_INFO", str, obj);
    }

    private static SharedPreferences.Editor a(Context context, String str) {
        return b(context, str).edit();
    }

    private static SharedPreferences b(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    private static void a(SharedPreferences.Editor editor, String str, Object obj) {
        if (obj instanceof String) {
            editor.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Set) {
            editor.putStringSet(str, (Set) obj);
        } else if (obj instanceof Double) {
            editor.putString(str, obj + "");
        } else {
            editor.putString(str, obj.toString());
        }
        editor.apply();
    }

    public static Object b(Context context, String str, Object obj) {
        return b(context, "HMTAGENT_INFO", str, obj);
    }

    public static Object b(Context context, String str, String str2, Object obj) {
        return context == null ? obj : a(b(context, str), str2, obj);
    }

    private static Object a(SharedPreferences sharedPreferences, String str, Object obj) {
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (obj instanceof Set) {
            return sharedPreferences.getStringSet(str, (Set) obj);
        }
        return null;
    }
}
