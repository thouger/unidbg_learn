package cn.missfresh.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFStringUtils */
public class e {
    public static boolean a(String str) {
        boolean z = false;
        AppMethodBeat.i(13896, false);
        if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim())) {
            z = true;
        }
        AppMethodBeat.o(13896);
        return z;
    }

    public static boolean a(CharSequence charSequence) {
        AppMethodBeat.i(13899, false);
        if (charSequence == null) {
            AppMethodBeat.o(13899);
            return true;
        }
        boolean a = a(charSequence.toString());
        AppMethodBeat.o(13899);
        return a;
    }
}
