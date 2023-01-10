package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$5 extends m {
    final /* synthetic */ boolean a;
    final /* synthetic */ a b;

    a$5(a aVar, boolean z) {
        this.b = aVar;
        this.a = z;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(4165, false);
        super.a(i);
        if (!this.a) {
            a.a(this.b).d(this.a);
        }
        AppMethodBeat.o(4165);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(4166, false);
        super.a(request, exc);
        if (!this.a) {
            a.a(this.b).d(this.a);
        }
        AppMethodBeat.o(4166);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(4168, false);
        super.a(str);
        a.c(this.b).a(str);
        if (!this.a) {
            a.a(this.b).c(this.a);
        }
        AppMethodBeat.o(4168);
    }
}
