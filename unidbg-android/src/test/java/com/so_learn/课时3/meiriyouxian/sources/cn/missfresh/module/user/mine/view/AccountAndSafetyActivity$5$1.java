package cn.missfresh.module.user.mine.view;

import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.module.user.mine.view.AccountAndSafetyActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

class AccountAndSafetyActivity$5$1 extends i<String> {
    final /* synthetic */ AccountAndSafetyActivity.5 a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AccountAndSafetyActivity$5$1(AccountAndSafetyActivity.5 r1, a aVar) {
        super(aVar);
        this.a = r1;
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(9258, false);
        a((String) obj);
        AppMethodBeat.o(9258);
    }

    public void a(String str) {
        AppMethodBeat.i(9250, false);
        cn.missfresh.module.user.login.b.a.a().a(2);
        AccountAndSafetyActivity.f(this.a.a);
        AppMethodBeat.o(9250);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        AppMethodBeat.i(9253, false);
        AccountAndSafetyActivity.g(this.a.a);
        AppMethodBeat.o(9253);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.i
    public void a(int i, String str) {
        AppMethodBeat.i(9256, false);
        if (b.a(str)) {
            str = "\u64cd\u4f5c\u5931\u8d25\u8bf7\u91cd\u8bd5";
        }
        cn.missfresh.ui.a.a.b(str);
        AppMethodBeat.o(9256);
    }
}
