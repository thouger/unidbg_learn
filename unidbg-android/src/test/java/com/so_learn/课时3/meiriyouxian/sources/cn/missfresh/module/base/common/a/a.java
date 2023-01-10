package cn.missfresh.module.base.common.a;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

/* compiled from: DefaultLocationTestConf */
public class a implements ABConfig {
    public static boolean a() {
        boolean z = false;
        AppMethodBeat.i(9964, false);
        if (ABTest.get("39") == 1) {
            z = true;
        }
        AppMethodBeat.o(9964);
        return z;
    }

    public static boolean b() {
        boolean z = false;
        AppMethodBeat.i(9967, false);
        if (ABTest.get("37") == 1) {
            z = true;
        }
        AppMethodBeat.o(9967);
        return z;
    }

    public static int c() {
        AppMethodBeat.i(9969, false);
        int i = ABTest.get("40");
        d.b("DefaultLocationTestConf", "timeOut:" + i);
        if (i <= 0) {
            i = 20;
        }
        AppMethodBeat.o(9969);
        return i;
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{"39", "37", "40"};
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        AppMethodBeat.i(9974, false);
        String simpleName = getClass().getSimpleName();
        AppMethodBeat.o(9974);
        return simpleName;
    }
}
