package cn.missfresh.utils;

import android.app.Activity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: MFCommonUtils */
public class b {
    private static Pattern a = Pattern.compile("[0-9]*");

    public static boolean a(CharSequence charSequence) {
        AppMethodBeat.i(13564, false);
        boolean a2 = e.a(charSequence);
        AppMethodBeat.o(13564);
        return a2;
    }

    public static boolean a(String str) {
        AppMethodBeat.i(13568, false);
        boolean a2 = e.a(str);
        AppMethodBeat.o(13568);
        return a2;
    }

    public static boolean a(Map map) {
        AppMethodBeat.i(13570, false);
        boolean a2 = a.a(map);
        AppMethodBeat.o(13570);
        return a2;
    }

    public static boolean a(Collection collection) {
        AppMethodBeat.i(13573, false);
        boolean a2 = a.a(collection);
        AppMethodBeat.o(13573);
        return a2;
    }

    static {
        AppMethodBeat.i(13608, false);
        AppMethodBeat.o(13608);
    }

    public static int a(String str, int i) {
        AppMethodBeat.i(13586, false);
        try {
            i = Integer.valueOf(str).intValue();
        } catch (Exception e) {
            d.a("MFCommonUtils", e);
        }
        AppMethodBeat.o(13586);
        return i;
    }

    public static boolean a(Activity activity) {
        boolean z = false;
        AppMethodBeat.i(13600, false);
        if (activity != null && !activity.isFinishing()) {
            z = true;
        }
        AppMethodBeat.o(13600);
        return z;
    }
}
