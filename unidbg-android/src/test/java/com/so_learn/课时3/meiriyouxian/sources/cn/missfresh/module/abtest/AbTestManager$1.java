package cn.missfresh.module.abtest;

import cn.missfresh.basiclib.thread.b.a;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class AbTestManager$1 extends a {
    final /* synthetic */ AbTestManager this$0;

    AbTestManager$1(AbTestManager abTestManager) {
        this.this$0 = abTestManager;
    }

    @Override // cn.missfresh.basiclib.thread.b.a
    public void runInTryCatch() {
        AppMethodBeat.i(22094, false);
        o.a(AbTestManager.access$000(this.this$0));
        AppMethodBeat.o(22094);
    }
}
