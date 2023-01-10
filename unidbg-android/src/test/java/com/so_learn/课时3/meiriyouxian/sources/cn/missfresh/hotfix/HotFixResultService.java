package cn.missfresh.hotfix;

import cn.missfresh.module.base.a.a;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tinkerlib.AbstractTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;

public class HotFixResultService extends AbstractTinkerResultService {
    @Override // cn.missfresh.tinkerlib.AbstractTinkerResultService
    public void a(PatchResult patchResult) {
        AppMethodBeat.i(1, false);
        a.a(getApplicationContext()).a(patchResult);
        AppMethodBeat.o(1);
    }

    @Override // cn.missfresh.tinkerlib.AbstractTinkerResultService
    public boolean a() {
        AppMethodBeat.i(2, false);
        boolean e = e.e();
        AppMethodBeat.o(2);
        return e;
    }
}
