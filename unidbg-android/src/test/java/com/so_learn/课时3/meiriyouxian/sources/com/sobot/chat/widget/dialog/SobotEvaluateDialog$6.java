package com.sobot.chat.widget.dialog;

import android.content.Intent;
import android.view.View;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;

/* access modifiers changed from: package-private */
public class SobotEvaluateDialog$6 implements View.OnClickListener {
    final /* synthetic */ e a;

    SobotEvaluateDialog$6(e eVar) {
        this.a = eVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.dismiss();
        Intent intent = new Intent();
        intent.setAction(ZhiChiConstants.sobot_close_now);
        m.d("isBackShowEvaluate:  " + this.a.j + "--------canBackWithNotEvaluation:   " + this.a.i);
        intent.putExtra("isBackShowEvaluate", this.a.j);
        d.a(this.a.c.getApplicationContext(), intent);
    }
}
