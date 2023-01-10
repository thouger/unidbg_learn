package cn.missfresh.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Collection;
import java.util.Map;

/* compiled from: MFCollectionsUtil */
public class a {
    public static boolean a(Collection collection) {
        boolean z = false;
        AppMethodBeat.i(13545, false);
        if (collection == null || collection.isEmpty()) {
            z = true;
        }
        AppMethodBeat.o(13545);
        return z;
    }

    public static boolean a(Map map) {
        boolean z = false;
        AppMethodBeat.i(13546, false);
        if (map == null || map.isEmpty()) {
            z = true;
        }
        AppMethodBeat.o(13546);
        return z;
    }
}
