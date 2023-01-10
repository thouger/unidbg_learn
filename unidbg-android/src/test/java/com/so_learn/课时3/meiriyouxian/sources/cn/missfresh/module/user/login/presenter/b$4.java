package cn.missfresh.module.user.login.presenter;

import cn.missfresh.module.base.network.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: LoginPresenter */
class b$4 extends j<Object> {
    final /* synthetic */ b a;

    b$4(b bVar) {
        this.a = bVar;
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onSuccess(Object obj) {
        AppMethodBeat.i(8015, false);
        b.a(this.a).a(0, "");
        AppMethodBeat.o(8015);
    }

    @Override // cn.missfresh.module.base.network.j
    public void a(int i, String str) {
        AppMethodBeat.i(8019, false);
        if (i == 4003) {
            b.a(this.a).a(str);
        } else {
            b.a(this.a).a(i, str);
        }
        AppMethodBeat.o(8019);
    }
}
