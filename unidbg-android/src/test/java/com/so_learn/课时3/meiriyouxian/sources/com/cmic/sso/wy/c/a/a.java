package com.cmic.sso.wy.c.a;

import com.umeng.analytics.pro.ai;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetAuthTokenParameter */
public class a extends f {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private JSONObject l;
    private String m;

    public void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public void a(String str) {
        this.h = str;
    }

    public void b(String str) {
        this.i = str;
    }

    public void c(String str) {
        this.m = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public void e(String str) {
        this.g = str;
    }

    public void f(String str) {
        this.a = str;
    }

    public void g(String str) {
        this.b = str;
    }

    public void h(String str) {
        this.c = str;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(String str) {
        this.e = str;
    }

    public String toString() {
        return a().toString();
    }

    public void k(String str) {
        this.j = str;
    }

    public void l(String str) {
        this.k = str;
    }

    @Override // com.cmic.sso.wy.c.a.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.a);
            jSONObject.put("msgid", this.b);
            jSONObject.put("appid", this.c);
            jSONObject.put("scrip", this.d);
            jSONObject.put("sign", this.e);
            jSONObject.put("interfacever", this.f);
            jSONObject.put("userCapaid", this.g);
            jSONObject.put("clienttype", this.h);
            jSONObject.put("sourceid", this.i);
            jSONObject.put("authenticated_appid", this.j);
            jSONObject.put("genTokenByAppid", this.k);
            jSONObject.put("rcData", this.l);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String m(String str) {
        return n(this.a + this.c + str + this.d);
    }

    /* compiled from: GetAuthTokenParameter */
    /* renamed from: com.cmic.sso.wy.c.a.a$a  reason: collision with other inner class name */
    public static class C0075a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;

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
            this.g = str;
        }

        public void h(String str) {
            this.h = str;
        }

        public void i(String str) {
            this.i = str;
        }

        public void j(String str) {
            this.j = str;
        }

        public void k(String str) {
            this.k = str;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("phone_id", this.a);
                jSONObject.put("os", this.b);
                jSONObject.put("dev_model", this.c);
                jSONObject.put("dev_brand", this.d);
                jSONObject.put("mnc", this.e);
                jSONObject.put("client_type", this.f);
                jSONObject.put(ai.T, this.g);
                jSONObject.put("ipv4_list", this.h);
                jSONObject.put("ipv6_list", this.i);
                jSONObject.put("is_cert", this.j);
                jSONObject.put("is_root", this.k);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }
}
