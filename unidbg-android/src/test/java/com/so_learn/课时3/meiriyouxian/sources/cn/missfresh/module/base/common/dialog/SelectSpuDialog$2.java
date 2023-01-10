package cn.missfresh.module.base.common.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSpuDialog$2 implements View.OnClickListener {
    final /* synthetic */ b a;

    SelectSpuDialog$2(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11399, false);
        this.a.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11399);
    }
}
