package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: CartScmStrategy */
public class d extends c {
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19043, false);
        if (SkipBean.Type.cart.equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            int hashCode = str.hashCode();
            if (hashCode != -1569181319) {
                if (hashCode != 606298091) {
                    if (hashCode == 883937877 && str.equals("page_view")) {
                        c = 0;
                    }
                } else if (str.equals("click_settleAccount")) {
                    c = 1;
                }
            } else if (str.equals("click_settleAccount_gio")) {
                c = 2;
            }
            if (c == 0 || c == 1 || c == 2) {
                AppMethodBeat.o(19043);
                return true;
            }
        }
        AppMethodBeat.o(19043);
        return false;
    }
}
