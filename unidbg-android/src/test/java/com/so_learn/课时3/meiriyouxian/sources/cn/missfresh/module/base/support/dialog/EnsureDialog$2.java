package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class EnsureDialog$2 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ f b;

    EnsureDialog$2(f fVar, View.OnClickListener onClickListener) {
        this.b = fVar;
        this.a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21363, false);
        View.OnClickListener onClickListener = this.a;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        if (this.b.b) {
            this.b.dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21363);
    }
}
