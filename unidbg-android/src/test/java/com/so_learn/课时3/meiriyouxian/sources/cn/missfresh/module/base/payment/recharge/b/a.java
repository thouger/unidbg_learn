package cn.missfresh.module.base.payment.recharge.b;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.payment.recharge.bean.BillingAccount;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.Map;
import okhttp3.Request;

/* compiled from: BalanceBillingPresenter */
public class a {
    private cn.missfresh.module.base.payment.recharge.view.a a;
    private cn.missfresh.module.base.payment.recharge.model.a b;

    public a(cn.missfresh.module.base.payment.recharge.view.a aVar) {
        JniLib.cV(this, aVar, Integer.valueOf((int) MetricsProto.MetricsEvent.VIRTUAL_KEYBOARDS));
    }

    public BillingAccount a() {
        return (BillingAccount) JniLib.cL(this, 342);
    }

    public void b() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.APP_DATA_USAGE));
    }

    public void c() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.USER_LOCALE_LIST));
    }

    private void a(String str, String str2, boolean z) {
        AppMethodBeat.i(17067, false);
        Map<String, String> a = c.a("page_no", str, "page_size", str2);
        this.a.d(z);
        c.a(this, i.ap, a, new AnonymousClass1(z));
        AppMethodBeat.o(17067);
    }

    /* compiled from: BalanceBillingPresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.a$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        final /* synthetic */ boolean a;

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 339);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 340);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 341);
        }

        AnonymousClass1(boolean z) {
            this.a = z;
        }
    }
}
