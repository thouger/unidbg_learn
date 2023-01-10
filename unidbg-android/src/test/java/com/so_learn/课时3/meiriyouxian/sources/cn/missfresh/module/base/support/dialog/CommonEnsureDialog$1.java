package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class CommonEnsureDialog$1 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ d b;

    CommonEnsureDialog$1(d dVar, View.OnClickListener onClickListener) {
        this.b = dVar;
        this.a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(20998, false);
        View.OnClickListener onClickListener = this.a;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        this.b.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(20998);
    }
}
