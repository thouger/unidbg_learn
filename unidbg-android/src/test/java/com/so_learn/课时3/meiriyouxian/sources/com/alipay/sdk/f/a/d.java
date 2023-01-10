package com.alipay.sdk.f.a;

import android.content.Context;
import com.alipay.sdk.e.a;
import com.alipay.sdk.f.b;
import com.alipay.sdk.f.e;
import com.alipay.sdk.g.a;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class d extends e {
    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public String a(a aVar, String str, JSONObject jSONObject) {
        return str;
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public JSONObject a() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.f.e
    public Map<String, String> a(boolean z, String str) {
        return new HashMap();
    }

    @Override // com.alipay.sdk.f.e
    public b a(a aVar, Context context, String str) throws Throwable {
        com.alipay.sdk.util.e.b("mspl", "mdap post");
        byte[] a = com.alipay.sdk.c.b.a(str.getBytes(Charset.forName("UTF-8")));
        HashMap hashMap = new HashMap();
        hashMap.put("utdId", com.alipay.sdk.g.b.a().e());
        hashMap.put("logHeader", "RAW");
        hashMap.put("bizCode", "alipaysdk");
        hashMap.put("productId", "alipaysdk_android");
        hashMap.put("Content-Encoding", "Gzip");
        hashMap.put("productVersion", "15.7.9");
        a.b a2 = com.alipay.sdk.e.a.a(context, new a.C0065a("https://loggw-exsdk.alipay.com/loggw/logUpload.do", hashMap, a));
        com.alipay.sdk.util.e.b("mspl", "mdap got " + a2);
        if (a2 != null) {
            boolean a3 = a(a2);
            try {
                byte[] bArr = a2.c;
                if (a3) {
                    bArr = com.alipay.sdk.c.b.b(bArr);
                }
                return new b("", new String(bArr, Charset.forName("UTF-8")));
            } catch (Exception e) {
                com.alipay.sdk.util.e.a(e);
                return null;
            }
        } else {
            throw new RuntimeException("Response is null");
        }
    }
}
