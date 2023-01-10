package com.sobot.chat.viewHolder;

import android.content.Context;
import android.view.View;

class RobotTemplateMessageHolder2$3 implements View.OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ g b;

    RobotTemplateMessageHolder2$3(g gVar, Context context) {
        this.b = gVar;
        this.a = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.b.i.b();
        this.b.a(this.a);
    }
}
