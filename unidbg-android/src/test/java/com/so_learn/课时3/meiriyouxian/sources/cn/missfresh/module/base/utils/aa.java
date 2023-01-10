package cn.missfresh.module.base.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: LogarithmUtil */
public class aa {
    public static double a(int i, int i2) {
        AppMethodBeat.i(23337, false);
        double log = Math.log((double) i) / Math.log((double) i2);
        AppMethodBeat.o(23337);
        return log;
    }

    public static int b(int i, int i2) {
        AppMethodBeat.i(23338, false);
        double a = a(i, i2);
        int i3 = (int) a;
        if (((double) i3) == a) {
            AppMethodBeat.o(23338);
            return i;
        }
        int pow = (int) Math.pow((double) i2, (double) (i3 + 1));
        AppMethodBeat.o(23338);
        return pow;
    }
}
