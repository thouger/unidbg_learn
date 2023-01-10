package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$4 extends m {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(4156, false);
        super.a(i);
        a.a(this.a).m();
        AppMethodBeat.o(4156);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(4160, false);
        super.a(request, exc);
        a.a(this.a).m();
        AppMethodBeat.o(4160);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(4161, false);
        super.a(str);
        a.a(this.a).r();
        AppMethodBeat.o(4161);
    }
}
