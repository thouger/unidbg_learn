package com.sobot.chat.viewHolder;

import android.view.View;
import com.sobot.chat.listener.NoDoubleClickListener;

/* access modifiers changed from: package-private */
public class RobotTemplateMessageHolder5$1 extends NoDoubleClickListener {
    final /* synthetic */ j a;

    RobotTemplateMessageHolder5$1(j jVar) {
        this.a = jVar;
    }

    @Override // com.sobot.chat.listener.NoDoubleClickListener
    public void a(View view) {
        if (this.a.p != null) {
            this.a.p.b();
        }
    }
}
