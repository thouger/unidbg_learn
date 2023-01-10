package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$20 implements View.OnClickListener {
    final /* synthetic */ ShareDialog.a a;

    DialogUtils$20(ShareDialog.a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21123, false);
        this.a.onShareClick(1);
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21123);
    }
}
