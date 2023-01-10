package com.cmic.sso.wy.c.a;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetConfigParameter */
public class b extends f {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    @Override // com.cmic.sso.wy.c.a.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.a);
            jSONObject.put("apptype", this.b);
            jSONObject.put("phone_ID", this.c);
            jSONObject.put("certflag", this.d);
            jSONObject.put("sdkversion", this.e);
            jSONObject.put("appid", this.f);
            jSONObject.put("expandparams", this.g);
            jSONObject.put("sign", this.h);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.f = str;
    }

    public void g(String str) {
        this.h = str;
    }

    public String b() {
        return n(this.a + this.e + this.f + "iYm0HAnkxQtpvN44").toLowerCase();
    }
}
