package com.sobot.chat.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import com.sobot.chat.R;
import com.sobot.chat.b;
import com.sobot.chat.utils.q;

/* compiled from: SobotLoadingDialog */
public class g extends Dialog {
    private String a;
    private boolean b;
    private TextView c;

    public g(Context context, String str) {
        this(context, R.style.sobot_dialog_Progress, str, false);
    }

    public g(Context context, int i, String str, boolean z) {
        super(context, i);
        this.a = str;
        this.b = z;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("SobotLoadingDialog", "onCreate: ");
        a();
    }

    private void a() {
        setContentView(R.layout.sobot_progress_dialog);
        getWindow().getWindowManager().getDefaultDisplay().getWidth();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        if (b.a(4) && b.a(1)) {
            attributes.flags = 8;
        }
        getWindow().setAttributes(attributes);
        setCancelable(this.b);
        this.c = (TextView) findViewById(q.g(getContext(), "tv_loading"));
        this.c.setText(this.a);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return this.b;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a(String str) {
        this.a = str;
        this.c.setText(str);
    }
}
