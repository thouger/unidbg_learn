package cn.missfresh.module.base.utils;

import android.os.Looper;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: Preconditions */
public final class ak {
    public static View a(View view, String str) {
        AppMethodBeat.i(23392, false);
        if (view != null) {
            AppMethodBeat.o(23392);
            return view;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        AppMethodBeat.o(23392);
        throw nullPointerException;
    }

    public static void a() {
        AppMethodBeat.i(23393, false);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            AppMethodBeat.o(23393);
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Must be called from the main thread. Was: " + Thread.currentThread());
        AppMethodBeat.o(23393);
        throw illegalStateException;
    }
}
