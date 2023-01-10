package cn.missfresh.module.base.utils;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ApkUtils */
public class i {
    public static boolean a(Context context) {
        boolean z = false;
        AppMethodBeat.i(23048, false);
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                z = true;
            }
            AppMethodBeat.o(23048);
            return z;
        } catch (Exception unused) {
            AppMethodBeat.o(23048);
            return false;
        }
    }
}
