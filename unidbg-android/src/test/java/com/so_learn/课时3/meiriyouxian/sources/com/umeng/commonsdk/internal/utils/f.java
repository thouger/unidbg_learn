package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: InfoPreference */
public class f {
    private static final String a = "info";
    private static final String b = "a_dc";
    private static final String c = "bssid";
    private static final String d = "ssid";
    private static final String e = "a_fcy";
    private static final String f = "a_hssid";
    private static final String g = "a_ip";
    private static final String h = "a_ls";
    private static final String i = "a_mac";
    private static final String j = "a_nid";
    private static final String k = "rssi";
    private static final String l = "sta";
    private static final String m = "ts";
    private static final String n = "wifiinfo";
    private static final String o = "ua";

    public static JSONArray a(Context context) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            if (sharedPreferences == null || (string = sharedPreferences.getString(n, null)) == null) {
                return null;
            }
            return new JSONArray(string);
        } catch (Exception e2) {
            ULog.e(e2.getMessage());
            return null;
        }
    }

    public static void b(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(n).commit();
        }
    }

    public static void a(Context context, a.b bVar) {
        JSONArray jSONArray;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            String str = null;
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString(n, null);
                if (string == null) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b, bVar.a);
                jSONObject.put("bssid", bVar.b);
                jSONObject.put("ssid", bVar.c);
                jSONObject.put(e, bVar.d);
                jSONObject.put(f, bVar.e);
                jSONObject.put(g, bVar.f);
                jSONObject.put(h, bVar.g);
                jSONObject.put(i, bVar.h);
                jSONObject.put(j, bVar.i);
                jSONObject.put(k, bVar.j);
                jSONObject.put(l, bVar.k);
                jSONObject.put("ts", bVar.l);
                jSONArray.put(jSONObject);
                str = jSONArray.toString();
            }
            if (str != null) {
                sharedPreferences.edit().putString(n, str).commit();
            }
        } catch (Exception e2) {
            ULog.e(e2.getMessage());
        }
    }

    public static void a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(o, str).commit();
        }
    }

    public static String c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(o, null);
        }
        return null;
    }
}
