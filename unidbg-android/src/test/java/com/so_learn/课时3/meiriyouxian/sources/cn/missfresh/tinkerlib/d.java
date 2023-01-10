package cn.missfresh.tinkerlib;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.service.AbstractResultService;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;

/* compiled from: TinkerManager */
public class d {
    private static ApplicationLike a;
    private static f b;
    private static boolean c;

    public static void a(ApplicationLike applicationLike) {
        a = applicationLike;
    }

    public static ApplicationLike a() {
        return a;
    }

    public static void b() {
        AppMethodBeat.i(12844, false);
        if (b == null) {
            b = new f();
            Thread.setDefaultUncaughtExceptionHandler(b);
        }
        AppMethodBeat.o(12844);
    }

    public static void a(boolean z) {
        AppMethodBeat.i(12847, false);
        UpgradePatchRetry.getInstance(a.getApplication()).setRetryEnable(z);
        AppMethodBeat.o(12847);
    }

    public static void a(ApplicationLike applicationLike, Class<? extends AbstractResultService> cls) {
        AppMethodBeat.i(12852, false);
        if (c) {
            TinkerLog.w("Tinker.TinkerManager", "install tinker, but has installed, ignore", new Object[0]);
            AppMethodBeat.o(12852);
            return;
        }
        TinkerInstaller.install(applicationLike, new TinkerLoadReporter(applicationLike.getApplication()), new TinkerPatchReporter(applicationLike.getApplication()), new TinkerPatchListener(applicationLike.getApplication()), cls, new UpgradePatch());
        c = true;
        AppMethodBeat.o(12852);
    }
}
