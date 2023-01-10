package com.alipay.sdk.f.a;

import android.content.Context;
import android.security.keystore.KeyProperties;
import com.alipay.sdk.f.b;
import com.alipay.sdk.f.e;
import com.alipay.sdk.g.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends e {
    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public String a(a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public JSONObject a() throws JSONException {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("des-mode", KeyProperties.BLOCK_MODE_CBC);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public String c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("api_name", "/sdk/log");
        hashMap.put("api_version", "1.0.0");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("log_v", "1.0");
        return a(hashMap, hashMap2);
    }

    @Override // com.alipay.sdk.f.e
    public b a(a aVar, Context context, String str) throws Throwable {
        return a(aVar, context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
