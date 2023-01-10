package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SpecificationPopScmStrategy */
public class k extends c {
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19099, false);
        if ("select_specificationPop".equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            int hashCode = str.hashCode();
            if (hashCode != -777411721) {
                if (hashCode == 883937877 && str.equals("page_view")) {
                    c = 0;
                }
            } else if (str.equals("click_cart")) {
                c = 1;
            }
            if (c == 0 || c == 1) {
                AppMethodBeat.o(19099);
                return true;
            }
        }
        AppMethodBeat.o(19099);
        return false;
    }
}
