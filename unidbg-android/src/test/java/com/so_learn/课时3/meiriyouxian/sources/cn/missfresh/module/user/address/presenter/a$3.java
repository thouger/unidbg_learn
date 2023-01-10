package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import okhttp3.Request;

/* compiled from: EditAddressPresenter */
class a$3 extends m {
    final /* synthetic */ UserAddress a;
    final /* synthetic */ a b;

    a$3(a aVar, UserAddress userAddress) {
        this.b = aVar;
        this.a = userAddress;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(4143, false);
        super.a(i);
        String b = a.b(this.b);
        d.d(b, "save error and code=" + i);
        a.a(this.b).t();
        AppMethodBeat.o(4143);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(4146, false);
        super.a(request, exc);
        a.a(this.b).t();
        AppMethodBeat.o(4146);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(4148, false);
        super.a(str);
        a c = c(str);
        String str2 = b.a(c.b) ? "\u4fee\u6539\u5730\u5740\u5931\u8d25" : c.b;
        if (c.a == 0) {
            a.a(this.b).b(this.a, c.b);
        } else {
            a.a(this.b).b(str2);
        }
        AppMethodBeat.o(4148);
    }
}
