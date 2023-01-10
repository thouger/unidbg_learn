package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.view.View;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;

class DialogUtils$38 implements View.OnClickListener {
    final /* synthetic */ Dialog a;

    DialogUtils$38(Dialog dialog) {
        this.a = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21200, false);
        this.a.dismiss();
        StatisticsManager.c("click_close", ai.e, "couponNoticePop");
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21200);
    }
}
