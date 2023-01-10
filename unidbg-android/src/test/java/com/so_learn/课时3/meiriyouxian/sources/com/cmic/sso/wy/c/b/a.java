package com.cmic.sso.wy.c.b;

import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony;
import com.cmic.sso.wy.b;
import com.cmic.sso.wy.c.a.a;
import com.cmic.sso.wy.c.a.b;
import com.cmic.sso.wy.c.a.c;
import com.cmic.sso.wy.c.a.f;
import com.cmic.sso.wy.utils.aa;
import com.cmic.sso.wy.utils.e;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.j;
import com.cmic.sso.wy.utils.m;
import com.cmic.sso.wy.utils.n;
import com.cmic.sso.wy.utils.p;
import com.cmic.sso.wy.utils.q;
import com.cmic.sso.wy.utils.u;
import com.cmic.sso.wy.utils.w;
import com.cmic.sso.wy.utils.y;
import com.cmic.sso.wy.utils.z;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseRequest */
public class a {
    private static a b;
    private Context a;

    protected a() {
    }

    protected a(Context context) {
        this.a = context;
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    public void a(boolean z, Bundle bundle, b bVar) {
        b bVar2 = new b();
        bVar2.a("1.0");
        bVar2.b("Android");
        bVar2.c(q.b("AID", ""));
        bVar2.d(z ? "1" : "0");
        bVar2.e("quick_login_android_5.7.1");
        bVar2.f(bundle.getString("appid"));
        bVar2.g(bVar2.b());
        a("https://config.cmpassport.com/client/uniConfig", bVar2, false, bundle, bVar);
    }

    public void a(Context context, Bundle bundle, b bVar) {
        int i = bundle.getInt("networkType");
        String string = bundle.getString(Telephony.Carriers.AUTH_TYPE, "");
        c cVar = new c();
        c.a aVar = new c.a();
        aVar.e("1.0");
        aVar.f("quick_login_android_5.7.1");
        aVar.g(bundle.getString("appid"));
        aVar.h(string);
        aVar.i(bundle.getString("smskey", ""));
        aVar.j(bundle.getString(Constants.KEY_IMSI, ""));
        aVar.k(p.a(context).c());
        aVar.d(p.a(context).d());
        aVar.c(p.a(this.a).b());
        aVar.l(bundle.getString("operatorType"));
        aVar.m(i + "");
        aVar.n(u.a());
        aVar.o(u.b());
        aVar.p(u.c());
        aVar.q("0");
        aVar.r(aa.a());
        aVar.s(w.a());
        aVar.t(bundle.getString("apppackage"));
        aVar.u(bundle.getString("appsign"));
        aVar.t(bundle.getString("apppackage"));
        aVar.u(bundle.getString("appsign"));
        aVar.v(aVar.w(bundle.getString("appkey")));
        cVar.a(bundle.getString(b.a.a));
        cVar.b(n.a().a(bundle.getString(b.a.a)));
        cVar.a(aVar);
        bundle.putString("interfacetype", bundle.getString("interfacetype", "") + "getPrePhonescrip;");
        bundle.putString("interfaceVersion", "7.0");
        bundle.putBoolean("isCloseIpv4", y.b());
        bundle.putBoolean("isCloseIpv6", y.c());
        String str = y.e() + "rs/getPrePhonescrip";
        if (i != 3 || !string.equals("3")) {
            g.b("BaseRequest", "\u4e0d\u4f7f\u7528wifi\u4e0b\u53d6\u53f7" + i);
            a(str, cVar, false, bundle, bVar);
            return;
        }
        com.cmic.sso.wy.utils.b.a(context);
        g.b("BaseRequest", "\u4f7f\u7528wifi\u4e0b\u53d6\u53f7" + i);
        a(str, cVar, true, bundle, bVar);
    }

    public void a(Bundle bundle, b bVar) {
        String str;
        com.cmic.sso.wy.c.a.a aVar = new com.cmic.sso.wy.c.a.a();
        a.C0075a aVar2 = new a.C0075a();
        aVar.f("0.1");
        aVar.i(bundle.getString("phonescrip"));
        aVar.h(bundle.getString("appid"));
        aVar.g(aa.a());
        aVar.c(w.a());
        if ("2".equals(bundle.getString(Telephony.Carriers.AUTH_TYPE))) {
            aVar.d("2.0");
        } else {
            aVar.d("6.0");
        }
        aVar.e(bundle.getString("userCapaid", "50"));
        aVar.a("0");
        aVar.b(bundle.getString("sourceid"));
        aVar.k(bundle.getString("authenticated_appid"));
        aVar.l(bundle.getString("genTokenByAppid"));
        aVar.j(aVar.m(bundle.getString("appkey")));
        aVar2.a(q.b("AID", ""));
        aVar2.b(u.c());
        aVar2.c(u.b());
        aVar2.d(u.a());
        aVar2.e(bundle.getString("operatorType", ""));
        aVar2.f("0");
        aVar2.g(u.a(this.a) + "");
        aVar2.h(z.a(true));
        aVar2.i(z.a(false, false));
        if (y.h()) {
            str = "0";
        } else {
            str = "1";
        }
        aVar2.j(str);
        if (m.a()) {
            aVar2.k("1");
        } else {
            aVar2.k("0");
        }
        aVar.a(aVar2.a());
        com.cmic.sso.wy.c.c.a.a(y.a(y.f()));
        bundle.putString("interfacetype", bundle.getString("interfacetype", "") + "getAuthToken;");
        bundle.putString("interfaceVersion", "6.0");
        a(y.f() + "api/getAuthToken", aVar, false, bundle, bVar);
    }

    public <T extends f> void a(String str, T t, boolean z, Bundle bundle, b bVar) {
        String string = bundle.getString("traceId");
        g.a("BaseRequest", "request https url : " + str + ">>>>>>> PARAMS : " + t.a().toString());
        if (u.a(this.a) == 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("resultCode", "200022");
                jSONObject.put(SocialConstants.PARAM_APP_DESC, "\u7f51\u7edc\u672a\u8fde\u63a5");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            g.a("BaseRequest", "request failed , url : " + str + ">>>>>errorMsg : " + jSONObject.toString());
            if (bVar != null) {
                bVar.a("200022", "\u7f51\u7edc\u672a\u8fde\u63a5", jSONObject);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("timeOut", bundle.getString("timeOut"));
            jSONObject2.put("imsiState", bundle.getString("imsiState"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        new e().a(str, t, z, new AnonymousClass1(str, bundle, bVar), "POST", string, bundle);
    }

    /* compiled from: BaseRequest */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.c.b.a$1  reason: invalid class name */
    public class AnonymousClass1 implements e.a {
        final /* synthetic */ String a;
        final /* synthetic */ Bundle b;
        final /* synthetic */ b c;
        private boolean e = false;

        AnonymousClass1(String str, Bundle bundle, b bVar) {
            this.a = str;
            this.b = bundle;
            this.c = bVar;
        }

        @Override // com.cmic.sso.wy.utils.e.a
        public void a(String str, String str2) {
            String str3;
            if (!this.e) {
                this.e = true;
                g.a("BaseRequest", "request success , url : " + this.a + ">>>>result : " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("resultCode")) {
                        str3 = jSONObject.getString("resultCode");
                    } else {
                        str3 = jSONObject.getString("resultcode");
                    }
                    if (!j.a(this.b.getString("traceId")) || this.a.contains("Config")) {
                        this.c.a(str3, jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    a("200021", "\u6570\u636e\u89e3\u6790\u5f02\u5e38", str2);
                }
            }
        }

        @Override // com.cmic.sso.wy.utils.e.a
        public void a(String str, String str2, String str3) {
            if (!this.e) {
                this.e = true;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("resultCode", str);
                    jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                g.a("BaseRequest", "request failed , url : " + this.a + ">>>>>errorMsg : " + jSONObject.toString());
                if (this.c == null) {
                    return;
                }
                if (!j.a(this.b.getString("traceId")) || this.a.contains("Config")) {
                    this.c.a(str, str2, jSONObject);
                }
            }
        }
    }
}
