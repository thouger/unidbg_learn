package com.sobot.chat.core.http.d;

import java.util.Map;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: GetRequest */
public class a extends b {
    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.core.http.d.b
    public RequestBody a() {
        return null;
    }

    public a(String str, Object obj, Map<String, String> map, Map<String, String> map2) {
        super(str, obj, map, map2);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.core.http.d.b
    public Request a(RequestBody requestBody) {
        return this.e.get().build();
    }
}
