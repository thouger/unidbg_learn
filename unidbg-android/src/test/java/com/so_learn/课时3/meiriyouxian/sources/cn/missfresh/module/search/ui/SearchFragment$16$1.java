package cn.missfresh.module.search.ui;

import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.search.a.c;
import cn.missfresh.module.search.ui.SearchFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class SearchFragment$16$1 implements BaseTipDialog.a {
    final /* synthetic */ SearchFragment.16 a;

    SearchFragment$16$1(SearchFragment.16 r1) {
        this.a = r1;
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
    public void a(int i, Object obj) {
        AppMethodBeat.i(11845, false);
        this.a.a.D.h();
        c.b();
        this.a.a.k.setVisibility(8);
        AppMethodBeat.o(11845);
    }
}
