package com.sina.weibo.sdk.cmd;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* compiled from: AppInstallCmd */
/* access modifiers changed from: package-private */
public class a extends c {
    private long a;
    private List<String> b;
    private String c;
    private String d;

    public a() {
    }

    public a(JSONObject jSONObject) {
        super(jSONObject);
    }

    @Override // com.sina.weibo.sdk.cmd.c
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.d = jSONObject.optString("download_url");
        String optString = jSONObject.optString("app_package");
        if (!TextUtils.isEmpty(optString)) {
            this.b = Arrays.asList(optString.split("\\|"));
        }
        this.c = jSONObject.optString("app_sign");
        this.a = jSONObject.optLong("app_version");
    }

    public long a() {
        return this.a;
    }

    public List<String> b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}
