package cn.missfresh.module.base.support.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class NewEnsureDialog$5 implements View.OnClickListener {
    final /* synthetic */ View.OnClickListener a;
    final /* synthetic */ h b;

    NewEnsureDialog$5(h hVar, View.OnClickListener onClickListener) {
        this.b = hVar;
        this.a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21579, false);
        View.OnClickListener onClickListener = this.a;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        if (this.b.b) {
            this.b.dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21579);
    }
}
