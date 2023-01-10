package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$10 implements View.OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ Activity b;
    final /* synthetic */ e.c c;
    final /* synthetic */ c d;

    DialogUtils$10(boolean z, Activity activity, e.c cVar, c cVar2) {
        this.a = z;
        this.b = activity;
        this.c = cVar;
        this.d = cVar2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21099, false);
        int id = view.getId();
        if (id == R.id.tv_double_btn_cancel) {
            if (!this.a) {
                cn.missfresh.module.base.manager.e.r(r.h(this.b.getApplicationContext()));
            }
            this.c.a();
            this.d.b();
        } else if (id == R.id.tv_double_btn_ok) {
            this.c.b();
            this.d.b();
        } else if (id == R.id.tv_single_btn_ok) {
            this.c.c();
            this.d.b();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21099);
    }
}
