package cn.missfresh.module.base.manager;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: PageManager */
public class j {
    private static Map<String, a> a = new WeakHashMap();

    /* compiled from: PageManager */
    public interface a {
    }

    static {
        AppMethodBeat.i(14502, false);
        AppMethodBeat.o(14502);
    }

    public static void a(String str, a aVar) {
        AppMethodBeat.i(14500, false);
        a.put(str, aVar);
        AppMethodBeat.o(14500);
    }
}
