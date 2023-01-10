package cn.missfresh.risk.f;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SecurityUtils */
public class i {
    public static String a(String str, Context context) throws Exception {
        AppMethodBeat.i(3122, false);
        String a = a.a(g.a(str.getBytes(), g.a(context.getResources().getAssets().open("public_key.pem"))));
        AppMethodBeat.o(3122);
        return a;
    }
}
