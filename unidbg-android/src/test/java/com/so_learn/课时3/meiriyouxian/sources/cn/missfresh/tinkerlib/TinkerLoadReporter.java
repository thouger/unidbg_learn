package cn.missfresh.tinkerlib;

import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import java.io.File;

public class TinkerLoadReporter extends DefaultLoadReporter {
    public TinkerLoadReporter(Context context) {
        super(context);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadPatchListenerReceiveFail(File file, int i) {
        AppMethodBeat.i(12800, false);
        super.onLoadPatchListenerReceiveFail(file, i);
        e.a(i);
        TinkerLog.e("Tinker.TinkerLoadReporter", "\u8865\u4e01\u6821\u9a8c\u5931\u8d25  :patchFile==" + file + "      errorCode=" + i, new Object[0]);
        AppMethodBeat.o(12800);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadResult(File file, int i, long j) {
        AppMethodBeat.i(12803, false);
        super.onLoadResult(file, i, j);
        TinkerLog.i("Tinker.TinkerLoadReporter", "onLoadResult\u56de\u8c03 :rstCode==" + i + "     cost==" + j, new Object[0]);
        if (i == 0) {
            e.a(j);
        }
        Looper.getMainLooper();
        Looper.myQueue().addIdleHandler(new AnonymousClass1());
        AppMethodBeat.o(12803);
    }

    /* renamed from: cn.missfresh.tinkerlib.TinkerLoadReporter$1  reason: invalid class name */
    class AnonymousClass1 implements MessageQueue.IdleHandler {
        AnonymousClass1() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            AppMethodBeat.i(12795, false);
            if (UpgradePatchRetry.getInstance(TinkerLoadReporter.this.context).onPatchRetryLoad()) {
                e.g();
            }
            AppMethodBeat.o(12795);
            return false;
        }
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadException(Throwable th, int i) {
        AppMethodBeat.i(12806, false);
        super.onLoadException(th, i);
        e.a(th, i);
        AppMethodBeat.o(12806);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadFileMd5Mismatch(File file, int i) {
        AppMethodBeat.i(12809, false);
        super.onLoadFileMd5Mismatch(file, i);
        e.d(i);
        AppMethodBeat.o(12809);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadFileNotFound(File file, int i, boolean z) {
        AppMethodBeat.i(12811, false);
        super.onLoadFileNotFound(file, i, z);
        e.c(i);
        AppMethodBeat.o(12811);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadPackageCheckFail(File file, int i) {
        AppMethodBeat.i(12812, false);
        super.onLoadPackageCheckFail(file, i);
        e.b(i);
        TinkerLog.e("Tinker.TinkerLoadReporter", "LoadPackageCheckFail.errorCode==" + i, new Object[0]);
        AppMethodBeat.o(12812);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadPatchInfoCorrupted(String str, String str2, File file) {
        AppMethodBeat.i(12814, false);
        super.onLoadPatchInfoCorrupted(str, str2, file);
        e.a();
        AppMethodBeat.o(12814);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadInterpret(int i, Throwable th) {
        AppMethodBeat.i(12817, false);
        super.onLoadInterpret(i, th);
        e.a(i, th);
        AppMethodBeat.o(12817);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultLoadReporter, com.tencent.tinker.lib.reporter.LoadReporter
    public void onLoadPatchVersionChanged(String str, String str2, File file, String str3) {
        AppMethodBeat.i(12820, false);
        super.onLoadPatchVersionChanged(str, str2, file, str3);
        AppMethodBeat.o(12820);
    }
}
