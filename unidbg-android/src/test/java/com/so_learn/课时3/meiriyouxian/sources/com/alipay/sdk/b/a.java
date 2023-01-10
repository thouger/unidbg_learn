package com.alipay.sdk.b;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.g.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.android.internal.location.GpsNetInitiatedHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static a q;
    public boolean a = false;
    private int b = 10000;
    private boolean c = false;
    private String d = "https://h5.m.taobao.com/mlapp/olist.html";
    private int e = 10;
    private boolean f = true;
    private boolean g = true;
    private boolean h = false;
    private boolean i = true;
    private boolean j = true;
    private String k = "";
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private boolean o = true;
    private List<C0063a> p = null;

    public int a() {
        int i = this.b;
        if (i < 1000 || i > 20000) {
            e.a("DynCon", "time(def) = 10000");
            return 10000;
        }
        e.a("DynCon", "time = " + this.b);
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        return this.g;
    }

    public String e() {
        return this.d;
    }

    public int f() {
        return this.e;
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.j;
    }

    public String j() {
        return this.k;
    }

    public boolean k() {
        return this.l;
    }

    public boolean l() {
        return this.m;
    }

    public boolean m() {
        return this.n;
    }

    public boolean n() {
        return this.o;
    }

    public List<C0063a> o() {
        return this.p;
    }

    public static a p() {
        if (q == null) {
            q = new a();
            q.q();
        }
        return q;
    }

    private void q() {
        a(h.b(com.alipay.sdk.g.a.a(), b.a().b(), "alipay_cashier_dynamic_config", null));
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                a(new JSONObject(str));
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(com.alipay.sdk.g.a aVar) {
        try {
            h.a(aVar, b.a().b(), "alipay_cashier_dynamic_config", r().toString());
        } catch (Exception e) {
            e.a(e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("st_sdk_config");
                if (optJSONObject != null) {
                    a(optJSONObject);
                } else {
                    e.c("DynCon", "empty config");
                }
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    private JSONObject r() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(GpsNetInitiatedHandler.NI_INTENT_KEY_TIMEOUT, a());
        jSONObject.put("h5_port_degrade", b());
        jSONObject.put("tbreturl", e());
        jSONObject.put("configQueryInterval", f());
        jSONObject.put("launchAppSwitch", C0063a.a(o()));
        jSONObject.put("scheme_pay_2", c());
        jSONObject.put("intercept_batch", d());
        jSONObject.put("deg_log_mcgw", g());
        jSONObject.put("deg_start_srv_first", h());
        jSONObject.put("prev_jump_dual", i());
        jSONObject.put("use_sc_only", j());
        jSONObject.put("bind_use_imp", k());
        jSONObject.put("retry_bnd_once", l());
        jSONObject.put("skip_trans", m());
        jSONObject.put("up_before_pay", n());
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        this.b = jSONObject.optInt(GpsNetInitiatedHandler.NI_INTENT_KEY_TIMEOUT, 10000);
        this.c = jSONObject.optBoolean("h5_port_degrade", false);
        this.d = jSONObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
        this.e = jSONObject.optInt("configQueryInterval", 10);
        this.p = C0063a.a(jSONObject.optJSONArray("launchAppSwitch"));
        this.f = jSONObject.optBoolean("scheme_pay_2", true);
        this.g = jSONObject.optBoolean("intercept_batch", true);
        this.h = jSONObject.optBoolean("deg_log_mcgw", false);
        this.i = jSONObject.optBoolean("deg_start_srv_first", true);
        this.j = jSONObject.optBoolean("prev_jump_dual", true);
        this.k = jSONObject.optString("use_sc_only", "");
        this.l = jSONObject.optBoolean("bind_use_imp", false);
        this.m = jSONObject.optBoolean("retry_bnd_once", false);
        this.n = jSONObject.optBoolean("skip_trans", false);
        this.o = jSONObject.optBoolean("up_before_pay", true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.b.a$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ com.alipay.sdk.g.a a;
        final /* synthetic */ Context b;

        AnonymousClass1(com.alipay.sdk.g.a aVar, Context context) {
            this.a = aVar;
            this.b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.alipay.sdk.f.b a = new com.alipay.sdk.f.a.b().a(this.a, this.b);
                if (a != null) {
                    a.this.b(a.b());
                    a.this.a(com.alipay.sdk.g.a.a());
                }
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    public void a(com.alipay.sdk.g.a aVar, Context context) {
        new Thread(new AnonymousClass1(aVar, context)).start();
    }

    /* renamed from: com.alipay.sdk.b.a$a  reason: collision with other inner class name */
    public static final class C0063a {
        public final String a;
        public final int b;
        public final String c;

        public C0063a(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }

        public static C0063a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0063a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        public static List<C0063a> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0063a a = a(jSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        }

        public static JSONObject a(C0063a aVar) {
            if (aVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", aVar.a).put("v", aVar.b).put("pk", aVar.c);
            } catch (JSONException e) {
                e.a(e);
                return null;
            }
        }

        public static JSONArray a(List<C0063a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C0063a aVar : list) {
                jSONArray.put(a(aVar));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(a(this));
        }
    }
}
