package cn.missfresh.buttomline.abtest.log;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class Logger {
    public static final String TAG = "buttomline";

    public static void i(String str) {
        AppMethodBeat.i(7397, false);
        d.d(TAG, str);
        AppMethodBeat.o(7397);
    }

    public static void e(String str) {
        AppMethodBeat.i(7398, false);
        d.b(TAG, str);
        AppMethodBeat.o(7398);
    }

    public static void e(Exception exc) {
        AppMethodBeat.i(7400, false);
        d.b(TAG, "Exception", exc);
        AppMethodBeat.o(7400);
    }
}
