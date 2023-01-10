package cn.missfresh.tinkerlib;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tinkerlib.g;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import java.io.File;

public abstract class AbstractTinkerResultService extends DefaultTinkerResultService {
    public abstract void a(PatchResult patchResult);

    public abstract boolean a();

    @Override // com.tencent.tinker.lib.service.DefaultTinkerResultService, com.tencent.tinker.lib.service.AbstractResultService
    public void onPatchResult(PatchResult patchResult) {
        if (patchResult == null) {
            TinkerLog.e("Tinker.AbstractTinkerResultService", "AbstractTinkerResultService received null result!!!!", new Object[0]);
            return;
        }
        TinkerLog.i("Tinker.AbstractTinkerResultService", "AbstractTinkerResultService receive result: %s", patchResult.toString());
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());
        new Handler(Looper.getMainLooper()).post(new AnonymousClass1(patchResult));
        if (patchResult.isSuccess) {
            deleteRawPatchFile(new File(patchResult.rawPatchFilePath));
            if (!checkIfNeedKill(patchResult)) {
                TinkerLog.i("Tinker.AbstractTinkerResultService", "I have already install the newly patch version!", new Object[0]);
            } else if (g.b()) {
                TinkerLog.i("Tinker.AbstractTinkerResultService", "it is in background, just restart process", new Object[0]);
                b();
            } else {
                TinkerLog.i("Tinker.AbstractTinkerResultService", "tinker wait screen to restart process", new Object[0]);
                new g.a(getApplicationContext(), new AnonymousClass2());
            }
        }
    }

    /* renamed from: cn.missfresh.tinkerlib.AbstractTinkerResultService$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ PatchResult a;

        AnonymousClass1(PatchResult patchResult) {
            this.a = patchResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(12785, false);
            AbstractTinkerResultService.this.a(this.a);
            if (AbstractTinkerResultService.this.a()) {
                if (this.a.isSuccess) {
                    Toast.makeText(AbstractTinkerResultService.this.getApplicationContext(), "patch success, please restart process", 1).show();
                } else {
                    Toast.makeText(AbstractTinkerResultService.this.getApplicationContext(), "patch fail, please check reason", 1).show();
                }
            }
            AppMethodBeat.o(12785);
        }
    }

    /* renamed from: cn.missfresh.tinkerlib.AbstractTinkerResultService$2  reason: invalid class name */
    class AnonymousClass2 implements g.a.AbstractC0055a {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.tinkerlib.g.a.AbstractC0055a
        public void a() {
            AppMethodBeat.i(12787, false);
            AbstractTinkerResultService.this.b();
            AppMethodBeat.o(12787);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        TinkerLog.i("Tinker.AbstractTinkerResultService", "app is background now, i can kill quietly", new Object[0]);
        Process.killProcess(Process.myPid());
    }
}
