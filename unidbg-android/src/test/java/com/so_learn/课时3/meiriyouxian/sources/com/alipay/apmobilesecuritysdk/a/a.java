package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.b.a.a.e.a.c;
import com.alipay.b.a.a.e.a.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class a {
    private Context a;
    private com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    private int c = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String b = b(context);
        return com.alipay.b.a.a.a.a.a(b) ? h.f(context) : b;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a = i.a(str);
            if (!com.alipay.b.a.a.a.a.a(a)) {
                return a;
            }
            String a2 = g.a(context, str);
            i.a(str, a2);
            return !com.alipay.b.a.a.a.a.a(a2) ? a2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private c b(Map<String, String> map) {
        String str;
        String str2;
        String str3;
        b b;
        b c;
        String str4 = "";
        try {
            Context context = this.a;
            d dVar = new d();
            String a = com.alipay.b.a.a.a.a.a(map, "appName", str4);
            String a2 = com.alipay.b.a.a.a.a.a(map, TextToSpeech.Engine.KEY_PARAM_SESSION_ID, str4);
            String a3 = com.alipay.b.a.a.a.a.a(map, "rpcVersion", str4);
            String a4 = a(context, a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (com.alipay.b.a.a.a.a.b(a2)) {
                dVar.c = a2;
            } else {
                dVar.c = a4;
            }
            dVar.d = securityToken;
            dVar.e = d;
            dVar.a = "android";
            com.alipay.apmobilesecuritysdk.e.c c2 = com.alipay.apmobilesecuritysdk.e.d.c(context);
            if (c2 != null) {
                str = c2.a;
                str2 = c2.c;
            } else {
                str2 = str4;
                str = str2;
            }
            if (com.alipay.b.a.a.a.a.a(str) && (c = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str = c.a;
                str2 = c.c;
            }
            com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b();
            if (b2 != null) {
                str4 = b2.a;
                str3 = b2.c;
            } else {
                str3 = str4;
            }
            if (com.alipay.b.a.a.a.a.a(str4) && (b = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str4 = b.a;
                str3 = b.c;
            }
            dVar.h = str;
            dVar.g = str4;
            dVar.j = a3;
            if (com.alipay.b.a.a.a.a.a(str)) {
                dVar.b = str4;
                dVar.i = str3;
            } else {
                dVar.b = str;
                dVar.i = str2;
            }
            dVar.f = e.a(context, map);
            return com.alipay.b.a.a.e.d.b(this.a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            th.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            String b = i.b();
            if (!com.alipay.b.a.a.a.a.a(b)) {
                return b;
            }
            com.alipay.apmobilesecuritysdk.e.c b2 = com.alipay.apmobilesecuritysdk.e.d.b(context);
            if (b2 != null) {
                i.a(b2);
                String str = b2.a;
                if (com.alipay.b.a.a.a.a.b(str)) {
                    return str;
                }
            }
            b b3 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b3 == null) {
                return "";
            }
            i.a(b3);
            String str2 = b3.a;
            return com.alipay.b.a.a.a.a.b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d7 A[Catch:{ Exception -> 0x0244 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0206 A[Catch:{ Exception -> 0x0244 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
        // Method dump skipped, instructions count: 587
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}
