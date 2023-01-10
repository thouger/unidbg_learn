package cn.missfresh.module.base.common.b;

import android.app.Application;
import cn.missfresh.module.base.common.config.a.a;
import cn.missfresh.module.base.common.resourcemanager.api.RMApiManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: BaseAppdelegate */
public class c extends b {
    @Override // cn.missfresh.module.base.common.b.b, cn.missfresh.module.base.common.b.d
    public void a(Application application, a aVar) {
        AppMethodBeat.i(10289, false);
        super.a(application, aVar);
        AppMethodBeat.o(10289);
    }

    @Override // cn.missfresh.module.base.common.b.b, cn.missfresh.module.base.common.b.d
    public void a() {
        AppMethodBeat.i(10292, false);
        super.a();
        RMApiManager.changeRMApi(e.Q());
        AppMethodBeat.o(10292);
    }
}
