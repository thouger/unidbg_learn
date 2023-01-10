package com.cmic.sso.wy.b;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Telephony;
import android.text.TextUtils;
import com.cmic.sso.wy.b;
import com.cmic.sso.wy.c.b.a;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.i;
import com.cmic.sso.wy.utils.m;
import com.cmic.sso.wy.utils.q;
import com.cmic.sso.wy.utils.r;
import com.cmic.sso.wy.utils.u;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.pro.ai;
import com.umeng.message.common.inter.ITagManager;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthBusiness */
public class e {
    private static e c;
    private a a = a.a(this.b);
    private Context b;

    private e(Context context) {
        this.b = context.getApplicationContext();
    }

    public static e a(Context context) {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e(context);
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Bundle bundle, f fVar) {
        int i = bundle.getInt("logintype", 0);
        if (!bundle.getBoolean("isCacheScrip", false)) {
            a(bundle, str, fVar);
        } else if (i == 3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("resultCode", "103000");
                jSONObject.put(SocialConstants.PARAM_APP_DESC, ITagManager.STATUS_TRUE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            fVar.a("103000", ITagManager.STATUS_TRUE, bundle, jSONObject);
        } else {
            String a = m.a(this.b);
            if (TextUtils.isEmpty(a)) {
                bundle.putBoolean("isCacheScrip", false);
                if (bundle.getInt("networkType") == 2) {
                    fVar.a("200027", "\u65e0\u6570\u636e\u7f51\u7edc", bundle, null);
                } else {
                    a(bundle, str, fVar);
                }
            } else {
                bundle.putString("sourceid", q.b("sourceid", ""));
                bundle.putString("phonescrip", a);
                if (1 == i) {
                    bundle.putString("securityphone", q.b("securityphone", ""));
                    fVar.a("103000", "\u663e\u793a\u767b\u5f55\u53d6\u53f7\u6210\u529f", bundle, null);
                    return;
                }
                a(bundle, fVar);
            }
        }
    }

    public void a(Bundle bundle, String str, f fVar) {
        g.c("AuthBusiness", "\u8fdb\u884c\u53d6\u53f7\u67e5\u8be2\u300b\u300b\u300b\u300bauthtype=" + str);
        String packageName = this.b.getPackageName();
        String a = i.a(r.a(this.b));
        bundle.putString("apppackage", packageName);
        bundle.putString("appsign", a);
        bundle.putString(b.a.a, UUID.randomUUID().toString().substring(0, 16));
        bundle.putInt("networkType", u.a(this.b));
        bundle.putString(Telephony.Carriers.AUTH_TYPE, "3");
        b(bundle, fVar);
    }

    /* compiled from: AuthBusiness */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.e$1  reason: invalid class name */
    public class AnonymousClass1 implements com.cmic.sso.wy.c.b.b {
        final /* synthetic */ Bundle a;
        final /* synthetic */ long b;
        final /* synthetic */ f c;

        AnonymousClass1(Bundle bundle, long j, f fVar) {
            this.a = bundle;
            this.b = j;
            this.c = fVar;
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x011e  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x0154  */
        @Override // com.cmic.sso.wy.c.b.b
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.String r17, java.lang.String r18, org.json.JSONObject r19) {
            /*
            // Method dump skipped, instructions count: 381
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.b.e.AnonymousClass1.a(java.lang.String, java.lang.String, org.json.JSONObject):void");
        }
    }

    private void b(Bundle bundle, f fVar) {
        this.a.a(this.b, bundle, new AnonymousClass1(bundle, SystemClock.elapsedRealtime(), fVar));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(JSONObject jSONObject, String str, Bundle bundle) {
        String str2;
        if (bundle.getBoolean("keyIsSimKeyICCID", false)) {
            str2 = bundle.getString(ai.aa, "");
        } else {
            str2 = bundle.getString(Constants.KEY_IMSI, "");
        }
        m.a(this.b, str, jSONObject.optLong("phonescripED"), str2);
    }

    public void a(Bundle bundle, f fVar) {
        q.a("tokentimes", System.currentTimeMillis());
        long elapsedRealtime = SystemClock.elapsedRealtime();
        g.c("AuthBusiness", "\u83b7\u53d6\u5e73\u53f0token\u300b\u300b\u300b\u300b");
        m.a(true);
        if (bundle.getInt("logintype") == 1) {
            bundle.putString("userCapaid", "200");
        }
        this.a.a(bundle, new AnonymousClass2(bundle, elapsedRealtime, fVar));
    }

    /* compiled from: AuthBusiness */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.e$2  reason: invalid class name */
    public class AnonymousClass2 implements com.cmic.sso.wy.c.b.b {
        final /* synthetic */ Bundle a;
        final /* synthetic */ long b;
        final /* synthetic */ f c;

        AnonymousClass2(Bundle bundle, long j, f fVar) {
            this.a = bundle;
            this.b = j;
            this.c = fVar;
        }

        @Override // com.cmic.sso.wy.c.b.b
        public void a(String str, String str2, JSONObject jSONObject) {
            g.c("AuthBusiness", "\u83b7\u53d6\u5e73\u53f0token \u300b\u300b\u300b\u300b" + jSONObject.toString());
            String string = this.a.getString("interfacecode", "");
            Bundle bundle = this.a;
            bundle.putString("interfacecode", string + str + ";");
            if (str.equals("103000")) {
                String optString = jSONObject.optString("phonescrip");
                this.a.putString("phonescrip", optString);
                e.this.a(jSONObject, optString, this.a);
                this.a.putString("openId", jSONObject.optString("openId"));
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.b;
            String string2 = this.a.getString("interfaceelasped", "");
            Bundle bundle2 = this.a;
            bundle2.putString("interfaceelasped", string2 + elapsedRealtime + ";");
            this.c.a(str, str2, this.a, jSONObject);
        }
    }
}
