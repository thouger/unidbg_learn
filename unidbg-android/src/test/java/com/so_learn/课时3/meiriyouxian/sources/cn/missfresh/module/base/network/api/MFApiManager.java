package cn.missfresh.module.base.network.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class MFApiManager {
    private static MFApi sMFApi = null;
    private static String sMFApiUrl = "https://as-vip.missfresh.cn/";

    public MFApiManager() {
        JniLib.cV(this, 69);
    }

    public static void changeMFApi(String str) {
        JniLib.cV(str, 70);
    }

    public static MFApi getMFApi() {
        AppMethodBeat.i(15709, false);
        if (sMFApi == null) {
            synchronized (MFApiManager.class) {
                try {
                    if (sMFApi == null) {
                        r.a a = a.a().c().a(sMFApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sMFApi = (MFApi) a.a().a(MFApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(15709);
                    throw th;
                }
            }
        }
        MFApi mFApi = sMFApi;
        AppMethodBeat.o(15709);
        return mFApi;
    }
}
