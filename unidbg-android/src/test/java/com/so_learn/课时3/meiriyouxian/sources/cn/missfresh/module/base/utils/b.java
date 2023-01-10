package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ABHomePerformanceUtils */
public class b implements ABConfig {
    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "ABHomePerformanceUtils";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{"48"};
    }

    public static boolean a() {
        boolean z = false;
        AppMethodBeat.i(23000, false);
        if (ABTest.get("48") != 0) {
            z = true;
        }
        AppMethodBeat.o(23000);
        return z;
    }

    public static int b() {
        AppMethodBeat.i(23001, false);
        int i = ABTest.get("48");
        AppMethodBeat.o(23001);
        return i;
    }
}
