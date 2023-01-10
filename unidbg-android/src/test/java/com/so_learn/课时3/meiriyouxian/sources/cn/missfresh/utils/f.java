package cn.missfresh.utils;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFUIUtils */
public class f {
    public static int a(Context context, int i) {
        AppMethodBeat.i(13910, false);
        if (context != null) {
            int i2 = (int) ((((float) i) / context.getResources().getDisplayMetrics().density) + 0.5f);
            AppMethodBeat.o(13910);
            return i2;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13910);
        throw nullPointerException;
    }

    public static int b(Context context, int i) {
        AppMethodBeat.i(13920, false);
        if (context != null) {
            int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
            AppMethodBeat.o(13920);
            return i2;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13920);
        throw nullPointerException;
    }

    public static int c(Context context, int i) {
        AppMethodBeat.i(13927, false);
        if (context != null) {
            int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
            AppMethodBeat.o(13927);
            return i2;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(13927);
        throw nullPointerException;
    }
}
