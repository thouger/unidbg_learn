package com.sobot.chat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* compiled from: SharedPreferencesUtil */
public class s {
    private static SharedPreferences a = null;
    private static String b = "sobot_config";

    public static void a(Context context, String str) {
        a = null;
        context.getSharedPreferences(b, 0).edit().putString("sobot_appkey_chat", str).apply();
    }

    public static String b(Context context, String str) {
        if (context == null) {
            return str;
        }
        return context.getSharedPreferences(b, 0).getString("sobot_appkey_chat", str);
    }

    public static void a(Context context, String str, String str2) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        a.edit().putString(str, str2).apply();
    }

    public static void a(Context context, String str, boolean z) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        a.edit().putBoolean(str, z).apply();
    }

    public static boolean b(Context context, String str, boolean z) {
        if (a == null) {
            if (context == null) {
                return z;
            }
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        return a.getBoolean(str, z);
    }

    public static String b(Context context, String str, String str2) {
        if (a == null) {
            if (context == null) {
                return str2;
            }
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        return a.getString(str, str2);
    }

    public static void a(Context context, String str, int i) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        a.edit().putInt(str, i).apply();
    }

    public static void a(Context context, String str, long j) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        a.edit().putLong(str, j).apply();
    }

    public static int b(Context context, String str, int i) {
        if (a == null) {
            if (context == null) {
                return i;
            }
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        return a.getInt(str, i);
    }

    public static long b(Context context, String str, long j) {
        if (a == null) {
            if (context == null) {
                return j;
            }
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        return a.getLong(str, j);
    }

    public static void c(Context context, String str) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        a.edit().remove(str).apply();
    }

    public static void a(Context context, String str, Object obj) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        SharedPreferences.Editor edit = a.edit();
        edit.putString(str, a(obj));
        edit.commit();
    }

    public static Object d(Context context, String str) {
        if (a == null) {
            a = context.getSharedPreferences(b + b(context, ""), 0);
        }
        String string = a.getString(str, null);
        if (string != null) {
            return a(string);
        }
        return null;
    }

    private static String a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object a(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
