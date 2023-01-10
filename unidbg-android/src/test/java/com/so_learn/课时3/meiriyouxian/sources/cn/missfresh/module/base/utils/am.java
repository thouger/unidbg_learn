package cn.missfresh.module.base.utils;

import android.content.Intent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: ProductStatisticsUtil */
public class am {
    public static String a(String str) {
        AppMethodBeat.i(23400, false);
        if (b.a(str)) {
            AppMethodBeat.o(23400);
            return Intent.EXTRA_MANUAL;
        }
        AppMethodBeat.o(23400);
        return "recommend";
    }
}
