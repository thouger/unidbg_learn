package cn.missfresh.module.base.order.widget;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class RemindEnsureDialog$1 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ c b;

    RemindEnsureDialog$1(c cVar, View.OnClickListener onClickListener) {
        this.b = cVar;
        this.a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(16331, false);
        View.OnClickListener onClickListener = this.a;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        this.b.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(16331);
    }
}
