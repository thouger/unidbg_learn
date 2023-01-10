package cn.missfresh.module.base.common.resourcemanager;

import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy;
import cn.missfresh.module.base.common.resourcemanager.strategy.b;
import cn.missfresh.module.base.common.resourcemanager.strategy.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ResourceManager */
public class a {
    private List<IResourceStrategy> a;

    /* compiled from: ResourceManager */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.module.base.common.resourcemanager.a$a  reason: collision with other inner class name */
    public static class C0023a {
        private static final a a = new a();

        static {
            AppMethodBeat.i(12280, false);
            AppMethodBeat.o(12280);
        }
    }

    public static a a() {
        AppMethodBeat.i(12283, false);
        a aVar = C0023a.a;
        AppMethodBeat.o(12283);
        return aVar;
    }

    private a() {
        AppMethodBeat.i(12284, false);
        this.a = new ArrayList();
        this.a.add(new b());
        this.a.add(new c());
        this.a.add(new cn.missfresh.module.base.common.resourcemanager.strategy.a());
        AppMethodBeat.o(12284);
    }

    public void a(SkipBean skipBean, Map<String, Object> map) {
        AppMethodBeat.i(12285, false);
        if (skipBean == null) {
            AppMethodBeat.o(12285);
            return;
        }
        for (IResourceStrategy iResourceStrategy : this.a) {
            iResourceStrategy.skip(skipBean, map);
        }
        AppMethodBeat.o(12285);
    }
}
