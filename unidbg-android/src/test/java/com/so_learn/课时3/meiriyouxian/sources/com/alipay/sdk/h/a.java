package com.alipay.sdk.h;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.util.e;
import com.huawei.hms.api.ConnectionResult;
import java.util.Random;
import org.json.JSONObject;

public class a {
    private static Context a;
    private static a g;
    private String b;
    private String c;
    private long d;
    private String e;
    private String f;
    private boolean h = false;

    private void h() {
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (g == null) {
                g = new a();
            }
            if (a == null) {
                g.b(context);
            }
            aVar = g;
        }
        return aVar;
    }

    private void b(Context context) {
        if (context != null) {
            a = context.getApplicationContext();
        }
        if (!this.h) {
            this.h = true;
            e();
        }
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
            r9 = this;
            java.lang.String r0 = ""
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r2 = 0
            java.lang.String r3 = "alipay_tid_storage"
            java.lang.String r4 = "tidinfo"
            r5 = 1
            java.lang.String r3 = com.alipay.sdk.h.a.C0067a.a(r3, r4, r5)     // Catch:{ Exception -> 0x005a }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x005a }
            if (r4 != 0) goto L_0x0056
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x005a }
            r4.<init>(r3)     // Catch:{ Exception -> 0x005a }
            java.lang.String r3 = "tid"
            java.lang.String r3 = r4.optString(r3, r0)     // Catch:{ Exception -> 0x005a }
            java.lang.String r5 = "client_key"
            java.lang.String r5 = r4.optString(r5, r0)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r6 = "timestamp"
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0050 }
            long r6 = r4.optLong(r6, r7)     // Catch:{ Exception -> 0x0050 }
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0050 }
            java.lang.String r6 = "vimei"
            java.lang.String r6 = r4.optString(r6, r0)     // Catch:{ Exception -> 0x0050 }
            java.lang.String r7 = "vimsi"
            java.lang.String r0 = r4.optString(r7, r0)     // Catch:{ Exception -> 0x004e }
            goto L_0x0062
        L_0x004e:
            r0 = move-exception
            goto L_0x005e
        L_0x0050:
            r0 = move-exception
            r6 = r2
            goto L_0x005e
        L_0x0053:
            r0 = move-exception
            r5 = r2
            goto L_0x005d
        L_0x0056:
            r0 = r2
            r5 = r0
            r6 = r5
            goto L_0x0063
        L_0x005a:
            r0 = move-exception
            r3 = r2
            r5 = r3
        L_0x005d:
            r6 = r5
        L_0x005e:
            com.alipay.sdk.util.e.a(r0)
            r0 = r2
        L_0x0062:
            r2 = r3
        L_0x0063:
            java.lang.String r3 = "mspl"
            java.lang.String r4 = "tid_str: load"
            com.alipay.sdk.util.e.a(r3, r4)
            boolean r3 = r9.a(r2, r5, r6, r0)
            if (r3 == 0) goto L_0x0076
            r9.f()
            goto L_0x0084
        L_0x0076:
            r9.b = r2
            r9.c = r5
            long r1 = r1.longValue()
            r9.d = r1
            r9.e = r6
            r9.f = r0
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.h.a.e():void");
    }

    private boolean a(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    private void f() {
        this.b = "";
        this.c = c();
        this.d = System.currentTimeMillis();
        this.e = g();
        this.f = g();
        C0067a.a("alipay_tid_storage", "tidinfo");
    }

    private String g() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(ConnectionResult.NETWORK_ERROR) + 1000);
    }

    public String c() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    public void a(String str, String str2) {
        e.a("mspl", "tid_str: save");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.b = str;
            this.c = str2;
            this.d = System.currentTimeMillis();
            i();
            h();
        }
    }

    private void i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.b);
            jSONObject.put("client_key", this.c);
            jSONObject.put("timestamp", this.d);
            jSONObject.put("vimei", this.e);
            jSONObject.put("vimsi", this.f);
            C0067a.a("alipay_tid_storage", "tidinfo", jSONObject.toString(), true);
        } catch (Exception e) {
            e.a(e);
        }
    }

    /* renamed from: com.alipay.sdk.h.a$a  reason: collision with other inner class name */
    public static class C0067a {
        public static void a(String str, String str2) {
            if (a.a != null) {
                a.a.getSharedPreferences(str, 0).edit().remove(str2).apply();
            }
        }

        public static String a(String str, String str2, boolean z) {
            if (a.a == null) {
                return null;
            }
            String string = a.a.getSharedPreferences(str, 0).getString(str2, null);
            if (!TextUtils.isEmpty(string) && z) {
                string = com.alipay.sdk.c.e.b(a(), string, string);
                if (TextUtils.isEmpty(string)) {
                    e.a("mspl", "tid_str: pref failed");
                }
            }
            e.a("mspl", "tid_str: from local");
            return string;
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (a.a != null) {
                SharedPreferences sharedPreferences = a.a.getSharedPreferences(str, 0);
                if (z) {
                    String a = a();
                    String a2 = com.alipay.sdk.c.e.a(a, str3, str3);
                    if (TextUtils.isEmpty(a2)) {
                        String.format("LocalPreference::putLocalPreferences failed %s\uff0c%s", str3, a);
                    }
                    str3 = a2;
                }
                sharedPreferences.edit().putString(str2, str3).apply();
            }
        }

        private static String a() {
            String str;
            try {
                str = a.a.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                e.a(th);
                str = "";
            }
            return (str + "0000000000000000000000000000").substring(0, 24);
        }
    }
}
