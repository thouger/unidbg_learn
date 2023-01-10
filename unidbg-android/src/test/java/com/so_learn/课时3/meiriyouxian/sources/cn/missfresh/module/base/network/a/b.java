package cn.missfresh.module.base.network.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: BusinessMonitoringServerList */
public class b {
    public static final Map<String, String> a = new HashMap();

    private b() {
        JniLib.cV(this, 22);
    }

    public static b a() {
        return (b) JniLib.cL(23);
    }

    public Map.Entry<String, String> a(String str) {
        return (Map.Entry) JniLib.cL(this, str, 21);
    }

    static {
        AppMethodBeat.i(15795, false);
        AppMethodBeat.o(15795);
    }

    private Map.Entry<String, String> b(String str) {
        AppMethodBeat.i(15786, false);
        Map.Entry<String, String> entry = null;
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(15786);
            return null;
        }
        Iterator<Map.Entry<String, String>> it2 = a.entrySet().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it2.next();
            if (next.getValue() != null && str.toLowerCase().contains(next.getValue().toLowerCase())) {
                entry = next;
                break;
            }
        }
        AppMethodBeat.o(15786);
        return entry;
    }

    /* compiled from: BusinessMonitoringServerList */
    private static final class a {
        private static final b a = new b();

        static {
            AppMethodBeat.i(15775, false);
            AppMethodBeat.o(15775);
        }
    }
}
