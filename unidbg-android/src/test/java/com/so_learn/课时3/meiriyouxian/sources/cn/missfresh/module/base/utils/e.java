package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ABTestEvaluateUtils */
public class e implements ABConfig {
    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "ABTestEvaluateUtils";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{"78"};
    }

    public static int a() {
        AppMethodBeat.i(23004, false);
        int i = ABTest.get("78");
        AppMethodBeat.o(23004);
        return i;
    }
}
