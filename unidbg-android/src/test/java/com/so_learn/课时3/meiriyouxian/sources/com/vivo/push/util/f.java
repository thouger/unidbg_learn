package com.vivo.push.util;

import android.os.Looper;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: DebugUtil */
public final class f {
    public static void a(String str) {
        AppMethodBeat.i(1383, false);
        if (n.a() && Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("DebugUtil", "Operation: " + str + " in main thread!", new Throwable());
        }
        AppMethodBeat.o(1383);
    }
}
