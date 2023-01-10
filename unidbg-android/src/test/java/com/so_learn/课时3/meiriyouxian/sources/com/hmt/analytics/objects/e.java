package com.hmt.analytics.objects;

import android.content.Context;
import android.text.TextUtils;
import com.hmt.analytics.android.a;
import com.hmt.analytics.android.k;
import com.hmt.analytics.util.b;
import com.hmt.analytics.util.h;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.pro.ai;
import com.unionpay.tsmservice.mi.data.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ParamCollector */
public class e {
    private static final String a = e.class.getSimpleName();

    public static JSONObject a(Context context, String str) throws JSONException {
        String str2;
        if (!a.F(context)) {
            context = context.getApplicationContext();
        }
        JSONObject jSONObject = new JSONObject();
        if (!h.k().j()) {
            h.u(context);
        }
        jSONObject.put("os", h.k().h());
        jSONObject.put("_ua", h.k().d(context));
        jSONObject.put("type", str);
        jSONObject.put("device_id", h.k().l(context));
        jSONObject.put("channel_id", h.k().k(context));
        jSONObject.put("ts", a.a());
        jSONObject.put("v", a.e());
        jSONObject.put("muid", a.a(context));
        jSONObject.put("sr", h.k().t(context));
        jSONObject.put("sv", a.f());
        jSONObject.put("sd", a.g());
        jSONObject.put("char", a.h());
        String[] j = a.j(context);
        if (!a.a(j, Constants.KEY_MODEL).booleanValue()) {
            jSONObject.put(Constants.KEY_MODEL, h.k().g());
        }
        if (!a.a(j, "network").booleanValue()) {
            jSONObject.put("network", a.n(context));
        }
        if (!a.a(j, "_openudid").booleanValue()) {
            jSONObject.put("_openudid", h.k().c(context));
        }
        if (!a.a(j, "_imei").booleanValue()) {
            jSONObject.put("_imei", a.v(context));
        }
        String str3 = "";
        if (!a.a(j, "_imei1").booleanValue()) {
            String a2 = b.a("mobileanalytics", a.b(context, 0));
            if (TextUtils.isEmpty(a2)) {
                str2 = str3;
            } else {
                str2 = a2.toLowerCase();
            }
            jSONObject.put("_imei1", str2);
        }
        if (!a.a(j, "_imei2").booleanValue()) {
            String a3 = b.a("mobileanalytics", a.b(context, 1));
            if (!TextUtils.isEmpty(a3)) {
                str3 = a3.toLowerCase();
            }
            jSONObject.put("_imei2", str3);
        }
        if (!a.a(j, "_androidid").booleanValue()) {
            jSONObject.put("_androidid", h.k().a(context));
        }
        if (!a.a(j, "_mac").booleanValue()) {
            jSONObject.put("_mac", a.x(context));
        }
        if (!a.a(j, Constants.KEY_IMEI).booleanValue()) {
            jSONObject.put(Constants.KEY_IMEI, h.k().o(context));
        }
        if (!a.a(j, "androidid").booleanValue()) {
            jSONObject.put("androidid", h.k().g(context));
        }
        if (!a.a(j, "androidid1").booleanValue()) {
            jSONObject.put("androidid1", h.k().f(context));
        }
        if (!a.a(j, "aaid").booleanValue()) {
            jSONObject.put("aaid", h.k().e(context));
        }
        if (!a.a(j, Constant.KEY_MAC).booleanValue()) {
            jSONObject.put(Constant.KEY_MAC, a.D(context));
        }
        if (!a.a(j, "mac1").booleanValue()) {
            jSONObject.put("mac1", a.E(context));
        }
        if (!a.a(j, ai.y).booleanValue()) {
            jSONObject.put(ai.y, h.k().r(context));
        }
        if (!a.a(j, "app_name").booleanValue()) {
            jSONObject.put("app_name", h.k().i(context));
        }
        if (!a.a(j, "app_version").booleanValue()) {
            jSONObject.put("app_version", h.k().j(context));
        }
        if (!a.a(j, "app_code").booleanValue()) {
            jSONObject.put("app_code", h.k().h(context));
        }
        if (!a.a(j, "device_name").booleanValue()) {
            jSONObject.put("device_name", h.k().a());
        }
        if (!a.a(j, "lang").booleanValue()) {
            jSONObject.put("lang", h.k().e());
        }
        a(jSONObject);
        return jSONObject;
    }

    private static void a(JSONObject jSONObject) throws JSONException {
        if (!TextUtils.isEmpty(k.a)) {
            jSONObject.put("cust_id", k.a);
        }
        if (k.b == null || k.b.isEmpty()) {
            a.a(a, "NO USER PARAMETER IS BEEN SETTLED!");
            return;
        }
        for (String str : k.b.keySet()) {
            String str2 = k.b.get(str);
            if (!jSONObject.has(str)) {
                jSONObject.put(str, str2);
                a.a(a, "USER PARAMETER ADDED INTO INFO");
            } else {
                a.a(a, "USER PARAMETER CONFLICT WITH SYSTEM PARAMETER");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject a(android.content.Context r15) {
        /*
        // Method dump skipped, instructions count: 542
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hmt.analytics.objects.e.a(android.content.Context):org.json.JSONObject");
    }

    public static JSONObject a(f fVar, Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = a(context, SocialConstants.PARAM_ACT);
            jSONObject.put("act_name", fVar.c());
            jSONObject.put("act_count", fVar.d());
            jSONObject.put("activity", fVar.b());
            return jSONObject;
        } catch (JSONException e) {
            String str = a;
            a.a(str, "Collected:" + e.getMessage());
            return jSONObject;
        }
    }
}
