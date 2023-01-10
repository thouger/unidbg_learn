package cn.missfresh.module.search.b;

import android.app.Application;
import cn.missfresh.module.base.common.b.b;
import cn.missfresh.module.base.common.config.a.a;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SearchAppdelegate */
public class c extends b {
    @Override // cn.missfresh.module.base.common.b.b, cn.missfresh.module.base.common.b.d
    public void a(Application application, a aVar) {
        AppMethodBeat.i(11760, false);
        super.a(application, aVar);
        AppMethodBeat.o(11760);
    }

    @Override // cn.missfresh.module.base.common.b.b, cn.missfresh.module.base.common.b.d
    public void a() {
        AppMethodBeat.i(11762, false);
        super.a();
        b.a(e.Q());
        AppMethodBeat.o(11762);
    }
}
