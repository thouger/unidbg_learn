package cn.missfresh.tinkerlib;

import android.content.Context;
import android.content.Intent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import java.io.File;
import java.util.List;

public class TinkerPatchReporter extends DefaultPatchReporter {
    public TinkerPatchReporter(Context context) {
        super(context);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchServiceStart(Intent intent) {
        AppMethodBeat.i(12859, false);
        super.onPatchServiceStart(intent);
        e.b();
        TinkerLog.i("Tinker.TinkerPatchReporter", "patch\u8fdb\u7a0b\u542f\u52a8.intent==" + intent, new Object[0]);
        AppMethodBeat.o(12859);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchDexOptFail(File file, List<File> list, Throwable th) {
        AppMethodBeat.i(12860, false);
        super.onPatchDexOptFail(file, list, th);
        e.a(th);
        AppMethodBeat.o(12860);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchException(File file, Throwable th) {
        AppMethodBeat.i(12861, false);
        super.onPatchException(file, th);
        e.b(th);
        AppMethodBeat.o(12861);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchInfoCorrupted(File file, String str, String str2) {
        AppMethodBeat.i(12862, false);
        super.onPatchInfoCorrupted(file, str, str2);
        e.c();
        AppMethodBeat.o(12862);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchPackageCheckFail(File file, int i) {
        AppMethodBeat.i(12864, false);
        super.onPatchPackageCheckFail(file, i);
        e.f(i);
        AppMethodBeat.o(12864);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchResult(File file, boolean z, long j) {
        AppMethodBeat.i(12866, false);
        super.onPatchResult(file, z, j);
        e.a(j, z);
        AppMethodBeat.o(12866);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchTypeExtractFail(File file, File file2, String str, int i) {
        AppMethodBeat.i(12868, false);
        super.onPatchTypeExtractFail(file, file2, str, i);
        e.e(i);
        AppMethodBeat.o(12868);
    }

    @Override // com.tencent.tinker.lib.reporter.DefaultPatchReporter, com.tencent.tinker.lib.reporter.PatchReporter
    public void onPatchVersionCheckFail(File file, SharePatchInfo sharePatchInfo, String str) {
        AppMethodBeat.i(12869, false);
        super.onPatchVersionCheckFail(file, sharePatchInfo, str);
        e.d();
        AppMethodBeat.o(12869);
    }
}
