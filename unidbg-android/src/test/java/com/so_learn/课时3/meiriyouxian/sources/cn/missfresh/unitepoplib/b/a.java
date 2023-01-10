package cn.missfresh.unitepoplib.b;

import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: Logger */
public class a {
    private static boolean a = true;

    public static void a() {
    }

    public static void a(String str, String str2) {
        AppMethodBeat.i(15564, false);
        if (a) {
            Log.e(str, str2);
        }
        AppMethodBeat.o(15564);
    }

    public static void b(String str, String str2) {
        AppMethodBeat.i(15566, false);
        if (a) {
            Log.i(str, str2);
        }
        AppMethodBeat.o(15566);
    }
}
