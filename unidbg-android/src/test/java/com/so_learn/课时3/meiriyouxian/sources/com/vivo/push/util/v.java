package com.vivo.push.util;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SharePreferenceManager */
public final class v extends a {
    private static v b;

    public static synchronized v b() {
        v vVar;
        synchronized (v.class) {
            AppMethodBeat.i(1747, false);
            if (b == null) {
                b = new v();
            }
            vVar = b;
            AppMethodBeat.o(1747);
        }
        return vVar;
    }

    public final synchronized void a(Context context) {
        AppMethodBeat.i(1749, false);
        if (this.a == null) {
            this.a = context;
            a(context, "com.vivo.push_preferences");
        }
        AppMethodBeat.o(1749);
    }
}
