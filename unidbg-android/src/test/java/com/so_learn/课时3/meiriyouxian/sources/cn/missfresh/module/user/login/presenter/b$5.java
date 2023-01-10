package cn.missfresh.module.user.login.presenter;

import cn.missfresh.module.base.utils.ac;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.disposables.b;
import io.reactivex.v;

/* compiled from: LoginPresenter */
class b$5 implements v<Long> {
    final /* synthetic */ b a;

    b$5(b bVar) {
        this.a = bVar;
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(8047, false);
        a((Long) obj);
        AppMethodBeat.o(8047);
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        AppMethodBeat.i(8027, false);
        b.a(this.a, bVar);
        AppMethodBeat.o(8027);
    }

    public void a(Long l) {
        AppMethodBeat.i(8034, false);
        if (l != null) {
            b.a(this.a, false);
            b bVar = this.a;
            b.a(bVar, String.format(b.g(bVar), Long.valueOf(l.longValue() / 1000)));
            b.i(this.a).setText(b.h(this.a));
            if (l.longValue() / 1000 <= 45 && b.j(this.a).getVisibility() != 0) {
                b.j(this.a).setVisibility(0);
            }
        }
        AppMethodBeat.o(8034);
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        AppMethodBeat.i(8037, false);
        ac.a("\u7528\u6237\u6a21\u5757", "\u83b7\u53d6\u9a8c\u8bc1\u7801\u5931\u8d25", "error:" + th.getMessage());
        AppMethodBeat.o(8037);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        AppMethodBeat.i(8042, false);
        b.k(this.a);
        AppMethodBeat.o(8042);
    }
}
