package cn.missfresh.module.base.common.ministart.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class MiniStartApiManager {
    private static MiniStartApi sMiniStartApi = null;
    private static String sMiniStartApiUrl = "https://as-vip.missfresh.cn/";

    public static MiniStartApi getMiniStartApi() {
        AppMethodBeat.i(12142, false);
        if (sMiniStartApi == null) {
            synchronized (MiniStartApiManager.class) {
                try {
                    if (sMiniStartApi == null) {
                        r.a a = a.a().c().a(sMiniStartApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sMiniStartApi = (MiniStartApi) a.a().a(MiniStartApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(12142);
                    throw th;
                }
            }
        }
        MiniStartApi miniStartApi = sMiniStartApi;
        AppMethodBeat.o(12142);
        return miniStartApi;
    }

    public static void changeMiniStartApi(String str) {
        sMiniStartApiUrl = str;
        sMiniStartApi = null;
    }
}
