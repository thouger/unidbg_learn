package cn.com.chinatelecom.account.api.b;

import android.os.Handler;
import android.os.Looper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class a {
    private static Executor b = Executors.newSingleThreadExecutor();
    public Handler a = new Handler(Looper.getMainLooper());

    static {
        AppMethodBeat.i(7897, false);
        AppMethodBeat.o(7897);
    }

    public a() {
        AppMethodBeat.i(7893, false);
        AppMethodBeat.o(7893);
    }

    public static void a(Runnable runnable) {
        AppMethodBeat.i(7895, false);
        b.execute(runnable);
        AppMethodBeat.o(7895);
    }
}
