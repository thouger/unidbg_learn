package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;

/* compiled from: ABTestWsgUtils */
public class g implements ABConfig {
    private static boolean a;
    private static boolean b;
    private static boolean c;
    private static boolean d;

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "ABTestWsgUtils";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{Constants.VIA_REPORT_TYPE_JOININ_GROUP, "76"};
    }

    public static int a() {
        AppMethodBeat.i(23036, false);
        int i = ABTest.get(Constants.VIA_REPORT_TYPE_JOININ_GROUP);
        AppMethodBeat.o(23036);
        return i;
    }

    public static boolean b() {
        AppMethodBeat.i(23037, false);
        if (d) {
            boolean z = c;
            AppMethodBeat.o(23037);
            return z;
        }
        d = true;
        if (ABTest.get("76") == 1) {
            c = true;
        } else {
            c = false;
        }
        boolean z2 = c;
        AppMethodBeat.o(23037);
        return z2;
    }
}
