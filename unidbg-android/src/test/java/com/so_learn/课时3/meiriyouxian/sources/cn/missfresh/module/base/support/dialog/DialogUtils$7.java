package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.view.View;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.manager.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$7 implements View.OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ ShareInfo b;

    DialogUtils$7(Activity activity, ShareInfo shareInfo) {
        this.a = activity;
        this.b = shareInfo;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21258, false);
        r.a(this.a).a(r.a.a(true, this.b));
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21258);
    }
}
