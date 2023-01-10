package com.cmic.sso.wy.d;

import com.cmic.sso.wy.c.a.f;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogBean */
public class a extends f {
    public static ArrayList<Throwable> a = new ArrayList<>();
    private String A = null;
    private String B = null;
    private String C = null;
    private String D;
    private String E = null;
    private String F = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private JSONArray q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private String u = null;
    private String v = null;
    private String w = null;
    private String x = null;
    private String y = null;
    private JSONObject z = null;

    public void a(String str) {
        this.D = str;
    }

    public void b(String str) {
        this.E = str;
    }

    public void c(String str) {
        this.C = str;
    }

    public void d(String str) {
        this.B = str;
    }

    public void a(JSONObject jSONObject) {
        this.z = jSONObject;
    }

    public void e(String str) {
        this.y = str;
    }

    public void f(String str) {
        this.m = str;
    }

    public void g(String str) {
        this.n = str;
    }

    public void h(String str) {
        this.o = str;
    }

    public void i(String str) {
        this.u = str;
    }

    public void j(String str) {
        this.l = str;
    }

    public void k(String str) {
        this.j = str;
    }

    public void l(String str) {
        this.f = str;
    }

    public void m(String str) {
        this.g = str;
    }

    public void o(String str) {
        this.c = str;
    }

    public void p(String str) {
        this.d = str;
    }

    public void a(JSONArray jSONArray) {
        this.q = jSONArray;
    }

    public void q(String str) {
        this.x = str;
    }

    public void r(String str) {
        this.r = str;
    }

    public void s(String str) {
        this.v = str;
    }

    public void t(String str) {
        this.w = str;
    }

    public void u(String str) {
        this.p = str;
    }

    public void v(String str) {
        this.b = str;
    }

    public void w(String str) {
        this.h = str;
    }

    public void x(String str) {
        this.t = str;
    }

    public void y(String str) {
        this.i = str;
    }

    public void z(String str) {
        this.k = str;
    }

    public void A(String str) {
        this.A = str;
    }

    public void B(String str) {
        this.e = str;
    }

    public void C(String str) {
        this.s = str;
    }

    public void D(String str) {
        this.F = str;
    }

    @Override // com.cmic.sso.wy.c.a.f
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("traceId", this.b);
            jSONObject.put("requestTime", this.h);
            jSONObject.put("responseTime", this.i);
            jSONObject.put("requestType", this.k);
            jSONObject.put("loginType", this.p);
            jSONObject.put(Constants.KEY_SDK_VERSION, this.e);
            jSONObject.put("networkType", this.s);
            jSONObject.put("networkClass", this.t);
            jSONObject.put("reqDevice", this.v);
            jSONObject.put("reqSystem", this.w);
            jSONObject.put("operatorType", this.r);
            jSONObject.put("simCardNum", this.x);
            jSONObject.put("exceptionStackTrace", this.q);
            jSONObject.put("appName", this.c);
            jSONObject.put("appVersion", this.d);
            jSONObject.put("interfaceCode", this.m);
            jSONObject.put("interfaceType", this.l);
            jSONObject.put("interfaceElasped", this.n);
            jSONObject.put("event", this.z);
            jSONObject.put("appid", this.A);
            jSONObject.put("brand", this.u);
            jSONObject.put("resultCode", this.y);
            jSONObject.put("is_root", this.B);
            jSONObject.put("imsiState", this.C);
            jSONObject.put("elapsedTime", this.j);
            jSONObject.put("loginTime", this.o);
            jSONObject.put("clientType", this.f);
            jSONObject.put("timeOut", this.g);
            jSONObject.put("is_phoneStatePermission", this.D);
            jSONObject.put("AID", this.E);
            jSONObject.put("sysOperType", this.F);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
