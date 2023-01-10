package com.cmic.sso.wy.c.a;

import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.i;
import com.taobao.accs.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetPrePhoneScripParameter */
public class c extends f {
    private a a;
    private String b;
    private String c;

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public a b() {
        return this.a;
    }

    @Override // com.cmic.sso.wy.c.a.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("encrypted", this.c);
            jSONObject.put(Constants.KEY_SEND_REQDATA, com.cmic.sso.wy.utils.a.a(this.b, this.a.toString()));
            g.a("GETpre", this.a.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* compiled from: GetPrePhoneScripParameter */
    public static class a {
        private String A = "";
        private String B = "";
        private String C = "";
        private String D;
        private String a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private String i = "";
        private String j = "";
        private String k = "";
        private String l = "";
        private String m = "";
        private String n = "";
        private String o = "";
        private String p = "";
        private String q = "";
        private String r = "";
        private String s = "";
        private String t = "";
        private String u = "";
        private String v = "";
        private String w = "";
        private String x = "";
        private String y = "";
        private String z = "";

        private String x(String str) {
            return str == null ? "" : str;
        }

        public void a(String str) {
            this.B = str;
        }

        public void b(String str) {
            this.C = str;
        }

        public void c(String str) {
            this.x = x(str);
        }

        public void d(String str) {
            this.y = x(str);
        }

        public void e(String str) {
            this.a = x(str);
        }

        public void f(String str) {
            this.b = x(str);
        }

        public void g(String str) {
            this.c = x(str);
        }

        public void h(String str) {
            this.d = x(str);
        }

        public void i(String str) {
            this.e = x(str);
        }

        public void j(String str) {
            this.f = x(str);
        }

        public void k(String str) {
            this.g = x(str);
        }

        public void l(String str) {
            this.h = x(str);
        }

        public void m(String str) {
            this.i = x(str);
        }

        public void n(String str) {
            String x = x(str);
            try {
                this.j = URLEncoder.encode(x, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.j = x;
            }
        }

        public void o(String str) {
            String x = x(str);
            try {
                this.k = URLEncoder.encode(x, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.k = x;
            }
        }

        public void p(String str) {
            this.l = x(str);
        }

        public void q(String str) {
            this.m = x(str);
        }

        public void r(String str) {
            this.o = x(str);
        }

        public void s(String str) {
            this.p = x(str);
        }

        public void t(String str) {
            this.z = x(str);
        }

        public void u(String str) {
            this.A = x(str);
        }

        public void v(String str) {
            this.D = x(str);
        }

        public String w(String str) {
            return i.a(this.b + this.c + this.d + this.e + this.f + this.g + this.h + this.i + this.j + this.k + this.l + this.m + this.o + this.p + str + this.q + this.r + this.s + this.t + this.u + this.v + this.w + this.x + this.y + this.z + this.A + this.B + this.C);
        }

        public String toString() {
            return this.a + "&" + this.b + "&" + this.c + "&" + this.d + "&" + this.e + "&" + this.f + "&" + this.g + "&" + this.h + "&" + this.i + "&" + this.j + "&" + this.k + "&" + this.l + "&" + this.m + "&7.0&" + this.n + "&" + this.o + "&" + this.p + "&" + this.q + "&" + this.r + "&" + this.s + "&" + this.t + "&" + this.u + "&" + this.v + "&" + this.w + "&" + this.x + "&" + this.y + "&" + this.z + "&" + this.A + "&" + this.D + "&&" + this.B + "&" + this.C;
        }
    }
}
