package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$2 implements View.OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ e.a b;

    DialogUtils$2(c cVar, e.a aVar) {
        this.a = cVar;
        this.b = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21120, false);
        this.a.b();
        this.b.a();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21120);
    }
}
