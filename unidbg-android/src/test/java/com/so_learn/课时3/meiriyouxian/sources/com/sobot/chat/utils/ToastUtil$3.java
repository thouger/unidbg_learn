package com.sobot.chat.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/* access modifiers changed from: package-private */
public class ToastUtil$3 implements View.OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ PopupWindow c;

    ToastUtil$3(Context context, String str, PopupWindow popupWindow) {
        this.a = context;
        this.b = str;
        this.c = popupWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            m.d("API\u662f\u5927\u4e8e11");
            ClipboardManager clipboardManager = (ClipboardManager) this.a.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager != null) {
                clipboardManager.setText(this.b);
            }
        } else {
            m.d("API\u662f\u5c0f\u4e8e11");
            android.text.ClipboardManager clipboardManager2 = (android.text.ClipboardManager) this.a.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager2 != null) {
                clipboardManager2.setText(this.b);
            }
        }
        Context context = this.a;
        ae.a(context, d.a(context, "sobot_ctrl_v_success"), d.c(this.a, "sobot_iv_login_right"));
        this.c.dismiss();
    }
}
