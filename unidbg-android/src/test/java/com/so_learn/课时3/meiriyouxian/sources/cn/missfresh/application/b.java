package cn.missfresh.application;

import android.app.Application;
import cn.missfresh.module.base.base.AppStartType;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ApplicationLikeDelegate */
public class b {
    private static Application a;
    private static AppStartType b;

    public static void a() {
        AppMethodBeat.i(10, false);
        String d = r.d();
        String t = e.t();
        e.g(cn.missfresh.utils.b.a(t));
        if (!e.u()) {
            b = AppStartType.newerFirstStart;
        } else if (d.equalsIgnoreCase(t)) {
            b = AppStartType.notFirstStart;
        } else {
            b = AppStartType.overWriteFirstStart;
        }
        e.j(d);
        AppMethodBeat.o(10);
    }

    public static AppStartType b() {
        return b;
    }

    public static void a(AppStartType appStartType) {
        b = appStartType;
    }

    public static Application c() {
        return a;
    }

    public static void a(Application application) {
        a = application;
    }
}
