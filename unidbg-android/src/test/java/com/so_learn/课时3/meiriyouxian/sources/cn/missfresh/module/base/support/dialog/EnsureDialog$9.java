package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class EnsureDialog$9 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ f b;

    EnsureDialog$9(f fVar, View.OnClickListener onClickListener) {
        this.b = fVar;
        this.a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21404, false);
        View.OnClickListener onClickListener = this.a;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        if (this.b.b) {
            this.b.dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21404);
    }
}
