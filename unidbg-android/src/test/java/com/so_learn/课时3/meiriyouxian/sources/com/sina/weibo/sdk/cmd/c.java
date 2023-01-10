package com.sina.weibo.sdk.cmd;

import org.json.JSONObject;

/* compiled from: BaseCmd */
/* access modifiers changed from: package-private */
public class c {
    private String a;
    private String b;
    private long c;

    public c() {
    }

    public c(JSONObject jSONObject) {
        a(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        this.a = jSONObject.optString("notification_text");
        this.b = jSONObject.optString("notification_title");
        this.c = jSONObject.optLong("notification_delay");
    }

    public String e() {
        return this.a;
    }

    public String f() {
        return this.b;
    }

    public long g() {
        return this.c;
    }
}
