package cn.missfresh.tinkerlib;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public abstract class AbstractHotFixManager {
    public a a;
    b b = new AnonymousClass1();
    private Context c;

    public abstract void a(String str, b bVar);

    public AbstractHotFixManager(Context context) {
        this.c = context;
    }

    /* renamed from: cn.missfresh.tinkerlib.AbstractHotFixManager$1  reason: invalid class name */
    class AnonymousClass1 implements b {
        @Override // cn.missfresh.tinkerlib.b
        public void b(String str) {
        }

        AnonymousClass1() {
        }

        @Override // cn.missfresh.tinkerlib.b
        public void a(String str) {
            AppMethodBeat.i(12780, false);
            TinkerInstaller.onReceiveUpgradePatch(AbstractHotFixManager.this.c, str);
            AppMethodBeat.o(12780);
        }
    }

    public void a(String str, a aVar) {
        if (!str.equals(g.a(this.c, Process.myPid())) || aVar == null || TextUtils.isEmpty(aVar.a) || TextUtils.isEmpty(aVar.b)) {
            return;
        }
        if (aVar.d) {
            Tinker.with(this.c).cleanPatch();
            Log.d("Tinker", "RollBack Hotfix: " + aVar.toString());
        } else if (this.a == null || !aVar.b.equals(this.a.b)) {
            String a = a();
            if (TextUtils.isEmpty(a) || !a.equals(aVar.b)) {
                this.a = aVar;
                a(aVar.a, this.b);
                return;
            }
            Log.d("Tinker", "Current patch version: " + a);
        } else if (aVar.c != this.a.c) {
            this.a.c = aVar.c;
        }
    }

    public void a(PatchResult patchResult) {
        this.a = null;
    }

    public String a() {
        return g.a(this.c, "patchVersion");
    }
}
