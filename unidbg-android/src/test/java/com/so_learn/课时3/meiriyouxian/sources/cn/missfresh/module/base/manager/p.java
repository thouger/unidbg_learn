package cn.missfresh.module.base.manager;

import cn.missfresh.module.base.bean.TabBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: TabBarManager */
public class p {
    private static p a;
    private a b;
    private List<TabBean> c;

    /* compiled from: TabBarManager */
    public interface a {
        void a(List<TabBean> list);
    }

    private p() {
    }

    public static p a() {
        AppMethodBeat.i(14609, false);
        if (a == null) {
            a = new p();
        }
        p pVar = a;
        AppMethodBeat.o(14609);
        return pVar;
    }

    public void b() {
        AppMethodBeat.i(14612, false);
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(this.c);
        }
        AppMethodBeat.o(14612);
    }

    public TabBean a(String str) {
        AppMethodBeat.i(14613, false);
        List<TabBean> list = this.c;
        if (list != null) {
            for (TabBean tabBean : list) {
                if (tabBean.tag.equals(str)) {
                    AppMethodBeat.o(14613);
                    return tabBean;
                }
            }
        }
        AppMethodBeat.o(14613);
        return null;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void c() {
        this.b = null;
    }
}
