package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.e;
import com.alipay.apmobilesecuritysdk.e.f;
import com.alipay.b.a.a.a.a;
import com.alipay.b.a.a.b.b;
import com.taobao.accs.common.Constants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class c {
    public static Map<String, String> a(Context context) {
        b a = b.a();
        HashMap hashMap = new HashMap();
        f a2 = e.a(context);
        String a3 = b.a(context);
        String b = b.b(context);
        String k = b.k(context);
        String m = b.m(context);
        if (a2 != null) {
            if (a.a(a3)) {
                a3 = a2.a();
            }
            if (a.a(b)) {
                b = a2.b();
            }
            if (a.a(k)) {
                k = a2.c();
            }
            if (a.a(m)) {
                m = a2.e();
            }
        }
        f fVar = new f(a3, b, k, "", m);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.KEY_IMEI, fVar.a());
                jSONObject.put(Constants.KEY_IMSI, fVar.b());
                jSONObject.put(Constant.KEY_MAC, fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                String jSONObject2 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", jSONObject2);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
        hashMap.put("AD1", a3);
        hashMap.put("AD2", b);
        hashMap.put("AD3", b.f(context));
        hashMap.put("AD5", b.h(context));
        hashMap.put("AD6", b.i(context));
        hashMap.put("AD7", b.j(context));
        hashMap.put("AD8", k);
        hashMap.put("AD9", b.l(context));
        hashMap.put("AD10", m);
        hashMap.put("AD11", b.d());
        hashMap.put("AD12", a.e());
        hashMap.put("AD13", b.f());
        hashMap.put("AD14", b.h());
        hashMap.put("AD15", b.i());
        hashMap.put("AD16", b.j());
        hashMap.put("AD17", "");
        hashMap.put("AD19", b.n(context));
        hashMap.put("AD20", b.k());
        hashMap.put("AD22", "");
        hashMap.put("AD23", b.p(context));
        hashMap.put("AD24", a.g(b.g(context)));
        hashMap.put("AD26", b.e(context));
        hashMap.put("AD27", b.p());
        hashMap.put("AD28", b.r());
        hashMap.put("AD29", b.t());
        hashMap.put("AD30", b.q());
        hashMap.put("AD31", b.s());
        hashMap.put("AD32", b.n());
        hashMap.put("AD33", b.o());
        hashMap.put("AD34", b.r(context));
        hashMap.put("AD35", b.s(context));
        hashMap.put("AD36", b.q(context));
        hashMap.put("AD37", b.m());
        hashMap.put("AD38", b.l());
        hashMap.put("AD39", b.c(context));
        hashMap.put("AD40", b.d(context));
        hashMap.put("AD41", b.b());
        hashMap.put("AD42", b.c());
        hashMap.put("AL3", b.o(context));
        return hashMap;
    }
}
