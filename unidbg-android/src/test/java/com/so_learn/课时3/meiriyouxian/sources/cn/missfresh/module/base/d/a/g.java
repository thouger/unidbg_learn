package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: PaySuccessScmStrategy */
public class g extends c {
    @Override // cn.missfresh.module.base.datastatistics.a.b.a
    public boolean b() {
        return true;
    }

    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19064, false);
        if ("pay_success".equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            if (str.hashCode() == 883937877 && str.equals("page_view")) {
                c = 0;
            }
            if (c == 0) {
                AppMethodBeat.o(19064);
                return true;
            }
        }
        AppMethodBeat.o(19064);
        return false;
    }
}
