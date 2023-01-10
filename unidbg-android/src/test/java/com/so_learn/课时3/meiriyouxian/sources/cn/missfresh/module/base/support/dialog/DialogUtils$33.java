package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.h5.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$33 implements View.OnClickListener {
    final /* synthetic */ Context a;

    DialogUtils$33(Context context) {
        this.a = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21176, false);
        a.a((Activity) this.a, "", i.cF);
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21176);
    }
}
