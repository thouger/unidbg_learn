package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.os.Environment;
import com.alipay.b.a.a.a.a.c;
import com.alipay.b.a.a.c.b;
import com.alipay.b.a.a.c.e;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

public class a {
    public static String a(Context context, String str, String str2) {
        if (context == null || com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
            return null;
        }
        try {
            String a = e.a(context, str, str2, "");
            if (com.alipay.b.a.a.a.a.a(a)) {
                return null;
            }
            return c.b(c.a(), a);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        synchronized (a.class) {
            if (com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
                return null;
            }
            try {
                String a = b.a(str);
                if (com.alipay.b.a.a.a.a.a(a)) {
                    return null;
                }
                String string = new JSONObject(a).getString(str2);
                if (com.alipay.b.a.a.a.a.a(string)) {
                    return null;
                }
                return c.b(c.a(), string);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
            try {
                String a = c.a(c.a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                e.a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        synchronized (a.class) {
            if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
                String a = b.a(str);
                JSONObject jSONObject = new JSONObject();
                if (com.alipay.b.a.a.a.a.b(a)) {
                    try {
                        jSONObject = new JSONObject(a);
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                }
                jSONObject.put(str2, c.a(c.a(), str3));
                jSONObject.toString();
                try {
                    System.clearProperty(str);
                } catch (Throwable unused2) {
                }
                if (com.alipay.b.a.a.c.c.a()) {
                    String str4 = ".SystemConfig" + File.separator + str;
                    try {
                        if (com.alipay.b.a.a.c.c.a()) {
                            File file = new File(Environment.getExternalStorageDirectory(), str4);
                            if (file.exists() && file.isFile()) {
                                file.delete();
                            }
                        }
                    } catch (Throwable unused3) {
                    }
                }
            }
        }
    }
}
