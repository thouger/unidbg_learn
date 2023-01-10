package cn.missfresh.module.user.vip.monthly.b;

import android.os.health.UidHealthStats;
import cn.missfresh.basiclib.net.c;
import cn.missfresh.module.base.bean.AutoPayInfo;
import cn.missfresh.module.base.bean.CloseAutoPay;
import cn.missfresh.module.base.common.config.b;
import cn.missfresh.module.base.network.api.MFApiManager;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.user.vip.monthly.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ContinueMouthlyModleImp */
public class a implements a.a {
    public void a(a.b bVar) {
        AppMethodBeat.i(10065, false);
        MFApiManager.changeMFApi(b.b);
        MFApiManager.getMFApi().getAutoPayInfo().a(cn.missfresh.basiclib.net.e.a.a).subscribe(new c(new AnonymousClass1(bVar)));
        AppMethodBeat.o(10065);
    }

    /* compiled from: ContinueMouthlyModleImp */
    /* renamed from: cn.missfresh.module.user.vip.monthly.b.a$1  reason: invalid class name */
    class AnonymousClass1 extends j<AutoPayInfo> {
        final /* synthetic */ a.b a;

        AnonymousClass1(a.b bVar) {
            this.a = bVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_OTHER_USER_ACTIVITY_COUNT, false);
            a((AutoPayInfo) obj);
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_OTHER_USER_ACTIVITY_COUNT);
        }

        public void a(AutoPayInfo autoPayInfo) {
            AppMethodBeat.i(UidHealthStats.TIMER_PROCESS_STATE_FOREGROUND_SERVICE_MS, false);
            this.a.a(autoPayInfo);
            AppMethodBeat.o(UidHealthStats.TIMER_PROCESS_STATE_FOREGROUND_SERVICE_MS);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(UidHealthStats.TIMER_PROCESS_STATE_BACKGROUND_MS, false);
            this.a.a(str);
            AppMethodBeat.o(UidHealthStats.TIMER_PROCESS_STATE_BACKGROUND_MS);
        }
    }

    public void a(a.e eVar) {
        AppMethodBeat.i(10066, false);
        MFApiManager.changeMFApi(b.b);
        MFApiManager.getMFApi().getCloseAutoPay().a(cn.missfresh.basiclib.net.e.a.a).subscribe(new c(new AnonymousClass2(eVar)));
        AppMethodBeat.o(10066);
    }

    /* compiled from: ContinueMouthlyModleImp */
    /* renamed from: cn.missfresh.module.user.vip.monthly.b.a$2  reason: invalid class name */
    class AnonymousClass2 extends j<CloseAutoPay> {
        final /* synthetic */ a.e a;

        AnonymousClass2(a.e eVar) {
            this.a = eVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_SYSTEM_CPU_TIME_MS, false);
            a((CloseAutoPay) obj);
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_SYSTEM_CPU_TIME_MS);
        }

        public void a(CloseAutoPay closeAutoPay) {
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_WIFI_RX_PACKETS, false);
            if (closeAutoPay.getCode() == 0) {
                this.a.c();
            } else {
                this.a.b(closeAutoPay.getMsg());
            }
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_WIFI_RX_PACKETS);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_BLUETOOTH_TX_PACKETS, false);
            this.a.b(str);
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_BLUETOOTH_TX_PACKETS);
        }
    }
}
