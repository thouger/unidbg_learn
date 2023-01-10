package cn.missfresh.module.base.c;

import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.risk.h;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RiskInitCallbackImp */
public class a implements h.a {
    @Override // cn.missfresh.risk.h.a
    public void a() {
    }

    @Override // cn.missfresh.risk.h.a
    public void b() {
        AppMethodBeat.i(18994, false);
        StatisticsManager.Z("init", "collectionType", 1, "appsCollectionType", Boolean.valueOf(h.c()));
        AppMethodBeat.o(18994);
    }

    @Override // cn.missfresh.risk.h.a
    public void a(String str) {
        AppMethodBeat.i(18997, false);
        StatisticsManager.Z("init", "collectionType", 0, "appsCollectionType", Boolean.valueOf(h.c()), "appsStack", str);
        AppMethodBeat.o(18997);
    }
}
