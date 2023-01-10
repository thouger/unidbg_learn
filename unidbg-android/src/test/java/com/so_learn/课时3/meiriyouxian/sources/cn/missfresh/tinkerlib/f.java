package cn.missfresh.tinkerlib;

import android.app.job.JobInfo;
import android.content.SharedPreferences;
import android.os.SystemClock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.lang.Thread;

/* compiled from: TinkerUncaughtExceptionHandler */
public class f implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

    public f() {
        AppMethodBeat.i(12918, false);
        AppMethodBeat.o(12918);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        AppMethodBeat.i(12921, false);
        TinkerLog.e("Tinker.SampleUncaughtExHandler", "uncaughtException:" + th.getMessage(), new Object[0]);
        a();
        a(th);
        this.a.uncaughtException(thread, th);
        AppMethodBeat.o(12921);
    }

    private void a(Throwable th) {
        AppMethodBeat.i(12923, false);
        ApplicationLike a = d.a();
        if (a == null || a.getApplication() == null) {
            TinkerLog.w("Tinker.SampleUncaughtExHandler", "applicationlike is null", new Object[0]);
            AppMethodBeat.o(12923);
        } else if (!TinkerApplicationHelper.isTinkerLoadSuccess(a)) {
            TinkerLog.w("Tinker.SampleUncaughtExHandler", "tinker is not loaded", new Object[0]);
            AppMethodBeat.o(12923);
        } else {
            boolean z = false;
            while (th != null) {
                if (!z) {
                    z = g.a(th);
                }
                if (z) {
                    if ((th instanceof IllegalAccessError) && th.getMessage().contains("Class ref in pre-verified class resolved to unexpected implementation")) {
                        e.f();
                        TinkerLog.e("Tinker.SampleUncaughtExHandler", "have xposed: just clean tinker", new Object[0]);
                        ShareTinkerInternals.killAllOtherProcess(a.getApplication());
                        TinkerApplicationHelper.cleanPatch(a);
                        ShareTinkerInternals.setTinkerDisableWithSharedPreferences(a.getApplication());
                        AppMethodBeat.o(12923);
                        return;
                    }
                }
                th = th.getCause();
            }
            AppMethodBeat.o(12923);
        }
    }

    private boolean a() {
        AppMethodBeat.i(12924, false);
        ApplicationLike a = d.a();
        if (a == null || a.getApplication() == null) {
            AppMethodBeat.o(12924);
            return false;
        } else if (!TinkerApplicationHelper.isTinkerLoadSuccess(a)) {
            AppMethodBeat.o(12924);
            return false;
        } else {
            if (SystemClock.elapsedRealtime() - a.getApplicationStartElapsedTime() < JobInfo.MIN_BACKOFF_MILLIS) {
                String currentVersion = TinkerApplicationHelper.getCurrentVersion(a);
                if (ShareTinkerInternals.isNullOrNil(currentVersion)) {
                    AppMethodBeat.o(12924);
                    return false;
                }
                SharedPreferences sharedPreferences = a.getApplication().getSharedPreferences(ShareConstants.TINKER_SHARE_PREFERENCE_CONFIG, 4);
                int i = sharedPreferences.getInt(currentVersion, 0);
                if (i >= 3) {
                    e.e();
                    TinkerApplicationHelper.cleanPatch(a);
                    TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash more than %d, we just clean patch!", Integer.valueOf(i));
                    AppMethodBeat.o(12924);
                    return true;
                }
                int i2 = i + 1;
                sharedPreferences.edit().putInt(currentVersion, i2).commit();
                TinkerLog.e("Tinker.SampleUncaughtExHandler", "tinker has fast crash %d times", Integer.valueOf(i2));
            }
            AppMethodBeat.o(12924);
            return false;
        }
    }
}
