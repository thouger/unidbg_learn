package cn.missfresh.module.base.push;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;

public class PushNoticeDialog extends Dialog implements View.OnClickListener {
    private String a;

    public PushNoticeDialog(Context context, int i, String str) {
        super(context, i);
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(18862, false);
        super.onCreate(bundle);
        setContentView(R.layout.base_dialog_push_notice);
        findViewById(R.id.tv_open).setOnClickListener(this);
        findViewById(R.id.v_close).setOnClickListener(this);
        AppMethodBeat.o(18862);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18865, false);
        if (view.getId() == R.id.tv_open) {
            ag.b(getContext());
            dismiss();
            if ("home".equals(this.a)) {
                StatisticsManager.c("click_zdypush_pop", ai.e, "zdypush_pop");
            } else if ("category".equals(this.a)) {
                StatisticsManager.Y("click_zdypush_pop", ai.e, "zdypush_pop");
            } else if ("pay_success".equals(this.a)) {
                StatisticsManager.i("click_zdypush_pop", ai.e, "zdypush_pop");
            }
        } else if (view.getId() == R.id.v_close) {
            dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18865);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        AppMethodBeat.i(18869, false);
        super.dismiss();
        a.a(false);
        AppMethodBeat.o(18869);
    }
}
