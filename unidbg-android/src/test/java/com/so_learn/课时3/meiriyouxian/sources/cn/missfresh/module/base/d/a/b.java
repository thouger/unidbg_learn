package cn.missfresh.module.base.d.a;

import cn.missfresh.module.base.datastatistics.a.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.api.push.pushselfshow.click.SelfShowType;

/* compiled from: AppScmStrategy */
public class b extends c {
    @Override // cn.missfresh.module.base.d.a.c, cn.missfresh.module.base.datastatistics.a.b.a
    public boolean a(a aVar) {
        AppMethodBeat.i(19029, false);
        if (SelfShowType.PUSH_CMD_APP.equals(aVar.b)) {
            String str = aVar.a;
            char c = '\uffff';
            if (str.hashCode() == -1617600076 && str.equals("click_navigation_cart")) {
                c = 0;
            }
            if (c == 0) {
                AppMethodBeat.o(19029);
                return true;
            }
        }
        AppMethodBeat.o(19029);
        return false;
    }
}
