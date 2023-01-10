package cn.missfresh.module.base.common.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class BusinessApiManager {
    private static BusinessApi sBusinessApi = null;
    private static String sBusinessApiUrl = "https://as-vip.missfresh.cn/";

    public static BusinessApi getBusinessApi() {
        AppMethodBeat.i(10208, false);
        if (sBusinessApi == null) {
            synchronized (BusinessApiManager.class) {
                try {
                    if (sBusinessApi == null) {
                        r.a a = a.a().c().a(sBusinessApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sBusinessApi = (BusinessApi) a.a().a(BusinessApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(10208);
                    throw th;
                }
            }
        }
        BusinessApi businessApi = sBusinessApi;
        AppMethodBeat.o(10208);
        return businessApi;
    }

    public static void changeBusinessApi(String str) {
        sBusinessApiUrl = str;
        sBusinessApi = null;
    }
}
