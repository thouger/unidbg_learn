package cn.missfresh.module.base.datastatistics;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

/* compiled from: NewDataStatisticsAPI2Manager */
public final class c {
    private static b a = null;
    private static String b = "https://dc-statistics.missfresh.cn/";

    public static b a() {
        AppMethodBeat.i(12433, false);
        if (a == null) {
            synchronized (c.class) {
                try {
                    if (a == null) {
                        r.a a2 = a.a().c().a(b);
                        a2.a(g.a());
                        a2.a(cn.missfresh.lib.b.a.a());
                        a = (b) a2.a().a(b.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(12433);
                    throw th;
                }
            }
        }
        b bVar = a;
        AppMethodBeat.o(12433);
        return bVar;
    }
}
