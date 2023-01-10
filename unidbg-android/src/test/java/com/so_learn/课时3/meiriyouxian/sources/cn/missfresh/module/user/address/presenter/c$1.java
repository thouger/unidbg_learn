package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.bean.TencentSuggestionAddress;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: SearchesAddressPresenter */
class c$1 implements q.c {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    @Override // cn.missfresh.module.base.manager.q.c
    public void a() {
        AppMethodBeat.i(4652, false);
        c.a(this.a).e();
        AppMethodBeat.o(4652);
    }

    @Override // cn.missfresh.module.base.manager.q.c
    public void a(String str, List<TencentSuggestionAddress> list) {
        AppMethodBeat.i(4655, false);
        if (c.a(this.a, str)) {
            c.b(this.a).a(list);
            c.a(this.a).b();
        }
        AppMethodBeat.o(4655);
    }

    @Override // cn.missfresh.module.base.manager.q.c
    public void a(String str) {
        AppMethodBeat.i(4658, false);
        if (c.a(this.a, str)) {
            c.b(this.a).b();
            c.a(this.a).c();
        }
        AppMethodBeat.o(4658);
    }
}
