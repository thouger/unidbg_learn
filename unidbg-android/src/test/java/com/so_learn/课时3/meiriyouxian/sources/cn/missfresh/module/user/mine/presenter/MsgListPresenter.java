package cn.missfresh.module.user.mine.presenter;

import cn.missfresh.module.mvp.mvp.impl.MVPPresenter;
import cn.missfresh.module.user.mine.model.MsgListModule;
import cn.missfresh.module.user.mine.view.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MsgListPresenter extends MVPPresenter<b> {
    private MsgListModule a = new MsgListModule();

    public MsgListPresenter(b bVar) {
        super(bVar);
        AppMethodBeat.i(8980, false);
        AppMethodBeat.o(8980);
    }

    /* renamed from: cn.missfresh.module.user.mine.presenter.MsgListPresenter$1  reason: invalid class name */
    class AnonymousClass1 extends MVPPresenter<b>.a {
        public void b() {
        }

        AnonymousClass1() {
            super(MsgListPresenter.this);
        }

        public void a() {
            AppMethodBeat.i(8972, false);
            b i = MsgListPresenter.this.i();
            if (i != null) {
                i.a(MsgListPresenter.this.a.a(), MsgListPresenter.this.a.b());
            }
            AppMethodBeat.o(8972);
        }

        public void a(int i, String str) {
            AppMethodBeat.i(8975, false);
            b i2 = MsgListPresenter.this.i();
            if (i2 != null) {
                i2.a(i, str);
            }
            AppMethodBeat.o(8975);
        }
    }

    public void a(int i, int i2) {
        AppMethodBeat.i(8983, false);
        this.a.a(i, i2, new AnonymousClass1());
        AppMethodBeat.o(8983);
    }
}
