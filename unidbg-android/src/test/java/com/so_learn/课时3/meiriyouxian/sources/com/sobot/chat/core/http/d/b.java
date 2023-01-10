package com.sobot.chat.core.http.d;

import com.sobot.chat.core.http.d.d;
import com.sobot.chat.core.http.h.a;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: OkHttpRequest */
public abstract class b {
    protected String a;
    protected Object b;
    protected Map<String, String> c;
    protected Map<String, String> d;
    protected Request.Builder e = new Request.Builder();
    protected transient d.b f;

    /* access modifiers changed from: protected */
    public abstract Request a(RequestBody requestBody);

    /* access modifiers changed from: protected */
    public abstract RequestBody a();

    /* access modifiers changed from: protected */
    public RequestBody a(RequestBody requestBody, com.sobot.chat.core.http.c.b bVar) {
        return requestBody;
    }

    protected b(String str, Object obj, Map<String, String> map, Map<String, String> map2) {
        this.a = str;
        this.b = obj;
        this.c = map;
        this.d = map2;
        if (str == null) {
            a.a("url can not be null.", new Object[0]);
        }
        e();
    }

    private void e() {
        this.e.url(this.a).tag(this.b);
        c();
    }

    public e b() {
        return new e(this);
    }

    public Request a(com.sobot.chat.core.http.c.b bVar) {
        return a(a(a(), bVar));
    }

    /* access modifiers changed from: protected */
    public void c() {
        Headers.Builder builder = new Headers.Builder();
        Map<String, String> map = this.d;
        if (!(map == null || map.isEmpty())) {
            for (String str : this.d.keySet()) {
                builder.add(str, this.d.get(str));
            }
            this.e.headers(builder.build());
        }
    }

    public b a(d.b bVar) {
        this.f = bVar;
        return this;
    }

    public String d() {
        return this.a;
    }

    public void a(String str, String str2) {
        Map<String, String> map = this.d;
        if (map == null || map.isEmpty()) {
            this.d = new HashMap();
        }
        this.d.put(str, str2);
        c();
    }
}
