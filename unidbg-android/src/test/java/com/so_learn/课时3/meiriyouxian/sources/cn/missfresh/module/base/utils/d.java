package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ABTestBusinessMonitoringUtils */
public class d implements ABConfig {
    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "ABTestBusinessMonitoringUtils";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{"93"};
    }

    public static int a() {
        AppMethodBeat.i(23003, false);
        int i = ABTest.get("93");
        AppMethodBeat.o(23003);
        return i;
    }
}
