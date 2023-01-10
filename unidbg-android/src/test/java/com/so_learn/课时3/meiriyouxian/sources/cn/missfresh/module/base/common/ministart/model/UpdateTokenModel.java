package cn.missfresh.module.base.common.ministart.model;

import cn.missfresh.basiclib.net.c;
import cn.missfresh.module.base.common.ministart.a.a;
import cn.missfresh.module.base.common.ministart.api.MiniStartApiManager;
import cn.missfresh.module.base.common.ministart.bean.RefreshTokenBean;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.mvp.mvp.impl.MVPModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class UpdateTokenModel extends MVPModel {
    public void a(a.b bVar) {
        AppMethodBeat.i(12217, false);
        MiniStartApiManager.getMiniStartApi().requestRefreshToken().a(cn.missfresh.basiclib.net.e.a.a).subscribe(new c(new AnonymousClass1(bVar)));
        AppMethodBeat.o(12217);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.ministart.model.UpdateTokenModel$1  reason: invalid class name */
    public class AnonymousClass1 extends j<RefreshTokenBean> {
        final /* synthetic */ a.b a;

        AnonymousClass1(a.b bVar) {
            this.a = bVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(12214, false);
            a((RefreshTokenBean) obj);
            AppMethodBeat.o(12214);
        }

        public void a(RefreshTokenBean refreshTokenBean) {
            AppMethodBeat.i(12210, false);
            this.a.a(refreshTokenBean);
            AppMethodBeat.o(12210);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(12212, false);
            this.a.a(i, str);
            AppMethodBeat.o(12212);
        }
    }
}
