package cn.missfresh.buttomline.logtrace.c;

import cn.missfresh.buttomline.logtrace.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: LogGeneratorFactory */
public class c {
    private static final a a = new b();
    private static final a b = new a();
    private static final a c = new d();

    static {
        AppMethodBeat.i(16939, false);
        AppMethodBeat.o(16939);
    }

    public static a a(int i) {
        if (i == 0) {
            return a;
        }
        if (i == 1) {
            return b;
        }
        if (i != 2) {
            return null;
        }
        return c;
    }
}
