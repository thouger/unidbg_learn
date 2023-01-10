package cn.missfresh.module.base.common.dialog;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSkuDialog$2 implements View.OnClickListener {
    final /* synthetic */ a a;

    SelectSkuDialog$2(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11308, false);
        this.a.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11308);
    }
}
