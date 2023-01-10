package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.view.View;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class AdSimpleDialog$3 implements View.OnClickListener {
    final /* synthetic */ b a;

    AdSimpleDialog$3(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(20610, false);
        j.a(this.a.h, (Activity) this.a.a, "", "", "pop_task");
        this.a.b();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(20610);
    }
}
