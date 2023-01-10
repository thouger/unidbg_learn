package com.cmic.sso.wy.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.cmic.sso.wy.c.a.e;
import com.cmic.sso.wy.d.b;
import com.umeng.message.proguard.m;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: EventUtils */
public class c {
    private static a<String, String> a = new a<>();

    public static void a() {
        String valueOf = String.valueOf(0);
        a.put("authPageIn", valueOf);
        a.put("authPageOut", valueOf);
        a.put("authClickFailed", valueOf);
        a.put("authClickSuccess", valueOf);
        a.put("timeOnAuthPage", valueOf);
        a.put("authPrivacyState", valueOf);
    }

    public static void a(String str) {
        try {
            String str2 = a.get(str);
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = Integer.valueOf(str2).intValue();
            }
            a.put(str, String.valueOf(i + 1));
            a<String, String> aVar = a;
            aVar.put(str + m.n, w.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        a.put(str, str2);
    }

    public static void a(Context context, Bundle bundle) {
        try {
            if (!y.k()) {
                e eVar = new e();
                String valueOf = String.valueOf(0);
                String str = null;
                eVar.e(!((String) a.a("authPageIn", valueOf)).equals(valueOf) ? a.get("authPageIn") : null);
                eVar.f(!((String) a.a("authPageOut", valueOf)).equals(valueOf) ? a.get("authPageOut") : null);
                eVar.c(!((String) a.a("authClickSuccess", valueOf)).equals(valueOf) ? a.get("authClickSuccess") : null);
                eVar.b(!((String) a.a("authClickFailed", valueOf)).equals(valueOf) ? a.get("authClickFailed") : null);
                if (!((String) a.a("timeOnAuthPage", valueOf)).equals(valueOf)) {
                    str = a.get("timeOnAuthPage");
                }
                eVar.d(str);
                eVar.a((String) a.a("authPrivacyState", valueOf));
                JSONObject a2 = eVar.a();
                com.cmic.sso.wy.d.a aVar = new com.cmic.sso.wy.d.a();
                if (bundle != null) {
                    aVar.A(bundle.getString("appid", ""));
                }
                aVar.v(bundle.getString("traceId"));
                aVar.A(bundle.getString("appid"));
                aVar.o(k.c(context));
                aVar.p(k.d(context));
                aVar.B("quick_login_android_5.7.1");
                aVar.l("android");
                aVar.m(bundle.getString("timeOut"));
                String str2 = (String) a.a("authPageInTime", "");
                if (TextUtils.isEmpty(str2)) {
                    str2 = (String) a.a("SMSInTime", "");
                }
                aVar.w(str2);
                String str3 = (String) a.a("authPageOutTime", "");
                if (TextUtils.isEmpty(str3)) {
                    str3 = (String) a.a("SMSOutTime", "");
                }
                aVar.y(str3);
                aVar.z("eventTracking5");
                aVar.r(bundle.getString("operatorType", ""));
                if (bundle.getInt("startnetworkType", 0) == 0) {
                    aVar.C(u.a(context) + "");
                } else {
                    aVar.C(bundle.getInt("startnetworkType", 0) + "");
                }
                aVar.x(bundle.getString("networkClass"));
                aVar.i(u.a());
                aVar.s(u.b());
                aVar.t(u.c());
                aVar.q(bundle.getString("simCardNum"));
                String str4 = "1";
                aVar.a(bundle.getBoolean("hsaReadPhoneStatePermission", false) ? str4 : "0");
                aVar.a(a2);
                if (!m.a()) {
                    str4 = "0";
                }
                aVar.d(str4);
                aVar.c(bundle.getString("imsiState", "0"));
                aVar.k((System.currentTimeMillis() - bundle.getLong("methodTimes", 0)) + "");
                g.a("EventUtils", "\u57cb\u70b9\u65e5\u5fd7\u4e0a\u62a5" + aVar.a());
                new b().a(context, aVar.a(), bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: EventUtils */
    private static class a<K, V> extends HashMap<K, V> {
        private a() {
        }

        public V a(Object obj, V v) {
            return (!containsKey(obj) || get(obj) == null) ? v : get(obj);
        }
    }
}
