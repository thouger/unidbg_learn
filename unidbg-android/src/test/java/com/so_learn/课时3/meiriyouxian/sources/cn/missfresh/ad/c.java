package cn.missfresh.ad;

import cn.missfresh.ad.api.IMFADClient;
import cn.missfresh.ad.data.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFADSDK */
public class c {
    public static synchronized void a(a aVar) {
        synchronized (c.class) {
            AppMethodBeat.i(5842, false);
            a.a().a(aVar);
            AppMethodBeat.o(5842);
        }
    }

    public static IMFADClient a() {
        AppMethodBeat.i(5844, false);
        a a = a.a();
        AppMethodBeat.o(5844);
        return a;
    }
}
