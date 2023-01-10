package cn.missfresh.risk.simcard;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SimCardHelper */
public class c extends d {
    public static SimCardBean a(Context context) {
        AppMethodBeat.i(2608, false);
        SimCardBean simCardBean = new SimCardBean();
        b.a(context, simCardBean);
        AppMethodBeat.o(2608);
        return simCardBean;
    }
}
