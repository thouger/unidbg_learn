package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class NewEnsureDialog$7 implements View.OnClickListener {
    final /* synthetic */ h a;

    NewEnsureDialog$7(h hVar) {
        this.a = hVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21585, false);
        if (this.a.b) {
            this.a.dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21585);
    }
}
