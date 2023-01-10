package cn.missfresh.risk.e;

import android.content.Context;
import cn.missfresh.risk.bean.TelephonyInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SimUtil */
public class a {
    public static TelephonyInfo a(Context context) {
        AppMethodBeat.i(2481, false);
        try {
            TelephonyInfo instance = TelephonyInfo.getInstance(context);
            AppMethodBeat.o(2481);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(2481);
            return null;
        }
    }
}
