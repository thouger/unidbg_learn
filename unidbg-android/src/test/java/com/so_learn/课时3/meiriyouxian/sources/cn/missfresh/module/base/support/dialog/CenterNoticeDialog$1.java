package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class CenterNoticeDialog$1 implements View.OnClickListener {
    final /* synthetic */ c a;

    CenterNoticeDialog$1(c cVar) {
        this.a = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(20732, false);
        this.a.h.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(20732);
    }
}
