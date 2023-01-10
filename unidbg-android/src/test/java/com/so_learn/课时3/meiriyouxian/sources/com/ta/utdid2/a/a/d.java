package com.ta.utdid2.a.a;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.Random;

public class d {
    public static String c(Context context) {
        return null;
    }

    public static String a() {
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] bytes = c.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = c.getBytes((int) System.nanoTime());
        byte[] bytes3 = c.getBytes(nextInt);
        byte[] bytes4 = c.getBytes(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return b.encodeToString(bArr, 2);
    }

    public static String a(Context context) {
        String str = null;
        if (f.m4071a((String) null)) {
            str = b();
        }
        if (f.m4071a(str)) {
            str = b(context);
        }
        return f.m4071a(str) ? a() : str;
    }

    private static String b(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            try {
                if (TextUtils.isEmpty(string) || string.equalsIgnoreCase("a5f5faddde9e9f02") || string.equalsIgnoreCase("8e17f7422b35fbea") || string.equalsIgnoreCase("0000000000000000")) {
                    return "";
                }
                return string;
            } catch (Throwable unused) {
                return string;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    private static String b() {
        String str = g.get("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(str)) {
            str = g.get("ro.sys.aliyun.clouduuid", "");
        }
        return TextUtils.isEmpty(str) ? c() : str;
    }

    private static String c() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }
}
