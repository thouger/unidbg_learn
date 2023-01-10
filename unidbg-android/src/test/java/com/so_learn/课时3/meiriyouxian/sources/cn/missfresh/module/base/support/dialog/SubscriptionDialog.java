package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SubscriptionDialog extends Dialog {
    public SubscriptionDialog(Context context) {
        super(context, R.style.style_dialog_transparent_bg);
        AppMethodBeat.i(21809, false);
        a(context);
        AppMethodBeat.o(21809);
    }

    private void a(Context context) {
        AppMethodBeat.i(21810, false);
        setContentView(R.layout.dialog_subscription_layout);
        setCanceledOnTouchOutside(false);
        findViewById(R.id.IKnow).setOnClickListener(new AnonymousClass1());
        AppMethodBeat.o(21810);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.SubscriptionDialog$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(21808, false);
            SubscriptionDialog.this.dismiss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21808);
        }
    }
}
