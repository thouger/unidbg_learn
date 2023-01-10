package cn.missfresh.module.user.login.presenter;

import cn.missfresh.module.base.network.j;
import cn.missfresh.module.user.login.view.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class MinePresenter$2 extends j<Object> {
    final /* synthetic */ MinePresenter a;

    MinePresenter$2(MinePresenter minePresenter) {
        this.a = minePresenter;
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onSuccess(Object obj) {
        AppMethodBeat.i(8174, false);
        MinePresenter.b(this.a).a(obj);
        AppMethodBeat.o(8174);
    }

    @Override // cn.missfresh.module.base.network.j
    public void a(int i, String str) {
        AppMethodBeat.i(8178, false);
        if (MinePresenter.b(this.a) instanceof a) {
            MinePresenter.b(this.a).a(i, str);
        }
        AppMethodBeat.o(8178);
    }
}
