package cn.missfresh.module.base.payment.recharge.b;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.common.d.d;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.payment.recharge.bean.Balance;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.module.base.payment.recharge.model.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Request;

/* compiled from: MyBalancePresenter */
public class c implements d.a {
    private a a;
    private cn.missfresh.module.base.payment.recharge.view.a b;
    private d c;

    public c(cn.missfresh.module.base.payment.recharge.view.a aVar) {
        JniLib.cV(this, aVar, 362);
    }

    @Override // cn.missfresh.module.base.common.d.d.a
    public void a(String str) {
        JniLib.cV(this, str, 352);
    }

    @Override // cn.missfresh.module.base.common.d.d.a
    public void a(List<StoreCardBean> list) {
        JniLib.cV(this, list, 353);
    }

    public void a(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 354);
    }

    public void b() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SCOPED_DIRECTORY_ACCESS_DENIED_AND_PERSIST_BY_FOLDER));
    }

    public Balance c() {
        return (Balance) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SCOPED_DIRECTORY_ACCESS_DENIED_AND_PERSIST_BY_PACKAGE));
    }

    public List<StoreCardBean> d() {
        return (List) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.OVERVIEW_DISMISS_ALL));
    }

    public String e() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.QS_EDIT));
    }

    public boolean g() {
        return JniLib.cZ(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_QS_EDIT_RESET));
    }

    public void h() {
        JniLib.cV(this, 360);
    }

    public boolean i() {
        return JniLib.cZ(this, 361);
    }

    /* compiled from: MyBalancePresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.c$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.PHYSICAL_KEYBOARDS));
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.ENABLE_VIRTUAL_KEYBOARDS));
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, Integer.valueOf((int) MetricsProto.MetricsEvent.DATA_SAVER_SUMMARY));
        }

        AnonymousClass1() {
        }
    }

    public void a() {
        AppMethodBeat.i(17106, false);
        cn.missfresh.module.base.network.c.a(this, i.ao, (Map<String, String>) null, new AnonymousClass1());
        AppMethodBeat.o(17106);
    }

    public void b(String str) {
        AppMethodBeat.i(17109, false);
        HashMap hashMap = new HashMap();
        hashMap.put("inviteCode", str);
        this.b.u();
        cn.missfresh.module.base.network.c.a(this, i.ah, hashMap, new AnonymousClass2());
        AppMethodBeat.o(17109);
    }

    /* compiled from: MyBalancePresenter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.b.c$2  reason: invalid class name */
    class AnonymousClass2 extends m {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.DATA_USAGE_UNRESTRICTED_ACCESS));
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 350);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 351);
        }

        AnonymousClass2() {
        }
    }

    public int f() {
        int i = 0;
        AppMethodBeat.i(17119, false);
        if (d() != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= d().size()) {
                    break;
                } else if (d().get(i2).getIsDefault() != 0) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
        }
        AppMethodBeat.o(17119);
        return i;
    }
}
