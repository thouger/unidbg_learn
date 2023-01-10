package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class DialogUtils$39 implements View.OnClickListener {
    final /* synthetic */ e.b a;
    final /* synthetic */ c b;

    DialogUtils$39(e.b bVar, c cVar) {
        this.a = bVar;
        this.b = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21203, false);
        this.a.a();
        this.b.b();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21203);
    }
}
