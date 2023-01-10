package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class CommonEnsureDialog$2 implements View.OnClickListener {
    final /* synthetic */ d a;

    CommonEnsureDialog$2(d dVar) {
        this.a = dVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21003, false);
        this.a.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21003);
    }
}
