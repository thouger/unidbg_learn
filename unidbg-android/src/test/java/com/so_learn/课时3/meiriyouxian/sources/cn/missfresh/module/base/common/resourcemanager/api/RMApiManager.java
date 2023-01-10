package cn.missfresh.module.base.common.resourcemanager.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class RMApiManager {
    private static RMApi sRMApi = null;
    private static String sRMApiUrl = "https://as-vip.missfresh.cn/";

    public static RMApi getRMApi() {
        AppMethodBeat.i(12286, false);
        if (sRMApi == null) {
            synchronized (RMApiManager.class) {
                try {
                    if (sRMApi == null) {
                        r.a a = a.a().c().a(sRMApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sRMApi = (RMApi) a.a().a(RMApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(12286);
                    throw th;
                }
            }
        }
        RMApi rMApi = sRMApi;
        AppMethodBeat.o(12286);
        return rMApi;
    }

    public static void changeRMApi(String str) {
        sRMApiUrl = str;
        sRMApi = null;
    }
}
