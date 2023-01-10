package com.sina.weibo.sdk.cmd;

import org.json.JSONObject;

/* compiled from: AppInvokeCmd */
/* access modifiers changed from: package-private */
public class b extends c {
    private String a;
    private String b;
    private String c;

    public b() {
    }

    public b(JSONObject jSONObject) {
        super(jSONObject);
    }

    @Override // com.sina.weibo.sdk.cmd.c
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.a = jSONObject.optString("package");
        this.b = jSONObject.optString("scheme");
        this.c = jSONObject.optString("url");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
