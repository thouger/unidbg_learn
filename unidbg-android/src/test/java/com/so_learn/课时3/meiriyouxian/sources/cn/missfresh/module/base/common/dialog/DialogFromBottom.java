package cn.missfresh.module.base.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class DialogFromBottom extends Dialog {
    private View a;
    private boolean b = false;

    public DialogFromBottom(Context context) {
        super(context, R.style.BottomDialog);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(11255, false);
        super.onCreate(bundle);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 81;
        int a = aw.a();
        int b = aw.b();
        if (a >= b) {
            a = b;
        }
        attributes.width = a;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(true);
        AppMethodBeat.o(11255);
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        AppMethodBeat.i(11257, false);
        this.a = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null);
        super.setContentView(this.a);
        AppMethodBeat.o(11257);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        AppMethodBeat.i(11258, false);
        this.a = view;
        super.setContentView(view);
        AppMethodBeat.o(11258);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(11260, false);
        this.a = view;
        super.setContentView(view, layoutParams);
        AppMethodBeat.o(11260);
    }
}
