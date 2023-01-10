package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$23 implements View.OnClickListener {
    final /* synthetic */ ShareDialog.a a;

    DialogUtils$23(ShareDialog.a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21132, false);
        this.a.onShareClick(4);
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21132);
    }
}
