package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$8 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ c b;

    DialogUtils$8(View.OnClickListener onClickListener, c cVar) {
        this.a = onClickListener;
        this.b = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21263, false);
        this.a.onClick(view);
        this.b.b();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21263);
    }
}
