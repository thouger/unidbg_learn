package cn.missfresh.module.user.location;

import cn.missfresh.module.base.network.j;
import cn.missfresh.module.user.location.bean.DefaultAddressBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: DefaultAddressManager */
class a$5 extends j<DefaultAddressBean> {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public /* synthetic */ void onSuccess(Object obj) {
        AppMethodBeat.i(7327, false);
        a((DefaultAddressBean) obj);
        AppMethodBeat.o(7327);
    }

    public void a(DefaultAddressBean defaultAddressBean) {
        AppMethodBeat.i(7321, false);
        a.a(this.a, defaultAddressBean);
        AppMethodBeat.o(7321);
    }

    @Override // cn.missfresh.module.base.network.j
    public void a(int i, String str) {
        AppMethodBeat.i(7324, false);
        a.a(this.a, (DefaultAddressBean) null);
        a aVar = this.a;
        a.a(aVar, 12, "requestDefalutAddressByLocation message=" + str + ",code=" + i);
        AppMethodBeat.o(7324);
    }
}
