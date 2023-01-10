package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import com.alipay.sdk.b.a;
import com.alipay.sdk.g.a;
import com.alipay.sdk.g.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.f;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.taobao.accs.utl.BaseMonitor;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AuthTask {
    static final Object a = f.class;
    private Activity b;
    private a c;

    public AuthTask(Activity activity) {
        this.b = activity;
        b.a().a(this.b);
        this.c = new a(activity, "\u53bb\u652f\u4ed8\u5b9d\u6388\u6743");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.app.AuthTask$1  reason: invalid class name */
    public class AnonymousClass1 implements f.c {
        @Override // com.alipay.sdk.util.f.c
        public void a() {
        }

        AnonymousClass1() {
        }

        @Override // com.alipay.sdk.util.f.c
        public void b() {
            AuthTask.this.c();
        }
    }

    private f.c a() {
        return new AnonymousClass1();
    }

    private void b() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.b();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        com.alipay.sdk.g.a aVar;
        aVar = new com.alipay.sdk.g.a(this.b, str, "authV2");
        return j.a(aVar, innerAuth(aVar, str, z));
    }

    public synchronized String auth(String str, boolean z) {
        return innerAuth(new com.alipay.sdk.g.a(this.b, str, BaseMonitor.ALARM_POINT_AUTH), str, z);
    }

    public synchronized String innerAuth(com.alipay.sdk.g.a aVar, String str, boolean z) {
        String c;
        Activity activity;
        String str2;
        if (z) {
            b();
        }
        b.a().a(this.b);
        c = b.c();
        a.a("");
        try {
            c = a(this.b, str, aVar);
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturnV", j.a(c, "resultStatus") + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + j.a(c, "memo"));
            if (!com.alipay.sdk.b.a.p().n()) {
                com.alipay.sdk.b.a.p().a(aVar, this.b);
            }
            c();
            activity = this.b;
            str2 = aVar.a;
        } catch (Exception e) {
            e.a(e);
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturnV", j.a(c, "resultStatus") + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + j.a(c, "memo"));
            if (!com.alipay.sdk.b.a.p().n()) {
                com.alipay.sdk.b.a.p().a(aVar, this.b);
            }
            c();
            activity = this.b;
            str2 = aVar.a;
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.b(aVar, "biz", "PgReturnV", j.a(c, "resultStatus") + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + j.a(c, "memo"));
            if (!com.alipay.sdk.b.a.p().n()) {
                com.alipay.sdk.b.a.p().a(aVar, this.b);
            }
            c();
            com.alipay.sdk.app.a.a.b(this.b, aVar, str, aVar.a);
            throw th;
        }
        com.alipay.sdk.app.a.a.b(activity, aVar, str, str2);
        return c;
    }

    private String a(Activity activity, String str, com.alipay.sdk.g.a aVar) {
        String a2 = aVar.a(str);
        List<a.C0063a> o = com.alipay.sdk.b.a.p().o();
        if (!com.alipay.sdk.b.a.p().a || o == null) {
            o = a.a;
        }
        if (l.b(aVar, this.b, o)) {
            String a3 = new f(activity, aVar, a()).a(a2);
            if (!TextUtils.equals(a3, "failed") && !TextUtils.equals(a3, "scheme_failed")) {
                return TextUtils.isEmpty(a3) ? b.c() : a3;
            }
            com.alipay.sdk.app.a.a.a(aVar, "biz", "LogBindCalledH5");
            return b(activity, a2, aVar);
        }
        com.alipay.sdk.app.a.a.a(aVar, "biz", "LogCalledH5");
        return b(activity, a2, aVar);
    }

    private String b(Activity activity, String str, com.alipay.sdk.g.a aVar) {
        c cVar;
        b();
        try {
            List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.f.a.a().a(aVar, activity, str).c().optJSONObject("form").optJSONObject("onload"));
            c();
            for (int i = 0; i < a2.size(); i++) {
                if (a2.get(i).a() == com.alipay.sdk.protocol.a.WapPay) {
                    String a3 = a(aVar, a2.get(i));
                    c();
                    return a3;
                }
            }
        } catch (IOException e) {
            c b = c.b(c.NETWORK_ERROR.a());
            com.alipay.sdk.app.a.a.a(aVar, ConstantKey.NET, e);
            c();
            cVar = b;
        } catch (Throwable th) {
            c();
            throw th;
        }
        c();
        cVar = null;
        if (cVar == null) {
            cVar = c.b(c.FAILED.a());
        }
        return b.a(cVar.a(), cVar.b(), "");
    }

    private String a(com.alipay.sdk.g.a aVar, com.alipay.sdk.protocol.b bVar) {
        String[] b = bVar.b();
        Bundle bundle = new Bundle();
        bundle.putString("url", b[0]);
        Intent intent = new Intent(this.b, H5AuthActivity.class);
        intent.putExtras(bundle);
        a.C0066a.a(aVar, intent);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException unused) {
                return b.c();
            }
        }
        String a2 = b.a();
        if (TextUtils.isEmpty(a2)) {
            return b.c();
        }
        return a2;
    }
}
