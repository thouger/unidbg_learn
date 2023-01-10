package cn.missfresh.hotfix;

import android.app.Application;
import android.content.Intent;
import cn.missfresh.application.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tinkerlib.AbstractTinkerBaseApplicationLike;
import com.tencent.tinker.lib.service.AbstractResultService;

public class HotFixApplicationLike extends AbstractTinkerBaseApplicationLike {
    public HotFixApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    @Override // cn.missfresh.tinkerlib.AbstractTinkerBaseApplicationLike
    public void onContextAttached() {
        AppMethodBeat.i(3, false);
        b.a(getApplication());
        AppMethodBeat.o(3);
    }

    @Override // cn.missfresh.tinkerlib.AbstractTinkerBaseApplicationLike
    public Class<? extends AbstractResultService> getResultServiceClass() {
        return HotFixResultService.class;
    }
}
