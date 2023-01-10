package cn.missfresh.risk;

import android.content.Context;
import cn.missfresh.risk.bean.RiskBean;
import cn.missfresh.risk.bean.RiskRealyBean;
import cn.missfresh.risk.f.c;
import cn.missfresh.risk.f.d;
import cn.missfresh.risk.f.i;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import cn.missfresh.utils.e;
import com.alibaba.fastjson.JSON;
import com.android.internal.logging.nano.MetricsProto;
import io.reactivex.c.g;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.TimeUnit;

/* compiled from: RiskManager */
public class h {
    public static int a = 4;
    public static int b = 4;
    public static Context c;
    public static a d;
    private static boolean e;
    private static String f;

    /* compiled from: RiskManager */
    public interface a {
        void a();

        void a(String str);

        void b();
    }

    static /* synthetic */ void a(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_USE_SIP, false);
        b(str);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_USE_SIP);
    }

    static /* synthetic */ void d() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ADD_VOICEMAIL, false);
        e();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ADD_VOICEMAIL);
    }

    public static void a(Context context) {
        boolean z = false;
        int i = MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_CALL_PHONE;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_CALL_PHONE, false);
        synchronized (h.class) {
            if (context == null) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("RiskManager:74:context == null ");
                    if (context == null) {
                        z = true;
                    }
                    sb.append(z);
                    cn.missfresh.risk.exceptions.a.a(sb.toString());
                } finally {
                    AppMethodBeat.o(i);
                }
            } else {
                d.a(context.getApplicationContext());
                c = context.getApplicationContext();
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_CALL_PHONE);
            }
        }
    }

    public static void a(a aVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_CALL_PHONE, false);
        if (c == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_CALL_PHONE);
            return;
        }
        d = aVar;
        a = 4;
        b = 4;
        e = false;
        f = "";
        i.a().b();
        new l().a(c);
        q.a(1, TimeUnit.SECONDS).b(io.reactivex.f.a.b()).a(new AnonymousClass3()).a(io.reactivex.a.b.a.a()).a(new AnonymousClass1(), new AnonymousClass2());
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_CALL_PHONE);
    }

    /* compiled from: RiskManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.h$3  reason: invalid class name */
    public static class AnonymousClass3 implements io.reactivex.c.h<Long, t<String>> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_RECORD_AUDIO, false);
            t<String> a = a((Long) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_RECORD_AUDIO);
            return a;
        }

        public t<String> a(Long l) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ACCESS_COARSE_LOCATION, false);
            RiskBean a = k.a(h.c);
            RiskRealyBean riskRealyBean = new RiskRealyBean();
            riskRealyBean.setOstype("adr");
            riskRealyBean.setInfoDetail(JSON.toJSONString(a));
            riskRealyBean.setToken(d.a());
            if (!b.a(a.getApps())) {
                boolean unused = h.e = true;
            }
            q a2 = q.a(JSON.toJSONString(riskRealyBean));
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ACCESS_COARSE_LOCATION);
            return a2;
        }
    }

    /* compiled from: RiskManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.h$1  reason: invalid class name */
    public static class AnonymousClass1 implements g<String> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_WRITE_CONTACTS, false);
            a((String) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_WRITE_CONTACTS);
        }

        public void a(String str) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_CONTACTS, false);
            try {
                String a = cn.missfresh.risk.d.b.a(16);
                new cn.missfresh.risk.f.h().a(cn.missfresh.risk.d.b.a(str, a), i.a(a, h.c));
                h.d();
            } catch (Exception e) {
                cn.missfresh.risk.exceptions.a.a("RiskManager:131" + e.getMessage());
                h.a(e.getMessage());
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_CONTACTS);
        }
    }

    /* compiled from: RiskManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.h$2  reason: invalid class name */
    public static class AnonymousClass2 implements g<Throwable> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ACCESS_FINE_LOCATION, false);
            a((Throwable) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_ACCESS_FINE_LOCATION);
        }

        public void a(Throwable th) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_ACCESS_FINE_LOCATION, false);
            cn.missfresh.risk.exceptions.a.a("RiskManager:138" + th.getMessage());
            h.a(th.getMessage());
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private static void e() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_CALL_PHONE, false);
        a aVar = d;
        if (aVar != null) {
            aVar.b();
            d.a();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_CALL_PHONE);
    }

    private static void b(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CALL_LOG, false);
        a aVar = d;
        if (aVar != null) {
            aVar.a(str);
            d.a();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CALL_LOG);
    }

    public static void a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_CALL_LOG, false);
        a = 0;
        b = 0;
        e = false;
        f = "";
        i.a().b();
        d = null;
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_CALL_LOG);
    }

    public static String b() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALL_LOG, false);
        String a2 = d.a();
        if (!e.a(a2)) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALL_LOG);
            return a2;
        }
        try {
            String a3 = i.a(c.a("beijing_missfresh_e-commerce_co.ltd" + System.currentTimeMillis() + k.a() + ((int) ((Math.random() * 900.0d) + 100.0d))), c);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALL_LOG);
            return a3;
        } catch (Exception e2) {
            cn.missfresh.risk.exceptions.a.a("RiskManager:206 " + e2.getMessage());
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALL_LOG);
            return "";
        }
    }

    public static boolean c() {
        return e;
    }
}
