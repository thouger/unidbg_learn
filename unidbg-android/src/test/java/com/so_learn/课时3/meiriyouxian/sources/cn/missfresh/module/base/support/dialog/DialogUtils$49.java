package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class DialogUtils$49 implements View.OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ View.OnClickListener b;

    DialogUtils$49(c cVar, View.OnClickListener onClickListener) {
        this.a = cVar;
        this.b = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21244, false);
        this.a.b();
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21244);
    }
}
