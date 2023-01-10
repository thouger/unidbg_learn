package cn.missfresh.module.base.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ClassifyFastClick */
public class o {
    private static long a;

    public static boolean a(int i) {
        boolean z = false;
        AppMethodBeat.i(23145, false);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a >= ((long) i)) {
            z = true;
        }
        a = currentTimeMillis;
        AppMethodBeat.o(23145);
        return z;
    }
}
