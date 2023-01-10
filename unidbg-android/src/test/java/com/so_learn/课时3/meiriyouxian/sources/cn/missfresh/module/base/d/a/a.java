package cn.missfresh.module.base.d.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AddToBuyPopScmStrategy */
public class a extends c {
    List<String> a = new ArrayList();

    public a() {
        AppMethodBeat.i(19017, false);
        this.a.add("exposure_addToBuyPop");
        AppMethodBeat.o(19017);
    }

    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        AppMethodBeat.i(19021, false);
        if ("order".equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            int hashCode = str.hashCode();
            if (hashCode != -227275569) {
                if (hashCode == 353657891 && str.equals("click_giveUp")) {
                    c = 1;
                }
            } else if (str.equals("exposure_addToBuyPop")) {
                c = 0;
            }
            if (c == 0 || c == 1) {
                AppMethodBeat.o(19021);
                return true;
            }
        }
        AppMethodBeat.o(19021);
        return false;
    }

    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public void b(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        char c = 0;
        AppMethodBeat.i(19023, false);
        String str = aVar.a;
        if (str.hashCode() != -227275569 || !str.equals("exposure_addToBuyPop")) {
            c = '\uffff';
        }
        if (c == 0) {
            aVar.c = "addToBuyPop";
        }
        super.b(aVar);
        AppMethodBeat.o(19023);
    }

    @Override // cn.missfresh.module.base.d.a.c
    public List<String> c() {
        return this.a;
    }
}
