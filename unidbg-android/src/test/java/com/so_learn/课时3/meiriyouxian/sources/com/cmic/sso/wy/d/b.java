package com.cmic.sso.wy.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.cmic.sso.wy.c.a.d;
import com.cmic.sso.wy.c.a.f;
import com.cmic.sso.wy.c.c.a;
import com.cmic.sso.wy.utils.aa;
import com.cmic.sso.wy.utils.e;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.k;
import com.cmic.sso.wy.utils.m;
import com.cmic.sso.wy.utils.p;
import com.cmic.sso.wy.utils.q;
import com.cmic.sso.wy.utils.u;
import com.cmic.sso.wy.utils.v;
import com.cmic.sso.wy.utils.w;
import com.cmic.sso.wy.utils.y;
import com.tencent.open.SocialConstants;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SendLog */
public class b {
    private Bundle a;
    private Context b;

    public void a(Context context, String str, Bundle bundle, Throwable th) {
        int i;
        this.b = context;
        try {
            a aVar = new a();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", str);
            JSONArray jSONArray = null;
            jSONObject.put("PGWResultCode", bundle.getString("PGWResultCode", null));
            JSONObject jSONObject2 = new JSONObject();
            aVar.C(u.a(this.b) + "");
            jSONObject2.put("timeOut", bundle.getString("timeOut"));
            jSONObject2.put("imsiState", bundle.getString("imsiState"));
            if (bundle.getBoolean("isCacheScrip", false)) {
                aVar.u("scrip");
            } else {
                aVar.u("pgw");
            }
            if ("loginAuth".equals(bundle.getString("loginMethod"))) {
                aVar.z("loginAuth");
            } else if ("mobileAuth".equals(bundle.getString("loginMethod"))) {
                aVar.z("mobileAuth");
            } else {
                aVar.z("preGetMobile");
            }
            aVar.v(bundle.getString("traceId"));
            aVar.A(bundle.getString("appid"));
            aVar.o(k.c(this.b));
            aVar.p(k.d(this.b));
            aVar.B("quick_login_android_5.7.1");
            aVar.l("android");
            aVar.m(bundle.getString("timeOut"));
            aVar.w(bundle.getString("starttime"));
            String str2 = "1";
            aVar.a(bundle.getBoolean("hsaReadPhoneStatePermission", false) ? str2 : "0");
            long currentTimeMillis = System.currentTimeMillis();
            aVar.y(w.a(currentTimeMillis));
            aVar.k((currentTimeMillis - bundle.getLong("starttimemills")) + "");
            aVar.j(bundle.getString("interfacetype", ""));
            bundle.putString("interfacetype", "");
            aVar.f(bundle.getString("interfacecode", ""));
            bundle.putString("interfacecode", "");
            aVar.g(bundle.getString("interfaceelasped", ""));
            bundle.putString("interfaceelasped", "");
            aVar.h(bundle.getLong("loginTime", 0) + "");
            aVar.r(bundle.getString("operatorType", ""));
            if (bundle.getInt("startnetworkType", 0) == 0) {
                aVar.C(u.a(this.b) + "");
                i = 0;
            } else {
                StringBuilder sb = new StringBuilder();
                i = 0;
                sb.append(bundle.getInt("startnetworkType", 0));
                sb.append("");
                aVar.C(sb.toString());
            }
            aVar.x(bundle.getString("networkClass"));
            aVar.i(u.a());
            aVar.s(u.b());
            aVar.t(u.c());
            aVar.q(bundle.getString("simCardNum"));
            aVar.e(str);
            if (!m.a()) {
                str2 = "0";
            }
            aVar.d(str2);
            aVar.c(bundle.getString("imsiState", "0"));
            aVar.k((System.currentTimeMillis() - bundle.getLong("methodTimes", 0)) + "");
            if (th != null) {
                jSONArray = new JSONArray();
                JSONObject jSONObject3 = new JSONObject();
                StringBuffer stringBuffer = new StringBuffer();
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                for (int i2 = i; i2 < length; i2++) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    stringBuffer.append("\n");
                    stringBuffer.append(stackTraceElement.toString());
                }
                jSONObject3.put("message", th.toString());
                jSONObject3.put("stack", stringBuffer.toString());
                jSONArray.put(jSONObject3);
            }
            if (a.a.size() > 0) {
                if (jSONArray == null) {
                    jSONArray = new JSONArray();
                }
                Iterator<Throwable> it2 = a.a.iterator();
                while (it2.hasNext()) {
                    Throwable next = it2.next();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    JSONObject jSONObject4 = new JSONObject();
                    StackTraceElement[] stackTrace2 = next.getStackTrace();
                    int length2 = stackTrace2.length;
                    for (int i3 = i; i3 < length2; i3++) {
                        StackTraceElement stackTraceElement2 = stackTrace2[i3];
                        stringBuffer2.append("\n");
                        stringBuffer2.append(stackTraceElement2.toString());
                    }
                    jSONObject4.put("message", next.toString());
                    jSONObject4.put("stack", stringBuffer2.toString());
                    jSONArray.put(jSONObject4);
                }
                a.a.clear();
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                aVar.a(jSONArray);
            }
            aVar.b(q.b("AID", ""));
            aVar.D(p.a(this.b).f());
            g.a("SendLog", "\u767b\u5f55\u65e5\u5fd7" + aVar.a());
            a(this.b, aVar.a(), bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, JSONObject jSONObject, Bundle bundle) {
        this.a = bundle;
        this.b = context;
        v.a(new AnonymousClass1(jSONObject));
    }

    /* compiled from: SendLog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.d.b$1  reason: invalid class name */
    public class AnonymousClass1 extends v.a {
        final /* synthetic */ JSONObject a;

        AnonymousClass1(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            b.this.a(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        a(jSONArray, new AnonymousClass2());
    }

    /* compiled from: SendLog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.d.b$2  reason: invalid class name */
    public class AnonymousClass2 implements com.cmic.sso.wy.c.b.b {
        @Override // com.cmic.sso.wy.c.b.b
        public void a(String str, String str2, JSONObject jSONObject) {
        }

        AnonymousClass2() {
        }
    }

    private void a(JSONArray jSONArray, com.cmic.sso.wy.c.b.b bVar) {
        d dVar = new d();
        d.a aVar = new d.a();
        d.b bVar2 = new d.b();
        bVar2.d(aa.a());
        bVar2.e(w.a());
        bVar2.b(this.a.getString("appid", ""));
        bVar2.a("2.0");
        bVar2.c(bVar2.f());
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        aVar.a(jSONArray);
        dVar.a(aVar);
        dVar.a(bVar2);
        String g = y.g();
        a.a(y.a(y.g()));
        a(g, (String) dVar, bVar);
    }

    private <T extends f> void a(String str, T t, com.cmic.sso.wy.c.b.b bVar) {
        if (y.m() == 0 || y.l() == 0 || System.currentTimeMillis() > q.b("logCloseTime", 0) + ((long) y.m())) {
            g.a("SendLog", "request https url : " + y.g() + ">>>>>>> PARAMS : " + t.a().toString());
            new e().a(str, t, false, new AnonymousClass3(str, bVar), "POST", "", this.a);
        }
    }

    /* compiled from: SendLog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.d.b$3  reason: invalid class name */
    public class AnonymousClass3 implements e.a {
        final /* synthetic */ String a;
        final /* synthetic */ com.cmic.sso.wy.c.b.b b;

        AnonymousClass3(String str, com.cmic.sso.wy.c.b.b bVar) {
            this.a = str;
            this.b = bVar;
        }

        @Override // com.cmic.sso.wy.utils.e.a
        public void a(String str, String str2) {
            g.a("SendLog", "request success , url : " + this.a + ">>>>result : " + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.b.a(jSONObject.optString("resultCode"), jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                a("200021", "\u6570\u636e\u89e3\u6790\u5f02\u5e38", str2);
            }
        }

        @Override // com.cmic.sso.wy.utils.e.a
        public void a(String str, String str2, String str3) {
            if (!(y.m() == 0 || y.l() == 0)) {
                int a = q.a("logFailTimes", 0) + 1;
                SharedPreferences.Editor a2 = q.a();
                if (a >= y.l()) {
                    a2.putInt("logFailTimes", 0);
                    a2.putLong("logCloseTime", System.currentTimeMillis());
                } else {
                    a2.putInt("logFailTimes", a);
                }
                a2.commit();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("resultCode", str);
                jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            g.a("SendLog", "request failed , url : " + this.a + ">>>>>errorMsg : " + jSONObject.toString());
            com.cmic.sso.wy.c.b.b bVar = this.b;
            if (bVar != null) {
                bVar.a(str, str2, jSONObject);
            }
        }
    }
}
