package cn.com.chinatelecom.account.api.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class g {
    private static ExecutorService a = Executors.newFixedThreadPool(3);

    public static abstract class a implements Runnable {
        private boolean a = false;

        public void a(boolean z) {
            this.a = z;
        }

        public boolean a() {
            return this.a;
        }
    }

    static {
        AppMethodBeat.i(8093, false);
        AppMethodBeat.o(8093);
    }

    public static void a(Runnable runnable) {
        AppMethodBeat.i(8085, false);
        a.execute(runnable);
        AppMethodBeat.o(8085);
    }

    public static Future b(Runnable runnable) {
        AppMethodBeat.i(8089, false);
        Future<?> submit = a.submit(runnable);
        AppMethodBeat.o(8089);
        return submit;
    }
}
