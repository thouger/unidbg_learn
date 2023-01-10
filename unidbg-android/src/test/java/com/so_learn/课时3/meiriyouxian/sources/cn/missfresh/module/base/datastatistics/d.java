package cn.missfresh.module.base.datastatistics;

import android.opengl.EGL14;
import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

/* compiled from: NewDataStatisticsAPIManager */
public final class d {
    private static a a = null;
    private static String b = "https://dc-eventlog.missfresh.cn/";

    public static a a() {
        AppMethodBeat.i(EGL14.EGL_MULTISAMPLE_RESOLVE_DEFAULT, false);
        if (a == null) {
            synchronized (d.class) {
                try {
                    if (a == null) {
                        r.a a2 = a.a().c().a(b);
                        a2.a(g.a());
                        a2.a(cn.missfresh.lib.b.a.a());
                        a = (a) a2.a().a(a.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(EGL14.EGL_MULTISAMPLE_RESOLVE_DEFAULT);
                    throw th;
                }
            }
        }
        a aVar = a;
        AppMethodBeat.o(EGL14.EGL_MULTISAMPLE_RESOLVE_DEFAULT);
        return aVar;
    }

    public static void a(String str) {
        b = str;
        a = null;
    }
}
