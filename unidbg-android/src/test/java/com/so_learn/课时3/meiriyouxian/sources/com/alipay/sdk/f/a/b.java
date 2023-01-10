package com.alipay.sdk.f.a;

import com.alipay.sdk.f.e;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends e {
    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public String b() {
        return "5.0.0";
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public JSONObject a() throws JSONException {
        return e.a("sdkConfig", "obtain");
    }
}
