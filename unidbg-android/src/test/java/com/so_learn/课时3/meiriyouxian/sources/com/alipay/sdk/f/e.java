package com.alipay.sdk.f;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.provider.Telephony;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import com.alipay.sdk.e.a;
import com.alipay.sdk.g.a;
import com.alipay.sdk.g.b;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.k;
import com.alipay.sdk.util.l;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class e {
    protected boolean a = true;
    protected boolean b = true;

    /* access modifiers changed from: protected */
    public abstract JSONObject a() throws JSONException;

    /* access modifiers changed from: protected */
    public String b() {
        return "4.9.0";
    }

    /* access modifiers changed from: protected */
    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("Operation-Type", "alipay.msp.cashier.dispatch.bytes");
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("Version", "2.0");
        hashMap.put("AppId", "TAOBAO");
        hashMap.put("Msp-Param", a.a(str));
        hashMap.put("des-mode", KeyProperties.BLOCK_MODE_CBC);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public String c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(UsbManager.EXTRA_DEVICE, Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put("api_name", "com.alipay.mcpay");
        hashMap.put("api_version", b());
        return a(hashMap, new HashMap<>());
    }

    protected static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public String a(a aVar, String str, JSONObject jSONObject) {
        b a = b.a();
        com.alipay.sdk.h.a a2 = com.alipay.sdk.h.a.a(a.b());
        JSONObject a3 = c.a(new JSONObject(), jSONObject);
        try {
            a3.put("external_info", str);
            a3.put("tid", a2.a());
            a3.put("user_agent", a.c().a(aVar, a2));
            a3.put("has_alipay", l.b(aVar, a.b(), com.alipay.sdk.app.a.a));
            a3.put("has_msp_app", l.a(a.b()));
            a3.put("app_key", "2014052600006128");
            a3.put("utdid", a.e());
            a3.put("new_client_key", a2.b());
            a3.put("pa", com.alipay.sdk.b.b.a(a.b()));
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "BodyErr", th);
            com.alipay.sdk.util.e.a(th);
        }
        return a3.toString();
    }

    protected static boolean a(a.b bVar) {
        return Boolean.valueOf(a(bVar, "msp-gzip")).booleanValue();
    }

    protected static String a(a.b bVar, String str) {
        List<String> list;
        if (bVar == null || str == null || bVar.a == null || (list = bVar.a.get(str)) == null) {
            return null;
        }
        return TextUtils.join(Constants.ACCEPT_TIME_SEPARATOR_SP, list);
    }

    /* access modifiers changed from: protected */
    public String a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (hashMap2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put(Constant.KEY_PARAMS, jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has(Constant.KEY_PARAMS)) {
                return false;
            }
            String optString = jSONObject.getJSONObject(Constant.KEY_PARAMS).optString(Telephony.CarrierColumns.PUBLIC_KEY, null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            com.alipay.sdk.b.b.a(optString);
            return true;
        } catch (JSONException e) {
            com.alipay.sdk.util.e.a(e);
            return false;
        }
    }

    public b a(com.alipay.sdk.g.a aVar, Context context) throws Throwable {
        return a(aVar, context, "");
    }

    public b a(com.alipay.sdk.g.a aVar, Context context, String str) throws Throwable {
        return a(aVar, context, str, k.a(context));
    }

    public b a(com.alipay.sdk.g.a aVar, Context context, String str, String str2) throws Throwable {
        return a(aVar, context, str, str2, true);
    }

    /* access modifiers changed from: protected */
    public b a(com.alipay.sdk.g.a aVar, Context context, String str, String str2, boolean z) throws Throwable {
        com.alipay.sdk.util.e.a("mspl", "Packet: " + str2);
        c cVar = new c(this.b);
        b bVar = new b(c(), a(aVar, str, a()));
        Map<String, String> a = a(false, str);
        d a2 = cVar.a(bVar, this.a, a.get("iSr"));
        a.b a3 = com.alipay.sdk.e.a.a(context, new a.C0065a(str2, a(a2.a(), str), a2.b()));
        if (a3 != null) {
            b a4 = cVar.a(new d(a(a3), a3.c), a.get("iSr"));
            if (a4 == null || !a(a4.a()) || !z) {
                return a4;
            }
            return a(aVar, context, str, str2, false);
        }
        throw new RuntimeException("Response is null.");
    }
}
