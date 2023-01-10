package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$11 implements View.OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ View.OnClickListener b;

    DialogUtils$11(c cVar, View.OnClickListener onClickListener) {
        this.a = cVar;
        this.b = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21101, false);
        this.a.b();
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21101);
    }
}
