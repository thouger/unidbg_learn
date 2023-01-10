package com.cmic.sso.wy.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.text.TextUtils;
import com.cmic.sso.wy.a;
import com.cmic.sso.wy.a.b;
import com.cmic.sso.wy.utils.aa;
import com.cmic.sso.wy.utils.f;
import com.cmic.sso.wy.utils.g;
import com.cmic.sso.wy.utils.h;
import com.cmic.sso.wy.utils.j;
import com.cmic.sso.wy.utils.l;
import com.cmic.sso.wy.utils.m;
import com.cmic.sso.wy.utils.p;
import com.cmic.sso.wy.utils.q;
import com.cmic.sso.wy.utils.u;
import com.cmic.sso.wy.utils.v;
import com.cmic.sso.wy.utils.w;
import com.cmic.sso.wy.utils.y;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.pro.ai;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthnHelper */
public class a {
    private static a c;
    private static com.cmic.sso.wy.a d;
    private e a;
    private Context b;
    private Handler e;
    private c f = null;
    private long g = 8000;

    public void a(com.cmic.sso.wy.a aVar) {
        d = aVar;
    }

    public com.cmic.sso.wy.a a() {
        if (d == null) {
            d = new a.C0071a().a();
        }
        return d;
    }

    public void a(long j) {
        this.g = j;
    }

    public long b() {
        return this.g;
    }

    private a(Context context) {
        this.e = new Handler(context.getMainLooper());
        this.b = context.getApplicationContext();
        this.a = e.a(this.b);
        q.a(this.b);
        v.a(new AnonymousClass1());
    }

    /* compiled from: AuthnHelper */
    /* renamed from: com.cmic.sso.wy.b.a$1  reason: invalid class name */
    class AnonymousClass1 extends v.a {
        AnonymousClass1() {
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            String b = q.b("AID", "");
            g.b("AuthnHelper", "aid = " + b);
            if (TextUtils.isEmpty(b)) {
                a.this.e();
            }
            if (f.a(a.this.b)) {
                g.b("AuthnHelper", "\u751f\u6210androidkeystore\u6210\u529f");
            } else {
                g.b("AuthnHelper", "\u751f\u6210androidkeystore\u5931\u8d25");
            }
        }
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e() {
        String str = "%" + aa.a();
        g.b("AuthnHelper", "generate aid = " + str);
        q.a("AID", str);
    }

    public void a(String str, String str2, d dVar) {
        a(str, str2, dVar, -1);
    }

    public void a(String str, String str2, d dVar, int i) {
        Bundle bundle = new Bundle(64);
        bundle.putInt("SDKRequestCode", i);
        bundle.putString("serviceType", "login");
        bundle.putString("caller", f());
        bundle.putLong("methodTimes", System.currentTimeMillis());
        v.a(new AnonymousClass2(this.b, bundle, bundle, str, str2, dVar));
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$2  reason: invalid class name */
    public class AnonymousClass2 extends v.a {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ d d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Bundle bundle, Bundle bundle2, String str, String str2, d dVar) {
            super(context, bundle);
            this.a = bundle2;
            this.b = str;
            this.c = str2;
            this.d = dVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            if (a.this.a(this.a, this.b, this.c, "loginAuth", 1, this.d)) {
                String valueOf = String.valueOf(3);
                g.a("AuthnHelper", "\u8d85\u65f6\u65f6\u95f4\uff1a" + a.this.g);
                a.this.a(valueOf, this.a);
            }
        }
    }

    public void b(String str, String str2, d dVar) {
        b(str, str2, dVar, -1);
    }

    public void b(String str, String str2, d dVar, int i) {
        Bundle bundle = new Bundle(64);
        bundle.putInt("SDKRequestCode", i);
        bundle.putString("serviceType", "authentication");
        bundle.putLong("methodTimes", System.currentTimeMillis());
        v.a(new AnonymousClass3(this.b, bundle, bundle, str, str2, dVar));
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$3  reason: invalid class name */
    public class AnonymousClass3 extends v.a {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ d d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Context context, Bundle bundle, Bundle bundle2, String str, String str2, d dVar) {
            super(context, bundle);
            this.a = bundle2;
            this.b = str;
            this.c = str2;
            this.d = dVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            if (a.this.a(this.a, this.b, this.c, "mobileAuth", 0, this.d)) {
                g.a("AuthnHelper", "\u8d85\u65f6\u65f6\u95f4\uff1a" + a.this.g);
                a.this.a(String.valueOf(3), this.a);
            }
        }
    }

    public void c(String str, String str2, d dVar) {
        c(str, str2, dVar, -1);
    }

    public void c(String str, String str2, d dVar, int i) {
        Bundle bundle = new Bundle(64);
        bundle.putInt("SDKRequestCode", i);
        bundle.putString("serviceType", "general");
        bundle.putString("caller", f());
        bundle.putLong("methodTimes", System.currentTimeMillis());
        v.a(new AnonymousClass4(this.b, bundle, bundle, str, str2, dVar));
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$4  reason: invalid class name */
    public class AnonymousClass4 extends v.a {
        final /* synthetic */ Bundle a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ d d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Context context, Bundle bundle, Bundle bundle2, String str, String str2, d dVar) {
            super(context, bundle);
            this.a = bundle2;
            this.b = str;
            this.c = str2;
            this.d = dVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            if (a.this.a(this.a, this.b, this.c, "preGetMobile", 3, this.d)) {
                g.a("AuthnHelper", "\u8d85\u65f6\u65f6\u95f4\uff1a" + a.this.g);
                a.this.a(String.valueOf(3), this.a);
            }
        }
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: private */
    /* renamed from: com.cmic.sso.wy.b.a$a  reason: collision with other inner class name */
    public class RunnableC0074a implements Runnable {
        private Bundle b;
        private volatile boolean c = false;

        RunnableC0074a(Bundle bundle) {
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("resultCode", "200023");
                    jSONObject.put("resultString", "\u767b\u5f55\u8d85\u65f6");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                a.this.a("200023", "\u767b\u5f55\u8d85\u65f6", this.b, jSONObject, null);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized boolean a() {
            boolean z;
            z = this.c;
            this.c = true;
            return !z;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str, Bundle bundle) {
        RunnableC0074a aVar = new RunnableC0074a(bundle);
        this.e.postDelayed(aVar, this.g);
        bundle.putString("authTypeInput", str);
        this.a.a(str, bundle, new AnonymousClass5(aVar));
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$5  reason: invalid class name */
    public class AnonymousClass5 implements f {
        final /* synthetic */ RunnableC0074a a;

        AnonymousClass5(RunnableC0074a aVar) {
            this.a = aVar;
        }

        @Override // com.cmic.sso.wy.b.f
        public void a(String str, String str2, Bundle bundle, JSONObject jSONObject) {
            g.b("onBusinessComplete", "onBusinessComplete");
            if (this.a.a()) {
                a.this.e.removeCallbacks(this.a);
                if (1 != bundle.getInt("logintype") || !"\u663e\u793a\u767b\u5f55\u53d6\u53f7\u6210\u529f".equals(str2) || j.a(bundle.getString("traceId"))) {
                    a.this.a(str, str2, bundle, jSONObject, null);
                } else {
                    aa.a(a.this.b, bundle, a.d.R(), a.d.S());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean a(Bundle bundle, String str, String str2, String str3, int i, d dVar) {
        String str4;
        String b = aa.b();
        bundle.putString("traceId", b);
        j.a(b, dVar);
        long currentTimeMillis = System.currentTimeMillis();
        bundle.putString("starttime", w.a(currentTimeMillis));
        bundle.putLong("starttimemills", currentTimeMillis);
        bundle.putString("loginMethod", str3);
        bundle.putString("appkey", str2);
        bundle.putString("appid", str);
        StringBuilder sb = new StringBuilder();
        sb.append(this.g);
        String str5 = "";
        sb.append(str5);
        bundle.putString("timeOut", sb.toString());
        bundle.putInt("logintype", i);
        bundle.putBoolean("CLOSE_CERT_VERIFY", y.h());
        boolean a = l.a(this.b, "android.permission.READ_PHONE_STATE");
        g.a("AuthnHelper", "\u6709READ_PHONE_STATE\u6743\u9650\uff1f" + a);
        bundle.putBoolean("hsaReadPhoneStatePermission", a);
        b.a().a(this.b, a);
        bundle.putString("networkClass", b.a().a(this.b));
        bundle.putString("simCardNum", b.a().b().i() + str5);
        int a2 = u.a(this.b);
        bundle.putInt("startnetworkType", a2);
        String a3 = p.a(this.b).a();
        String c2 = p.a(this.b).c();
        String e = p.a(this.b).e();
        String a4 = p.a(this.b).a(false);
        bundle.putString(Constants.KEY_IMEI, c2);
        bundle.putString(ai.aa, e);
        bundle.putString("operatorType", a4);
        g.b("AuthnHelper", "iccid=" + e);
        g.b("AuthnHelper", "imsi=" + a3);
        if (TextUtils.isEmpty(a3)) {
            g.a("AuthnHelper", "\u4f7f\u7528iccid\u4f5c\u4e3a\u7f13\u5b58key");
            bundle.putBoolean("keyIsSimKeyICCID", true);
        }
        bundle.putString(Constants.KEY_IMSI, a3);
        boolean a5 = m.a(bundle);
        bundle.putBoolean("isCacheScrip", a5);
        g.b("AuthnHelper", "isCachePhoneScrip = " + a5);
        if (dVar == null) {
            a("200026", "listener\u4e0d\u80fd\u4e3a\u7a7a", bundle, null, null);
            return false;
        } else if (y.d()) {
            a("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", bundle, null, null);
            return false;
        } else {
            if (str == null) {
                str4 = str5;
            } else {
                str4 = str.trim();
            }
            if (TextUtils.isEmpty(str4)) {
                a("200026", "appId \u4e0d\u80fd\u4e3a\u7a7a", bundle, null, null);
                return false;
            }
            if (str2 != null) {
                str5 = str2.trim();
            }
            if (TextUtils.isEmpty(str5)) {
                a("200026", "appkey\u4e0d\u80fd\u4e3a\u7a7a", bundle, null, null);
                return false;
            } else if (a2 == 0) {
                a("200022", "\u672a\u68c0\u6d4b\u5230\u7f51\u7edc", bundle, null, null);
                return false;
            } else if (TextUtils.isEmpty(a4)) {
                bundle.putString(Telephony.Carriers.AUTH_TYPE, "0");
                a("200002", "\u65e0\u6cd5\u8bc6\u522bsim\u5361\u6216\u6ca1\u6709sim\u5361", bundle, null, null);
                return false;
            } else if ("2".equals(a4) && y.i()) {
                a("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", bundle, null, null);
                return false;
            } else if ("3".equals(a4) && y.j()) {
                a("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", bundle, null, null);
                return false;
            } else if (a2 != 2 || a5) {
                return true;
            } else {
                a("200027", "\u65e0\u6570\u636e\u7f51\u7edc", bundle, null, null);
                return false;
            }
        }
    }

    public void a(String str, JSONObject jSONObject) {
        c cVar = this.f;
        if (cVar != null) {
            cVar.a(str, jSONObject);
        }
    }

    public void a(String str, String str2, Bundle bundle, JSONObject jSONObject, Throwable th) {
        a(str, str2, bundle, jSONObject, th, false);
    }

    public void a(String str, String str2, Bundle bundle, JSONObject jSONObject, Throwable th, boolean z) {
        d c2;
        try {
            String string = bundle.getString("traceId");
            int i = bundle.getInt("SDKRequestCode", -1);
            if (!j.a(string)) {
                synchronized (this) {
                    c2 = j.c(string);
                    if (!z) {
                        j.b(string);
                    }
                    if (c2 == null) {
                        return;
                    }
                }
                int i2 = bundle.getInt("logintype", -1);
                if (jSONObject == null) {
                    jSONObject = g.a(str, str2);
                }
                if (i2 != 3) {
                    jSONObject = g.a(str, str2, bundle, jSONObject);
                }
                jSONObject.put("traceId", string);
                this.e.post(new AnonymousClass6(c2, i, jSONObject));
                if (!y.k()) {
                    new com.cmic.sso.wy.d.b().a(this.b, str, bundle, th);
                }
                v.a(new AnonymousClass7(this.b, bundle, bundle));
            }
            if (j.a()) {
                com.cmic.sso.wy.utils.b.a(this.b).a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$6  reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ d a;
        final /* synthetic */ int b;
        final /* synthetic */ JSONObject c;

        AnonymousClass6(d dVar, int i, JSONObject jSONObject) {
            this.a = dVar;
            this.b = i;
            this.c = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    /* compiled from: AuthnHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.b.a$7  reason: invalid class name */
    public class AnonymousClass7 extends v.a {
        final /* synthetic */ Bundle a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass7(Context context, Bundle bundle, Bundle bundle2) {
            super(context, bundle);
            this.a = bundle2;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            if (this.a.getBoolean("isNeedToGetCert", false)) {
                y.a(a.this.b, this.a);
            } else if (y.a()) {
                y.a(a.this.b, this.a);
            }
        }
    }

    public JSONObject b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            b.a().a(context, l.a(context, "android.permission.READ_PHONE_STATE"));
            String a = p.a(context).a(true);
            int a2 = u.a(context);
            jSONObject.put("operatorType", a);
            jSONObject.put("networkType", a2 + "");
            g.c("AuthnHelper", "\u7f51\u7edc\u7c7b\u578b: " + a2);
            g.c("AuthnHelper", "\u8fd0\u8425\u5546\u7c7b\u578b: " + a);
        } catch (Exception unused) {
            try {
                jSONObject.put("errorDes", "\u53d1\u751f\u672a\u77e5\u9519\u8bef");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    private String f() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String className = stackTrace[i].getClassName();
                if (!TextUtils.isEmpty(className) && className.contains("AuthnHelper")) {
                    break;
                }
                i2++;
                i++;
            }
            int i3 = i2 + 2;
            if (i3 < stackTrace.length) {
                sb.append(stackTrace[i3].getClassName());
                sb.append(";");
            }
            int i4 = i2 + 3;
            if (i4 < stackTrace.length) {
                sb.append(stackTrace[i4].getClassName());
                sb.append(";");
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public void c() {
        try {
            if (h.a().b() != null) {
                h.a().b().a();
            }
        } catch (Exception e) {
            e.printStackTrace();
            g.a("AuthnHelper", "\u5173\u95ed\u6388\u6743\u9875\u5931\u8d25");
        }
    }
}
