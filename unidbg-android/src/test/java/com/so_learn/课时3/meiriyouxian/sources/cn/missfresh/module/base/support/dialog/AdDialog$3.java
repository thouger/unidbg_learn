package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class AdDialog$3 implements View.OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ a b;

    AdDialog$3(a aVar, Activity activity) {
        this.b = aVar;
        this.a = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(20554, false);
        Activity activity = this.a;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(20554);
            return;
        }
        this.b.b();
        if (this.b.i == 1 && this.b.j != null) {
            StatisticsManager.c((Context) null, "click_close", this.b.j);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(20554);
    }
}
