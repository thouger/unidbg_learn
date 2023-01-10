package cn.missfresh.module.base.support.modelsupport;

import cn.missfresh.module.base.api.CSLineApiManager;
import cn.missfresh.module.base.bean.CSLineBean;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.mvp.mvp.impl.MVPModel;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.HashMap;

public class CSLineModel extends MVPModel {
    private CSLineBean a;

    public CSLineBean a() {
        return this.a;
    }

    public void a(String str, String str2, boolean z, IModel.a aVar) {
        AppMethodBeat.i(21939, false);
        HashMap hashMap = new HashMap();
        hashMap.put("user_id", str);
        if (!b.a(str2)) {
            hashMap.put("order_no", str2);
        }
        hashMap.put("ab", z ? "a" : "b");
        RequestParam requestParam = new RequestParam();
        requestParam.setParam(hashMap);
        a(CSLineApiManager.getCSLineApi().inLine(requestParam), new AnonymousClass1(aVar), aVar.c());
        AppMethodBeat.o(21939);
    }

    /* renamed from: cn.missfresh.module.base.support.modelsupport.CSLineModel$1  reason: invalid class name */
    class AnonymousClass1 extends j<CSLineBean> {
        final /* synthetic */ IModel.a a;

        AnonymousClass1(IModel.a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(21937, false);
            a((CSLineBean) obj);
            AppMethodBeat.o(21937);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(21934, false);
            this.a.a(i, str);
            AppMethodBeat.o(21934);
        }

        public void a(CSLineBean cSLineBean) {
            AppMethodBeat.i(21935, false);
            CSLineModel.this.a = cSLineBean;
            this.a.a();
            AppMethodBeat.o(21935);
        }
    }
}
