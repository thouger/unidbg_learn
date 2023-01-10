package cn.missfresh.module.base.payment.recharge.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class RechargApiManager {
    private static RechargApi sRechargApi = null;
    private static String sRechargApiUrl = "https://as-vip.missfresh.cn/";

    public RechargApiManager() {
        JniLib.cV(this, 336);
    }

    public static void changeRechargApi(String str) {
        JniLib.cV(str, 337);
    }

    public static RechargApi getRechargApi() {
        AppMethodBeat.i(16822, false);
        if (sRechargApi == null) {
            synchronized (RechargApiManager.class) {
                try {
                    if (sRechargApi == null) {
                        r.a a = a.a().c().a(sRechargApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sRechargApi = (RechargApi) a.a().a(RechargApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(16822);
                    throw th;
                }
            }
        }
        RechargApi rechargApi = sRechargApi;
        AppMethodBeat.o(16822);
        return rechargApi;
    }
}
