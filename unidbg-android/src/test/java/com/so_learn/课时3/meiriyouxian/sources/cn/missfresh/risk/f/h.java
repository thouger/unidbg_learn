package cn.missfresh.risk.f;

import android.os.Build;
import cn.missfresh.risk.api.RequestParam;
import cn.missfresh.risk.bean.RiskCcgReqParam;
import cn.missfresh.risk.bean.RiskJsonBean;
import cn.missfresh.risk.bean.RiskReqParam;
import cn.missfresh.risk.c.a;
import cn.missfresh.risk.g;
import cn.missfresh.risk.i;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.e;
import io.reactivex.disposables.b;
import io.reactivex.v;

/* compiled from: RiskModel */
public class h {
    public void a(String str, String str2) {
        AppMethodBeat.i(3080, false);
        if (e.a(i.a().c())) {
            i.a().a(str);
            i.a().c(str2);
        }
        a.b().a().getRiskToken(a(), b(str, str2)).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new AnonymousClass1());
        AppMethodBeat.o(3080);
    }

    /* compiled from: RiskModel */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.f.h$1  reason: invalid class name */
    public class AnonymousClass1 implements v<RiskJsonBean> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
        }

        AnonymousClass1() {
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(3028, false);
            a((RiskJsonBean) obj);
            AppMethodBeat.o(3028);
        }

        public void a(RiskJsonBean riskJsonBean) {
            AppMethodBeat.i(3012, false);
            if (riskJsonBean == null || !riskJsonBean.isSuccess()) {
                i.a().g();
            } else if (riskJsonBean.getData() != null) {
                String token = riskJsonBean.getData().getToken();
                cn.missfresh.risk.exceptions.a.a(token, "RiskModel:47:" + riskJsonBean.getData().getToken());
                d.a(riskJsonBean.getData().getToken());
                cn.missfresh.risk.h.a = 0;
                g.a(riskJsonBean.getData().getCcg());
            } else {
                cn.missfresh.risk.exceptions.a.a("RiskModel:53:riskJsonBean.getData null");
            }
            AppMethodBeat.o(3012);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            AppMethodBeat.i(3018, false);
            i.a().g();
            cn.missfresh.risk.exceptions.a.a("RiskModel:63:" + th.toString());
            AppMethodBeat.o(3018);
        }
    }

    public void a(String str) {
        AppMethodBeat.i(3082, false);
        if (e.a(i.a().d())) {
            i.a().b(str);
        }
        a.b().a().upRiskCcg(a(), b(str)).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new AnonymousClass2());
        AppMethodBeat.o(3082);
    }

    /* compiled from: RiskModel */
    /* renamed from: cn.missfresh.risk.f.h$2  reason: invalid class name */
    class AnonymousClass2 implements v<RiskJsonBean> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
        }

        AnonymousClass2() {
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(3066, false);
            a((RiskJsonBean) obj);
            AppMethodBeat.o(3066);
        }

        public void a(RiskJsonBean riskJsonBean) {
            AppMethodBeat.i(3051, false);
            if (riskJsonBean == null || !riskJsonBean.isSuccess()) {
                i.a().g();
            } else {
                cn.missfresh.risk.h.b = 0;
                i.a().b();
            }
            AppMethodBeat.o(3051);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            AppMethodBeat.i(3057, false);
            i.a().h();
            AppMethodBeat.o(3057);
        }
    }

    private RequestParam<RiskReqParam> b(String str, String str2) {
        AppMethodBeat.i(3088, false);
        RequestParam<RiskReqParam> requestParam = new RequestParam<>();
        RiskReqParam riskReqParam = new RiskReqParam();
        riskReqParam.setDevideinfo(str);
        riskReqParam.setToken(d.a());
        riskReqParam.setPwd(str2);
        requestParam.setParam(riskReqParam);
        AppMethodBeat.o(3088);
        return requestParam;
    }

    private RequestParam<RiskCcgReqParam> b(String str) {
        AppMethodBeat.i(3095, false);
        RequestParam<RiskCcgReqParam> requestParam = new RequestParam<>();
        RiskCcgReqParam riskCcgReqParam = new RiskCcgReqParam();
        riskCcgReqParam.setCcg(str);
        requestParam.setParam(riskCcgReqParam);
        AppMethodBeat.o(3095);
        return requestParam;
    }

    private String a() {
        String str;
        AppMethodBeat.i(3103, false);
        try {
            str = System.getProperty("http.agent");
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        if (e.a(str)) {
            try {
                str = String.format("%s/%s (Linux; U; Android %s; %s Build/%s)", "Dalvik", "2.1.0", Build.VERSION.RELEASE, Build.MANUFACTURER, Build.ID);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        AppMethodBeat.o(3103);
        return str;
    }
}
